import java.*;

class Post{
	
	// empty hash map which will be passed to the post constructor
	HashMap<String, ArrayList<Integer>> storage = new HashMap<String, ArrayList<Integer>>();
	
	// constructor
	// original
	// should have an array to store all the comments and endorsements
	public Post(int numIdentifier,String message,int accountID, HashMap storage){
		this.numIdentifier = numIdentifier;
		this.message = message;
		this.accountID = accountID;
		this.storage = storage;
		// endorsements - the no. endorsements the post has
		// comments - the no. comments the post has
		storage.put("comments", new ArrayList<Integer>());
		storage.put("endorsements", new ArrayList<Integer>());
		/*
		arrayOfPosts = {endorsements: [id],
						comments: [id]} */
	}
	
	// comment
	// should have an array to store all the comments and endorsements
	public Post(int numIdentifier,String message, int pointerToOriginal, HashMap storage){
		this.numIdentifier = numIdentifier;
		this.message = message;
		this.pointerToOriginal = pointerToOriginal;
		this.storage = storage;
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