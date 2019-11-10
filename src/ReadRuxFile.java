import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Project for ******
 * Lund University
 *
 * This class contains method read() to read a certain RUX file and 
 * save formulae from the file in the arrayList list. 
 * 
 * @author Nadia Letth
 * version 1.0; 2019-03-06  
 */

public class ReadRuxFile {
	/* Method to read a file and save formulae in the arrayList 
	 * @param path		a path to RUX file
	 * @return the list of formulae
	 */
	public ArrayList<String> read(String path) {
		//An arrayList to save formulae as strings
		ArrayList<String> list = new ArrayList<>();
		
		try{
			//Objects to read a file
			FileReader file = new FileReader(path);
			BufferedReader br = new BufferedReader(file);
			//Variable to read file line by line
			String code = br.readLine();
			
			//Read each line of the file
			while(code != null) {
				//Variable to keep formulae as a string
				String formula = "";			
				//If the line is not empty
				if (code.length() > 1) {
					String trimmed = code.trim();
					
					//Check that the line starts with '['
					if (trimmed.startsWith("[")) {
						//Add the line into String formula
						formula += trimmed;
						
							//Check that the line finishes with ';'
							if(trimmed.endsWith(";")) {
								//Add String formula into arrayList list
								list.add(formula);
								
							//If the line does not finish with ';'
							}else {
								boolean stillFormula = true;
								//While stillFormula is true...
								while (stillFormula == true) {
									//Read the next line
									code = br.readLine();

									//If the line inside of the formula is empty
									if(code.length() == 0) {
										formula += "";
									//If only semicolon is in line
									}else if(code.length() == 1) {
										char temp = code.charAt(0);
										if (temp == ';') {
											formula += temp;
											//Add the String formula into arrayList list
											list.add(formula);
											//Turn stillFormula to false
											stillFormula = false;
										}
									//If there are more than one character in the line
									}else if (code.length() > 1) {
										if (code.trim().endsWith(";") == false) {
											//Add the line into String formula
											formula += code.trim();
										//If the line finishes with ';'
										} else if (code.trim().endsWith(";") == true) {
											//Add the line into String formula
											formula += code.trim();
											//Add the String formula into arrayList list
											list.add(formula);
											//Turn stillFormula to false
											stillFormula = false;
										}		
									}															
								}												
							} 
					}				
				}
								
			//Read the next line while you do not come to the one that starts with '['
			code = br.readLine();
			}
			//Closing BufferedReader input
			br.close();
			
		} catch (Exception e){
			e.printStackTrace();
			//To console
			System.out.println("Problems with reading a RUX file.");
			
			//To user
			View view = new View();
			view.setErrorMessage(1);
		}
		//Return an arrayList of formulae
		return list;
	}
}