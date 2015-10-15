package nyt;



import com.mashape.unirest.http.*;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.json.JSONObject;
import com.mongodb.MongoClient;

import javax.json.Json;
/**
 * Created by Yuan on 2015/10/15.
 */
public class Feed {
    static String urlToRead = "http://api.nytimes.com/svc/news/v3/content/all/business day/1.json";
    static String querySectionList = "http://api.nytimes.com/svc/news/v3/content/section-list.json";
    static String apiKey = "973770bd18cd53050c557ab3fa0c7150:8:73221556";

    public static void manager() throws Exception {
        // connect to DB
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
        Document doc = Document.parse(getFeed().toString());
        db.getCollection("news").insertOne(doc);

    }


    public static JSONObject getFeed() throws Exception {
        HttpResponse<JsonNode> jsonResponse = Unirest.get(urlToRead)
                .queryString("api-key", apiKey)
                .asJson();
        JSONObject result = new JSONObject(jsonResponse.getBody());
        return result;
    }
}
