package com.fjern.app.security;

import com.fjern.app.persistence.entities.Privilege;
import com.fjern.app.persistence.entities.Role;
import com.fjern.app.persistence.entities.User;
import com.fjern.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Profile("oauth")
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByName(name);
        return new org.springframework.security.core.userdetails.User(
                user.getName()
                , user.getPassword()
                , user.getIsEnabled(),true,true,true
                ,getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        Set<Role> roles = user.getRoles();
        Set<Privilege> privileges = new HashSet<>();
        for (Role role:roles) {
            privileges.addAll(role.getPrivileges());
        }

        return  privileges.stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())
        ).collect(Collectors.toList());
    }
}
