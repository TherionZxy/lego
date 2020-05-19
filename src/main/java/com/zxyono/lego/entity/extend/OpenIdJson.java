package com.zxyono.lego.entity.extend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenIdJson {
    @JsonProperty(value = "openid")
    private String openId;
    @JsonProperty(value = "session_key")
    private String sessionKey;
    @JsonProperty(value = "errcode")
    private Integer errcode;
    @JsonProperty(value = "errmsg")
    private String errmsg;
}