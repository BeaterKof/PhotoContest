package com.photocontest.utils;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/4/16
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */

public class LoginBean {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String email;

    @Size(min = 6, max = 60)
    private String password;

    @Pattern(regexp = EMAIL_PATTERN)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
