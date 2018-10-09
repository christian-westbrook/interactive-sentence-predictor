//=================================================================================================
// Program		: Interactive Sentence Predictor
// Class		: GramFreq.java
// Developer	: Renae Fisher
// Abstract		: 
//=================================================================================================

import java.io.Serializable;

public class GramFreq implements Serializable {

    private String wx;
    private int freq;

    public GramFreq(String wx, int freq) {

        this.wx = wx;
        this.freq = freq;

    }

    public String getWx() {
        return wx;
    }

    public int getFreq() {
        return freq;
    }

}
