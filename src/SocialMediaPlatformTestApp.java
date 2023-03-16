import socialmedia.AccountIDNotRecognisedException;
import socialmedia.NotActionablePostException;
import socialmedia.InvalidPostException;
import socialmedia.PostIDNotRecognisedException;
import socialmedia.BadSocialMedia;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;
import socialmedia.HandleNotRecognisedException;
import socialmedia.SocialMediaPlatform;
import socialmedia.Account;
import socialmedia.Post;
import java.util.*;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new BadSocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id,id2,id3;
		try {
			
			
			id = platform.createAccount("maryia");
			id2 = platform.createAccount("ashley", "Hello world!");
			id3 = platform.createAccount("dog");
			assert (platform.getNumberOfAccounts() == 3) : "number of accounts registered in the system does not match";
			
			int maryiaOrigZero = platform.createPost("maryia", "First post");
			int ashleyOrigOne = platform.createPost("ashley", "First post");
			int dogCommTwo = platform.commentPost("dog",maryiaOrigZero,"First comment");
			int ashleyEndorThree = platform.endorsePost("ashley",dogCommTwo);
			int maryiaEndroFour = platform.endorsePost("maryia",dogCommTwo);
			int maryiacommFive = platform.commentPost("maryia",ashleyOrigOne,"First comment");
			//int dogEndorSix = platform.endorsePost("dog",maryiaOrigZero);
			
			System.out.println("ACCOUNTS INITIALLY");
			System.out.println(platform.showAccount("maryia"));
			System.out.println(platform.showAccount("ashley"));
			System.out.println(platform.showAccount("dog"));
			System.out.println(" ");
			
			/* System.out.println(platform.showIndividualPost(ashleyOrigOne));
			System.out.println(" ");
			System.out.println(platform.showIndividualPost(dogCommTwo));
			System.out.println(" "); */
			
			//platform.deletePost(dogCommTwo);
			
			
			System.out.println(" ");
			/*System.out.println("ACCOUNTS AFTER DELETING DOG'S COMMENT");
			System.out.println(platform.showAccount("maryia"));
			System.out.println(platform.showAccount("ashley"));
			System.out.println(platform.showAccount("dog")); */
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			
			System.out.println("Post before deleting dog");
			System.out.println(platform.showIndividualPost(maryiaOrigZero));
			platform.removeAccount(id3);
			System.out.println("Post after deleting dog");
			System.out.println(platform.showIndividualPost(maryiaOrigZero));
			System.out.println("accounts");
			System.out.println(platform.showAccount("maryia"));
			System.out.println(platform.showAccount("ashley")); 
			
			assert (platform.getNumberOfAccounts() == 2) : "number of accounts registered in the system does not match";
		
		} catch (PostIDNotRecognisedException e){
			assert (false): "PostIDNotRecognisedException thrown incorrectly";
		} catch (NotActionablePostException e){
			assert (false): "NotActionablePostException thrown incorrectly";
		} catch (HandleNotRecognisedException e){
			assert (false): "HandleNotRecognisedException thrown incorrectly";
		} catch (InvalidPostException e){
			assert (false): "InvalidPostException thrown incorrectly";
		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		}

	}

}