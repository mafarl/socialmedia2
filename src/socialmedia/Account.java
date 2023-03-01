import java.util.*;

class Account{
	
	// attributes
	private String handle;
	private int id;
	private String description;
	
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
	
	// setter methods
	public void setHandle(String handle){
		this.handle = handle;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
	// constructor
	public Account(String handle, int id){
		this.handle = handle;
		this.id = id;
		String description;
	}
	
	public Account(String handle,int id, String description){
		this.handle = handle;
		this.id = id;
		this.description = description;
		HashMap<String, ArrayList<Integer>> storage = new HashMap<String, ArrayList<Integer>>();
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
