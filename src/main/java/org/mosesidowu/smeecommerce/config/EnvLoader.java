package org.mosesidowu.smeecommerce.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {

    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        System.setProperty("EMAIL_USERNAME", dotenv.get("EMAIL_USERNAME"));
        System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
    }
}
