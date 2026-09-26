package io.bittersweet.max.core;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/** Builds the TLS context required by the MAX API without replacing the JVM trust store. */
final class MaxSslContext {

  private static final List<String> MAX_CA_RESOURCES = List.of(
      "/io/bittersweet/max/core/certificates/russian_trusted_root_ca_pem.crt",
      "/io/bittersweet/max/core/certificates/russian_trusted_sub_ca_2024_pem.crt");

  private MaxSslContext() {
  }

  static SSLContext defaultSslContext() {
    return Holder.INSTANCE;
  }

  private static SSLContext createSslContext() {
    try {
      KeyStore maxTrustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      maxTrustStore.load(null, null);
      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

      int certificateIndex = 0;
      for (String resource : MAX_CA_RESOURCES) {
        try (InputStream input = MaxSslContext.class.getResourceAsStream(resource)) {
          if (input == null) {
            throw new CertificateException("MAX CA certificate resource not found: " + resource);
          }
          Collection<? extends Certificate> certificates =
              certificateFactory.generateCertificates(input);
          if (certificates.isEmpty()) {
            throw new CertificateException("No X.509 certificate found in " + resource);
          }
          for (Certificate certificate : certificates) {
            maxTrustStore.setCertificateEntry("max-ca-" + certificateIndex++, certificate);
          }
        }
      }

      TrustManagerFactory defaultFactory = TrustManagerFactory.getInstance(
          TrustManagerFactory.getDefaultAlgorithm());
      defaultFactory.init((KeyStore) null);

      TrustManagerFactory maxFactory = TrustManagerFactory.getInstance(
          TrustManagerFactory.getDefaultAlgorithm());
      maxFactory.init(maxTrustStore);

      SSLContext context = SSLContext.getInstance("TLS");
      context.init(null, new TrustManager[]{new FallbackTrustManager(
          findX509TrustManager(defaultFactory), findX509TrustManager(maxFactory))}, null);
      return context;
    } catch (IOException | GeneralSecurityException exception) {
      throw new IllegalStateException("Could not initialize MAX API TLS certificates", exception);
    }
  }

  private static X509TrustManager findX509TrustManager(TrustManagerFactory factory)
      throws CertificateException {
    for (TrustManager trustManager : factory.getTrustManagers()) {
      if (trustManager instanceof X509TrustManager x509TrustManager) {
        return x509TrustManager;
      }
    }
    throw new CertificateException("No X.509 trust manager available");
  }

  private static final class Holder {
    private static final SSLContext INSTANCE = createSslContext();
  }

  private static final class FallbackTrustManager implements X509TrustManager {
    private final X509TrustManager jvmTrustManager;
    private final X509TrustManager maxTrustManager;

    private FallbackTrustManager(
        X509TrustManager jvmTrustManager,
        X509TrustManager maxTrustManager) {
      this.jvmTrustManager = jvmTrustManager;
      this.maxTrustManager = maxTrustManager;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
        throws CertificateException {
      checkTrusted(chain, authType, true);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
        throws CertificateException {
      checkTrusted(chain, authType, false);
    }

    private void checkTrusted(X509Certificate[] chain, String authType, boolean client)
        throws CertificateException {
      try {
        if (client) {
          jvmTrustManager.checkClientTrusted(chain, authType);
        } else {
          jvmTrustManager.checkServerTrusted(chain, authType);
        }
      } catch (CertificateException jvmException) {
        try {
          if (client) {
            maxTrustManager.checkClientTrusted(chain, authType);
          } else {
            maxTrustManager.checkServerTrusted(chain, authType);
          }
        } catch (CertificateException maxException) {
          maxException.addSuppressed(jvmException);
          throw maxException;
        }
      }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
      List<X509Certificate> issuers = new ArrayList<>();
      issuers.addAll(Arrays.asList(jvmTrustManager.getAcceptedIssuers()));
      issuers.addAll(Arrays.asList(maxTrustManager.getAcceptedIssuers()));
      return issuers.toArray(X509Certificate[]::new);
    }
  }
}
