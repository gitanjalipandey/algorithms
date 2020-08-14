package string;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        Map<Character,Integer> map = new HashMap<>();
        int i = 0 ;
        int res = 1;
        int start = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                start = Math.max(start , map.get(c) + 1);

            }
            res = Math.max(res,i - start + 1);
            map.put(c,i);
            i++;
        }
        return  res;
    }
}
