package socialmedia;
import java.util.*;
import java.io.Serializable;



/**
 *
 */
public class Post implements Serializable{
	
	// Attributes
	private int numIdentifier;
	private String message;
	private int pointerToOriginal;
	private HashMap<String, ArrayList<Integer>> storage ;
	
	// Getter methods
	
	/**
	 * @return numIdentifier ID of post
	 */
	public int getNumIdentifier(){
		return numIdentifier;
	}
	
	/**
	 * @return message of post
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * @return pointerToOriginal ID of post parent
	 */
	public int getPointerToOriginal(){
		return pointerToOriginal;
	}
	
	/**
	 * @return storage of post comment and endorsement IDs
	 */
	public HashMap<String, ArrayList<Integer>> getPostStorage(){
		return storage;
	}
	
	// Setter methods
	
	/**
	* @param idPost id of post being added to comment storage
	 */
	public void addToPostStorageComment(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("comments");
		value.add(idPost);
		storage.put("comments", value);	
	}
	
	/**
	* @param idPost id of post being added to endorsement storage
	 */
	public void addToPostStorageEndors(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("endorsements");
		value.add(idPost);
		storage.put("endorsements", value);
	}
	
	/**
	* @param message post message
	 */
	public void setMessage(String message){
		this.message = message;
	}
	
	/**
	* Original post constructor
	* @param numIdentifier ID of post
	* @param message post message
	 */ 
	public Post(int numIdentifier,String message){
		this.numIdentifier = numIdentifier;
		this.message = message;
		storage = new HashMap<String, ArrayList<Integer>>();
		pointerToOriginal = -1;
		// endorsements - the no. endorsements the post has
		// comments - the no. comments the post has
		// Arraylist contains the postIDs of these
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	
	/**
	* Comment post constructor
	* @param numIdentifier ID of post
	* @param message post message
	* @param pointerToOriginal ID of parent post
	 */
	public Post(int numIdentifier,String message,int pointerToOriginal){
		this.numIdentifier = numIdentifier;
		this.message = message;
		this.pointerToOriginal = pointerToOriginal;
		storage = new HashMap<String, ArrayList<Integer>>();
		/* endorsements - the no. endorsements the post has
		 comments - the no. comments the post has
		 */
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	
	/**
	* Endorsement post constructor
	* @param numIdentifier ID of post
	* @param pointerToOriginal ID of parent post
	 */ 
	public Post(int numIdentifier,int pointerToOriginal){
		this.numIdentifier = numIdentifier;
		this.pointerToOriginal = pointerToOriginal;
		message = new String();
	}
	
	/**
	* Empty post constructor 
	* @param numIdentifier ID of post
	 */ 
	public Post(int numIdentifier){
		this.numIdentifier = numIdentifier;
		message = "The original content was removed from the system and is no longer available.";
	}
}
