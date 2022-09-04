package uz.jl.springbootfeatures.domains.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import uz.jl.springbootfeatures.repository.AuthUserRepository;

@Component
@RequiredArgsConstructor
@Configuration

public class AuthenticationEventListener {

    private final AuthUserRepository repository;

//    @EventListener
//    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
//
//        String username = (String) event.getAuthentication().getPrincipal();
//        AuthUser authUser =  repository.findByUsername(username).orElseThrow();
//
//        authUser.setLoginTryCount(authUser.getLoginTryCount()+1);
//
//        if(authUser.getLoginTryCount() == 3){
//            authUser.setStatus(AuthUser.Status.BLOCKED);
//            unBlock();
//            authUser.setLoginTryCount(0);
//            authUser.setStatus(AuthUser.Status.NOT_ACTIVE);
//        }
//
//
//    }


//    @Scheduled(fixedDelay = 2000)
//    public void unBlock(){
//        System.out.println("here user's status again changed into not active");
//    }



        // update the failed login count for the user
        // ...
    }




