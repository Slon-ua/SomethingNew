package com.socks.api.services;

import com.socks.api.assertions.AssertableResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomersApiService extends ApiService{

    public AssertableResponse openCustomers(String cookie){
        log.info("________________________________________________");
        log.info("About to show all customers in company. Use user cookie credentials => {}",cookie);

        Response response = setup()
              //  .cookie(cookie)  //  вынес эту логику в ApiService
                .get("/customers");

        return new AssertableResponse(response);


//        return setup()
//                .cookie(cookie)
//                .get("/customers");
//
    }

    public AssertableResponse openUniqueCustomer(String cookie, String id){
        log.info("________________________________________________");
        log.info("About to show unique customer with next id => {}",id);

        Response response = setup()
                .cookie(cookie)
                .get("/customers/"+id);

        return new AssertableResponse(response);

//        return setup()
//                .cookie(cookie)
//                .get("/customers/"+id);
    }

}
