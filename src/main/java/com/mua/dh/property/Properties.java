package com.mua.dh.property;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "custom")
@NoArgsConstructor
public class Properties {

    public static String headerString = "Authorization";
    public static String tokenPrefix = "Bearer ";
    public static String secret = "8f821a74-367b-4741-95b6-fdfad9b44705";
    public static Long expirationTime = 2L*24L*60L*60L*1000L;
}
