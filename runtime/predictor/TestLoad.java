/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author fishe
 */
public class TestLoad {

    public static HashMap<String, Integer> loadNGrams(String filename) {

        HashMap<String, Integer> res = null; // Check for this prior to preceding.
        BufferedReader br;
        String read;
        String[] spl;
        String del;
        int tmp;

        try {

            res = new HashMap<>();
            br = new BufferedReader(new FileReader(filename));
            del = ",";

            while ((read = br.readLine()) != null) {

                spl = read.split(del);
                tmp = Integer.parseInt(spl[0]); // Depends on whether frequency is listed first or second.

                res.put(spl[1].trim(), tmp);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return res;

    }

}
