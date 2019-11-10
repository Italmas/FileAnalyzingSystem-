import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JButton;


import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;

/**
 * Project for ******
 * Lund University
 *
 * This class displays the gui for the application.
 * 
 * @author Mattias Edlund and Nadia Letth
 * version 1.0; 2019-03-12 
 */

public class View {

	private JFrame frmModellanalys;
	private JLabel lblResultMessage;
	/**
	 * @wbp.nonvisual location=662,99
	 */
	private final DefaultListModel<String> dlmModel = new DefaultListModel<String>();
	private JTextField txtDesignatedFolder;
	private JTextField txtDexample;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frmModellanalys.setVisible(true);
					
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public View() {
		//Initialize the contents of the frame
		initialize();
	}
	
	private void initialize() {
		//Creates the application-window
		frmModellanalys = new JFrame();
		frmModellanalys.setTitle("File Analyzing System");
		frmModellanalys.setBounds(100, 100, 562, 423);
		frmModellanalys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModellanalys.getContentPane().setLayout(null);
		//Special settings of interface color
		//frmModellanalys.getContentPane().setBackground(new Color(220,255,200));	
		
				
		//ArrayLists of paths
		ArrayList<String> listOfPaths = new ArrayList<String>();
		//Object of class ToCSV
		ToCSV toCSV = new ToCSV();
		
		//Run through the modelPath array and add elements to the list that is shown
		for(String m : listOfPaths)
		{
		    dlmModel.addElement(m);
		}
		
		lblResultMessage = new JLabel("");
		lblResultMessage.setBounds(21, 350, 403, 23);
		frmModellanalys.getContentPane().add(lblResultMessage);
		
		JLabel lblLocationOfResult = new JLabel("Result location:");
		lblLocationOfResult.setBounds(21, 11, 284, 14);
		frmModellanalys.getContentPane().add(lblLocationOfResult);
		
		txtDesignatedFolder = new JTextField();
		txtDesignatedFolder.setBounds(22, 36, 402, 20);
		frmModellanalys.getContentPane().add(txtDesignatedFolder);
		txtDesignatedFolder.setEditable(false);
		//Predefined location of result file 
		String designatedFolder = System.getProperty("user.home") + "\\Desktop";
		txtDesignatedFolder.setText(designatedFolder);
		txtDesignatedFolder.setColumns(10);
		
		JButton btnDesignatedFolder = new JButton("Browse");
		btnDesignatedFolder.setBounds(439, 35, 89, 23);
		frmModellanalys.getContentPane().add(btnDesignatedFolder);
		
		//Button that when pressed exports the content of all .rux-files in the selected directories
		JButton btnExportRux = new JButton("Rules");
		btnExportRux.setBounds(303, 328, 121, 23);
		frmModellanalys.getContentPane().add(btnExportRux);
		
		txtDexample = new JTextField();
		txtDexample.addActionListener(new ActionListener() {
			//Action for pressing ENTER to add element for analysis
			public void actionPerformed(ActionEvent e) {
				File tmpDirModel = new File(txtDexample.getText());
				boolean existsModel = tmpDirModel.exists();
				if (existsModel) {
					String name = txtDexample.getText();
			        if (name != null && !"".equals(name)) {
			        	//Add element to JList/dlmModel
			            dlmModel.addElement(name);
			        } else {
			        	//If there isn't anything in txtDexample-textfield display a error message
			            JOptionPane.showMessageDialog(null, "Please fill in a directory", "Error", JOptionPane.ERROR_MESSAGE);
			        }	           
				}
				else {
					JOptionPane.showMessageDialog(null, "Directory doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		txtDexample.setText("D:\\example");
		txtDexample.setColumns(10);
		txtDexample.setBounds(21, 92, 403, 20);
		frmModellanalys.getContentPane().add(txtDexample);
		
		JLabel lblChooseDirectoriesTo = new JLabel("Choose directories to compare:");
		lblChooseDirectoriesTo.setBounds(21, 67, 252, 14);
		frmModellanalys.getContentPane().add(lblChooseDirectoriesTo);
		
		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JFileChooserModels();
			}
		});
		button.setBounds(439, 92, 89, 23);
		frmModellanalys.getContentPane().add(button);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(21, 157, 403, 139);
		frmModellanalys.getContentPane().add(scrollPane);
		
		JList<String> list = new JList<String>(dlmModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		//Remove selected item i JList when pressing DELETE
		list.addKeyListener(new KeyListener() {
					@Override
					public void keyTyped(KeyEvent e) { }

					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_DELETE) {
		                    dlmModel.remove(list.getSelectedIndex());
		                }
					}
					@Override
					public void keyReleased(KeyEvent e) { }
				});
				
		JButton btnDown = new JButton("Add");
		btnDown.addActionListener(new ActionListener() {
			//Action for pressing ADD to add element for analysis
			public void actionPerformed(ActionEvent e) {
				File tmpDirModel = new File(txtDexample.getText());
				boolean existsModel = tmpDirModel.exists();
				if (existsModel) {
					String name = txtDexample.getText();
			        if (name != null && !"".equals(name)) {
			        	//Add element to JList/dlmModel
			            dlmModel.addElement(name);
			        } else {
			        	//If there isn't anything in txtDexample-textfield display a error message
			            JOptionPane.showMessageDialog(null, "Please fill in a directory", "Error", JOptionPane.ERROR_MESSAGE);
			        }           
				}
				else {
					JOptionPane.showMessageDialog(null, "Directory doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		btnDown.setBounds(53, 123, 89, 23);
		frmModellanalys.getContentPane().add(btnDown);
		
		JButton btnClear = new JButton("Clear all");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlmModel.clear();
				listOfPaths.clear();
			}
		});
		btnClear.setBounds(303, 123, 89, 23);
		frmModellanalys.getContentPane().add(btnClear);
		
		JButton btnCompare = new JButton("Content");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File dir = new File(txtDesignatedFolder.getText());
				if(dir.exists()){
						if(dir.canWrite()){
							//Sending fileNames and their presence in folders to CSV
							try {
								//Saving the content of JList to ArrayList listOfPaths
								for(int i=0; i < list.getModel().getSize(); i++){
									String temp = String.valueOf(list.getModel().getElementAt(i));
									//System.out.println(temp);
									listOfPaths.add(temp);
								}
								String directory = txtDesignatedFolder.getText();
								String message = toCSV.filesINfolderCSV(listOfPaths, directory);
								lblResultMessage.setForeground(Color.BLUE);
								lblResultMessage.setText(message);
								//Clearing the ArrayList listOfPaths
								listOfPaths.clear();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}else {
							lblResultMessage.setForeground(Color.RED);
							lblResultMessage.setText("No write permission for designated folder!");
						}						
					}else {
						lblResultMessage.setForeground(Color.RED);
						lblResultMessage.setText("Designated folder does not exist!");
					}						
			}
		});
		btnCompare.setBounds(21, 328, 120, 23);
		frmModellanalys.getContentPane().add(btnCompare);
		
		JButton btnLastUpdate = new JButton("Last update");
		btnLastUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File dir = new File(txtDesignatedFolder.getText());
				if(dir.exists()){
						if(dir.canWrite()){
							//Sending fileNames and their dates of change to CSV
							try {
								//Saving the content of JList to ArrayList listOfPaths
								for(int i=0; i < list.getModel().getSize(); i++){
									String temp = String.valueOf(list.getModel().getElementAt(i));
									//System.out.println(temp);
									listOfPaths.add(temp);
								}
								String directory = txtDesignatedFolder.getText();
								String message = toCSV.datesOFchangeCSV(listOfPaths, directory);
								lblResultMessage.setForeground(Color.BLUE);
								lblResultMessage.setText(message);
								//Clearing the ArrayList listOfPaths
								listOfPaths.clear();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}else {
							lblResultMessage.setForeground(Color.RED);
							lblResultMessage.setText("No write permission for designated folder!");
						}						
					}else {
						lblResultMessage.setForeground(Color.RED);
						lblResultMessage.setText("Designated folder does not exist!");
					}														
			}
		});
		btnLastUpdate.setBounds(161, 328, 120, 23);
		frmModellanalys.getContentPane().add(btnLastUpdate);
		
