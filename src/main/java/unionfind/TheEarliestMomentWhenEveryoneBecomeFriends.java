package unionfind;

// https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/submissions/

import java.util.Arrays;
import java.util.Comparator;

public class TheEarliestMomentWhenEveryoneBecomeFriends {
    public int earliestAcq(int[][] logs, int N) {
        Comparator<int[]> cm = new Comparator<int[]>(){
            public int compare(int[] a , int[] b){
                return Integer.compare(a[0] , b[0]);
            }
        };

        Arrays.sort(logs,cm);

        int ctr = N ;
        int[] parents = new int[N];
        for(int i = 0 ; i < N;i++){
            parents[i] = i;
        }

        for(int[] log : logs){
            int time = log[0];
            int from = log[1];
            int to = log[2];

            int root1 = find(from,parents);
            int root2 = find(to,parents);

            if(root1 != root2){
                parents[root2] = root1;
                ctr = ctr - 1;
                if(ctr == 1){
                    return time;
                }
            }
        }
        return -1;
    }

    private int find(int node , int[] parents){
        if(parents[node] == node){
            return node;
        }

        parents[node] = find(parents[node] , parents);
        return parents[node];
    }

}
