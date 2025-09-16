package io.bittersweet.max.core.method.model.attachment;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bittersweet.max.core.method.enums.AttachmentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = NAME, property = "type", visible = true)
@JsonSubTypes({
    @Type(value = ImageAttachment.class, name = "IMAGE"),
    @Type(value = VideoAttachment.class, name = "VIDEO"),
    @Type(value = AudioAttachment.class, name = "AUDIO"),
    @Type(value = FileAttachment.class, name = "FILE"),
    @Type(value = StickerAttachment.class, name = "STICKER"),
    @Type(value = ContactAttachment.class, name = "CONTACT"),
    @Type(value = InlineKeyboardAttachment.class, name = "INLINE_KEYBOARD"),
    @Type(value = ShareAttachment.class, name = "SHARE"),
    @Type(value = LocationAttachment.class, name = "LOCATION")
})
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class Attachment {

  private AttachmentType type;
}
