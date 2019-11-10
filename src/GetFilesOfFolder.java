import java.io.File;
import java.util.ArrayList;
/**
 * Project for ******
 * Lund University
 *
 * This class is used to get a list of files from a certain folder and save the list in an ArrayList.
 * 
 * @author Nadia Letth
 * version 1.0; 2019-02-21 
 */

public class GetFilesOfFolder {

	/* Method to get the list of files in a certain folder, save the list in the array list and return the array list 
	 * @param path		a path to the folder
	 * @return the list of files in the folder
	 */
	public ArrayList<String> getFiles(String path){
		//ArrayList to save the data of the method
		ArrayList<String> listOfFiles = new ArrayList<String>();
		
		//Object File
		File folder = new File(path);
		//Using method listFiles() to get files in the folder and putting them into array files
		File[] files = folder.listFiles();
		
			//Inserting filenames into arrayList by using methods isFile() and getName()
			for(int i=0; i<files.length; i++){
				if(files[i].isFile()){
					listOfFiles.add(files[i].getName());
				}
			}
			//Returning arrayList of fileNames
			return listOfFiles;
	}
}
