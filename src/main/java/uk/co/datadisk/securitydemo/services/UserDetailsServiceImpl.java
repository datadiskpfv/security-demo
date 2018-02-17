package uk.co.datadisk.securitydemo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.securitydemo.domain.entities.Role;
import uk.co.datadisk.securitydemo.domain.entities.User;
import uk.co.datadisk.securitydemo.domain.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Slf4j
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            log.debug("Adding role: " + role.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        session.setAttribute("testattribute", "hello");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
