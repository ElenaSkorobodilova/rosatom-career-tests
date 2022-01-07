package rosatom.autotests.tests;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import rosatom.autotests.config.ProjectConfig;
import rosatom.autotests.helpers.Attaches;

import static java.lang.String.format;


@ExtendWith({AllureJunit5.class})
public class TestBase {
    @BeforeAll
    static void setup() {
        ProjectConfig credentials =
                ConfigFactory.create(ProjectConfig.class);
        String login = credentials.login();
        String password = credentials.password();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;
        Configuration.remote = format("https://%s:%s@%s",login,password,System.getProperty("testUrl"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attaches.screenshotAs("Last screenshot");
        Attaches.pageSource();
        Attaches.browserConsoleLogs();
        Attaches.addVideo();
    }

}
