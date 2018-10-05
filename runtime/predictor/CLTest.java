
import java.util.HashMap;

public class CLTest {

    public static void main(String[] args) {
        
        HashMap<String, Integer> unigrams;
        HashMap<String, Integer> bigrams;
        HashMap<String, Integer> trigrams;
        Load l;
        Predictor p;
        String bRes;
        String tRes;
        
        if(args != null) {
        
            if(args.length >= 3) {
            
                l = new Load();

                unigrams = l.getUnigrams();
                bigrams = l.getBigrams();
                trigrams = l.getTrigrams();

                p = new Predictor(unigrams, bigrams, trigrams);
                
                String speech = "";
                
                for(int i = 0; i < args.length; i++) {
                    speech += args[i] + " ";
                }
            
                bRes = p.bigramSentence(speech);
                tRes = p.trigramSentence(speech);
                
                System.out.println(bRes);
                System.out.println(tRes);
            
            }
            
        }

    }

}
