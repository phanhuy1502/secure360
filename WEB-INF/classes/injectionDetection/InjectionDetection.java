package injectionDetection;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import injectionDetection.Config.InjectionType;
import injectionDetection.Config;
import injectionDetection.Injection;

import java.util.regex.Matcher;

public class InjectionDetection {
	
	private static ArrayList<Pattern> SQLInjectionPattern;
	private static ArrayList<Pattern> WindowInjectionPattern;
	private static ArrayList<Pattern> LinuxInjectionPattern;
		
	private static InjectionDetection injectionDetection = null;
	private static ServletContext cntx = null;

	private InjectionDetection() {

		SQLInjectionPattern = new ArrayList<Pattern>();
		WindowInjectionPattern = new ArrayList<Pattern>();
		LinuxInjectionPattern = new ArrayList<Pattern>();
	
		loadInjectionCheatSheet();
	}

	private InjectionDetection(ServletContext passedCntx) {

		SQLInjectionPattern = new ArrayList<Pattern>();
		WindowInjectionPattern = new ArrayList<Pattern>();
		LinuxInjectionPattern = new ArrayList<Pattern>();
		cntx = passedCntx;
		
		loadInjectionCheatSheet();
	}
	
	public static InjectionDetection getInjectionDetection(ServletContext passedCntx) {
		if(injectionDetection == null) {
			if (passedCntx == null){
				injectionDetection = new InjectionDetection();
			}
			else 
				injectionDetection = new InjectionDetection(passedCntx);

		}
		return injectionDetection;
	}

	private void loadInjectionCheatSheet() {
		BufferedReader br = null;
		
		try {
			String sCurrentLine;
			if (cntx == null) {
				br = new BufferedReader(new FileReader(Config.getPath()[0]));
			} else
      		br = new BufferedReader(new InputStreamReader(cntx.getResourceAsStream(Config.getPath()[0])));
			//Load SQL injection cheatsheet
			while ((sCurrentLine = br.readLine()) != null) {
				SQLInjectionPattern.add(Pattern.compile(sCurrentLine));
			}
			br.close();

			//Load Linux injection cheatsheet
			if (cntx == null) {
				br = new BufferedReader(new FileReader(Config.getPath()[1]));
			} else
			br = new BufferedReader(new InputStreamReader(cntx.getResourceAsStream(Config.getPath()[1])));
			while ((sCurrentLine = br.readLine()) != null) {
				LinuxInjectionPattern.add(Pattern.compile(sCurrentLine));
			}
			br.close();
			
			//Load Window injection cheatsheet
			if (cntx == null) {
				br = new BufferedReader(new FileReader(Config.getPath()[2]));
			} else
			br = new BufferedReader(new InputStreamReader(cntx.getResourceAsStream(Config.getPath()[2])));
			while ((sCurrentLine = br.readLine()) != null) {
				WindowInjectionPattern.add(Pattern.compile(sCurrentLine));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ArrayList<Injection> checkFile(String fileDirectory) {
		BufferedReader br = null;
        String line = "";
        InjectionType t = null;
        ArrayList<Injection> injectionList = new ArrayList<Injection>();
        try {
        	InputStream is = cntx.getResourceAsStream(fileDirectory);
        	if (is == null) return null;
            br = new BufferedReader(new InputStreamReader(is));
            String[] parsed_request = new String[4];

            while ((line = br.readLine()) != null) {
                if (line.indexOf("#") < 0) continue;
                int lastIndex = 0;
                for (int i = 0; i < 3; i++) {
                    int index = line.indexOf("#", lastIndex);
                    parsed_request[i] = line.substring(lastIndex, index);
                    lastIndex = index + 1;
                }
                parsed_request[3] = line.substring(lastIndex, line.length());
                // Check request
                String[] injectionValue = new String[3];
                for (int i = 0; i < SQLInjectionPattern.size(); i++) {
                	Matcher sqlMatcher = SQLInjectionPattern.get(i).matcher(parsed_request[3]);
        			if (sqlMatcher.find()) {
        				t = InjectionType.SQLInjection;
        				int start = sqlMatcher.start();
        				int end = sqlMatcher.end();
        				injectionValue[0] = parsed_request[3].substring(0, start);
        				injectionValue[1] = parsed_request[3].substring(start, end);
        				if (end == parsed_request[3].length() -1) {
        					injectionValue[2] = "";
        				}
        				else injectionValue[2] = parsed_request[3].substring(end, parsed_request[3].length());
        			}
        		}
        		
        		for (int i = 0; i < WindowInjectionPattern.size(); i++) {
        			Matcher winMatcher = WindowInjectionPattern.get(i).matcher(parsed_request[3]);
        			if (winMatcher.find()) {
        				t = InjectionType.WindowsCmdInjection;
        				int start = winMatcher.start();
        				int end = winMatcher.end();
        				injectionValue[0] = parsed_request[3].substring(0, start);
        				injectionValue[1] = parsed_request[3].substring(start, end);
        				if (end == parsed_request[3].length() -1) {
        					injectionValue[2] = "";
        				}
        				else injectionValue[2] = parsed_request[3].substring(end, parsed_request[3].length());
        			}
        		}
        		
        		for (int i = 0; i < LinuxInjectionPattern.size(); i++) {
        			Matcher unixMatcher = LinuxInjectionPattern.get(i).matcher(parsed_request[3]);
        			if (unixMatcher.find()) {
        				t = InjectionType.UnixCmdInjection;
        				int start = unixMatcher.start();
        				int end = unixMatcher.end();
        				injectionValue[0] = parsed_request[3].substring(0, start);
        				injectionValue[1] = parsed_request[3].substring(start, end);
        				if (end == parsed_request[3].length() - 1) {
        					injectionValue[2] = "";
        				}
        				else injectionValue[2] = parsed_request[3].substring(end, parsed_request[3].length());
        			}
        		}
        		
                if (t != null) {
                    Injection i = new Injection (t, parsed_request[0], parsed_request[1],
                    		parsed_request[2], injectionValue);
                    injectionList.add(i);
                    System.out.println(i.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null; 
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return injectionList;
	}



}
