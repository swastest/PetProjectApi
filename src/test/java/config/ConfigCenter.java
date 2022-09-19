package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigCenter {
    public static TestData configTestData = ConfigFactory.create(TestData.class);
    public static DataBaseInterface configDB = ConfigFactory.create(DataBaseInterface.class);
    public static LinkInterface configLink = ConfigFactory.create(LinkInterface.class);
}
