package injectionDetection;

public class Config {
	
	public enum InjectionType {
		SQLInjection,
		WindowsCmdInjection,
		UnixCmdInjection
	}
	
	private static String path[] = {"/cheatsheetPattern/sqlInjection.txt",
			"/cheatsheetPattern/unixCmdInjection.txt",
			"/cheatsheetPattern/winCmdInjection.txt"};

	private static String rootDir = "webapps/secure360/";
	
	public static String[] getPath () {
		return path;
	}

	public static String getRootDirectory() {
		return rootDir;
	}
}
