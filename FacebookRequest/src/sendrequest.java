import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;

public class sendrequest {

    String DeviceName = "*******************";    //Your Device Name
    String DeviceVersion = "*******";             //Device Version ... ex 8.1.0
    String fb_id ="********";                     //Facebook Id
    String fb_password="**************";          //facebook Password

    int m=0;


    List<WebElement> abc;

    WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("device", "Android");

        capabilities.setCapability("deviceName", DeviceName);

        capabilities.setCapability("platformVersion", DeviceVersion);

        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("browserName", "Chrome");

        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

    }

    @Test
    public void test() {

        driver.get("https://m.facebook.com/friends/center/suggestions/?mff_nav=1&fb_ref=nr");

        driver.findElement(By.name("email")).sendKeys(fb_id);
        driver.findElement(By.name("pass")).sendKeys(fb_password);
        driver.findElement(xpath("//button[contains(@name, 'login')]")).click();

        start();


    }

    @After
    public void End() {
    }

    protected void start() {


        abc= driver.findElements(xpath("//a[contains(@data-sigil, 'touchable m-add-friend')]"));

        int i=0;
        for(WebElement element: abc) {
            i++;
        }


        for(int j=m;j<i;j++){
            try{
                abc.get(j).click();
                System.out.println(j);
            }
            catch (WebDriverException e) {
                System.out.println("Fuck");
                driver.findElements(xpath("//div[contains(@class, '_55wr _4g34')]")).get(1).click();
                j--;
            }

        }

            m=i+15;
        System.out.println("Complete"+m);
        start();



    }
}