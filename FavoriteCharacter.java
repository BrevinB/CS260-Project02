/**
 * 
 * @author brevin
 * This class represents and sets the data that is used in each node
 */
public class FavoriteCharacter {
	
	public static String character;
	public static int characterLength;

	
	public static String getCharacter() {
		
		character = DataBaseAccess.txtAddNode.getText();
		characterLength = character.length();
		return character;
		
	}
	
	
	public static int getCharacterLength() {
		
		characterLength = character.length();
		return characterLength;
		
	}
}
