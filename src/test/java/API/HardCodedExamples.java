package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODcyNjU5MzAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NzMwOTEzMCwidXNlcklkIjoiNTU0NyJ9._VR2jq3ju-UT9HsonDCzrXcDgFBzKVM6DCpMEWKkdbs";

    @Test
    public void createEmployee(){
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
        response.prettyPrint();

    }
}
