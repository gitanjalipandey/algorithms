package string;

import java.util.*;

// https://leetcode.com/problems/concatenated-words/discuss/541520/Java-DFS-%2B-Memoization-Clean-code

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wordset = new HashSet<>(Arrays.asList(words));
        List<String> res = new ArrayList<>();

        for(String word : words){
            if(dfs(word,wordset)){
                res.add(word);
            }
        }
        return res;
    }

    private boolean dfs(String word , Set<String> wordset){
        for(int i = 1 ; i < word.length() ; i++){
            String sub = word.substring(0,i);
            if(wordset.contains(sub)){
                String suffix = word.substring(i);
                if(wordset.contains(suffix) || dfs(suffix,wordset)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        ConcatenatedWords cw = new ConcatenatedWords();
        List<String> res = cw.findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"});
        for(String s : res){
            System.out.println("res="+s);
        }
    }
}
