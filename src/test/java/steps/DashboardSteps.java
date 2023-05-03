package steps;

import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(io.cucumber.datatable.DataTable dataTable) {

        // this data is coming from feature file
        List<String> expectedTabs = dataTable.asList();

        List<String> actualTabs = new ArrayList<>();

        for (WebElement ele:dashboardPage.dashboardTabs
        ) {
            actualTabs.add(ele.getText());
        }
        System.out.println(actualTabs);
        System.out.println(expectedTabs);
        dashboardPage = new DashboardPage();

    }

}
