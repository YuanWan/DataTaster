package sentiment;
import uk.ac.wlv.sentistrength.*;
/**
 * Created by Yuan on 2015/10/12.
 */
public class Analysis {
    static SentiStrength sentiStrength = new SentiStrength();
    public static void init(){
        //Method 2: One initialisation and repeated classifications
//        SentiStrength sentiStrength = new SentiStrength();
//Create an array of command line parameters to send (not text or file to process)
        String ssthInitialisation[] = {"sentidata", "C:\\Working\\DataTaster\\lib\\SentStrength_Data\\", "explain"};
        sentiStrength.initialise(ssthInitialisation); //Initialise

    }
    public static String rate(String sentense){
        return sentiStrength.computeSentimentScores(sentense);
    }


}
