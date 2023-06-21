package API;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.equalTo;   //equalTo or *
import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)   //will execute tests alphabetically a>b>c!
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //RENEW TOKEN EVERYTIME !!!
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODcyNjU5MzAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NzMwOTEzMCwidXNlcklkIjoiNTU0NyJ9._VR2jq3ju-UT9HsonDCzrXcDgFBzKVM6DCpMEWKkdbs";
    static String employee_id;

    @Test
    public void aCreateEmployee(){
        RequestSpecification request = given().header("Content-Type", "application/json").
        header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Stepan\",\n" +
                        "  \"emp_lastname\": \"Bandera\",\n" +
                        "  \"emp_middle_name\": \"ST\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2009-01-01\",\n" +
                        "  \"emp_status\": \"Probation\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        Response response = request.when().post("/createEmployee.php");
        //response.prettyPrint();
        response.then().assertThat().statusCode(201);

        //Hamcrest matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Stepan"));

        //using jsonPath(), to specify the key in the body
        //it returns the value of employee_id
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }


    @Test
    public void bGetCreatedEmployee() {
        RequestSpecification prepareRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);

        Response response = prepareRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        String tempID = response.jsonPath().getString("employee.employee_id");
        System.out.println(tempID);
        Assert.assertEquals(tempID,employee_id);
    }

    @Test
    public void cUpdateEmployee(){
        RequestSpecification prepareRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "    \"employee_id\": \""+ employee_id + "\",\n" +
                        "    \"emp_firstname\": \"Ostap\",\n" +
                        "    \"emp_middle_name\": \"ST\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_lastname\": \"Stupka\",\n" +
                        "    \"emp_birthday\": \"2009-01-01\",\n" +
                        "    \"emp_job_title\": \"QA\",\n" +
                        "    \"emp_status\": \"Probation\"\n" +
                        "}");

        Response response = prepareRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee(){
        RequestSpecification prepareRequest = given().header("Content-Type", "application/json").
                header("Authorization", token). queryParam("employee_id", employee_id);

        Response response = prepareRequest.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void eGetAllEmployee(){
        RequestSpecification request = given().header("Authorization", token).
                header("Content-Type", "application/json");

        Response response = request.when().get("/getAllEmployees.php");

        //it returns string of response
        String allEmployees = response.prettyPrint();

        //jsonPath() vs jsonPath
        //jsonPath is a class that contains method for converting the values into json object
        //jsonPath() is a method belongs to jsonPath class

        //creating object of jsonPath class
        JsonPath js = new JsonPath(allEmployees);

        //retrieving the total number of employees
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        //for-loop to print only employee id of all the employees
        for (int i=0; i<count; i++){
            String empID = js.getString("Employees["+ i + "].employee_id");
            System.out.println(empID);
        }

    }

}
