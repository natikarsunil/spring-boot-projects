package com.sunil.bank.jwt;

import com.sunil.bank.config.AppConfigValues;
import com.sunil.bank.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final AppConfigValues appConfig;

    public JwtUtils(final AppConfigValues appConfig){
        this.appConfig = appConfig;
    }

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Date issuedAt = new Date();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + appConfig.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, appConfig.getJwtSecret())
                .compact();
    }

    public String getUserNameFromJwt(String token){
        return Jwts.parser()
                .setSigningKey(appConfig.getJwtSecret())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String token){
        try{
            Jwts.parser().setSigningKey(appConfig.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Jwt token is expired : {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Jwt token is unsupported : {}", e);
        } catch (MalformedJwtException e) {
            logger.error("Jwt token is invalid : {}", e);
        } catch (SignatureException e) {
            logger.error("Invalid Jwt token signature : {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("Jwt claims string is empty : {}", e);
        }

        return false;
    }


}
