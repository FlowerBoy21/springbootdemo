package com.example.demo.security;


import com.example.demo.domain.AccountCredentials;
import com.example.demo.domain.Token;
import com.example.demo.repo.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private final String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";


    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(AccountCredentials userDetails) {
        Map<String, Object> claims = new HashMap<>();
        var token =  doGenerateToken(claims, userDetails.getEmail());
        tokenRepository.save(new Token(token,userDetails.getId()));
        return token;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, AccountCredentials userDetails) {
        final String username = getUsernameFromToken(token);
        var exist = tokenRepository.findByToken(token);
        return (username.equals(userDetails.getEmail()) && !isTokenExpired(token) && exist!=null);
    }

    public Token getTokenByUserId(Integer uuid) {
        return tokenRepository.findByUserID(uuid);
    }

    public void deleteToken(String token) {
        var tkn = tokenRepository.findByToken(token);
        tokenRepository.delete(tkn);
    }
}