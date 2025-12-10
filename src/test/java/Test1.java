import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {
    public static void main(String[] args) {

        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://syntaxprojects.com/index.php");

        WebElement introBtn=driver.findElement(By.xpath("//a[contains(text(),'Introduction')]"));
        introBtn.click();

        WebElement sum1=driver.findElement(By.xpath("//input[@id='sum1']"));
        sum1.sendKeys("55");

        WebElement sum2=driver.findElement(By.xpath("//input[@id='sum2']"));
        sum2.sendKeys("32");

        WebElement getSum= driver.findElement(By.xpath("//button[contains(text(),'Get the Sum')]"));
        getSum.click();

        WebElement getResults= driver.findElement(By.xpath("//*[@id='displayvalue']"));
        String results=getResults.getText();

        System.out.println(results);

    }
}
