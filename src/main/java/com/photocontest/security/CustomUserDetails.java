package com.photocontest.security;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.User;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/24/16
 * Time: 4:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("costumUserDetailsService")
public class CustomUserDetails extends User implements UserDetails{

    public CustomUserDetails(){

    }

    public CustomUserDetails(User u){
        super(u);
    }

    public Collection getAuthorities(){
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    public String getUsername(){
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
