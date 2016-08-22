import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;

public class IphoneCredit {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void byCredit() throws Exception {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        driver.get("http://city.com.ua/goods/smartphones/apple-iphone-5c-16gb-blue-refurbished.html");
        driver.findElement(By.id("buy_credit_btn")).click();

        WebElement iFrame = driver.findElement(By.xpath(".//iframe[@class='fancybox-iframe']"));
        driver.switchTo().frame(iFrame);

        driver.findElement(By.xpath(".//input[@name='down_payment']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath(".//input[@name='down_payment']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath(".//input[@name='down_payment']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath(".//input[@name='down_payment']")).sendKeys(Keys.BACK_SPACE);

        driver.findElement(By.xpath(".//input[@name='down_payment']")).sendKeys("2000");
        driver.findElement(By.xpath(".//select[@name = 'period_credit']")).click();


        //Ежемесячный платеж в Пумб-Банке
        WebElement pumbMonthlyPayment= driver.findElement(By.xpath(".//img[contains(@src,'renes.jpg')]/../following-sibling::td[2]"));
        String actualPumbMonthlyPayment = pumbMonthlyPayment.getText();
        String expectedPumbMonthlyPayment = "271 грн";

        //Ежемесячный платеж в Альфа-Банке
        WebElement alfaMonthlyPayment= driver.findElement(By.xpath(".//img[contains(@src,'alfa-bank.jpg')]/../following-sibling::td[2]"));
        String actualAlfaMonthlyPayment = alfaMonthlyPayment.getText();
        String expectedAlfaMonthlyPayment = "264 грн";


        collector.checkThat(expectedPumbMonthlyPayment, equalTo(actualPumbMonthlyPayment));
        collector.checkThat(expectedAlfaMonthlyPayment, equalTo(actualAlfaMonthlyPayment));

    }

}
