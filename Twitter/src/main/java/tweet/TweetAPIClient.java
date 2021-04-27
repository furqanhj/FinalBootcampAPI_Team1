package tweet;

import io.restassured.response.ValidatableResponse;
import load.RestAPI;
import static io.restassured.RestAssured.given;


public class TweetAPIClient extends RestAPI{

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String GET_USER_ACCOUNT_SETTINGS = "/account/settings.json";
    private final String GET_STATUS_SAMPLE = "/statuses/sample.json";


    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Get User Account Settings
    public ValidatableResponse getUserAccountSettings() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_ACCOUNT_SETTINGS).then().statusCode(200);

    }
    public ValidatableResponse getStatusSample(){
    return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
            .when().get(this.baseUrl + this.GET_STATUS_SAMPLE).then();

    }

}
