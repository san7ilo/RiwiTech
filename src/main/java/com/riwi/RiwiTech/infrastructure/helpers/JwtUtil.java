package com.riwi.RiwiTech.infrastructure.helpers;

import com.riwi.RiwiTech.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes()); // Crear la clave HMAC a partir de la clave secreta
    }

    public String generateToken(User user) {
        System.out.println("Generando token para el usuario: " + user.getUsername() + " con rol: " + user.getRole());
        return Jwts.builder()
                .addClaims(Map.of(
                        "id", user.getId(),
                        "role", user.getRole().name(),
                        "name", user.getUsername()
                ))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String extractRole(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("El token no puede ser nulo o estar vacío");
        }

        Claims claims = extractClaims(token);
        System.out.println("Claims extraídos: " + claims); // Log para ver los claims

        if (claims == null || !claims.containsKey("role")) {
            throw new IllegalArgumentException("El rol no está presente en los claims del token");
        }

        String role = claims.get("role", String.class);
        System.out.println("Rol extraído: " + role); // Log para verificar el rol extraído

        return role; // Asegúrate de que 'role' esté en el formato correcto
    }


}
