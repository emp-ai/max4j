package io.bittersweet.max.core.configuration.properties.method.model.user;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class UserWithPhoto extends User {

}
