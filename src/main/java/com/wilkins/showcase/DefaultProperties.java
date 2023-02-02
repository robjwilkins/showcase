package com.wilkins.showcase;

import com.wilkins.showcase.domain.Greeting;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.default")
public record DefaultProperties(Greeting greeting) {

}
