package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.response.Response;
import rest.utils.DataUtils;
import rest.utils.RestUtils;
import testdata.AddDataPayLoad;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTests extends BaseTest {
	
	static String token = "";
	
	
	
	@Test
	public void login_TC_01() throws IOException {
		String sEnv = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.prod.URL");
		sUri = sUri+JsonPath.read(sEnv, "$.prod.endpoint.login");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		HashMap<String, String> sPayload = new HashMap<String, String>();
		String creds = DataUtils.readJsonFileToString(FileConstants.USER_ACCOUNTS_FILE_PATH);
		sPayload.put("username", JsonPath.read(creds, "$.prod.valid.username"));
		sPayload.put("password", JsonPath.read(creds, "$.prod.valid.password"));
		Response res = oRestUtil.taPostSchemaValidation(sUri, headers, sPayload);
		token = res.jsonPath().getString("token").replaceAll("\\[", "").replaceAll("]", "");
//		String expectedStatusCode = DataUtils.readJsonFileToString(FileConstants.LOGIN_VALIDATION_FILE_PATH);
//		assertThat(String.valueOf(res.getStatusCode()), JsonPath.read(expectedStatusCode, "$.login_TC01[0]"));
//		assertThat(String.valueOf(res.getStatusCode()), DataUtils.sLoginValidationData("$.login_TC01[0]").toString());
	}
	
	@Test(dependsOnMethods = "login_TC_01")
	public void addData_TC_01() throws IOException {
		String sEnv = DataUtils.readJsonFileToString(FileConstants.ENV_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.prod.URL");
		sUri = sUri+JsonPath.read(sEnv, "$.prod.endpoint.adddata");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("token", token);
		AddDataPayLoad user1 = new AddDataPayLoad("TA-1234567", "5", "3456733", "234123");
		Response res = oRestUtil.taPost(sUri, headers,  oRestUtil.serializeObject(user1));
		System.out.println(res.asPrettyString());
	}
	

}
