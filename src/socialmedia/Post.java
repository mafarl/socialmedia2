package socialmedia;
import java.util.*;

public class Post{
	
	// attributes
	private int numIdentifier;
	private String message;
	private int pointerToOriginal;
	private HashMap<String, ArrayList<Integer>> storage ;
	
	// getter methods
	public int getNumIdentifier(){
		return numIdentifier;
	}
	public String getMessage(){
		return message;
	}
	public int getPointerToOriginal(){
		return pointerToOriginal;
	}
	public HashMap<String, ArrayList<Integer>> getPostStorage(){
		return storage;
	}
	
	//setter methods
	public void addToPostStorageComment(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("comments");
		value.add(idPost);
		storage.put("comments", value);
		
	}
	public void addToPostStorageEndors(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("endorsements");
		value.add(idPost);
		storage.put("endorsements", value);
		
	}
	
	// constructor
	// original
	// should have an array to store all the comments and endorsements
	public Post(int numIdentifier,String message){
		this.numIdentifier = numIdentifier;
		this.message = message;
		storage = new HashMap<String, ArrayList<Integer>>();
		// endorsements - the no. endorsements the post has
		// comments - the no. comments the post has
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	//comment
	public Post(int numIdentifier,String message,int pointerToOriginal){
		this.numIdentifier = numIdentifier;
		this.message = message;
		this.pointerToOriginal = pointerToOriginal;
		storage = new HashMap<String, ArrayList<Integer>>();
		// endorsements - the no. endorsements the post has
		// comments - the no. comments the post has
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	
	
	// endorsement
	public Post(int numIdentifier,int pointerToOriginal){
		this.numIdentifier = numIdentifier;
		this.pointerToOriginal = pointerToOriginal;
	}
	
	//empty 
	public Post(int numIdentifier){
		this.numIdentifier = numIdentifier;
		message = "The original content was removed from the system and is no longer available.";
	}

}
