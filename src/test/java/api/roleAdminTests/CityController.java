package api.roleAdminTests;

import api.models.city.ContentItem;
import api.models.city.create.Region;
import api.models.city.create.RequestCreate;
import api.preRequests.FiasId;
import api.specification.TestBase;
import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableList;
import dataBase.DataBaseQuery;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.specification.Specs.requestCleanPath;
import static api.specification.Specs.response200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CityController extends TestBase {
    private final DataBaseQuery bd = new DataBaseQuery();
    private final String city = bd.getRandomCity();
    private final float price = bd.getPriceForHour(city);
    private final int cityId = bd.getCityId(city);
    private static final String endpointCityList = "/city/list",
            endpointForSearchCity = "/city/autocomplete_dadata",
            endpointForSearchInDataBase = "/city/autocomplete",
            endpointCityById = "/city",
            endpointCreatingCity = "/city/create",
            endpointDeleteCity = "/city/delete";

    @Test
    @DisplayName("Поиск по городам в БД")
    void searchCityInDataBase() {
        List<ContentItem> ci = given()
                .spec(requestCleanPath)
                .queryParam("search", city)
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get(endpointCityList)
                .then()
                .spec(response200)
                .extract().body().jsonPath().getList("content", ContentItem.class);
        Assertions.assertTrue(ci.stream().limit(0).allMatch(x -> x.getName().equals(city)));
        Assertions.assertTrue(ci.stream().limit(0).allMatch(x -> x.getPriceForHour().equals(price)));
    }

    @Test
    @DisplayName("Поиск по городам для autocomplete в dadate")
    void searchCityInDadata() {
        given()
                .spec(requestCleanPath)
                .queryParam("search", city)
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get(endpointForSearchCity)
                .then()
                .spec(response200)
                .body("name[0]", equalTo(city));
    }

    @Test
    @DisplayName("Поиск по городам для autocomplete в бд")
    void searchCityForAutocomplete() {
        given()
                .spec(requestCleanPath)
                .queryParam("search", city)
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get(endpointForSearchInDataBase)
                .then()
                .spec(response200)
                .body("name[0]", equalTo(city))
                .body("priceForHour[0]", Matchers.equalTo(price));
    }

    @Test
    @DisplayName("Получение города по id города")
    void searchCityId() {
        given()
                .spec(requestCleanPath)
                .queryParam("id", cityId)
                .header("Authorization", tokenSuperAdmin)
                .when()
                .get(endpointCityById)
                .then()
                .spec(response200)
                .body("id", equalTo(cityId))
                .body("name", equalTo(city))
                .body("priceForHour", Matchers.equalTo(price));

    }

    @Test
    @DisplayName("Добавление города в справочники и удаление его")
    void createTest() {
        FiasId fiasId = new FiasId();

        String testCity = "Елабуга";
        String fiasIdTestCity = fiasId.getFiasId(testCity);
        Faker f = new Faker();
        Integer testPrice = Integer.valueOf(f.numerify("####"));

        Region region = new Region();
        region.setId(null);
        region.setName("");

        RequestCreate rc = new RequestCreate();
        rc.setCityAdmin(null);
        rc.setId(null);
        rc.setName(testCity);
        rc.setFiasId(fiasIdTestCity);
        rc.setLatitude((double) 0);
        rc.setLongitude((double) 0);
        rc.setPriceForHour(testPrice);
        rc.setCityAdmin(null);
        rc.setDiscountList(ImmutableList.of());
        rc.setRegion(region);

        step("Добавить новый город",()->{
            int idNewCity = given()
                    .spec(requestCleanPath)
                    .body(rc)
                    .header("Authorization", tokenSuperAdmin)
                    .when()
                    .post(endpointCreatingCity)
                    .then()
                    .spec(response200)
                    .body("id", notNullValue())
                    .body("name", equalTo(testCity))
                    .body("priceForHour", equalTo(testPrice))
                    .extract().path("id");

            step("Удалить добавленный город",()->{
                given()
                        .spec(requestCleanPath)
                        .queryParam("id", idNewCity)
                        .header("Authorization", tokenSuperAdmin)
                        .when()
                        .delete(endpointDeleteCity)
                        .then()
                        .spec(response200);
            });
            step("Удалить данные удаленного города из БД",()-> bd.deleteCity(idNewCity));
        });





    }
}
