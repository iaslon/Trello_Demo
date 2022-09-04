package uz.jl.springbootfeatures.dtos.auth;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserDTO {
    private Long id;
    private String username;
    private int loginTryCount;
    private String email;
    private String status;
    private LocalDateTime lastLoginTime;
}
