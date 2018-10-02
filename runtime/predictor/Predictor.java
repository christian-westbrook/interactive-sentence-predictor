

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Predictor {

    HashMap<String, Integer> unigrams;
    HashMap<String, Integer> bigrams;
    HashMap<String, Integer> trigrams;
    HashMap<String, Integer> misc;

    public Predictor(HashMap<String, Integer> unigrams, HashMap<String, Integer> bigrams,
            HashMap<String, Integer> trigrams) {

        this.unigrams = unigrams;
        this.bigrams = bigrams;
        this.trigrams = trigrams;
        
        misc = new HashMap<>();
        
        calcMisc(unigrams,"UNI_N","UNI_V");
        //calcMisc(bigrams,"BI_N","BI_V");
        //calcMisc(trigrams,"TRI_N","TRI_V");

    }

    public void calcMisc(HashMap<String, Integer> hm, String key1, String key2) {

        Iterator i = hm.entrySet().iterator();
        int n = 0;
        int v = 0;

        while (i.hasNext()) {

            n += (int)((Map.Entry)i.next()).getValue();
            v++;

        }

        misc.put(key1,n);
        misc.put(key2,v);

    }

    // ===========================================================================================================
    // Accepts input from Voce, a single string.
    //
    // ===========================================================================================================
    public String bigramSentence(String line) {

        StringBuilder sentence = new StringBuilder();
        String[] spl;
        String first;
        
        spl = line.split(" ");
        first = spl[spl.length - 1];

        buildBigramResult(sentence, first, 1.0, 10);

        return sentence.toString();

    }

    public void buildBigramResult(StringBuilder sb, String prev, double prob, int count) {

        Node n;

        if (count > 0) {

            n = bigramMLE(prev);

            sb.append(n.getVal() + " ");

            prob = prob * n.getProb();

            buildBigramResult(sb, n.getVal(), prob, --count);

        } else {

            sb.append(prob);

        }

    }

    // ===========================================================================================================
    // Accepts input from Voce, a single string.
    //
    // ===========================================================================================================
    public String trigramSentence(String line) {

        StringBuilder sentence = new StringBuilder();
        String[] spl;
        String first;
        String second;

        spl = line.split(" ");

        first = spl[spl.length - 2]; // Could be an issue of the line of the spoken line is less than three.
        second = spl[spl.length - 1];

        buildTrigramResult(sentence, first, second, 1.0, 10);

        return sentence.toString();

    }

    public void buildTrigramResult(StringBuilder sb, String first, String second, double prob, int count) {

        Node n;

        if (count > 0) {

            n = trigramMLE(first, second);

            if (n.getProb() == Double.NEGATIVE_INFINITY) {

                n = bigramMLE(second);

            }

            sb.append(n.getVal() + " ");

            prob = prob * n.getProb();

            buildTrigramResult(sb, second, n.getVal(), prob, --count);

        } else {

            sb.append(prob);

        }

    }

    // ===========================================================================================================
    // Calculate unigram MLE. I'm not sure if we actually need this.
    //
    // ===========================================================================================================
    public Node unigramMLE() {

        Node res = new Node("", Double.NEGATIVE_INFINITY); // Technically a zero probability. Either this or return
        // null, & check for null.
        String s;
        double min = Double.NEGATIVE_INFINITY;
        double log;

        Iterator i = unigrams.entrySet().iterator();

        while (i.hasNext()) {

            s = ((Map.Entry) i.next()).getKey().toString();

            /*
			 * LOG PROBABILITY NOTES: Probabilities occur between zero and one. The smallest
			 * probability is 0 and the greatest is 1. In Java, the range of a log
			 * probability is -infinity to 0. Fractions return negative value from a log
			 * function. So, we're looking for negative exponents closest to zero.
             */
            // .size() isn't an adequate solution. We need N in this case.
            log = Math.log10((double) unigrams.get(s) / misc.get("UNI_N"));

            if (log > min) {

                min = log;
                res.setValues(s, min);

            }

        }

        return res;

    }

    // ===========================================================================================================
    // Calculate bigram MLE from a single word input. This is called after the input
    // from Voce is split
    // into fragments we can use.
    // ===========================================================================================================
    public Node bigramMLE(String first) {

        Node res = new Node("", Double.NEGATIVE_INFINITY);
        String s;
        String e;
        double min = Double.NEGATIVE_INFINITY;
        double log;

        Iterator i = bigrams.entrySet().iterator();

        while (i.hasNext()) {

            s = ((Map.Entry) i.next()).getKey().toString();

            if (s.matches(first + " .+")) {

                /*
		 * I used a rough attempt to implement smoothing. The example in the textbook
		* said that we need to add one to the numerator, and V to the denominator.
                 */
                
                // .size() isn't an adequate solution, we would need V in this case.
                e = s.split(" ")[1];
                log = Math.log10(((double) bigrams.get(s) + 1) / (unigrams.get(e) + misc.get("UNI_V")));

                if (log > min) {

                    min = log;
                    res.setValues(e, min);

                }

            }

        }

        return res;

    }

    // ===========================================================================================================
    // Calculate trigram MLE from a single word input. This is called after the
    // input from Voce is split
    // into fragments we can use.
    // ===========================================================================================================
    public Node trigramMLE(String first, String second) {

        Node res = new Node("", Double.NEGATIVE_INFINITY);
        String s;
        String e;
        double min = Double.NEGATIVE_INFINITY;
        double log;

        Iterator i = bigrams.entrySet().iterator();

        while (i.hasNext()) {

            s = ((Map.Entry) i.next()).getKey().toString();

            if (s.matches(first + " " + second + " .+")) {

                e = s.split(" ")[2];
                log = Math.log10((double) trigrams.get(s) / bigrams.get(first + " " + second));

                if (log > min) {

                    min = log;
                    res.setValues(e, min);

                }

            }

        }

        return res;

    }

}
