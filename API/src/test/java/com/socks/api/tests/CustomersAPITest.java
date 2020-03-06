package com.socks.api.tests;

import com.socks.api.conditions.Conditions;
import com.socks.api.services.CustomersApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

@Slf4j
public class CustomersAPITest extends BaseAPITest {

    private final CustomersApiService customersApiService = new CustomersApiService();

//    @BeforeAll
//    static void setUp(){
//        RestAssured.baseURI="http://192.168.99.101";
//    }

    String id = "57a98d98e4b00679b4a830af";
    String cookie = "logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA\").get(\"/customers";

    @Test
    void canGetAllCustomers() {
      //  RestAssured.registerParser("text/plain", Parser.JSON);

        String pojo = customersApiService.openCustomers(cookie)

                /*RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                        //.cookie("logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA")
                        .get("/customers")
                      */
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("_embedded.customer[0].firstName", not(isEmptyString())))
                .shouldHave(Conditions.bodyField("_embedded.customer[0].lastName", equalTo("Berger")))

                // .getValueLikeJSON("_embedded.customer[0]")   // рабочий код, возвращает JSON
                // .getValueLikeString("_embedded.customer[0]");   // рабочий код, возвращает JSON как строку
                 .getValueLikeString("_embedded.customer[0].lastName");  // рабочий код, возвращает конкретное значение из JSON

                // .asPojo(UserRegistrationResponce.class) //Работает через pojo, только нужно будет поменять тип данных
                ;


          log.info("All JSON ===1===> "+pojo);
    //      log.info("All JSON ===2===> "+pojo.get_embedded());
    //      log.info("All JSON ===3===> "+pojo.get_embedded().containsKey("customer"));
    //      log.info("All JSON ===4===> "+pojo.get_embedded().containsValue("*"));
    //      log.info("All JSON ===5===> "+pojo.get_embedded().get(""));


    //    BDDAssertions.then(pojo.get_embedded()).isNotEmpty();  //работает
    //    BDDAssertions.then(pojo.get_embedded().get(""));  //нужно доделать

    //      log.info("All JSON ===2===> "+pojo.getId());
    //      log.info("All JSON ======> "+pojo.get_embedded());



//                        .then()
//                        .assertThat()
//                        .body("_embedded.customer[0].lastName",equalTo("Berger"))
//                        .statusCode(200);

    }

    @Test
    void canGetUniqueCustomer() {
        Map<Object, Object> valueLikeJSON = customersApiService.openUniqueCustomer(cookie, id)
                .shouldHave(Conditions.statusCode(200))
                .getValueLikeJSON("");

        log.info("All JSON ======> "+valueLikeJSON);

//                .then()
//                .statusCode(200);
    }

    @Test
    void canNotGetUniqueUserWithBeadCredentials() {
        String id = "Incorrect_ID";

        Map<Object, Object> valueLikeJSON = customersApiService.openUniqueCustomer(cookie, id)
                .shouldHave(Conditions.statusCode(200))
                .getValueLikeJSON("")
                ;

        log.info("All JSON ======> "+valueLikeJSON);

//                .then()
//                .statusCode(200);
    }
/*   //Этот кусок кода вынесли в внешний сервис
    public RequestSpecification setup1(){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public Response openCustomers(String cookie){
       return setup1()
               //.cookie("logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA")
               .cookie(cookie)
               .get("/customers");
    }

    public Response openUniqueCustomer(String cookie, String id){
        return setup1()
                //.cookie("logged_in=H7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG; md.sid=s%3AH7PfQ4P5kSFL9egBAvSP5o8zTau_HsmG.5pJA8ci%2BhjpXPwonRQeTgtOc9%2BDcrUKDYmfmr1pm2UA")
                .cookie(cookie)
                .get("/customers/"+id);
    }

 */
}
