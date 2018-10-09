//=================================================================================================
// Program		: Interactive Sentence Predictor Preprocessor
// Class		: GramFreq.java
// Developer	: Renae Fisher
// Abstract		: This is used to create special HashMaps for the Predictor.
//=================================================================================================

import java.io.Serializable;
import java.util.Comparator;

class GramComparator implements Comparator<GramFreq>, Serializable {

    @Override
    public int compare(GramFreq o1, GramFreq o2) {
        if (o1.getFreq() < o2.getFreq()) {
            return 1;
        } else if (o1.getFreq() > o2.getFreq()) {
            return -1;
        } else {
            return 0;
        }
    }

}
