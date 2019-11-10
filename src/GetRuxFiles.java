import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Project for ******
 * Lund University
 *
 * This class is used to get a list of paths for all RUX files and save them in an ArrayList.
 * 
 * @author Nadia Letth
 * version 1.0; 2019-02-21 
 */

public class GetRuxFiles {
	
	/* Method to get paths of RUX files and save them in the arrayList listOfRuxFiles 
	 * @param listOfRuxFiles		an array, containing paths of RUX files from folders
	 * @param listOfPaths			an array, containing paths of folders
	 */
	public void getRUXfiles (ArrayList<String> listOfRuxFiles, ArrayList<String>listOfPaths) {
		//Looping along the list of folder paths
		for(int i=0; i<listOfPaths.size(); i++){
			//Saving a certain path of folder in a String variable path
			String path = listOfPaths.get(i);	
			//Object File
			File folder = new File(path);
			//Using method listFiles() to get files in the folder and putting them into array files
			File[] files = folder.listFiles();
			
			//Looping an array files
			for(int j=0; j<=(files.length-1); j++){
				if(files[j].isFile()){
					//Checking that the file is .rux
					String tempFile = files[j].getName();	//Name of the file
					//Pattern for comparison
					Pattern patternRUX = Pattern.compile("^.+\\.RUX$");
					Matcher matcherRUX = patternRUX.matcher(tempFile);
						//If the name corresponds the pattern
						if(matcherRUX.find() == true) {
							String folderFile = path + "/" + tempFile;	//folderName+fileName
							//Save name in the arrayList
							listOfRuxFiles.add(folderFile);
						}	
				}
			}			
		}
	}
}