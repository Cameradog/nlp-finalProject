package classifer;

import java.util.HashMap;
import java.util.Set;
import java.lang.Math;

public class NaiveBayer {

    public static void main(String[] args) {
        String s = "abbaadfdgdeiivnielo";
        String s2 = "";
        String s3 = "abcdefghijklmn";//negative
        String sentiment="";
        double Entropy;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char c;
        for (int i = 0; i < s.length() - 1; i++) {
            c = s.charAt(i);
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                Integer in = map.get(c);
                map.put(c, in.intValue() + 1);
            }
        }
        HashMap<Character, Integer> map3 = new HashMap<Character, Integer>();//negative unigram hashmap
        for (int i = 0; i < s3.length() - 1; i++) {
            c = s3.charAt(i);
            if (map3.get(c) == null) {
                map3.put(c, 1);
            } else {
                Integer in = map3.get(c);
                map3.put(c, in.intValue() + 1);
            }
            System.out.println(c + " " + map3.get(c));
        }
        //positive
        Set<Character> set = map.keySet();
        int count = 0;
        for (Character ch : set) {
            System.out.println(ch + " " + map.get(ch));
            count = count + map.get(ch) + 1; //Laplace smoothing add-1
        }
        //negative
        Set<Character> set2 = map3.keySet();
        int count2 = 0;
        for (Character ch : set2) {
            count2 = count2 + map3.get(ch) + 1; //Laplace smoothing add-1
        }
        //positive
        double UniProbP = 1;
        //negative
        double UniProbN = 1;
        String test = "ab";
        for (int i = 0; i < test.length(); i++) {
            if (map.get(test.charAt(i)) != null) {
                UniProbP = UniProbP * (map.get(test.charAt(i)) + 1) / count;//Laplace smoothing add-1
            } else {
                UniProbP = UniProbP * 1 / count;
            }
            if (map3.get(test.charAt(i)) != null) {
                UniProbN = UniProbN * (map3.get(test.charAt(i)) + 1) / count2;//Laplace smoothing add-1
            } else {
                UniProbN = UniProbN * 1 / count2;
            }
        }
        Entropy=UniProbN*Math.log(UniProbN)+UniProbP*Math.log(UniProbP);
        if (UniProbN>UniProbP){sentiment="Negative";}
        else
            sentiment="Positive";
        System.out.println(sentiment);
    }
}
