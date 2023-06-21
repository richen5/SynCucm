package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;   //equalTo or *
import static io.restassured.RestAssured.given;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared to created an employee")
    public void a_request_is_prepared_to_created_an_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for the created employee is {int}")
    public void the_status_code_for_the_created_employee_is(Integer code) {
        response.then().assertThat().statusCode(code);

    }

    @And("the employee created contains key {string} and value {string}")
    public void theEmployeeCreatedContainsKeyAndValue(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }


    @And("the employee id {string} is stored as a global variable to be used for other calls")
    public void theEmployeeIdIsStoredAsAGlobalVariableToBeUsedForOtherCalls(String empId) {
        employee_id = response.jsonPath().getString(empId);
        System.out.println(employee_id);
    }


    //------------------------------------------------------------------
    //second call for getting the employee after creating it

    @Given("a request is prepared to get the employee")
    public void aRequestIsPreparedToGetTheEmployee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                queryParam("employee_id", employee_id);

    }

    @When("a GET call is made to retrieve the created employee")
    public void aGETCallIsMadeToRetrieveTheCreatedEmployee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status call for this employee is {int}")
    public void theStatusCallForThisEmployeeIs(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }


    @And("the employee we are getting having ID {string} must match with the global stored employee id")
    public void theEmployeeWeAreGettingHavingIDMustMatchWithTheGlobalStoredEmployeeId(String empID) {
        String tempID = response.jsonPath().getString(empID);
        Assert.assertEquals(tempID, employee_id);
    }

    @Then("the retrieved data at {string} object matches the data used to create the employee having employee id {string}")
    public void the_retrieved_data_at_object_matches_the_data_used_to_create_the_employee_having_employee_id(String empObject, String resEmpID, DataTable dataTable) {

        // data comes from feature file
        List<Map<String,String>> expectedData = dataTable.asMaps(String.class, String.class);

        // data comes from get call body
        Map<String, String> actualData = response.body().jsonPath().get(empObject);

        for (Map<String, String> singlePairOfData :expectedData) {

            // it will return the set of keys from map
            Set<String> keys = singlePairOfData.keySet();

            for (String key:keys){
                String exspectedValue = singlePairOfData.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(exspectedValue, actualValue);
            }
        }

    }

}
