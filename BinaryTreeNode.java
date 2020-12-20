
/**
 * 
 * @author brevin
 *
 * This class represents individual nodes in the tree and sets their values
 */
public class BinaryTreeNode {

	int key;
	String name;
	BinaryTreeNode leftChild;
	BinaryTreeNode rightChild;
	
	
	public BinaryTreeNode(int key, String name) {
		
		setData(key, name);
	
	}
	
	
	public void setData(int key, String name) {
		
		this.key = key;
		this.name = name;
		leftChild = rightChild = null;
		
	}
	
	
	public String toString() {
		
		return name;
	}
	
}