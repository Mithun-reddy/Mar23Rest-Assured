package constants;

public class FileConstants {
	
	public static final String ENV_URI_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\configs\\envURIs.json"; 
	public static final String USER_ACCOUNTS_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\configs\\useraccounts.json";
	public static final String ADD_USER_DATA_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\payload\\adduserdata.json";
	public static final String ADD_DATA_VALIDATION_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\testdata\\adddatavalidation.json";
	public static final String LOGIN_VALIDATION_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\testdata\\loginvalidation.json";
	public static final String UPDATE_DATA_VALIDATION_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\resources\\testdata\\updatevalidation.json";
	public static final String LOGIN_SCHEMA_FILE_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\schema\\LoginResponseSchema.json";
}
