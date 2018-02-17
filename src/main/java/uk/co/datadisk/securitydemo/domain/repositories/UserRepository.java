package uk.co.datadisk.securitydemo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.securitydemo.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
