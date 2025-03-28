package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class MySteps {

    String city;

    @BeforeMethod
    public void initTest() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        setWebDriver(driver);

        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.browserSize = "1024x768";
    }

    @Given("User is looking for car on Booking")
    public void openPageWithCars() {
        open("https://www.booking.com/");
        $x("//*[text()='Такси от/до аэропорта']").click();
    }

    @And("User looking for car from {string}")
    public void userLookingCarInCity(String city) {
        this.city = city;
        $(By.name("pickup")).setValue(city);
    }

    @And("User enter destination is {string}")
    public void userDestinationIs(String city) {
        $(By.name("dropoff")).setValue(city);
    }

    @And("The user enters the date of the trip")
    public void enterDatesOfTrip() {
        $(By.cssSelector("[data-testid='taxi-date-time-picker-form-element__button-oneway']")).click();
        $(By.cssSelector("[data-date='2025-04-09']")).click();
    }

    @When("User click search")
    public void userClickSearch() {
        $(By.cssSelector("[data-testid='submit-button']")).click();
    }

    @Then("Car {string} should be on the results page")
    public void carShouldBeOnTheResultsPage(String car) {
        Assert.assertTrue($(String.format("[data-testid='taxi-card-wrapper__car-card--%s']", car)).exists());
    }
}
