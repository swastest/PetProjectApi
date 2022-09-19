package api.roleAdminTests;

import api.specification.TestBase;
import config.ConfigCenter;
import dataBase.DataBaseQuery;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.specification.Specs.requestCleanPath;
import static api.specification.Specs.response200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ClientController extends TestBase {
    private static final String endpointClientById = "/client/{id}",
            endpointClientActiveOrdersById = "/client/{id}/activeOrders",
            endpointClientAddressList = "/client/{id}/address",
            endpointClientLastFinishedOrder = "/client/{id}/lastFinished",
            endpointClientBlock = "/client/{id}/block",
            endpointClientUnblock = "/client/{id}/unblock";


    @Test
    @DisplayName("Суперадмин запрашивает информацию о клиенте")
    void clientInfo() {
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", ConfigCenter.configTestData.idClient())
                .when()
                .get(endpointClientById)
                .then()
                .spec(response200)
                .body("id", equalTo(ConfigCenter.configTestData.idClient()))
                .body("name", equalTo(ConfigCenter.configTestData.firstNameClient()))
                .body("surname", equalTo(ConfigCenter.configTestData.lastNameClient()))
                .body("phone", equalTo("+7" + ConfigCenter.configTestData.loginClient()))
                .body("role", equalTo("ROLE_CLIENT"));
    }

    @Test
    @DisplayName("Суперадмин запрашивает информацию об активных зказах Клиента (по айди клиента)")
    void clientActiveOrders() {
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", ConfigCenter.configTestData.idClient())
                .when()
                .get(endpointClientActiveOrdersById)
                .then()
                .spec(response200);
    }

    @Test
    @DisplayName("Суперадмин запрашивает список адресов клиента ")
    void clientAddress() {
        Response r = given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", ConfigCenter.configTestData.idClient())
                .queryParam("list", "")
                .when()
                .get(endpointClientAddressList)
                .then()
                .spec(response200)
                .extract().response();
        JsonPath jsonPath = r.jsonPath();
        List<String> s = jsonPath.getList("address");
        for (int i = 0; i < s.size(); i++) {
            Assertions.assertEquals(new DataBaseQuery().clientAddress(ConfigCenter.configTestData.idClient(), i),
                    s.get(i));
        }
    }

    @Test
    @DisplayName("Суперадмин запрашивает информацию последнего завершенного заказа")
    void clientLastFinishedOrder() {
        given()
                .spec(requestCleanPath)
                .header("Authorization", tokenSuperAdmin)
                .pathParam("id", ConfigCenter.configTestData.idClient())
                .when()
                .get(endpointClientLastFinishedOrder)
                .then()
                .spec(response200);
    }

    @Test
    @DisplayName("Блокировка и разблокировка клиента Суперадмином")
    void blockUnblockClient() {
        String blockTxt = "Причина блокировки-разблокировки";
        Map<String, String> body = new HashMap<>();
        body.put("reason", blockTxt);
        step("Заблокировать Клиента", () -> {
            given()
                    .spec(requestCleanPath)
                    .header("Authorization", tokenSuperAdmin)
                    .pathParam("id", ConfigCenter.configTestData.idClient())
                    .body(body)
                    .when()
                    .post(endpointClientBlock)
                    .then()
                    .spec(response200);
        });

        step("Разблокировать Клиента", () -> {
            given()
                    .spec(requestCleanPath)
                    .header("Authorization", tokenSuperAdmin)
                    .pathParam("id", ConfigCenter.configTestData.idClient())
                    .body(body)
                    .when()
                    .post(endpointClientUnblock)
                    .then()
                    .spec(response200);
        });
    }
}
