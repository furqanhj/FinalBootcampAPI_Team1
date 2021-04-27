package tweetTest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;


import java.io.FileNotFoundException;
import java.util.UUID;


import java.util.UUID;

public class TweetAPIClientTest {


    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();

    }

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet tweet
        String tweet = "4 days until survival" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }


    /////////////////////////////////////////////////////////////
    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        String tweet = "4 days until survival";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage);

    }

    @Test
    public void testGetUserAccountSettings() {
        String expectedName = "RasheedAnaam";
        ValidatableResponse response = this.tweetAPIClient.getUserAccountSettings();
        System.out.println(response.extract().body().asString());//screen_name
        String actualName = response.extract().body().path("screen_name");
        Assert.assertEquals(actualName, expectedName, "Name did not not match");
    }

}