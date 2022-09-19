package api.specification;

import config.ConfigCenter;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;

public class Specs {

    public static RequestSpecification requestApiV1 = with()
            .filter(withCustomTemplates())
            .baseUri(ConfigCenter.configLink.testUrl())
            .basePath(ConfigCenter.configLink.apiPath())
            .log().all()
            .contentType(ContentType.JSON);


    public static RequestSpecification requestCleanPath = with()
            .filter(withCustomTemplates())
            .baseUri(ConfigCenter.configLink.testUrl())
            .log().all()
            .contentType(ContentType.JSON);


    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification response400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();

    public static ResponseSpecification response405 = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .log(ALL)
            .build();
}