		JButton btnUp = new JButton("Remove");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if(index != -1) {
					dlmModel.remove(index);
				}
			}
		});
		btnUp.setBounds(171, 123, 89, 23);
		frmModellanalys.getContentPane().add(btnUp);
		
		JLabel lblChooseAnalysis = new JLabel("Choose analysis:");
		lblChooseAnalysis.setBounds(21, 307, 168, 14);
		frmModellanalys.getContentPane().add(lblChooseAnalysis);
		btnExportRux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File dir = new File(txtDesignatedFolder.getText());
				if(dir.exists()){
						if(dir.canWrite()){
							//Sending all formulae to CSV
							try {
								//Saving the content of JList to ArrayList listOfPaths
								for(int i=0; i < list.getModel().getSize(); i++){
									String temp = String.valueOf(list.getModel().getElementAt(i));
									//System.out.println(temp);
									listOfPaths.add(temp);
								}
								String directory = txtDesignatedFolder.getText();
								String message = toCSV.formulaeCSV(listOfPaths, directory);
								lblResultMessage.setForeground(Color.BLUE);
								lblResultMessage.setText(message);
								//Clearing the ArrayList listOfPaths
								listOfPaths.clear();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}else {
							lblResultMessage.setForeground(Color.RED);
							lblResultMessage.setText("No write permission for designated folder!");
						}						
					}else {
						lblResultMessage.setForeground(Color.RED);
						lblResultMessage.setText("Designated folder does not exist!");
					}		
			}
		});
		btnDesignatedFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JFileChooserToDirectory();
			}
		});
	}

	public void setErrorMessage(int i) {	
		//Exception message from class ReadRuxFile
		if(i == 1) {	
			JOptionPane.showMessageDialog(null, "<html><div color=red>Problem with reading a RUX file." , "subject" , JOptionPane.ERROR_MESSAGE);
		}else if(i == 2) {	
			JOptionPane.showMessageDialog(null, "<html><div color=red>Problem with CSV file creation." , "subject" , JOptionPane.ERROR_MESSAGE);
		}else {
			//System.out.println("Another problem");
			JOptionPane.showMessageDialog(null, "<html><div color=red>Unexpected problem!" , "subject" , JOptionPane.ERROR_MESSAGE);
		}
	}
		
	public class JFileChooserModels {
		
		//Browser that opens when "browse"-button is pressed in the model-tab
	    public JFileChooserModels(){
	         
	        JFileChooser jFileChooser = new JFileChooser();
	        
	        //Sets the directory to begin at the folder where user was in the previous time
	        //jFileChooser.setCurrentDirectory(new File(txtDexample.getText()));
	        String previousPath = txtDexample.getText(); //opens the previously chosen folder
	        String folderPath = "";
	        
	        String[] subString;		//Array for parts of String previousPath
			subString = previousPath.split("\\\\");	//Splitting the previousPath
			
			if (subString.length == 1) { //root catalog
				folderPath = previousPath;
			}else if (subString.length > 1) { //deeper
				//"removing" the previously chosen folder from the path
				for(int i = 0; i < (subString.length-1); i++) {
						folderPath += subString[i] + "/";
				}
			}		
			jFileChooser.setCurrentDirectory(new File(folderPath));
	        
	        //Makes it possible to only select directories
	        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
	        
	        jFileChooser.setApproveButtonText("Choose");

	        int result = jFileChooser.showOpenDialog(new JFrame());
	     
	        //Add result to txtDexample-textfield
	        if (result == JFileChooser.APPROVE_OPTION) {
	        	txtDexample.setText(jFileChooser.getSelectedFile().getAbsolutePath());
	        }
	    }
	}
		
	public class JFileChooserToDirectory {
		
		//Browser that opens when "browse"-button is pressed beside "to directory"
	    public JFileChooserToDirectory(){
	         
	        JFileChooser jFileChooser = new JFileChooser();
	        
	        //Sets the directory to begin at "D:\\"
	        jFileChooser.setCurrentDirectory(new File("D:\\"));
	        
	        //Makes it possible to only select directories
	        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        
	        jFileChooser.setApproveButtonText("Choose");
	         
	        int result = jFileChooser.showOpenDialog(new JFrame());
	     
	        //Add result to txtDesignatedFolder-textfield
	        if (result == JFileChooser.APPROVE_OPTION) {
	        	
	            txtDesignatedFolder.setText(jFileChooser.getSelectedFile().getAbsolutePath());
	        }
	    }
	}
}