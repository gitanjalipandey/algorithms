package tree;

// https://leetcode.com/problems/all-elements-in-two-binary-search-trees/discuss/464073/C%2B%2B-One-Pass-Traversal
public class AllElementsInTwoBinarySearchTrees {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        pushleft(stack1,root1);
        pushleft(stack2,root2);

        while(!stack1.isEmpty() || !stack2.isEmpty()){
            Deque<TreeNode> currstack = null;
            if(stack1.isEmpty()){
                currstack = stack2;
            }else if(stack2.isEmpty()){
                currstack = stack1;
            }else{
                if(stack1.peek().val < stack2.peek().val){
                    currstack = stack1;
                }else{
                    currstack = stack2;
                }
            }

            TreeNode currnode = currstack.pop();
            res.add(currnode.val);
            if(currnode.right != null){
                pushleft(currstack, currnode.right);
            }
        }
        return res;
    }

    private void pushleft(Deque<TreeNode> stack , TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }

}
