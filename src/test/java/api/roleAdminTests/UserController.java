package api.roleAdminTests;

import api.specification.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static api.specification.Specs.requestApiV1;
import static api.specification.Specs.response200;
import static io.restassured.RestAssured.given;

public class UserController extends TestBase {
    private static final String endpointUsersFiles = "/user/file/{filePath}";

    @ParameterizedTest(name = "Админ запрашивает просмотр {1}")
    @MethodSource(value = "helpers.Params#passportUrlPhotoUrl")
    void adminRequestFile(String url, String testName) {
        given()
                .header("Authorization", tokenSuperAdmin)
                .spec(requestApiV1)
                .when()
                .pathParam("filePath", url)
                .get(endpointUsersFiles)
                .then()
                .spec(response200);
    }
}
