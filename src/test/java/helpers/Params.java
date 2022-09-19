package helpers;

import config.ConfigCenter;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Params {
    static Stream<Arguments> passportUrlPhotoUrl() {
        return Stream.of
                (Arguments.of(ConfigCenter.configTestData.passport_url1(), "Картинка-документ исполнителя"),
                        Arguments.of(ConfigCenter.configTestData.photo_url(), "Аватар пользователя")
                );
    }

    static Stream<Arguments> adminsLoginPassRole(){
        return Stream.of(
                Arguments.of(ConfigCenter.configTestData.superAdminLogin(),ConfigCenter.configTestData.superAdminPass(),
                        ConfigCenter.configTestData.superAdminRole(),"Суперадминистратор"),
                Arguments.of(ConfigCenter.configTestData.cityAdminLogin(),ConfigCenter.configTestData.cityAdminPass(),
                        ConfigCenter.configTestData.cityAdminRole(),"Администратор города"),
                Arguments.of(ConfigCenter.configTestData.regionAdminLogin(),ConfigCenter.configTestData.regionAdminPass(),
                        ConfigCenter.configTestData.regionAdminRole(),"Администратор региона"),
                Arguments.of(ConfigCenter.configTestData.franchizeAdminLogin(),ConfigCenter.configTestData.franchizeAdminPass(),
                        ConfigCenter.configTestData.franchizeAdminRole(),"Администратор франшизы")
        );
    }
}
