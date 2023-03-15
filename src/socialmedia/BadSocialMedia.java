package socialmedia;

import java.util.*;
import java.io.IOException;

/**
 * BadSocialMedia is a minimally compiling, but non-functioning implementor of
 * the SocialMediaPlatform interface.
 * 
 * @author Diogo Pacheco
 * @version 1.0 */
public class BadSocialMedia implements SocialMediaPlatform {
	
	// attributes
	private ArrayList<Account> listOfAccounts = new ArrayList<>();
	private ArrayList<Post> listOfPosts = new ArrayList<>();
	private ArrayList<Post> listOfEmptyPosts = new ArrayList<>();
	private int idAccount = 0;
	private int idPost = 0;
	// 0 - original, 1 - comments, 2 - endorsements
	private int[] numberOfPosts = new int[]{0,0,0};
	
	// getters
	public ArrayList<Account> getAccounts() {
		return listOfAccounts;
	}
	
	public ArrayList<Post> getPosts() {
		return listOfPosts;
	}
	
	public ArrayList<Post> getEmptyPosts() {
		return listOfEmptyPosts;
	}
	
	public int getIDAccount() {
		return idAccount;
	}
	
	public int getIDPost() {
		return idPost;
	}
	
	// methods 
	
	/**
	 * Creates a new account instance with given handle
	 * Adds it to listOfAccounts
	 * Increments idAccount by 1
	 */
	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		
		// Check if the handle already exists
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)) {
				throw new IllegalHandleException();
			}
		}
		
		// Check if the handle is valid
		if (handle.isEmpty() || handle.length() > 30 || handle.matches(".*\\s.*")){
			throw new InvalidHandleException();
		}
		
		// Create a new account object 
		Account acc = new Account(handle, idAccount);
		listOfAccounts.add(acc);
		return idAccount++;
		
	}

	/**
	 * Creates a new account instance with the given handle and description
	 * Adds it to listOfAccounts
	 * Increments idAccount by 1
	 */
	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		
		// Check if the handle already exists
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)) {
				throw new IllegalHandleException();
			}
		}
		
		// Check if the handle is valid
		if (handle.isEmpty() || handle.length() > 30 || handle.matches(".*\\s.*")){
			throw new InvalidHandleException();
		}
		
		// Create a new account object 
		Account acc = new Account(handle, idAccount, description);
		listOfAccounts.add(acc);
		return idAccount++;
		
	}
	
	/**
	* Removes an account with the given ID from listOfAccounts
	*
	*/
	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		
		// Need to remove all posts/endorsements
		// Finds the account with the given id
		int counter = 0;
		boolean isThere = false;
		for (Account acc : listOfAccounts) {
			if (acc.getID() == id) {
				
				//
				HashMap<String, ArrayList<Integer>> storage = new HashMap<>();
				storage = acc.getAccountStorage();
				ArrayList<Integer> origposts = new ArrayList<Integer>();
				ArrayList<Integer> commposts = new ArrayList<Integer>();
				ArrayList<Integer> endorposts = new ArrayList<Integer>();
				origposts = storage.get("original");
				commposts = storage.get("comments");
				endorposts = storage.get("endorsements");
				ArrayList<Integer> listOfPostsToDelete = new ArrayList<>();

				for (int post : origposts) {
					listOfPostsToDelete.add(post);
				}

				for (int post : commposts) {
					listOfPostsToDelete.add(post);
				}
			
				for (int post : endorposts) {
					listOfPostsToDelete.add(post);
				}
				
				
				for (int i : listOfPostsToDelete) {
					
					try {
					deletePost(i);
					}
					catch(PostIDNotRecognisedException e){}
				}
				
				
				listOfAccounts.remove(counter);
				isThere = true;
				break;
			}
			counter++;
		}
		
		// If the id doesn't exist
		if (!isThere){
			throw new AccountIDNotRecognisedException();
		}
		
		
		
		
		
	}
	
	/**
	* Removes an account with the given handle from listOfAccounts
	*
	*/
	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		
		// Need to remove all posts/endorsements
		// Finds the account with the given handle
		int counter = 0;
		boolean isThere = false;
		for (Account i : listOfAccounts) {
			if (i.getHandle() == handle) {
				listOfAccounts.remove(counter);
				isThere = true;
				break;
			}
			counter++;
		}
		// If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
	}
	
	/**
	* Replaces an accounts oldHandle with newHandle
	*/
	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		
		// Check if newHandle already exists 
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(newHandle)) {
				throw new IllegalHandleException();
			}
		}
		
		// Check if new handle is valid
		if (newHandle.isEmpty() || newHandle.length() > 30 || newHandle.matches(".*\\s.*")){
			throw new InvalidHandleException();
		}
			
		// Find the account that matches oldHandle
		boolean isThere = false;
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(oldHandle)){
				i.setHandle(newHandle);
				isThere = true;
				break;
			}
			
		}
		
		// If the oldHandle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		
		// Find the account with the given handle
		boolean isThere = false;
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)){
				i.setDescription(description);
				isThere = true;
				break;
			}
		}
		
		// If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}	

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		
		// Find the account with the given handle
		boolean isThere = false;
		int index=0;
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)){
				isThere = true;
				break;
			}
			index++;
		}
		
		// If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		// Getting the posts made by the account
		int sumOfEndors = 0;
		HashMap<String, ArrayList<Integer>> storageOfPostsFromAccount = listOfAccounts.get(index).getAccountStorage();
		
		// Iterate over original
		for (int eachID : storageOfPostsFromAccount.get("original")){
			for (Post i : listOfPosts) {
				if (i.getNumIdentifier() == eachID) {
					HashMap<String, ArrayList<Integer>> storageOfPostsFromPost = i.getPostStorage();
					sumOfEndors+=storageOfPostsFromPost.get("endorsements").size();
				}
			}
		}
		
		// Iterate over comments
		for (int eachID : storageOfPostsFromAccount.get("comments")){
			for (Post i : listOfPosts) {
				if (i.getNumIdentifier() == eachID) {
					HashMap<String, ArrayList<Integer>> storageOfPostsFromPost = i.getPostStorage();
					sumOfEndors+=storageOfPostsFromPost.get("endorsements").size();
				}
			}
		}
		
		// Find each post that the account's made and count its endorsements	
		int sumOfPosts = storageOfPostsFromAccount.get("original").size()+storageOfPostsFromAccount.get("comments").size()+storageOfPostsFromAccount.get("endorsements").size();
		String toReturn = String.format("ID: %d%n"+
										"Handle: %s%n"+
										"Description: %s%n"+
										"Post count: %d%n"+
										"Endorse count: %d", listOfAccounts.get(index).getID(), listOfAccounts.get(index).getHandle(), listOfAccounts.get(index).getDescription(), sumOfPosts, sumOfEndors);
		return toReturn;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		
		// Check if the message is valid
		if (message.isEmpty() || message.length() > 100){
			throw new InvalidPostException();
		}
		
		// Find the account with the given handle and create the post
		boolean isThere = false;
		int counter = 0;
		for (Account i : listOfAccounts) {
			if (i.getHandle() == handle) {
				listOfAccounts.get(counter).addToAccountStorageOriginal(idPost);
				Post post = new Post(idPost, message);
				listOfPosts.add(post);
				isThere = true;
				break;
			}
			counter++;
		}
		
		// If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		return idPost++;
	}

	@Override
	public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		
		// Find the post with the given id
		boolean postIsThere = false;
		int postCounter = 0;
		for (Post i : listOfPosts) {
			if (i.getNumIdentifier() == id) {
				postIsThere = true;
				if (listOfPosts.get(postCounter).getMessage() == null){
					throw new NotActionablePostException();
				}
				listOfPosts.get(postCounter).addToPostStorageEndors(idPost);
				break;
			}
			postCounter++;
		}
		
		// If the post doesn't exist
		if (!postIsThere){
			throw new PostIDNotRecognisedException();
		}
		
		// Find the account with the given handle
		boolean isThere = false;
		int counter = 0;
		for (Account i : listOfAccounts) {
			if (i.getHandle() == handle) {
				listOfAccounts.get(counter).addToAccountStorageEndors(idPost);
				Post post = new Post(idPost, id);
				listOfPosts.add(post);
				isThere = true;
				break;
			}
			counter++;
		}
		
		// If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		return idPost++;	

	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

		// Check if the message is valid
		if (message.isEmpty() || message.length() > 100){
			throw new InvalidPostException();
		}
		
		// Find the post with the given id
		boolean postIsThere = false;
		int postCounter = 0;
		for (Post i : listOfPosts) {
			if (i.getNumIdentifier() == id) {
				postIsThere = true;
				if (listOfPosts.get(postCounter).getMessage() == null){
					System.out.println("Comment exception");
					throw new NotActionablePostException();
				}
				listOfPosts.get(postCounter).addToPostStorageComment(idPost);
				break;
			}
			postCounter++;
		}
				
		// If the post doesn't exist
		if (!postIsThere){
			throw new PostIDNotRecognisedException();
		}
				
		// Find the account with the given handle and create the comment
		boolean isThere = false;
		int counter = 0;
		for (Account i : listOfAccounts) {
			if (i.getHandle() == handle) {
				listOfAccounts.get(counter).addToAccountStorageComment(idPost);
				Post post = new Post(idPost, message,id);
				listOfPosts.add(post);
				isThere = true;
				break;
			}
			counter++;
		}
		
		// If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		return idPost++;	

	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		
		// Find the post with the given id
		boolean postIsThere = false;
		int postCounter = 0;
		for (Post i : listOfPosts) {
			List<Integer> counters = new ArrayList<>();
			if (i.getNumIdentifier() == id) {
				postIsThere = true;
				Post post = new Post(id);
				listOfEmptyPosts.add(post);
				
				//If comm or endors, remove it from the post it was done on
				
				boolean isOriginal = false;
				if (i.getPointerToOriginal() == null) {
					isOriginal = true;
				}
				if (!isOriginal) {
					for (Post postmain : listOfPosts) {
						if (postmain.getNumIdentifier() == i.getPointerToOriginal()) {
							HashMap<String, ArrayList<Integer>> postMainStorage = postmain.getPostStorage;
							postMainStorage.get("comments").removeAll(List.of(id));
							postMainStorage.get("endorsements").removeAll(List.of(id));
							
						}
					}
				}
				
				
				
				// Remove all its endorsements
				HashMap<String, ArrayList<Integer>> storagePosts = listOfPosts.get(postCounter).getPostStorage();
				ArrayList<Integer> value = new ArrayList<Integer>();
				value = storagePosts.get("endorsements");
				
				// Copy of the storage with postIDs of endorsements to delete from Account storage
				ArrayList<Integer> storageOfEndors =  new ArrayList<Integer>();
				for (int val : value){
					storageOfEndors.add(val);
				}
				
				// Deleting all the endorsements from listOfPosts
				// do after iterating over endorsements?
				value.clear();			
				
				// Remove from Account's storage
				for(Account acc : listOfAccounts){
					HashMap<String, ArrayList<Integer>> storage = new HashMap<>();
					storage = acc.getAccountStorage();
					
					// Iterate over original
					boolean deleted = false;
					int counter = 0;
					for (int orig : storage.get("original")){
							if (orig == id){
								storage.get("original").remove(counter);
								deleted = true;
								break;
							}
							counter++;
					}
					
					// Iterate over comments
					if (!deleted){
						counter = 0;
						for (int comm : storage.get("comments")){
							if (comm == id){
								storage.get("comments").remove(counter);
								break;
							}
							counter++;
						}
					}
					
					// Iterate over endorsements
					// could we have just done value.clear after this and used storage.get("endorsements")?
					for (int endor : storage.get("endorsements")){
						for (int idEndors : storageOfEndors){
							if (endor == idEndors){
								counters.add(endor);
								break;
							}
						}
					}
					storage.get("endorsements").removeAll(counters);
				}

				// Remove from the whole listOfPosts (global)
				listOfPosts.remove(listOfPosts.get(postCounter));
				
				// also need to remove endorsemetns from ListOfPosts
				int counter = 0;
				ArrayList<Post> indexes = new ArrayList<>();
				
				// Finds the indexes of the endorsements to be removed
				for (Post a : listOfPosts){
					for (int idInCounters : counters){
						if (a.getNumIdentifier() == idInCounters){
							indexes.add(a);
							break;
						}
					}
					counter++; 						
				}			
				listOfPosts.removeAll(indexes);			
				break;
			}
			postCounter++;
		}
		
		// If the post doesn't exist
		if (!postIsThere){
			throw new PostIDNotRecognisedException();
		}	
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		
		// Finding the account with the post
		boolean postIsThere = false;
		String handle = "";
		for (Account acc : listOfAccounts){
			HashMap<String, ArrayList<Integer>> storage = new HashMap<>();
			storage = acc.getAccountStorage();
			
			// Search the original
			for (int orig : storage.get("original")){
				if (orig == id){
					handle = acc.getHandle();
					postIsThere = true;
					break;
				}
			}
			
			// Search the comments
			for (int comm : storage.get("comments")){
				if (comm == id){
					handle = acc.getHandle();
					postIsThere = true;
					break;
				}
			}
			
			// Search the endorsements
			for (int endor : storage.get("endorsements")){
				if (endor == id){
					handle = acc.getHandle();
					postIsThere = true;
					break;
				}
			}
		}
		
		// If the post doesn't exist
		if (!postIsThere){
				throw new PostIDNotRecognisedException();
		}
		
		// Finds the index of the post in listOfPosts
		int indexNeededPost=0;
		for (Post post : listOfPosts){
			if (post.getNumIdentifier() == id){
				indexNeededPost = listOfPosts.indexOf(post);
				break;
			}
		}
		
		// Gets the number of endorsements the post has received
		ArrayList<Integer> postStorageEnd = new ArrayList<>();
		postStorageEnd = listOfPosts.get(indexNeededPost).getPostStorage().get("endorsements");
		int noEndor = postStorageEnd.size();
		
		// Gets the number of comments the post has received
		ArrayList<Integer> postStorageComm = new ArrayList<>();
		postStorageComm = listOfPosts.get(indexNeededPost).getPostStorage().get("comments");
		int noComm = postStorageComm.size();
		
		// Gets the message of the post
		String message = listOfPosts.get(indexNeededPost).getMessage();
		
		return String.format("ID: %d%n Account: %s%n No.endorsements: %d|No.comments: %d%n %s", id, handle, noEndor, noComm, message);
	}

	@Override
	public StringBuilder showPostChildrenDetails(int id)
			throws PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfAccounts() {
		// TODO Auto-generated method stub
		return listOfAccounts.size();
	}

	@Override
	public int getTotalOriginalPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalEndorsmentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCommentPosts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedPost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMostEndorsedAccount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void erasePlatform() {
		// TODO Auto-generated method stub

	}

	@Override
	public void savePlatform(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadPlatform(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
