package io.bittersweet.max.core.method.model.markup;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bittersweet.max.core.method.enums.MarkupType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@JsonTypeInfo(use = NAME, property = "type", visible = true)
@JsonSubTypes({
    @Type(value = StrongMarkupElement.class, name = "STRONG"),
    @Type(value = EmphasizedMarkupElement.class, name = "EMPHASIZED"),
    @Type(value = MonospacedMarkupElement.class, name = "MONOSPACED"),
    @Type(value = LinkMarkupElement.class, name = "LINK"),
    @Type(value = StrikethroughtMarkupElement.class, name = "STRIKETHROUGH"),
    @Type(value = UnderlineMarkupElement.class, name = "UNDERLINE"),
    @Type(value = UserMentionMarkupElement.class, name = "USER_MENTION")
})
@NoArgsConstructor
@SuperBuilder
@Data
public abstract class MarkupElement {

  @NotNull
  MarkupType type;

  @NotNull
  Integer from;

  @NotNull
  Integer length;
}
