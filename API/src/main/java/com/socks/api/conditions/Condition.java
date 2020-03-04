package com.socks.api.conditions;

import io.restassured.response.Response;

public interface Condition {
    void check(Response response);  // этот метод получает респонс от класса AssertableResponse
}
