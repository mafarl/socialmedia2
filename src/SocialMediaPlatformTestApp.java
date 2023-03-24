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
import java.io.*;

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
			int dogEndorSix = platform.endorsePost("dog",maryiaOrigZero);
			int maryiaCommSeven = platform.commentPost("maryia",dogCommTwo,"Comment to dog's comment to maryia's orig post");
			int ashleyCommEight = platform.commentPost("ashley", maryiaOrigZero, "Second comment");
			int maryiaCommNine = platform.commentPost("maryia", ashleyCommEight, "Comment to ashley comment");
			int ashleyCommTen = platform.commentPost("ashley", ashleyOrigOne, "Comment to my own post");
			int ashleyEndorEleven = platform.endorsePost("ashley", ashleyCommTen);
			
			System.out.println(platform.showIndividualPost(ashleyEndorEleven));
			
			assert (platform.getNumberOfAccounts() == 3) : "number of accounts registered in the system does not match";
		
		} /*catch (PostIDNotRecognisedException e){
			assert (false): "PostIDNotRecognisedException thrown incorrectly";
		} catch (InvalidHandleException e){
			assert (false): "InvalidHandleException thrown incorrectly";
		} catch (NotActionablePostException e){
			assert (false): "NotActionablePostException thrown incorrectly";
		}catch (HandleNotRecognisedException e){
			assert (false): "HandleNotRecognisedException thrown incorrectly";
		} catch (InvalidPostException e){
			assert (false): "InvalidPostException thrown incorrectly";
		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} // catch (AccountIDNotRecognisedException e) {
			//assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		//}//catch (IOException e){
			//assert (false) : "IOException";
		//} //catch (ClassNotFoundException e){
			//assert (false) : "ClassNotFoundException thrown incorrectly";
		//} */
		catch (Exception e){
			assert (false): "Caught an exception";
		}

	}

}