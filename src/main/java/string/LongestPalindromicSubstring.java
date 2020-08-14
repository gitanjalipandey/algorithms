package string;

// https://www.youtube.com/watch?v=y2BD4MJqV20


public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int start = 0;
        int end = 0 ;
        for(int i = 0 ; i < s.length() ; i++){
            System.out.println("index is="+i);
            int len1 = extend(i,i,s);
            int len2 = extend(i,i+1,s);
            int len = Math.max(len1,len2);
            if(len > end- start){
                start  = i - (len - 1) / 2;
                end = i+ len / 2;
            }
        }
        return s.substring(start,end+1);

    }

    private int extend(int left , int right , String s){
        System.out.println("begin left="+left+" right="+right);
        if(s.length() == 0 || left > right) return 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        left = left + 1;
        System.out.println("end left="+left+" right="+right);
        return right - left;

    }

    public static void main(String[] args){
        LongestPalindromicSubstring lp = new LongestPalindromicSubstring();
        String res = lp.longestPalindrome("racecar");
        System.out.println("res="+res);
       // res = lp.longestPalindrome("babdc");
        //System.out.println("res2="+res);
    }
}
