package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"steps", "Hooks"},
        dryRun = false,
        monochrome = false,
        tags ="@1_Login",  //tags = "",  // runs ALL tests ,we use "" to run in parallel
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}

)
public class TestRunner {
}
