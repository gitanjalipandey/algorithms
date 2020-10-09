package hard;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/longest-valid-parentheses/discuss/14126/My-O(n)-solution-using-a-stack
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == '('){
                stack.push(i);
            }else{
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    int prev = stack.peek();
                    if(arr[prev] == '('){
                        prev = stack.pop();
                        int start = 0;
                        if(!stack.isEmpty()){
                            start = stack.peek() + 1;
                        }
                        res = Math.max(res, i-start+1);
                    }else{
                        stack.push(i);
                    }
                    //stack.push(i);
                }
            }
        }
        return res;
    }
}
