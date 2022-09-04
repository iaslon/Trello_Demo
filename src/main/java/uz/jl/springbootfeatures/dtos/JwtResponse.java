package uz.jl.springbootfeatures.dtos;

public record JwtResponse(
        String accessToken,
        String refreshToken,
        String tokenType) {
}
