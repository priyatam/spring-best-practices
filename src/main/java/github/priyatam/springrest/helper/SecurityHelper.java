package github.priyatam.springrest.helper;

import github.priyatam.springrest.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collections;

@Service
public class SecurityHelper implements UserDetailsService {

    @Inject
    private PersistenceHelper persistenceHelper;

    @PostConstruct
    protected void initialize() {
       // persistenceHelper.saveUser(new User("user", "demo", "ROLE_USER"));
      //  persistenceHelper.saveUser(new User("admin", "admin", "ROLE_ADMIN"));
    }

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
