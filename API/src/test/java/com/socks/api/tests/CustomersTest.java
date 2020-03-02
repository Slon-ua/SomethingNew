package com.socks.api.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomersTest {
    
    @BeforeAll
    static void setup(){
        RestAssured.baseURI="http://192.168.99.101";
    }

    String id = "57a98d98e4b00679b4a830b5";
    String cookie = "logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA\").get(\"/customers";

    @Test
    void canGetAllCustomers() {
        openCustomers()
        /*RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                //.cookie("logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA")
                .get("/customers")
              */  .then()
                //.body("_embedded", not(isEmptyString()))
                .statusCode(200);
    }

    @Test
    void canGetUniqueCustomer() {
        openUniqueCustomer(id)
                .then()
                .statusCode(200);

    }

    @Test
    void canNotGetUniqueUserWithBeadCredentials() {
        String id = "Incorrect_ID";

        openUniqueCustomer(id)
                .then()
                .statusCode(200);

    }

    public RequestSpecification setup1(){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Response openCustomers(){
       return setup1()
               //.cookie("logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA")
               .cookie(cookie)
               .get("/customers");
    }

    public Response openUniqueCustomer(String id){

        return setup1()
                //.cookie("logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA")
                .cookie(cookie)
                .get("/customers/"+id);

    }
}
