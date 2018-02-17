package uk.co.datadisk.securitydemo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.datadisk.securitydemo.domain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
