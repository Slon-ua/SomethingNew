package com.socks.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@Getter
@Setter
@ToString
@Accessors(fluent = true)
@Generated("com.robohorse.robopojogenerator")
public class UserLogin{

    @JsonProperty("password")
    private String password;

    @JsonProperty("login")
    private String login;

    @JsonProperty("device_token")
    private String device_token;

    @JsonProperty("device_type")
    private String device_type;

    @JsonProperty("login_by")
    private String login_by;

}