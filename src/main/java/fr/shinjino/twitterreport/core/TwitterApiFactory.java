package fr.shinjino.twitterreport.core;


/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class TwitterApiFactory {

    public static TwitterApi instanceFromLogin(String login, String password) throws Exception {
        TwitterApi twitterApi = new TwitterApi();
        twitterApi.connection(login, password);
        return twitterApi;
    }
}
