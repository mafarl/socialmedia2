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
	
	// constructor
	// original and comment
	// should have an array to store all the comments and endorsements
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

}
