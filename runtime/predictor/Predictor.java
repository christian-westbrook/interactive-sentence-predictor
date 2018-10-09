//=================================================================================================
// Program		: Language Model Predictor Preprocessor
// Class		: Predictor.java
// Developer		: Renae Fisher
// Abstract		: This program accepts a String input from Voce, which is used to
//			determine probability of a sentence that is construsted using the MLE of
//			bigrams, or trigrams with backoff. The bigrams utlize smoothing.
//=================================================================================================

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Predictor {

    HashMap<String, Integer> unigrams;
    HashMap<String, Integer> bigrams;
    HashMap<String, Integer> trigrams;
    private HashMap<String, PriorityQueue> bigramsPredict;
	private HashMap<String, PriorityQueue> trigramsPredict;
	Load l;
    int uniN;
    int uniV;

    public Predictor() {

        l = new Load();

        unigrams = l.getUnigrams();
        bigrams = l.getBigrams();
        trigrams = l.getTrigrams();
        bigramsPredict = l.getBigramsPredict();
        trigramsPredict = l.getTrigramsPredict();

        uniN = l.getUnigramsN();
        uniV = l.getUnigramsV();

    }

    // ===========================================================================================================
    // Accepts input from Voce, a single string.
    // ===========================================================================================================
    public String bigramSentence(String line) {

        StringBuilder sentence = new StringBuilder();
        String[] spl;
        String w1;
        double prob = 0.0;

        spl = line.split(" ");

        // Find probability of first half of sentence, the user input.
        int ind = 0;
        while (ind < spl.length - 1) {

            prob += bigramLogProb(spl[ind], spl[ind + 1]);
            ind++;

        }

        // Start to estimate next 10 words.
        w1 = spl[spl.length - 1];

        buildBigramResult(sentence, w1, prob, 10);

        return sentence.toString();

    }

    public void buildBigramResult(StringBuilder sb, String w1, double prob, int count) {

        Node n;
        String fmt;
        double res ;

        if (count > 0) {

            n = bigramMLE(w1);
                
            if(n == null) {
                sb.append("N-gram not recognized.");
                return;
            }

            prob = prob + n.getProb();
            sb.append(n.getVal() + " ");

            buildBigramResult(sb, n.getVal(), prob, --count);

        } else {

            fmt = String.format("%4.2f", prob);
            sb.append(fmt);

        }

    }

    // ===========================================================================================================
    // Accepts input from Voce, a single string.
    // ===========================================================================================================
    public String trigramSentence(String line) {

        StringBuilder sentence = new StringBuilder();
        String[] spl;
        String w1;
        String w2;
        double prob = 0.0;
        double t;
        byte b = -1;

        spl = line.split(" ");

        // Find probability of first half of sentence, the user input.
        int ind = 0;
        while (ind < spl.length - 2) {

            t = trigramLogProb(spl[ind], spl[ind + 1], spl[ind + 2]);

            if (t == Double.NEGATIVE_INFINITY) {

                prob += bigramLogProb(spl[ind], spl[ind + 1]);
                b = 0;

            } else {

                prob += t;
                b = 1;

            }

            ind++;

        }
        
        if(b == 0) {
            // If the last thing that we saved was a bigram, we need to add one last bigram.
            prob += bigramLogProb(spl[ind], spl[ind + 1]);
        }

        // Start to estimate next 10 words.
        w1 = spl[spl.length - 2];
        w2 = spl[spl.length - 1];

        buildTrigramResult(sentence, w1, w2, prob, 10);

        return sentence.toString();

    }

    public void buildTrigramResult(StringBuilder sb, String w1, String w2, double prob, int count) {

        Node n;
        String fmt;

        if (count > 0) {

            n = trigramMLE(w1, w2);

            if (n == null) {

                n = bigramMLE(w2);
                
                if( n == null ) {
                    sb.append("N-gram not recognized.");
                    return;
                }

            }

            sb.append(n.getVal() + " ");

            prob = prob + n.getProb();

            buildTrigramResult(sb, w2, n.getVal(), prob, --count);

        } else {

            fmt = String.format("%4.2f", prob);

            sb.append(fmt);

        }

    }

    // ===========================================================================================================
    // Calculate bigram MLE from a single word input. This is called after the input
    // from Voce is split into fragments we can use.
    // ===========================================================================================================
    public Node bigramMLE(String w1) {

        PriorityQueue<GramFreq> pq;
        GramFreq g;
        Node res = null;
        double log;
        int freq = 1;

        pq = bigramsPredict.get(w1);

        if (pq != null) {

            g = pq.peek();

            log = Math.log10(((double) g.getFreq() + 1) / (unigrams.get(w1) + uniV));
            res = new Node(g.getWx(), log);
            
        }
        
        if(res == null) {
            res = searchBigramList();
        }

        return res;

    }

    // ===========================================================================================================
    // Find another bigram if bigramMLE returns null.
    // ===========================================================================================================
    public Node searchBigramList() {

        Node res = new Node("",0.0);
        String s;
        String[] e;
        double min = Double.NEGATIVE_INFINITY;
        double log;

        Iterator i = bigrams.entrySet().iterator();

        while (i.hasNext() ) {

            s = ((Map.Entry) i.next()).getKey().toString();
            
            e = s.split(" ");

            log = Math.log10( ((double) bigrams.get(s) + 1) / (unigrams.get(e[1]) + uniV) );

            if (log > min) {

                min = log;
                res.setValues(e[1], min);

            }

        }

        return res;

    }
    
    public double bigramLogProb(String w1, String w2) {

        double res = Math.log10(0.0);
        String s;
        int freq = 0;
        int u = 0;

        s = w1 + " " + w2;

        if (bigrams.get(s) != null) {
            freq = bigrams.get(s);
        }

        if (unigrams.get(w1) != null) {
            u = unigrams.get(w1);
        }
        
        res = Math.log10(((double) freq + 1) / ( u + (uniV)));

        return res;

    }

    // ===========================================================================================================
    // Calculate trigram MLE from a single word input. This is called after the
    // input from Voce is split into fragments we can use.
    // ===========================================================================================================
    public Node trigramMLE(String w1, String w2) {

        PriorityQueue<GramFreq> pq;
        GramFreq g;
        Node res = null;
        double log;
        int freq = 1;

        pq = trigramsPredict.get(w1 + " " + w2);

        if (pq != null) {

            g = pq.peek();

            log = Math.log10((double) g.getFreq() / bigrams.get(w1 + " " + w2));
            res = new Node(g.getWx(), log);

        }

        return res;
        
    }

    public double trigramLogProb(String w1, String w2, String w3) {

        double res = Math.log10(0.0);
        String s;

        s = w1 + " " + w2 + " " + w3;

        if (trigrams.get(s) != null && bigrams.get(w1 + " " + w2) != null ) {
            res = Math.log10((double) trigrams.get(s) / bigrams.get(w1 + " " + w2));
        }

        return res;

    }

}
