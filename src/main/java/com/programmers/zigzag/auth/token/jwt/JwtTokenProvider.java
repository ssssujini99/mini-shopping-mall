package com.programmers.zigzag.auth.token.jwt;

import com.programmers.zigzag.auth.exception.TokenInvalidException;
import com.programmers.zigzag.auth.exception.TokenNotFoundException;
import com.programmers.zigzag.auth.token.TokenProvider;
import com.programmers.zigzag.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${token.secret-key}")
    private String SECRET_KEY;


    @Value("${token.expire-date}")
    private String EXPIRE_DATE;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(createClaims(user))
                .setIssuedAt(new Date())
                .setExpiration(createExpireDate(Integer.parseInt(EXPIRE_DATE)))
                .signWith(SignatureAlgorithm.HS256, createSigningKey(SECRET_KEY))
                .compact();
    }

    @Override
    public void throwIfTokenIsNull(String token) {
        if (token == null) {
            throw new TokenNotFoundException();
        }
    }

    @Override
    public String throwIfNotValidToken(String token) {
        try {
            getClaimsFormToken(token.split(" ")[1]);
        } catch (JwtException exception) {
            throw new TokenInvalidException();
        }
        return token.split(" ")[1];
    }

    @Override
    public long getUserId(String token) {
        Claims accessClaims = getClaimsFormToken(token);
        return ((Number)accessClaims.get("userId")).longValue();
    }

    @Override
    public String getUserRole(String token) {
        Claims accessClaims = getClaimsFormToken(token);
        return accessClaims.get("userRole").toString();
    }

    private Date createExpireDate(long expireDate) {
        long curTime = System.currentTimeMillis();
        return new Date(curTime + expireDate);
    }


    private Map<String, Object> createClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("userEmail", user.getEmail());
        claims.put("userRole", user.getUserRole());
        return claims;
    }

    private Key createSigningKey(String key) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }
}
