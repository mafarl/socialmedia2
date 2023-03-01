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
	private int idAccount = 0;
	private int idPost = 0;
	// 0 - original, 1 - comments, 2 - endorsements
	private int[] numberOfPosts = new int[]{0,0,0};
	
	// getters
	public ArrayList getAccounts() {
		return listOfAccounts;
	}
	
	public ArrayList getPosts() {
		return listOfPosts;
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
		for (Account i : listOfAccounts) {
			if (i.getID() == id) {
				listOfAccounts.remove(counter);
				break;
			}
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
		for (Account i : listOfAccounts) {
			if (i.getHandle() == handle) {
				listOfAccounts.remove(counter);
				break;
			}
		}
	}

	@Override
	public void changeAccountHandle(String oldHandle, String newHandle)
			throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showAccount(String handle) throws HandleNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endorsePost(String handle, int id)
			throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException,
			PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deletePost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public String showIndividualPost(int id) throws PostIDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
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
		return 0;
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
