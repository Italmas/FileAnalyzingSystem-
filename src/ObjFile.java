import java.util.ArrayList;

/**
 * Project for ******
 * Lund University
 *
 * This class represents a file in the system. 
 * File has a name, information about its presence and last change in four folders of the system, 
 * 
 * @author Nadia Letth
 * version 1.0; 2019-02-21  
 */

public class ObjFile {

		//Variables
		private String fileName;
		private ArrayList<String> presenceINfolders = new ArrayList<String>();
		private ArrayList<String> datesOFchange = new ArrayList<String>();

		//Constructor to create an object and set its name
		public ObjFile(String fileName) {
			super();
			this.fileName = fileName;
		}

		/* Method to return the name of the file 
		 * @return the name of the file
		 */
		public String getFileName() {
			return fileName;
		}
		/* Method to set the name of the file
		 * @param fileName		name of the file
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
		/* Method to return the presence of file in folders 
		 * @return an arrayList with information about the presence of file in folders
		 */
		public ArrayList<String> getPresenceINfolders() {
			return presenceINfolders;
		}
		/* Method to set an arrayList of the presence of file in folders
		 * @param presenceINfolders		an arrayList with information about the presence of file in folders
		 */
		public void setPresenceINfolders(ArrayList<String> presenceINfolders) {
			this.presenceINfolders = presenceINfolders;
		}
		
		/* Method to return the dates of change of files in folders 
		 * @return an arrayList with information about the dates of change of files in folders
		 */
		public ArrayList<String> getDatesOFchange() {
		return datesOFchange;
		}
		/* Method to set an arrayList of the the dates of change of files in folders
		 * @param datesOFchange		an arrayList with information about the dates of change of files in folders
		 */
		public void setDatesOFchange(ArrayList<String> datesOFchange) {
			this.datesOFchange = datesOFchange;
		}
}