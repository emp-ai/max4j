package io.bittersweet.max.core.method.model.markup;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class LinkMarkupElement extends MarkupElement {

  @NotNull
  String url;
}
