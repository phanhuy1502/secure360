package report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import anormallyDetection.FileType;
import anormallyDetection.MaliciousFile;

public class ReportParser {
	public static ArrayList<MaliciousFile> ParseAnormallyFile() {
		BufferedReader br = null;
		ArrayList<MaliciousFile> maliciousFileList = new ArrayList<MaliciousFile>();
		try {
			File fileDir = new File("/home/phanhuy1502/Spark/Scala/files/report/anormally.txt");
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "Cp1252"));
			String lastName = "";
			String fileName = "";
			MaliciousFile tempFile = null;
			int position; double distance; double exceedingRatio;
			while ((fileName = br.readLine()) != null) {
				if (fileName != lastName) {
					if (tempFile != null) {
						maliciousFileList.add(tempFile);
					}
					lastName = fileName;
					tempFile = new MaliciousFile(fileName);
				}
				position = Integer.parseInt(br.readLine());
				distance = Double.parseDouble(br.readLine());
				exceedingRatio = Double.parseDouble(br.readLine());
				tempFile.addAnormally(position, distance, exceedingRatio);
			}
			maliciousFileList.add(tempFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		return maliciousFileList;
	}
	
	public static ArrayList<FileType> ParseReportFile() {
		BufferedReader br = null;
		ArrayList<FileType> fileList = new ArrayList<FileType>();
		
		try {
			File fileDir = new File("/home/phanhuy1502/Spark/Scala/files/report/report.txt");
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "Cp1252"));
			String fileName = "";
			while ((fileName = br.readLine()) != null) {
				if (br.readLine().contains("0")) {
					fileList.add(new FileType(fileName, false));
				}
				else {
					fileList.add(new FileType(fileName, true));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
		return fileList;
	}
}
