package com.socks.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
//@Accessors(fluent = true)   // укорачивает способ вызова метода из этого класса с GetId() -> id()
public class UserRegistrationResponce{
    @JsonProperty
    private  String id;
    private  Map<Object, Object> _embedded;
    private  String jParse="_embedded.customer[0].lastName";


}
