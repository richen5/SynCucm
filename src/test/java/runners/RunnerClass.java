package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (

        features = "src/test/resources/features/",   //provide path of all files
        glue = "steps",               //glue - implementations for gherkin steps
        dryRun = false,          //true - quickly scan, false - real time execute
        monochrome = true,       //true - removes all the irrelevant information

        tags = "@sql",

        //html report will  be generated under target folder
        plugin = {"html:target/cucumber.html", "pretty", "json:target/cucumber.json",
                "rerun:target/failed.txt"
        }

)
public class RunnerClass { }
