package api.roleAdminTests;

import api.models.AuthResponse;
import com.github.javafaker.Faker;
import config.ConfigCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;

import static api.specification.Specs.*;
import static io.restassured.RestAssured.given;

public class AuthAdmin {
    private static final String endpointAuthAdmins = "/auth/signin";

    @ParameterizedTest(name = "Позитивная проверка авторизации {3}")
    @MethodSource(value="helpers.Params#adminsLoginPassRole")
    void authAdminPositive(String login, String password, String role, String nameTest) {
        Map<String, String> user = new HashMap<>();
        user.put("password", password);
        user.put("phone", login);
        AuthResponse r = given()
                .spec(requestCleanPath)
                .body(user)
                .when()
                .post(endpointAuthAdmins)
                .then()
                .spec(response200)
                .extract().as(AuthResponse.class);
        Assertions.assertEquals(r.getRole(), role);
        Assertions.assertEquals(r.getTokenType(), "Bearer");
        Assertions.assertNotNull(r.getAccessToken());
    }

    @Test
    @DisplayName("Негативная проверка авторизации Суперадминистратора (неверный пароль)")
    void authAdminNegative() {
        Faker faker = new Faker();
        String fakePass = faker.numerify("##########");
        Map<String, String> user = new HashMap<>();
        user.put("password", fakePass);
        user.put("phone", ConfigCenter.configTestData.superAdminLogin());
         given()
                .spec(requestCleanPath)
                .body(user)
                .when()
                .post(endpointAuthAdmins)
                .then()
                .spec(response400);
    }
}
