package com.wheel.safe.wheelsafe;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String secret;
    private boolean secretIsBase64=true;
    private long access;
    private long refresh;
}
