import java.util.ArrayList;
import java.io.*;
import java.lang.StringBuilder;
import java.nio.charset.StandardCharsets;

/**
 * Project for ******
 * Lund University
 *
 * The class is used to send data to CSV files. It contains three methods.
 * Method filesINfolderCSV() creates CSV file with data about the presence of files in certain folders.
 * Method datesOFchangeCSV() creates CSV file with data about the dates of change of files in certain folders.
 * Method formulaeCSV() creates CSV file with data about formulae from RUX files of certain folders.
 * 
 * @author Nadia Letth
 * version 1.0; 2019-03-11 
 */

public class ToCSV {	
	
	/* Method to create CSV file with data about the presence of files in certain folders
	 * @param paths		arrayList of paths to folders
	 * @param directory	path where result file should be saved
	 */
	public String filesINfolderCSV(ArrayList<String>paths, String directory){
		
		String resultMessage = "";
		
		//Object of class CreateObjects
		CreateObjects createObjects = new CreateObjects();
		
		//ArrayList for objects of type ObjFile
		ArrayList<ObjFile> fileList = new ArrayList<ObjFile>();
		
		//Creating objects of ObjFile
		createObjects.createObjFile(fileList, paths);
		
		try {
		//Creating CSV file
			String filePath = directory + "/FAS_Content.csv";
			
			//With UTF-8 encoding
			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
			br.write('\ufeff');
			StringBuilder sb = new StringBuilder();
			
			//Creating data for CSV file - Header
			sb.append("File" + ";");
			for(int j=0; j<paths.size(); j++){
				String folderName = createFolderName(paths.get(j));
				sb.append(folderName + ";");
			}
			sb.append(System.getProperty("line.separator"));
			
			//Creating data for the CSV file - Content
			for(int i=0; i<fileList.size(); i++){
				ObjFile objFile = fileList.get(i);
				String name = objFile.getFileName();
				ArrayList<String> listOFpresence = objFile.getPresenceINfolders();
				sb.append(name  + ";");
				for(int j=0; j<listOFpresence.size(); j++){
					sb.append(listOFpresence.get(j) + ";");
				}
				sb.append(System.getProperty("line.separator"));
			}
			//Inserting data into the CSV file	
			br.write(sb.toString());
			//Closing BufferedWriter
			br.close();
			
			//Information to console
			System.out.println("File FAS_Content.csv is written successfully.");
			
			resultMessage = "File FAS_Content.csv is written successfully.";
			
		} catch (Exception e) {
			e.printStackTrace();
			//To console
			System.out.println("Problems with CSV file creation.");
			
			//To user
			View view = new View();
			view.setErrorMessage(2);
		}		
		return resultMessage;
	}
	
	
	/* Method to create CSV file with data about the dates of change of files in certain folders
	 * @param paths		arrayList of paths to folders
	 * @param directory	path where result file should be saved
	 */
	public String datesOFchangeCSV(ArrayList<String>paths, String directory){
		
		String resultMessage = "";
		
		//Object of class CreateObjects
		CreateObjects createObjects = new CreateObjects();
		
		//ArrayList for objects of type ObjFile
		ArrayList<ObjFile> fileList = new ArrayList<ObjFile>();
		
		//Creating objects of ObjFile
		createObjects.createObjFile(fileList, paths);
		
		try {
		//Creating CSV file
		String filePath = directory + "/FAS_LastUpdate.csv";
		
		//With UTF-8 encoding
		BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
		br.write('\ufeff');
		StringBuilder sb = new StringBuilder();
		
		//Creating data for the CSV file - Header
		sb.append("File" + ";");
		for(int j=0; j<paths.size(); j++){
			String folderName = createFolderName(paths.get(j));
			sb.append(folderName + ";");
		}
		sb.append(System.getProperty("line.separator"));
		
		//Creating data for the CSV file - Content
		for(int i=0; i<fileList.size(); i++){
			ObjFile objFile = fileList.get(i);
			String name = objFile.getFileName();
			ArrayList<String> datesOFchange = objFile.getDatesOFchange();
			sb.append(name  + ";");
			for(int j=0; j<datesOFchange.size(); j++){
				sb.append(datesOFchange.get(j) + ";");
			}
			sb.append(System.getProperty("line.separator"));
		}
		//Inserting data into the CSV file	
		br.write(sb.toString());
		//Closing BufferedWriter
		br.close();
		
		//Information to console
		System.out.println("File FAS_LastUpdate.csv is written successfully.");
		
		resultMessage = "File FAS_LastUpdate.csv is written successfully.";
		
		} catch (Exception e) {
			e.printStackTrace();
			//To console
			System.out.println("Problems with CSV file creation.");
			
			//To user
			View view = new View();
			view.setErrorMessage(2);
		}
		return resultMessage;
	}
	
	
	
