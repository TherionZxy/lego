package com.zxyono.lego.security.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 小程序端Token
 */
@Getter
@Setter
public class WechatAuthenticationToken extends AbstractAuthenticationToken {
    private String openid;
    private String sessionKey;

    public WechatAuthenticationToken(String openid, String sessionKey) {
        super(null);
        this.openid = openid;
        this.sessionKey = sessionKey;
    }

    public WechatAuthenticationToken(String openid, String sessionKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.openid = openid;
        this.sessionKey = sessionKey;
        super.setAuthenticated(true);
    }

    public WechatAuthenticationToken(String openid, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.openid = openid;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.openid;
    }

    public Object getPrincipal() {
        return this.sessionKey;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}