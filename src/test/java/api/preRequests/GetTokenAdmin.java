package api.preRequests;

import config.ConfigCenter;

import java.util.HashMap;
import java.util.Map;

import static api.specification.Specs.requestCleanPath;
import static io.restassured.RestAssured.given;
public class GetTokenAdmin {
     public String superAdminToken(){
        Map<String, String> user = new HashMap<>();
        user.put("password", ConfigCenter.configTestData.superAdminPass());
        user.put("phone", ConfigCenter.configTestData.superAdminLogin());
        return  given()
                .spec(requestCleanPath)
                .body(user)
                .when()
                .post("/auth/signin")
                .then().log().all()
                .extract().path("accessToken");
    }

}
