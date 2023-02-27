import java.*;

class Account{

	// empty hash map which will be passed to the post constructor
	HashMap<String, ArrayList<Integer>> storage = new HashMap<String, ArrayList<Integer>>();
	
	// constructor
	public Account(String handle,int id, String description, storage){
		this.handle = handle;
		this.id = id;
		this.description = description;
		this.storage = storage;
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