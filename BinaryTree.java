import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author Brevin Blalock
 *	This class is the binary tree for the database, it communicates with the database class and the 
 *	bnarytreenode class to add nodes to the tree, delete slected nodes, find selected nodes and 
 *	print and traverse the tree.
 *
 */
public class BinaryTree {
	
	static BinaryTreeNode root;
	static BinaryTreeNode temp = root;
	static final int count = 10;
	
	
	public void addNode(int key, String name) {
		
		BinaryTreeNode newNode = new BinaryTreeNode(key, name);
		
		if(root == null) {
			
			root = newNode;
			
		} else {
			
			BinaryTreeNode focusNode = root;
			BinaryTreeNode parent;
			
			while(true) {
				
				parent = focusNode;
				
				if(key < focusNode.key) {
					
					focusNode = focusNode.leftChild;
					
					if(focusNode == null) {
						
						parent.leftChild = newNode;
						return;
						
					}
					
				} else {
					
					focusNode = focusNode.rightChild;
					
					if(focusNode == null) {
						
						parent.rightChild = newNode;
						return;
						
					}
				}
			}
		}
		
	}
	
	
	public BinaryTreeNode removeNodeWithData(int key, String name) {
		
		BinaryTreeNode focusNode = root;
		BinaryTreeNode parent = root;
		
		boolean isItALeftChild = true;
		
		
		//Search through binary tree to find node that matches the delete node request
		while(focusNode.name.compareTo(name) != 0) {
			
			parent = focusNode;
			
			if(key < focusNode.key) {
				
				isItALeftChild = true;
				
				focusNode = focusNode.leftChild;
				
			} else {
				
				isItALeftChild = false;
				
				focusNode = focusNode.rightChild;
			
			}
			
			if(focusNode == null) {
				
				JOptionPane.showMessageDialog(null, "Node is not in tree");
				return focusNode;
				
			}
		}
		
		
		if(focusNode.leftChild == null && focusNode.rightChild == null) {
			
			if(focusNode == root) {
				
				root = null;
				
			} else if(isItALeftChild) {
				
				parent.leftChild = null;
				
			} else {
				
				parent.rightChild = null;
				
			}
			
		} else if(focusNode.rightChild == null) {
			
			if(focusNode == root) {
				
				root = focusNode.leftChild;
				
			} else if(isItALeftChild) {
				
				parent.leftChild = focusNode.leftChild;
				
			} else {
				
				parent.rightChild = focusNode.leftChild;
				
			}
			
		} else if(focusNode.leftChild == null) {
			
			if(focusNode == root) {
				
				root = focusNode.rightChild;
				
			} else if(isItALeftChild) {
				
				parent.leftChild = focusNode.rightChild;
				
			} else {
				
				parent.rightChild = focusNode.leftChild;
				
			}
			
		} else {
			
			BinaryTreeNode replacement = getReplacementNode(focusNode);
			
			if(focusNode == root) {
				
				root = replacement;
				
			} else if(isItALeftChild) {
				
				parent.leftChild = replacement;
				
			} else {
				
				parent.rightChild = replacement;
				
			}
				
			replacement.leftChild = focusNode.leftChild;
			
		}
		
		return null;
	}
	
	
	//get the node to replace the deleted node in tree
	public BinaryTreeNode getReplacementNode(BinaryTreeNode replacedNode) {
		
		BinaryTreeNode replacementParent = replacedNode;
		BinaryTreeNode replacement = replacedNode;
		
		BinaryTreeNode focusNode = replacedNode.rightChild;
		
		while(focusNode != null) {
			
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
			
		}
		
		if(replacement != replacedNode.rightChild) {
			
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
			
		}
		
		return replacement;
	}
	
	
	public static BinaryTreeNode binarySearch(BinaryTreeNode root, int key) {
		
		//Root is null or key is at root
		if(root == null || root.key == key) {
			
			return root;
			
		}
		
		//key s greater than root
		if(root.key < key) {
			
			return binarySearch(root.rightChild, key);
			
		} else {
			
			//key is smaller than root
			return binarySearch(root.leftChild, key);
			
		}
	}
	
	
	//Find if node is in the tree
	public static String findNodeWithData(int key, String name) {
		
		BinaryTreeNode focusNode = root;
		
		while(focusNode.name.compareTo(name) != 0) {
			
			if(key < focusNode.key) {
				
				focusNode = focusNode.leftChild;
				
			} else {
				
				focusNode = focusNode.rightChild;
			
			}
			
			if(focusNode == null) {
				
				JOptionPane.showMessageDialog(null, "Node is not in tree");
				return "";
				
			} 

		}
		
		if(focusNode.name.compareTo(name) == 0) {
			
			JOptionPane.showMessageDialog(null, name + " is in the Binary Tree");
			
		} else {
			
			return "";
			
		}
		
		return "";
	}
	
	
	public static String printTreeDiagram() throws FileNotFoundException {
		
		PrintWriter outPutFile = new PrintWriter("src/OutputFile.txt");
		outPutFile.print("");
		
		StringBuilder sb = new StringBuilder();
		traversePreOrderForPrint(sb, "", "", root);

		outPutFile.println(sb.toString());
		outPutFile.close();
		
		return sb.toString();
		
	}
	
	
	public static String saveToFile() throws FileNotFoundException {
		
		PrintWriter outputFile = new PrintWriter("src/StarWarsCharacters.txt");
		outputFile.print("");
		
		StringBuilder sb = new StringBuilder();
		inOrderTraversalForSave(sb, root);
		
		outputFile.println(sb.toString());
		outputFile.close();
		
		return sb.toString();
		
		
	}
	
	
	public static void inOrderTraversalForSave(StringBuilder sb, BinaryTreeNode focusNode) throws FileNotFoundException {
		
		if(focusNode != null) {
			
			inOrderTraversalForSave(sb, focusNode.leftChild);
			
			sb.append(focusNode.name);
			sb.append("\n");
			
			inOrderTraversalForSave(sb, focusNode.rightChild);
			
		}
		
		
	}
	
	
	public static void traversePreOrderForPrint(StringBuilder sb, String padding, String pointer, BinaryTreeNode root) {
		
		if(root != null) {
			
			sb.append(padding);
			sb.append(pointer);
			sb.append(root.name);
			sb.append("\n");
			
			StringBuilder paddingBuilder = new StringBuilder(padding);
			paddingBuilder.append("|   ");
			
			String paddingForBoth = paddingBuilder.toString();
			String pointerForRight = "└──";
			String pointerForLeft = (root.rightChild != null) ? "├──" : "└──";
			
			traversePreOrderForPrint(sb, paddingForBoth, pointerForLeft, root.leftChild);
	        traversePreOrderForPrint(sb, paddingForBoth, pointerForRight, root.rightChild);
			
		}
	}
	
}