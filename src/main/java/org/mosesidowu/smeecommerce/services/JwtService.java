package org.mosesidowu.smeecommerce.services;

public interface JwtService {

    void blacklistToken(String token);

    boolean isBlacklisted(String token);
}
