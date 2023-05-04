package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //older version of writing the code
        // WebElement pimOPtion = driver.findElement(By.xpath("abc"));
        // pimOPtion.click();
        click(employeeSearchPage.pimOption);
    }
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(employeeSearchPage.addEmployeeOption);
    }

    @And("user enters firstName middleName and lastName")
    public void user_enters_firstName_middleName_and_lastName() {
        sendText(addEmployeePage.firstNameField, "Ostap");
        sendText(addEmployeePage.middleNameField, "Stepanovich");
        sendText(addEmployeePage.lastNameField, "Bandera");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstNameValue, String middleNameValue, String lastNameValue) {
        sendText(addEmployeePage.firstNameField, firstNameValue);
        sendText(addEmployeePage.middleNameField, middleNameValue);
        sendText(addEmployeePage.lastNameField, lastNameValue);
    }

    @When("user provides {string} {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstNameField, firstName);
        sendText(addEmployeePage.middleNameField, middleName);
        sendText(addEmployeePage.lastNameField, lastName);
    }

    @When("user provides multiple employee data and verify they are added")
//    Remove io.cucumber.datatable.Datatable from: (io.cucumber.datatable.DataTable dataTable) and print&find Datatable
    public void user_provides_multiple_employee_data_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employee :employeeNames) {
            System.out.println(employee);
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");
            System.out.println(firstNameValue + " " + middleNameValue + " " + lastNameValue);

            sendText(addEmployeePage.firstNameField, firstNameValue);
            sendText(addEmployeePage.middleNameField, middleNameValue);
            sendText(addEmployeePage.lastNameField, lastNameValue);

            click(addEmployeePage.saveButton);
            Thread.sleep(2000);
            click(employeeSearchPage.addEmployeeOption);

        }
    }

    @When("user add multiple employees from excel file using {string} sheet and verify the user added")
    public void user_add_multiple_employees_from_excel_file_using_sheet_and_verify_the_user_added(String sheetName) {
        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();

        //it checks whether the next element exist
        while (itr.hasNext()){
            //it returns the key and value for employees
            Map<String, String> mapNewEmp = itr.next();
            System.out.println(mapNewEmp.get("FirstName"));
            System.out.println(mapNewEmp.get("MiddleName"));
            System.out.println(mapNewEmp.get("LastName"));

        }

    }

}
