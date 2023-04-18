package demo;

import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//io.restassured.RestAssured.*

public class TeakarchAPiTesting {
	
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
//		User id, password - payload
//		method - POST
//		content-type - header
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
//		JsonObject joHeaders = new JsonObject();
//		joHeaders.addProperty("Content-Type", "application/json");
		
		Response res = RestAssured.given().headers(headers).when()
		.body("{\"username\": \"mithun@ta.com\", \"password\": \"mithun\"}").post("login")
		.then().statusCode(201).extract().response();
		
		System.out.println(res.asString());
		
		String token = res.jsonPath().getString("token").replaceAll("\\[", "").replaceAll("]", "");
		
		System.out.println(token);
		
		
		HashMap<String, String> addDataHeaders = new HashMap<String, String>();
		addDataHeaders.put("Content-Type", "application/json");
		addDataHeaders.put("token", token);
		
//		String payload = "{\"accountno\": \"TA-8686690\", \"departmentno\": \"8\",
//		\"salary\": \"378903\", \"pincode\": \"367890\"}";
		
		
		HashMap<String, String> addDataPayload = new HashMap<String, String>();
		addDataPayload.put("accountno", "TA-8686690");
		addDataPayload.put("departmentno", "0");
		addDataPayload.put("salary", "677899");
		addDataPayload.put("pincode", "767890");
		
		Response res2 = RestAssured.given().headers(addDataHeaders).when()
				.body(addDataPayload).post("addData")
				.then().statusCode(201).extract().response();
		
		System.out.println(res2.asPrettyString());
		
		
		
		
		
		
		
		
	}
	
	

}
