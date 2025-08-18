package mx.edu.utez.rbbackendcomite.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// CUARTO PASO: Generar las utilerias para jwt
@Service
public class JWTUtils {
    @Value("${secret.key}")
    private String SECRET_KEY;

    // Esta función ayuda a extraer todas las propiedades del token
    // Es decir, el cuerpo del token
    public Claims exctractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Esta función nos ayuda a poder extraer las propiedades del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims CLAIMS = exctractAllClaims(token);
        return claimsResolver.apply(CLAIMS);
    }

    // Esta función extrae el nombre de usuario del token
    public String exctractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Esta función extrae la fecha de expiración
    public Date exctractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Esta función ayuda a validar si el token está expirado
    private Boolean isTokenExpired(String token) {
        return exctractExpirationDate(token).before(new Date());
    }

    // Esta función consume la función de arriba, también verifica que coincida el usuario con su token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String USERNAME = exctractUsername(token);
        return (USERNAME.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Esta función nos ayuda a crear nuestro token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder() // Aquí decimos que vamos a construir un token
                .setClaims(claims).setSubject(subject) // Aquí mandamos la información del usuario
                .setIssuedAt(new Date(System.currentTimeMillis())) // Aquí le decimos cuando se creó el token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Cuanto va a durar
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Aquí lo firmamos
                .compact(); // Terminamos por compactar el token
    }

    // Esta función consume la función de crear solo para retornar
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
}
