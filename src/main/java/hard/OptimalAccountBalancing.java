package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/optimal-account-balancing/discuss/95355/Concise-9ms-DFS-solution-(detailed-explanation)

public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int[] t : transactions){
            int from = t[0];
            int to = t[1];
            int amt = t[2];
            map.put(from,map.getOrDefault(from,0)-amt);
            map.put(to,map.getOrDefault(to,0)+amt);
        }
        List<Integer> list = new ArrayList<>(map.values());

        return dfs(0,list);
    }

    private int dfs(int index , List<Integer> list ){
        while(index < list.size() && list.get(index) == 0){
            index++;
        }
        if(index == list.size()){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int i = index ; i < list.size() ; i++){
            if(list.get(i) * list.get(index) < 0){
                list.set(i , list.get(i) + list.get(index));
                min = Math.min(min, 1 + dfs(index+1,list));
                list.set(i , list.get(i) - list.get(index));
            }
        }
        return min;
    }


}
