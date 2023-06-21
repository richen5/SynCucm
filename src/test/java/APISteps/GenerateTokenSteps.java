package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    //String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    //create APIRunner + APIWorks.feature + APISteps - to generate TOKEN
    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                body("{\n" +
                        "  \"email\": \"batchtwelwe@test.com\",\n" +
                        "  \"password\": \"Test123\"\n" +
                        "}");


        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);

        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);

    }
}
