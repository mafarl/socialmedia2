package socialmedia;
import java.util.*;
import java.io.Serializable;

/**
 * Represents an individual account. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */
public class Account implements Serializable{
	
	// Attributes
	private String handle;
	private int id;
	private String description;
	private HashMap<String, ArrayList<Integer>> storage;
	
	// Getter methods
	
	/** Gets account's handle.
	 * @return handle of account
	 */
	public String getHandle(){
		return handle;
	}
	
	/** Gets account's ID.
	 * @return ID of account
	 */
	public int getID(){
		return id;
	}
	
	/** Gets account's description.
	 * @return description of account
	 */
	public String getDescription(){
		return description;
	}
	
	/** Gets account's storage of posts.
	 * @return storage of account
	 */
	public HashMap<String, ArrayList<Integer>> getAccountStorage(){
		return storage;
	}

	// Setter methods
	
	/** Sets account's handle.
	 * @param handle handle of account
	 */
	public void setHandle(String handle){
		this.handle = handle;
	}
	
	/** Sets account's description.
	 * @param description description of account
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/** Adds original post ID to the account's storage of posts.
	 * @param idPost ID of post to be added to account original storage
	 */
	public void addToAccountStorageOriginal(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("original");
		value.add(idPost);
		storage.put("original", value);
	}
	
	/** Adds comment post ID to the account's storage of posts.
	 * @param idPost ID of post to be added to account comment storage
	 */
	public void addToAccountStorageComment(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("comments");
		value.add(idPost);
		storage.put("comments", value);
	}
	
	/** Adds endorsement post ID to the account's storage of posts.
	 * @param idPost ID of post to be added to account endorsement storage
	 */
	public void addToAccountStorageEndors(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("endorsements");
		value.add(idPost);
		storage.put("endorsements", value);
	}
	
	// Constructors
	
	/** Creates account with a specified handle.
	 * @param handle handle of account
	 * @param id ID of account
	 */
	public Account(String handle, int id){
		this.handle = handle;
		this.id = id;
		description="";
		storage = new HashMap<String, ArrayList<Integer>>();
		/* 
		 * original - posts that account made 
		 * comments - comments that account made
		 * endorsements - account made
		 * ArrayList contains post IDs
		 */
		storage.put("original", new ArrayList<Integer>());
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	
	/** Creates account with a specified handle and description.
	 * @param handle handle of account
	 * @param id ID of account
	 * @param description description of account
	 */
	public Account(String handle,int id, String description){
		this.handle = handle;
		this.id = id;
		this.description = description;
		storage = new HashMap<String, ArrayList<Integer>>();
		/* 
		 * original - posts that account made 
		 * comments - comments that account made
		 * endorsements - account made
		 * ArrayList contains post IDs
		 */
		storage.put("original", new ArrayList<Integer>());
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
}