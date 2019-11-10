import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Project for ******
 * Lund University
 *
 * This class is used to create objects of ObjFile.
 * There are four methods in the class. Method createObjFile() creates objects of ObjFile with certain names. 
 * Then it calls for methods getPresenceInFolder() and getChangeDates() to get data about the presence 
 * of the file with a certain name in different folders and about last date of change of files 
 * with this name in folders. Method getDateChange() is used by method getChangeDates() to get a date of 
 * change in a proper format.
 * 
 * @author Nadia Letth
 * version 1.0; 2019-02-21 
 */

public class CreateObjects {

	/* Method to create objects of type ObjFile and save them in the arrayList fileList
	 * @param fileList			arrayList of file objects
	 * @param paths				arrayList of paths to folders
	 */
	public void createObjFile(ArrayList<ObjFile> fileList, ArrayList<String>paths){
		
		//object of class GetFilesOfFolder
		GetFilesOfFolder getFileOfFolder = new GetFilesOfFolder();
		
		//Using arryaList of paths, we get an arrayList of files in the respective folder
		//and save the gotten arrayList of files in the arrayList of arrayLists filesINfolder
		List<ArrayList<String>> filesINfolders = new ArrayList<>();	
		for(int i=0; i<paths.size(); i++) {
			String path = paths.get(i);
			ArrayList<String> listOFfiles = new ArrayList<String>();
			listOFfiles = getFileOfFolder.getFiles(path);
			filesINfolders.add(listOFfiles);
		}
		
		//Create objects of ObjFile with certain names and saving new objects in the arrayList fileList.
		//Looping the list of folders
		for(int i=0; i<filesINfolders.size(); i++){
			//Looping the list of files in the folder
			for(int j=0; j<filesINfolders.get(i).size(); j++){		
				//Temporal filename from the arrayList of Strings
				String tempFileName = filesINfolders.get(i).get(j);
				//Variable for existence of the object with the name similar to tempFileName
				boolean existASobject = false;
				//Looping the list of objects
				for(int k=0; k<fileList.size(); k++) {
					//Temporal object name from the arrayList of objects
					String tempObjName = fileList.get(k).getFileName();
					//Checking if temporal filename and object name have the same values
					if(tempObjName.equals(tempFileName)) {
						//If they do, turn existence variable to true
						existASobject = true;
					} 
				}
				//If there is no object with the name equal to the temporal file name...
				if(existASobject == false) {
					//...creating an object with this new name
					fileList.add(new ObjFile(tempFileName));
					}
			}			
		}
		//Calling for other methods to fill arrayLists with data about presence and dates of change
		getPresenceInFolder(fileList, filesINfolders);
		getChangeDates(fileList, paths);
	}
	
	
	
	/* Method to fill an arrayList of file presence in different folders
	 * @param fileList			arrayList of file objects
	 * @param filesINfolders	arrayList of arrayLists of files in folders
	 */
	private void getPresenceInFolder(ArrayList<ObjFile> fileList, List<ArrayList<String>> filesINfolders) {
		//Looping the list of objects
		for(int i=0; i<fileList.size(); i++) {
			//Getting one object
			ObjFile objFile = fileList.get(i);
			//ArrayList to save the data of the method
			ArrayList<String> presenceINfolders = new ArrayList<String>();
			//Temporal variable for name of the object
			String tempObjName = fileList.get(i).getFileName();
			//Looping the arrayList of arrayLists (list of folders)
			for(int j=0; j<filesINfolders.size(); j++){	
				//Variable for presence of the file with name similar to the object name in the folder
				boolean inList = false;
				//Looping the list of files in a certain folder
				for(int k=0; k<filesINfolders.get(j).size(); k++){	
					//If there is a file with name similar to the name of object...
					if(filesINfolders.get(j).contains(tempObjName) == true) {
						//...turn inList to true
						inList = true;
					}
				}
				//If inList is true...
				if(inList == true) {
					//...add Y into array presenceINfolders
					presenceINfolders.add(j, "Y");
				//If inList is false...
				}else if(inList == false){
					//...add N into array presenceINfolders
					presenceINfolders.add(j, "N");
				}
			}
			//Add an arrayList presenceINfolders to the object
			objFile.setPresenceINfolders(presenceINfolders);
		}	
	}
	
	
	
	/* Method to fill an arrayList of files' last date of change in different folders
	 * @param fileList			arrayList of file objects
	 * @param paths				arrayList of paths to folders
	 */
	private void getChangeDates(ArrayList<ObjFile> fileList, ArrayList<String>paths) {
		//Looping the list of objects
		for(int i=0; i<fileList.size(); i++) {	
			//Getting one object
			ObjFile objFile = fileList.get(i);
			//ArrayList to save the data of the method
			ArrayList<String> datesOFchange = new ArrayList<String>();
			//Temporal variable for name of the object
			String tempObjName = fileList.get(i).getFileName();
			//Looping the list of paths to folders
			for(int j=0; j<paths.size(); j++) {
				//Object File
				File folder = new File(paths.get(j));
				//Using method listFiles() to get files in the folder and putting them into array files
				File[] files = folder.listFiles();
				//String variable for date
				String date = "";
				
				//Looping the list of files
				for(int k=0; k<files.length; k++){
					//Getting file name
					String name = files[k].getName(); 
					//If gotten filename is equal to the temporal object name...
					if((name.compareTo(tempObjName)) == 0) {
						//...getting the date of change of the file in the folder
						long timestamp = files[k].lastModified();
						//Turning the date to the proper format
						date = getDateChange(timestamp);
					}
				}
			//Saving the date in the arrayList datesOFchange 
			datesOFchange.add(j, date);
			}
		//Add an arrayList datesOFchange to the object
		objFile.setDatesOFchange(datesOFchange);
		}	
	}
	
	
	
	/* Method to convert date to a certain format and return it
	 * @param date			date in type long
	 * @return the date in format according to Swedish system
	 */
	public String getDateChange(long date){
		//Format of the date according to Swedish system
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy.MM.dd");
		//Converting
		String dateChanged = sdf.format(new Date(date));

		return dateChanged;
	}
}
