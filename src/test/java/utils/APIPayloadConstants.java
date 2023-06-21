package utils;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String createEmployee = "{\n" +
                "  \"emp_firstname\": \"Stepan\",\n" +
                "  \"emp_lastname\": \"Bandera\",\n" +
                "  \"emp_middle_name\": \"ST\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2009-01-01\",\n" +
                "  \"emp_status\": \"Probation\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";

        return createEmployee;

    }

}
