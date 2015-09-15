import hbc.Fire;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

    public static void main(String[] args) throws TwitterException, InterruptedException, JSONException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("6ZKmg5xXhRH2IMIpNU1bR93pb")
                .setOAuthConsumerSecret("ybvUGYtuSbj1XpoyHSk5bCfmvMdm35kMZduqt2njPryy1lzba1")
                .setOAuthAccessToken("3220384082-dH3wzKE8j1w7HZUqIIToa7fniU8g5xk3EMseQem")
                .setOAuthAccessTokenSecret("QKCnfRHGyj52Jzuvx6oYcMq0J41jitPWk1pVNZAZ1pd3f");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        //post new twitter
//        String latestStatus = "Posted from JAVA";
//        Status status = twitter.updateStatus(latestStatus);
//        System.out.println("Successfully updated the status to [" + status.getText() + "].");

        //query
//        Query query = new Query("����").lang("zh-cn").count(100);
//        QueryResult result = twitter.search(query);
//        for (Status status : result.getTweets()) {
//            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
//        }

        //live listening
        Fire.steam();
    }
}
