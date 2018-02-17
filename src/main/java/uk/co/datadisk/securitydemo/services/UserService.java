package uk.co.datadisk.securitydemo.services;

import uk.co.datadisk.securitydemo.domain.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
