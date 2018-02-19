package uk.co.datadisk.securitydemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import uk.co.datadisk.securitydemo.domain.entities.Role;
import uk.co.datadisk.securitydemo.domain.entities.User;
import uk.co.datadisk.securitydemo.domain.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class DatadiskAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession session;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = userRepository.findByUsername(authentication.getPrincipal().toString());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            log.debug("Adding role (ddauthenticator): " + role.getName());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        ///
        // do some checking, cause an exception to not login in
        ///

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), grantedAuthorities);

        session.setAttribute("testattribute", "hello from datadiskauthenticator");
        session.setAttribute("roles", user.getRoles());

        return token;
    }

    @Override
    public boolean supports(Class<?> auth) {
        return UsernamePasswordAuthenticationToken.class.equals(auth);
    }
}