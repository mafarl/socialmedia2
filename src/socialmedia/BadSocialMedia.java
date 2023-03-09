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
	 * Adds it to the list of all accounts
	 * Increments idAccount by 1
	 */
	@Override
	public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
		
		//Check if already exists
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)) {
				throw new IllegalHandleException();
			}
		}
		// Check if valid form
		if (handle.isEmpty() || handle.length() > 30 || handle.matches(".*\\s.*")){
			throw new InvalidHandleException();
		}
		
		Account acc = new Account(handle, idAccount);
		listOfAccounts.add(acc);
		
		return idAccount++;
		
	}


	/**
	 * Creates a new account instance with the given handle and description
	 * Adds it to the list of all accounts
	 * Increments idAccount by 1
	 */
	@Override
	public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
		
		//Check if already exists
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)) {
				throw new IllegalHandleException();
			}
		}
		// Check if valid form
		if (handle.isEmpty() || handle.length() > 30 || handle.matches(".*\\s.*")){
			throw new InvalidHandleException();
		}
		
		Account acc = new Account(handle, idAccount, description);
		listOfAccounts.add(acc);
		return idAccount++;
		
	}
	
	/**
	* Removes an account with the given ID 
	* 
	*/
	@Override
	public void removeAccount(int id) throws AccountIDNotRecognisedException {
		// Need to remove all posts/endorsements
		int counter = 0;
		boolean isThere = false;
		for (Account i : listOfAccounts) {
			if (i.getID() == id) {
				listOfAccounts.remove(counter);
				isThere = true;
				break;
			}
			counter++;
		}
		
		if (!isThere){
			throw new AccountIDNotRecognisedException();
		}
	}
	
	/**
	* Removes an account with the given handle
	*
	*/
	@Override
	public void removeAccount(String handle) throws HandleNotRecognisedException {
		// Need to remove all posts/endorsements
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
		
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		//Check if newHandle already exists 
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(newHandle)) {
				throw new IllegalHandleException();
			}
		}
		//Check if new handle is valid
		if (newHandle.isEmpty() || newHandle.length() > 30 || newHandle.matches(".*\\s.*")){
			throw new InvalidHandleException();
		}
			
		//Find the account that matches oldHandle
		boolean isThere = false;
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(oldHandle)){
				i.setHandle(newHandle);
				isThere = true;
				break;
			}
			
		}
		//If the oldHandle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		
		//Find the account that matches oldHandle
		boolean isThere = false;
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)){
				i.setDescription(description);
				isThere = true;
				break;
			}
		}
		//If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}	

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		boolean isThere = false;
		int index=0;
		for (Account i : listOfAccounts) {
			if (i.getHandle().equals(handle)){
				isThere = true;
				break;
			}
			index++;
		}
		//If the handle doesn't exist
		if (!isThere){
			throw new HandleNotRecognisedException();
		}	
		
		int sumOfEndors = 0;
		HashMap<String, ArrayList<Integer>> storageOfPostsFromAccount = listOfAccounts.get(index).getAccountStorage();
		
		//Iterate over original
		for (int eachID : storageOfPostsFromAccount.get("original")){
			for (Post i : listOfPosts) {
				if (i.getNumIdentifier() == eachID) {
					HashMap<String, ArrayList<Integer>> storageOfPostsFromPost = i.getPostStorage();
					sumOfEndors+=storageOfPostsFromPost.get("endorsements").size();
				}
			}
		}
		
		//Iterate over comments
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
		// Check if valid form
		if (message.isEmpty() || message.length() > 100){
			throw new InvalidPostException();
		}
		
		//
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
		
		//
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		return idPost++;
	}

	@Override
	public int endorsePost(String handle, int id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		
		//
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
		//
		if (!postIsThere){
			throw new PostIDNotRecognisedException();
		}
		
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
		
		//
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		return idPost++;	

	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {

		// Check if valid form
		if (message.isEmpty() || message.length() > 100){
			throw new InvalidPostException();
		}
		
		//
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
		//
		if (!postIsThere){
			throw new PostIDNotRecognisedException();
		}
		
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
		
		//
		if (!isThere){
			throw new HandleNotRecognisedException();
		}
		
		return idPost++;	

	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		
		boolean postIsThere = false;
		int postCounter = 0;
		for (Post i : listOfPosts) {
			List<Integer> counters = new ArrayList<>();
			if (i.getNumIdentifier() == id) {
				postIsThere = true;
				Post post = new Post(id);
				listOfEmptyPosts.add(post);
				
				//Remove all its endorsements
				HashMap<String, ArrayList<Integer>> storagePosts = listOfPosts.get(postCounter).getPostStorage();
				ArrayList<Integer> value = new ArrayList<Integer>();
				value = storagePosts.get("endorsements");
				
				//Storage with ids of endorsements to delete them from Account storage (copy)
				ArrayList<Integer> storageOfEndors =  new ArrayList<Integer>();
				for (int val : value){
					storageOfEndors.add(val);
				}
				
				value.clear();			
				
				//Remove from Account's storage
				for(Account acc : listOfAccounts){
					HashMap<String, ArrayList<Integer>> storage = new HashMap<>();
					storage = acc.getAccountStorage();
					
					//Iterate over original
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
					//Iterate over comments
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
					//Iterate over endorsements
					//List<Integer> counters = new ArrayList<>();
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

				//Remove from the whole listOfPosts (global)
				listOfPosts.remove(listOfPosts.get(postCounter));
				
				//also need to remove endorsemetns from ListOfPosts
				int counter = 0;
				ArrayList<Post> indexes = new ArrayList<>();
				
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
		//
		if (!postIsThere){
			throw new PostIDNotRecognisedException();
		}	
	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		boolean postIsThere = false;
		String handle = "";
		for (Account acc : listOfAccounts){
			HashMap<String, ArrayList<Integer>> storage = new HashMap<>();
			storage = acc.getAccountStorage();
			//look in the original
			for (int orig : storage.get("original")){
				if (orig == id){
					handle = acc.getHandle();
					postIsThere = true;
					break;
				}
			}
			//look in the comments
			for (int comm : storage.get("comments")){
				if (comm == id){
					handle = acc.getHandle();
					postIsThere = true;
					break;
				}
			}
			//look in the endorsements
			for (int endor : storage.get("endorsements")){
				if (endor == id){
					handle = acc.getHandle();
					postIsThere = true;
					break;
				}
			}
		}
		
		if (!postIsThere){
				throw new PostIDNotRecognisedException();
		}
		
		int indexNeededPost=0;
		for (Post post : listOfPosts){
			if (post.getNumIdentifier() == id){
				indexNeededPost = listOfPosts.indexOf(post);
				break;
			}
		}
		
		ArrayList<Integer> postStorageEnd = new ArrayList<>();
		postStorageEnd = listOfPosts.get(indexNeededPost).getPostStorage().get("endorsements");
		int noEndor = postStorageEnd.size();
		
		ArrayList<Integer> postStorageComm = new ArrayList<>();
		postStorageComm = listOfPosts.get(indexNeededPost).getPostStorage().get("comments");
		int noComm = postStorageComm.size();
		
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
