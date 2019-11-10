//import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**
 * Project for ******
 * Lund University
 *
 * The class to create objects of type ObjFormula and save them into ArrayList<ObjFormula> formulaeList
 * The class contain method createObjFormula.
 * 
 * @author Nadia Letth
 * version 1.0; 2019-03-07 
 */

public class CreateFormulaObjects {

	/* Method to create objects of type ObjFormula and save them in the arrayList formulaeList
	 * @param formulaeList		arrayList of formula objects
	 * @param listOfRuxFiles	arrayList of paths to RUX files
	 * @return an arrayList of fileNames for the counter of formulae in the files
	 */
	public ArrayList<String> createObjFormula(ArrayList<ObjFormula> formulaeList, ArrayList<String>listOfRuxFiles){
		//Object of ReadRuxFile class
		ReadRuxFile readFile = new ReadRuxFile();
		
		//ArrayList for file names
		ArrayList<String> fileNames = new ArrayList<String>();
				
		//Looping along the arrayList of RUX files
		for(int i=0; i<listOfRuxFiles.size(); i++) {
			//Saving path to RUX file in the String variable ruxFile
			String ruxFile = listOfRuxFiles.get(i);
			
			//Variables for parts of formula; used to create an object ObjFormula
			String folderName;
			String fileName;
						
			//Getting folderName and fileName from the variable ruxFile
			String delimiter = "/"; //Variable for delimiter
			String[] subString;		//Array for parts of String path
			subString = ruxFile.split(delimiter);	//Splitting the path
			fileName = subString[(subString.length-1)];
			//add fileName to the ArrayList fileNames
			fileNames.add(fileName);			
			folderName = subString[(subString.length-2)];
			//ArrayList to save formulae from the RUXfile
			ArrayList<String> list = new ArrayList<String>();
			
			//Getting the list
			list = readFile.read(ruxFile);
			
			//Looping along the arrayList with formulae
			for(int j=0; j<list.size(); j++) {
				String formulaName;
				String formulaBody;
				//Getting the index of the first '='
				int temp = list.get(j).indexOf('='); 
				
				//Getting formula name
				formulaName = list.get(j).substring(0, temp);				
			
				//Getting formulaBody without =
				formulaBody = list.get(j).substring(temp+1, (list.get(j).length())-1);
				
				//Adding an object of Formula into arrayList formulaeList
				formulaeList.add(new ObjFormula(folderName, fileName, formulaName, formulaBody));
			}
		}
		return fileNames;
	}
}