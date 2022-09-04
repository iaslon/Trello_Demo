package uz.jl.springbootfeatures.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jl.springbootfeatures.domains.auth.AuthRole;


public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
}
