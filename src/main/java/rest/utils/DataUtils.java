package rest.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;

public class DataUtils {
	
	public static String readJsonFileToString(String sFilePath) throws IOException {
		byte[] data = Files.readAllBytes(Paths.get(sFilePath));
		return new String(data, StandardCharsets.UTF_8);
	}
	
	public static Object sLoginValidationData(String jsonPath) {
		String loginValidation = null;
		try {
			loginValidation = DataUtils.readJsonFileToString(FileConstants.LOGIN_VALIDATION_FILE_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object value = JsonPath.read(loginValidation, jsonPath);
		return value;
	}
	
	

}
