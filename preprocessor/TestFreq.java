
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFreq {

    private static HashMap<String, Integer> unigrams;
    private static HashMap<String, Integer> bigrams;
    private static HashMap<String, Integer> trigrams;

    private int n;
    private int v;

    private int unigramsN;
    private int unigramsV;

    private int bigramsN;
    private int bigramsV;

    private int trigramsN;
    private int trigramsV;

    public TestFreq() {
        try {
            // Load unigram table
            FileInputStream ufis = new FileInputStream("../runtime/predictor/data/unigrams.map");
            ObjectInputStream uois = new ObjectInputStream(ufis);
            unigrams = (HashMap<String, Integer>) uois.readObject();
            uois.close();

            // Load bigram table
            FileInputStream bfis = new FileInputStream("../runtime/predictor/data/bigrams.map");
            ObjectInputStream bois = new ObjectInputStream(bfis);
            bigrams = (HashMap<String, Integer>) bois.readObject();
            bois.close();

            // Load trigram table
            FileInputStream tfis = new FileInputStream("../runtime/predictor/data/trigrams.map");
            ObjectInputStream tois = new ObjectInputStream(tfis);
            trigrams = (HashMap<String, Integer>) tois.readObject();
            tois.close();

            // Load metrics
            File mFile = new File("../runtime/predictor/data/metrics.dat");
            FileReader mfr = new FileReader(mFile);
            BufferedReader bfr = new BufferedReader(mfr);

            String[] metrics = bfr.readLine().split(",");

            n = Integer.parseInt(metrics[0]);
            v = Integer.parseInt(metrics[1]);

            unigramsN = Integer.parseInt(metrics[2]);
            unigramsV = Integer.parseInt(metrics[3]);

            bigramsN = Integer.parseInt(metrics[4]);
            bigramsV = Integer.parseInt(metrics[5]);

            trigramsN = Integer.parseInt(metrics[6]);
            trigramsV = Integer.parseInt(metrics[7]);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // Getters
    public HashMap<String,Integer> getUnigrams() {
        return unigrams;
    }

    public HashMap<String,Integer> getBigrams() {
        return bigrams;
    }

    public HashMap<String,Integer> getTrigrams() {
        return trigrams;
    }

    public int getN() {
        return n;
    }

    public int getV() {
        return v;
    }

    public int getUnigramsN() {
        return unigramsN;
    }

    public int getUnigramsV() {
        return unigramsV;
    }

    public int getBigramsN() {
        return bigramsN;
    }

    public int getBigramsV() {
        return bigramsV;
    }

    public int getTrigramsN() {
        return trigramsN;
    }

    public int getTrigramsV() {
        return trigramsV;
    }
    
    public static int checkCounts(HashMap<String,Integer> hm, String filename) {
    
        String read;
        String[] splt;
        int freq;
        int test;
        int res = 0;
        
        try {
    
            BufferedReader br = new BufferedReader(new FileReader(filename));
        
            while((read = br.readLine())!=null) {
        
                splt = read.split(",");
            
                freq = Integer.parseInt(splt[1]);

                if(hm.get(splt[0]) != null) {
            
                    test = hm.get(splt[0]);
            
                    if(freq != test) {
                        res++;
                    }
                    
                } else {
                    res++;
                }
        
            }
        
        } catch(Exception e) {
        
        }
        
        return res;
    
    }

    public static void main(String[] args) {

        new TestFreq();
        
        int u = checkCounts(unigrams, "./ngrams/unigrams_freq.txt");
        int b = checkCounts(bigrams, "./ngrams/bigrams_freq.txt");
        int t = checkCounts(trigrams, "./ngrams/trigrams_freq.txt");
        
        System.out.println("UNIGRAMS ERROR COUNT: "+u);
        System.out.println("BIGRAMS ERROR COUNT: "+b);
        System.out.println("TRIGRAMS ERROR COUNT: "+t);

    }

}
