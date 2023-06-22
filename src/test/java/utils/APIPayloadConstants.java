package utils;

import org.json.JSONObject;

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

    //passing the body from json object
    public static String createEmployeePayloadViaJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Asel");
        obj.put("emp_lastname", "BK");
        obj.put("emp_middle_name", "MS");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "2000-06-11");
        obj.put("emp_status", "Probation");
        obj.put("emp_job_title", "Lead");

        return obj.toString();
    }

}