	/* Method to create CSV file with data about formulae from RUX files of certain folders
	 * @param paths		arrayList of paths to folders
	 * @param directory	path where result file should be saved
	 */
	public String formulaeCSV(ArrayList<String>paths, String directory){
		
		String resultMessage = "";
		
			//ArrayList for objects of type ObjFormula
			ArrayList<ObjFormula> formulaeList = new ArrayList<ObjFormula>();
			
			//Objects of classes GetRuxFiles and CreateFormulaObjects
			GetRuxFiles getRuxFiles = new GetRuxFiles();
			CreateFormulaObjects formulaeObjects = new CreateFormulaObjects();
			
			//ArrayList for RUX files
			ArrayList<String> listOfRuxFiles = new ArrayList<String>();
			//Getting a list of all RUX files
			getRuxFiles.getRUXfiles(listOfRuxFiles, paths);
			
			ArrayList<String> fileNames = formulaeObjects.createObjFormula(formulaeList, listOfRuxFiles);
			
			try {				
			//Creating CSV file
			String filePath = directory + "/FAS_Rules.csv";
						
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8));
			output.write('\ufeff');
		
			//Creating data for the CSV file - Header
			output.write("Folder"  + ";" + "File"  + ";" + "Nr" + ";" + "Name"  + ";" + "Formula"  + ";");
			output.write(System.getProperty("line.separator"));
			// Going along the arrayList and inserting the data into the file
			for(int j=0; j<fileNames.size(); j++){
				String fn = fileNames.get(j);
				int nr = 1;
				for(int i=0; i<formulaeList.size(); i++){
					ObjFormula formula = formulaeList.get(i);
					if(formula.getFileName() == fn){
						String folderName = createFolderName(formula.getFolderName());							
						
						String out = folderName+";"+formula.getFileName()+";"+nr+";"+formula.getFormulaName()+";"+formula.getFormulaBody()+";";
						byte[] b = out.getBytes(StandardCharsets.UTF_8);
						out = new String(b, StandardCharsets.UTF_8);
						
						output.write(out);
						output.write(System.getProperty("line.separator"));
											
						nr ++;
					}		
				}
			}			
			//Closing BufferedWriter
			output.close();
			
			//Information to console
			System.out.println("File FAS_Rules.csv is written successfully.");
			
			resultMessage = "File FAS_Rules.csv is written successfully.";
			
			} catch (Exception e) {
				e.printStackTrace();
				//To console
				System.out.println("Problems with CSV file creation.");
				
				//To user
				View view = new View();
				view.setErrorMessage(2);
			}		
		return resultMessage;
	}
	
	
	/* Method to cut the path of the folder 
	 * @param paths		String paths to the certain folder
	 * @return the cut version of path
	 */
	public String createFolderName(String path) {
		String folder = "";
		String[] subString;		//Array for parts of String path
		subString = path.split("\\\\");	//Splitting the path
		
		if (subString.length == 1) { //root catalog
			folder = path;
		}else if (subString.length > 1) { //deeper
			String folder1 = subString[(subString.length-1)];
			String folder2 = subString[(subString.length-2)];
			folder = folder2 + "/" + folder1;
		}		
		return folder;
	}
	
}

