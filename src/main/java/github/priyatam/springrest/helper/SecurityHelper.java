package github.priyatam.springrest.helper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;

public class SecurityHelper implements UserDetailsService {

    @Inject
    private PersistenceHelper persistenceHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        github.priyatam.springrest.domain.User user = persistenceHelper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(authority));
    }

}
