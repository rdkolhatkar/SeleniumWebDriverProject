package selenium.grid.hub;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class HomePageCheck {
    // To run the tests in a remote environment we have to write below code
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome"); // Setting Up the Browser which We have to run
        capabilities.setPlatform(Platform.WIN11); // Setting up the Platform as Windows 11
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true); // Accepting unsecure certificates
        // capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");// This is another way to set browser name
        WebDriver driver = new RemoteWebDriver(new URI("http://192.168.40.1:4444").toURL(), capabilities);
        // http://192.168.40.1:4444 this is the address of our Hub which is running on port 4444
        driver.get("http://google.com");
        System.out.println(driver.getTitle());
        driver.findElement(By.name("q")).sendKeys("rahul shetty");
        driver.close();
    }
}
