package com.zxyono.lego.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return "";
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return true;
    }
}