package api.preRequests;

import api.models.RequestRatingExecutor;
import api.specification.TestBase;

import static api.specification.Specs.requestCleanPath;
import static api.specification.Specs.response200;
import static io.restassured.RestAssured.given;

public class InfoExecutor extends TestBase {

   public int getExecutorActualRating(int id){
        RequestRatingExecutor body = new RequestRatingExecutor();
        body.setComment("Пререквест");
        body.setId(id);
        body.setPoints(0);
       return given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .body(body)
                .when()
                .post("/executor/rating")
                .then()
                .spec(response200)
                .extract().path("executorRating");
    }
}
