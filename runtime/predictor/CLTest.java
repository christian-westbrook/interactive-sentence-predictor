//=================================================================================================
// Program		: Interactive Sentence Predictor
// Class		: CLTest.java
// Developer	: Renae Fisher
// Abstract		: 
//=================================================================================================

import java.util.HashMap;

public class CLTest {

    public static void main(String[] args) {
        
        Predictor p;
        String bRes;
        String tRes;
        int uniN;
        int uniV;
        
        if(args != null) {
        
            if(args.length >= 3) {
            
                p = new Predictor();
                
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
