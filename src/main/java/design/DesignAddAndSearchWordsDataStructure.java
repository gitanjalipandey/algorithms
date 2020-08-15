package design;

public class DesignAddAndSearchWordsDataStructure {
    TrieNode root;
    /** Initialize your data structure here. */
    public DesignAddAndSearchWordsDataStructure() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] warr = word.toCharArray();
        TrieNode curr = root;
        for(char c : warr){
            int index = c - 'a';
            if(curr.children[index] == null){
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isword = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray() , 0 , root);
    }

    private boolean match(char[] warr , int index , TrieNode node){
        if(index == warr.length){
            if(node != null && node.isword == true) return true;
            return false;
        }

        if(warr[index] != '.'){
            int i = warr[index] - 'a';
            if(node.children[i] != null && match(warr,index+1 , node.children[i])) return true;
        }else{
            for(int i = 0 ; i < 26 ; i++){
                if(node.children[i] != null){
                    if(match(warr, index+1 , node.children[i])) return true;
                }
            }
        }
        return false;
    }
}

class TrieNode{
    TrieNode[] children;
    boolean isword = false;

    public TrieNode(){
        children = new TrieNode[26];
    }

}
