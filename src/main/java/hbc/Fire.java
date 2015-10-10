package hbc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.collect.Lists;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.*;
import com.twitter.hbc.core.endpoint.Location;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import nlp.NLP;
import org.json.*;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

/**
 * Created by Yuan on 2015/6/13.
 */
public class Fire {
    public static void steam() throws InterruptedException, JSONException {
        /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>(1000);

/** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
// Optional: set up some followings and track terms
        List<String> languages = Lists.newArrayList("en");
        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        List<String> terms = Lists.newArrayList("VolksWagen");
        //List<Location> locations = Lists.newArrayList(-122.75,36.8,-121.75,37.8,-74,40,-73,41);

        hosebirdEndpoint.languages(languages);
  //      hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);
      //  hosebirdEndpoint.locations(locations);
// These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1("6ZKmg5xXhRH2IMIpNU1bR93pb", "ybvUGYtuSbj1XpoyHSk5bCfmvMdm35kMZduqt2njPryy1lzba1", "3220384082-dH3wzKE8j1w7HZUqIIToa7fniU8g5xk3EMseQem", "QKCnfRHGyj52Jzuvx6oYcMq0J41jitPWk1pVNZAZ1pd3f");

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        Client hosebirdClient = builder.build();
// Attempts to establish a connection.
        hosebirdClient.connect();
        NLP.init();

        while (!hosebirdClient.isDone()) {
            String msg = msgQueue.take();
            boolean indexStart = msg.contains("\"text\":\"");
            boolean indexEnd = msg.contains("\"source\":\"");
            if (indexStart && indexEnd){

                System.out.println(msg.substring(msg.indexOf("\"text\":\"")+8,msg.indexOf("\"source\":\"")-2));
                System.out.println("Sentiment : " + NLP.findSentiment(msg.substring(msg.indexOf("\"text\":\"")+8,msg.indexOf("\"source\":\"")-2)));
            }

            //profit();
        }

    }
}
