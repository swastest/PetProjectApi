package config;

import org.aeonbits.owner.Config;
@Config.Sources("classpath:properties/link.properties")
public interface LinkInterface extends Config {
    String testUrl();
    String apiPath();
}
