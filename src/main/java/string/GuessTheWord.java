package string;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessTheWord {

    // https://leetcode.com/problems/guess-the-word/discuss/556075/How-to-explain-to-interviewer-843.-Guess-the-Word

    public void findSecretWord(String[] wordlist, Master master) {
        int matches = 0;
        for(int i = 0 ; i < 10 ; i++){
            int index = new Random().nextInt(wordlist.length);
            String str = wordlist[index];
            matches = master.guess(str);
            List<String> remaining = new ArrayList<>();
            for(String word : wordlist){
                if(matches == getMatches(word,str)){
                    remaining.add(word);
                }
            }
            wordlist = remaining.toArray(new String[remaining.size()]);
        }
    }

    private int getMatches(String str1 , String str2){
        int matches = 0;
        for(int i = 0 ; i < str1.length() ; i++){
            if(str1.charAt(i) == str2.charAt(i)){
                matches++;
            }
        }
        return matches;
    }
}
