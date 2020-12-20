import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

/**
 * 
 * @author brevin
 * This class is the "main" class and also how the tree is interacted with.
 * This class sets up the GUI and controls what happens when the GUI is interacted with
 */


@SuppressWarnings("serial")
public class DataBaseAccess extends JFrame {
	
	public static JTextField txtAddNode;
	private JTextField txtRemoveNode;
	private JTextField txtFindNode;
	public int getNodeCharacter;
	public static String filePath;
	public static String removeNode;
	static BinaryTreeNode focusNode;
	public static String findNode;
	
	static BinaryTree theTree = new BinaryTree();
	
	
	public DataBaseAccess() throws FileNotFoundException {
		
		setSize(634, 530);
		getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(6, 6, 622, 496);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		txtAddNode = new JTextField();
		txtAddNode.setText("Enter Random Number");
		txtAddNode.setBounds(6, 108, 130, 26);
		mainPanel.add(txtAddNode);
		txtAddNode.setColumns(10);
		
		
		JPanel treeViewPanel = new JPanel();
		treeViewPanel.setBounds(284, 38, 332, 452);
		mainPanel.add(treeViewPanel);
		
		JTextArea printFileHere = new JTextArea(25, 25);
		treeViewPanel.add(printFileHere);
		
		
		JButton btnAddNode = new JButton("Add Node");
		btnAddNode.setBounds(148, 108, 117, 29);
		mainPanel.add(btnAddNode);
		btnAddNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FavoriteCharacter.getCharacter();
				addEntry();
				updateTextField(printFileHere);
				txtAddNode.setText("");
				
		}
		});
		
		txtRemoveNode = new JTextField();
		txtRemoveNode.setText("Remove Node");
		txtRemoveNode.setBounds(6, 146, 130, 26);
		mainPanel.add(txtRemoveNode);
		txtRemoveNode.setColumns(10);
		
		JButton btnRemoveNode = new JButton("Remove Node");
		btnRemoveNode.setBounds(148, 146, 117, 29);
		mainPanel.add(btnRemoveNode);
		btnRemoveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				removeEntry();
				updateTextField(printFileHere);
				txtRemoveNode.setText("");
				
			}
		});
		
		txtFindNode = new JTextField();
		txtFindNode.setText("Find Node");
		txtFindNode.setBounds(6, 184, 130, 26);
		mainPanel.add(txtFindNode);
		txtFindNode.setColumns(10);
		
		JButton btnFindNode = new JButton("Find Node");
		btnFindNode.setBounds(148, 184, 117, 29);
		mainPanel.add(btnFindNode);
		btnFindNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				findEntry();
				txtFindNode.setText("");
			}
		});
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(6, 0, 610, 26);
		mainPanel.add(toolBar);
		
		JButton btnLoad = new JButton("Load");
		toolBar.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					loadDatabaseFromFile();
					
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
					
				}
				updateTextField(printFileHere);
			}
		});
		
		JButton btnSave = new JButton("Save");
		toolBar.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						
						saveDatabaseToFile();
						
					} catch (IOException e1) {
						
						e1.printStackTrace();
						
					}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		toolBar.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		
		
	}

	
	//Update text field whenever a change is made to the tree
	public void updateTextField(JTextArea printFileHere) {
		
		printFileHere.setText(null);
		try {
			
			printFileHere.append("Below is your current Binary Tree" + "\n" + BinaryTree.printTreeDiagram());
		
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
			
		}
	}
	
	
	public void addEntry() {
		
		theTree.addNode(FavoriteCharacter.characterLength, FavoriteCharacter.character);
	
	}
	
	
	public void removeEntry() {
		
		removeNode = txtRemoveNode.getText();
		theTree.removeNodeWithData(removeNode.length(), removeNode);
	
	}
	
	
	public void findEntry() {
		
		findNode = txtFindNode.getText();
		BinaryTree.findNodeWithData(findNode.length(), findNode);
		
	}
	
	
	public static void saveDatabaseToFile() throws FileNotFoundException {
		
			BinaryTree.saveToFile();
			
	}
	
	
	public static void loadDatabaseFromFile() throws FileNotFoundException {
		
		String fileName = filePath;
		File file = new File(fileName);
		Scanner inputFile = new Scanner(file);	
		
		while(inputFile.hasNext()) {
			
			String line = inputFile.nextLine();
			theTree.addNode(line.length(), line);
			
		}
		
		inputFile.close();
		
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		DataBaseAccess dba = new DataBaseAccess();
		
		dba.setVisible(true);
		dba.setTitle("Binary Tree Implementaiton");

		filePath = "src/StarWarsCharacters.txt";
		
	}
}
