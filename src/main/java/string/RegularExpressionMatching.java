package string;

// https://leetcode.com/problems/regular-expression-matching/discuss/5802/Simple-java-recursive-solution-with-two-cases

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        return helper(s,p);
    }

    private boolean helper(String s , String p){
        if(p.length() == 0){
            if(s.length() == 0) return true; else return false;
        }

        if(p.length() > 1 && p.charAt(1) == '*'){
            if(helper(s, p.substring(2))) return true;
            if(s.length() > 0 && p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'){
                return helper(s.substring(1),p);
            }
            return false;
        }
        else{
            if(s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')){
                return helper(s.substring(1) , p.substring(1));
            }
        }
        return false;
    }

    public static void main(String[] args){
        RegularExpressionMatching ma = new RegularExpressionMatching();
        boolean res = ma.isMatch("aaab" , "a*b");
        System.out.println("res="+res);

        res = ma.isMatch("aaab" , "c*a*b");
        System.out.println("res2="+res);

        res = ma.isMatch("aaab" , "a.*b");
        System.out.println("res="+res);
    }
}
