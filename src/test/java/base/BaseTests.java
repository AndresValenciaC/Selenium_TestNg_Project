package base;

import helper.DriverManage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseTests {

    public WebDriver driver;
    public Logger logger = LogManager.getLogger();
    private DriverManage driverManage;
    private final String baseUrl = "https://practice.automationtesting.in/";
    private final String browser = "chrome";


    private void openBrowser(String browser, String url) {
        driverManage = new DriverManage(browser);
        this.driver = driverManage.getDriver();
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }


    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "basUrl"})
    public void setUp() {
        openBrowser(browser, baseUrl);
    }


   /*  @AfterClass
   public void closeDriver() {
        this.driver.quit();
    }*/
}
