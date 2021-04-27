package tweetTest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.LinkedHashMap;
import java.util.UUID;


public class TweetAPIClientTest {


    private TweetAPIClient tweetAPIClient;
    @BeforeClass public void setUpTweetAPI(){
        this.tweetAPIClient = new TweetAPIClient();
    }


    //TEST 1
    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet tweet
        String tweet = "Pritam is da best" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet does not match");
    }

    //TEST 2
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "4 days until survival";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage);
    }
    //TEST 3
    @Test(priority = 3)
    public void testGetUserAccountSettings() {
        String expectedName = "bee_shaykh";
        ValidatableResponse response = this.tweetAPIClient.getUserAccountSettings();
        System.out.println(response.extract().body().asString());//screen_name
        String actualName = response.extract().body().path("screen_name");
        Assert.assertEquals(actualName, expectedName, "Name did not not match");
    }
    //TEST 4
    @Test
    public void testGetStatusSample(){
        ValidatableResponse response = this.tweetAPIClient.getStatusSample();
        System.out.println(response.extract().body().asString());
        String expected = "{message=Sorry, that page does not exist, code=34}";
        LinkedHashMap actual = response.extract().body().path("errors[0].");
        Assert.assertTrue(expected.equalsIgnoreCase(String.valueOf(actual)));
    }




}
