package unionfind;

// https://leetcode.com/problems/redundant-connection-ii/discuss/112569/Easiest-understanding-Java-Solution-using-Union-Find-O(n).
public class RedundantConnectionii {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length + 1;
        int[] parent = new int[n];
        for(int i = 0 ; i < parent.length ; i++){
            parent[i] = i;
        }

        int[] res1 = null;
        int[] res2 = null;

        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            int p1 = find(from,parent);
            int p2 = find(to,parent);

            if(p2 != to){
                res1 = e;
            }
            else if(p1 == p2){
                res2 = e;
            }else{
                parent[p2] = p1;
            }

        }

        if(res1 == null) return res2;
        if(res2 == null) return res1;

        for(int[] e : edges){
            if(e[1] == res1[1]){
                return e;
            }
        }

        return new int[2];
    }

    private int find(int id, int[] parent){
        while(parent[id] != id){
            parent[id] = parent[parent[id]];
            id = parent[id];
        }
        return id;
    }
}
