package rest.utils;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author user
 *
 */
public class RestUtils {


	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public Response taPost(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {
		
		RestAssured.baseURI = sBaseUri;
		System.out.println("taPost: URI :"+sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();
		Response res = RestAssured.given().headers(header).when()
				.body(payload).post();
		return res;
	}

	/**
	 * @param sBaseUri
	 * @param header
	 * @return
	 */
	public Response taGet(String sBaseUri, HashMap<String, String> header) {
		RestAssured.baseURI = sBaseUri;
		Response res = RestAssured.given().headers(header).when().get();
		return res;
	}
	
	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public Response taPut(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {
		RestAssured.baseURI = sBaseUri;
		Response res = RestAssured.given().headers(header).when().body(payload).put();
		return res;
	}
	
	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public Response taDelete(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {
		RestAssured.baseURI = sBaseUri;
		Response res = RestAssured.given().headers(header).when().body(payload).delete();
		return res;
	}

}
