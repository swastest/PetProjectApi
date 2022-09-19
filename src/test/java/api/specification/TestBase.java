package api.specification;

import api.preRequests.GetTokenAdmin;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

   public static String tokenSuperAdmin,
            tokenClient,
            tokenExecutor;
    @BeforeAll
    static void setUp() {
        GetTokenAdmin  token = new GetTokenAdmin();
        tokenSuperAdmin = token.superAdminToken();
    }
}
