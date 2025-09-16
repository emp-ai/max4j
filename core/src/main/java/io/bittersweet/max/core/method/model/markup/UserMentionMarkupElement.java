package io.bittersweet.max.core.method.model.markup;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.Nullable;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserMentionMarkupElement extends MarkupElement {

  /** The @username of the mentioned user */
  @Nullable
  String userLink;

  @Nullable
  Long userId;
}
