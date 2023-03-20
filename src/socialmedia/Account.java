package socialmedia;
import java.util.*;
import java.io.Serializable;

public class Account implements Serializable{
	
	// attributes
	private String handle;
	private int id;
	private String description;
	private HashMap<String, ArrayList<Integer>> storage;
	
	// getter methods
	public String getHandle(){
		return handle;
	}
	public int getID(){
		return id;
	}
	public String getDescription(){
		return description;
	}
	public HashMap<String, ArrayList<Integer>> getAccountStorage(){
		return storage;
	}

	// setter methods
	public void setHandle(String handle){
		this.handle = handle;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void addToAccountStorageOriginal(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("original");
		value.add(idPost);
		storage.put("original", value);
		
	}
	public void addToAccountStorageComment(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("comments");
		value.add(idPost);
		storage.put("comments", value);
		
	}
	public void addToAccountStorageEndors(int idPost){
		ArrayList<Integer> value = new ArrayList<>();
		value = storage.get("endorsements");
		value.add(idPost);
		storage.put("endorsements", value);
		
	}
	
	// constructor
	public Account(String handle, int id){
		this.handle = handle;
		this.id = id;
		description="";
		storage = new HashMap<String, ArrayList<Integer>>();
		storage.put("original", new ArrayList<Integer>());
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	
	public Account(String handle,int id, String description){
		this.handle = handle;
		this.id = id;
		this.description = description;
		storage = new HashMap<String, ArrayList<Integer>>();
		/* original - posts that account made 
		   comments - comments that account made
		   endorsements - account made
		*/
		storage.put("original", new ArrayList<Integer>());
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
	}
	
	/* If we want to find the most popular acccount (most no. endorsements), then 
	We loop through all the posts (original, comments) the account has made (stored as their id's in the storage array),
	and count the number of endorsements for each of the post in the array and add to some sumVariable */
	
}