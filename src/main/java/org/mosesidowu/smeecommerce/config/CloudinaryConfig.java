package org.mosesidowu.smeecommerce.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dmgnmtv39");
        config.put("api_key", "278425737814724");
        config.put("api_secret", "LaeU8xgLrT6aiImKJQW9a3BS7uA");
        return new Cloudinary(config);
    }

}
