package uz.jl.springbootfeatures.config.security;


public class SecurityConstants {
    public static final String[] WHITE_LIST = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/activate**",
            "/swagger-ui/**",
            "/api-docs/**"
    };
}
