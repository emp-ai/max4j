package io.bittersweet.max.core.configuration.properties.method.model.attachment.payload.dto.button;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.bittersweet.max.core.configuration.properties.method.enums.BotButtonType;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonTypeInfo(use = NAME, property = "type", visible = true)
@JsonSubTypes({
    @Type(value = MessageButton.class, name = "MESSAGE"),
    @Type(value = LinkButton.class, name = "LINK"),
    @Type(value = RequestGeoButton.class, name = "REQUEST_GEO"),
    @Type(value = RequestContactButton.class, name = "REQUEST_CONTACT"),
    @Type(value = OpenAppButton.class, name = "OPEN_APP"),
    @Type(value = MessageButton.class, name = "MESSAGE"),
})
@NoArgsConstructor
@SuperBuilder
@Data
public abstract class BotButton {

  @Nullable
  private BotButtonType type;

  @NotNull
  @Size(max = 128)
  private String text;

}
