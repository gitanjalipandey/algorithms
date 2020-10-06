package unionfind;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        int[] parents = new int[n];
        for(int i = 0 ; i < parents.length ; i++){
            parents[i] = i;
        }
        int ctr = n;
        for(int[] e : edges){
            int from = e[0];
            int to = e[1];
            int froot = find(parents,from);
            int troot = find(parents,to);
            System.out.println("from="+from+" froot="+froot+" to="+to+" troot="+troot);
            if(froot == troot) return false;
            if(froot != troot){
                // if(froot != from || troot != to) return false;
                parents[troot] = froot;
                ctr--;
            }
        }
        System.out.println("ctr="+ctr);
        if(ctr > 1) return false; else return true;
    }

    private int find(int[] parents,int node){
        if(parents[node] == node) return node;

        parents[node] = find(parents , parents[node]);
        return parents[node];
    }
}
