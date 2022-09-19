package api.roleAdminTests;

import api.specification.TestBase;
import dataBase.DataBaseQuery;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.specification.Specs.requestCleanPath;
import static api.specification.Specs.response200;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegionController extends TestBase {
    private static final String endpointRegionById = "/region",
    endpointRegionList = "/region/list";

    @Test
    @DisplayName("Суперадминистратор запрашивает регион по id")
    void superAdminRequestRegionId() {
        int regionId = 1;
        String regionName = "Республика Татарстан";

        Response response = given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .queryParam("id", regionId)
                .when()
                .get(endpointRegionById)
                .then()
                .spec(response200)
                .body("id", equalTo(regionId))
                .body("name", equalTo(regionName))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idRegion = jsonPath.get("cities.region.id");
        List<String> nameRegion = jsonPath.get("cities.region.name");
        if (idRegion.size() > 0 && nameRegion.size() > 0) {
            for (Integer x : idRegion) {
                Assertions.assertEquals(regionId, x);
            }
            for (String x : nameRegion) {
                Assertions.assertEquals(regionName, x);
            }
        }

    }

    @Test
    @DisplayName("Суперадминистратор запрашивает список регионов")
    void regionList() {
        Response response = given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .queryParam("page", "")
                .queryParam("search", "")
                .when()
                .get(endpointRegionList)
                .then()
                .spec(response200)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<Object> content = jsonPath.get("content");
        int actualCount = content.size();
        Assertions.assertEquals(new DataBaseQuery().howManyCountRegions(),actualCount);

    }
 // POST /region/create     DELETE /region/delete

}
