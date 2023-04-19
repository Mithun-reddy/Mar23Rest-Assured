package rest.utils;

import java.io.File;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import testdata.AddDataPayLoad;
import testdata.GetDataPayload;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.given;


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
		System.out.println("taPost: URI :" + sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();
		Response res = RestAssured.given().headers(header).when().body(payload).post();
		return res;
	}

	public Response taPostSchemaValidation(String sBaseUri, HashMap<String, String> header, Object payload) {

		RestAssured.baseURI = sBaseUri;
		System.out.println("taPost: URI :" + sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();
		Response res = RestAssured.given().headers(header).when()
				.body(payload).post().then().assertThat()
				.body(matchesJsonSchema(new File(FileConstants.LOGIN_SCHEMA_FILE_PATH))).extract().response();
		return res;
	}
	
	public Response taPost(String sBaseUri, HashMap<String, String> header, String payload) {

		RestAssured.baseURI = sBaseUri;
		System.out.println("taPost: URI :" + sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();
		Response res = RestAssured.given().headers(header).when().body(payload).post();
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
	
	/**
	 * @param user
	 * @return
	 * @throws JsonProcessingException
	 */
	public String serializeObject(Object user) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String jsonPayload = om.writeValueAsString(user);
		System.out.println("RestUtils : serializeObject : "+ jsonPayload);
		return jsonPayload;
	}
	
	/**
	 * @param res
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Object deSerializeJSON(Response res) throws JsonMappingException, JsonProcessingException {
		String sFirstObj = JsonPath.read(res.getBody().asString(), "$.data[0]");
		ObjectMapper om = new ObjectMapper();
		GetDataPayload user = om.readValue(sFirstObj, GetDataPayload.class);
		return user;
	}

}
