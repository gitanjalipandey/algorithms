package string;

// https://www.youtube.com/watch?v=y2BD4MJqV20


public class LongestPalindromeicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int start = 0;
        int end = 0 ;
        for(int i = 0 ; i < s.length() ; i++){
            int len1 = extend(i,i,s);
            int len2 = extend(i,i+1,s);
            int len = Math.max(len1,len2);
            if(len > end- start){
                start  = i - (len - 1) / 2;
                end = i+ len / 2;
            }
        }
        return s.substring(start,end);

    }

    private int extend(int left , int right , String s){
        if(s.length() == 0 || left > right) return 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;

    }

    public static void main(String[] args){

    }
}
