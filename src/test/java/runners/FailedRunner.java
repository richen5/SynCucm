package runners;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (

        features = "@target/failed.txt",
        glue = "steps",
        dryRun = false,
        monochrome = true,


        plugin = {"html:target/cucumber.html", "pretty",
                "json:target/cucumber.json", "rerun:target/failed.txt"
        }
)
public class FailedRunner { }
