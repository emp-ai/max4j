package io.bittersweet.max.core.method.model.message;

import io.bittersweet.max.core.method.model.attachment.Attachment;
import io.bittersweet.max.core.method.model.markup.MarkupElement;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value
@Builder
public class MessageBody {

  /** Unique message Id */
  @NotNull
  String mid;

  /** Message sequence in chat */
  @NotNull
  Long seq;

  /** Message text */
  @Nullable
  String text;

  /** Message attachments */
  @Nullable
  List<Attachment> attachments;

  /** Mesasage markup */
  @Nullable
  List<MarkupElement> markup;
}
