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

		Integer id,id2;
		try {
			
			
			id = platform.createAccount("handle1");
			id2 = platform.createAccount("handle2", "Hello world!");
			assert (platform.getNumberOfAccounts() == 2) : "number of accounts registered in the system does not match";
			
			int idpost1 = platform.createPost("handle1", "helo");
			int idendorspost = platform.endorsePost("handle1", idpost1);
			int anotherpost = platform.createPost("handle2", "message");
			int commentpost = platform.commentPost("handle1", idpost1, "hello");
			int anotherendorspost = platform.endorsePost("handle2", commentpost);
			
			System.out.println(platform.showAccount("handle1"));
			System.out.println(platform.showAccount("handle2"));

			

			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";
		
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
