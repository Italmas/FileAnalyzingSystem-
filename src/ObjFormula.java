/**
 * Project for ******
 * Lund University
 *
 * This class represents a formula saved in .RUX files. 
 * Formula has a name of a folder (where the file is), a name of the file (where the formula is written),
 * a name and a body.
 * 
 * @author Nadia Letth
 * version 1.0; 2019-02-21  
 */

public class ObjFormula {

	//Variables
	private String folderName;
	private String fileName;
	private String formulaName;
	private String formulaBody;
			
	//Constructor to create an object and set certain values to the variables of object
	public ObjFormula(String folderName, String fileName, String formulaName, String formulaBody) {
		super();
		this.folderName = folderName;
		this.fileName = fileName;
		this.formulaName = formulaName;
		this.formulaBody = formulaBody;
	}
			
	/* Method to return a name of the folder 
	 * @return the name of the folder
	 */
	public String getFolderName() {
		return folderName;
	}
	/* Method to set a name of the folder
	* @param folderName		name of the folder
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
			
	/* Method to return a name of the file 
	* @return the name of the file
	*/
	public String getFileName() {
		return fileName;
	}
	/* Method to set a name of the file
	* @param fileName		name of the file
	*/
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
			
	/* Method to return a name of the formula 
	 * @return the name of the formula
	 */
	public String getFormulaName() {
		return formulaName;
	}
	/* Method to set a name of the formula
	 * @param formulaName		name of the formula
	 */
	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
			
	/* Method to return a body of the formula 
	 * @return the body of the formula
	 */
	public String getFormulaBody() {
		return formulaBody;
	}
	/* Method to set a body of the formula
	 * @param formulaBody		body of the formula
	 */
	public void setFormulaBody(String formulaBody) {
		this.formulaBody = formulaBody;
	}
}