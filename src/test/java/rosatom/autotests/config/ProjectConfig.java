package rosatom.autotests.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties",
        "classpath:config/credential.properties"
})
public interface ProjectConfig extends Config {

    @DefaultValue("chrome")
    String browser();
    @DefaultValue("91.0")
    String browserVersion();
    @DefaultValue("1920x1080")
    String browserSize();
    @DefaultValue("")
    String browserMobileView();
    @DefaultValue("")
    String remoteDriverUrl();
    @DefaultValue("true")
    String videoStorage();
    String login();
    String password();
}