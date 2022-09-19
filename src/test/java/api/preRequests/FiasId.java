package api.preRequests;

import api.specification.TestBase;

import static api.specification.Specs.requestCleanPath;
import static api.specification.Specs.response200;
import static io.restassured.RestAssured.given;

public class FiasId extends TestBase {
    public  String getFiasId(String city){
       return given()
                .spec(requestCleanPath)
                .queryParam("search", city)
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get("/city/autocomplete_dadata")
                .then()
                .spec(response200)
                .extract().path("fiasId[0]");
    }

}
