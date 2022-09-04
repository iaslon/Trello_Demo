package uz.jl.springbootfeatures.domains.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.jl.springbootfeatures.exceptions.GenericRuntimeException;
import uz.jl.springbootfeatures.repository.AuthUserRepository;
import uz.jl.springbootfeatures.services.AuthUserService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final AuthUserRepository userRepository;

    private final AuthUserService userDetails;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        AuthUser authUser = userRepository.findByUsername(username).orElseThrow();

        if(authUser.getStatus().equals(AuthUser.Status.BLOCKED)){
            throw new GenericRuntimeException("You are blocked",500);
        }

        if(!passwordEncoder.matches(password, authUser.getPassword())){
            authUser.setLoginTryCount(authUser.getLoginTryCount()+1);
            if(authUser.getLoginTryCount() == 3){
                authUser.setStatus(AuthUser.Status.BLOCKED);
            }
            userRepository.save(authUser);
            throw new GenericRuntimeException("Bad credentials",401);
        }
        return new UsernamePasswordAuthenticationToken(userDetails.loadUserByUsername(username), password, authentication.getAuthorities());

    }



    @Scheduled(cron = "0 */1 * * * *")
    private void activate_blockedUser(){

        List<AuthUser> users = userRepository.findAll();
        for (AuthUser user : users) {
            if(user.getStatus().equals(AuthUser.Status.BLOCKED)){
                if(ChronoUnit.MINUTES.between(user.getLastLoginTime(),LocalDateTime.now())>1){
                    user.setStatus(AuthUser.Status.NOT_ACTIVE);
                    user.setLoginTryCount(0);
                }
            }
        }
        userRepository.saveAll(users);
        System.out.println("User unblocked");
    }


}
