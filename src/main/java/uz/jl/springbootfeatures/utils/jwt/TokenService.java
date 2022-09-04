package uz.jl.springbootfeatures.utils.jwt;

import org.springframework.security.core.userdetails.UserDetails;



public interface TokenService {
    String generateToken(UserDetails userDetails);

    boolean isValid(String token);

    default String getSubject(String token) {
        return null;
    }
}
