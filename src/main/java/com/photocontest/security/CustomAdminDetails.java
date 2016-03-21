package com.photocontest.security;

import com.photocontest.model.Admin;
import com.photocontest.model.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 3/8/16
 * Time: 4:38 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("costumAdminDetailsService")
public class CustomAdminDetails extends Admin implements UserDetails {

    public CustomAdminDetails(){
    }

    public CustomAdminDetails(Admin a){
        super(a);
    }

    public Collection getAuthorities(){
        if(this.getType().equals("admin")){
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }
        if(this.getType().equals("dba")){
            return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_DBA");
        }
        return AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");
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