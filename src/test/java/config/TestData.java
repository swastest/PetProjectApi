package config;


import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/testData.properties")
public interface TestData extends Config {
    String superAdminLogin();
    String superAdminPass();
    String superAdminRole();

    String franchizeAdminLogin();
    String franchizeAdminPass();
    String franchizeAdminRole();

    String cityAdminLogin();
    String cityAdminPass();
    String cityAdminRole();

    String regionAdminLogin();
    String regionAdminPass();
    String regionAdminRole();

    String loginClient();
    String passClient();
    String tokenClient();
    String firstNameClient();
    String lastNameClient();
    String cityClient();
    String verificationPhoneClient();
    Integer idClient();


    String loginExecutor();
    String passExecutor();
    String idExecutor();

    String passport_url1();
    String photo_url();


}
