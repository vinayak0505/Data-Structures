import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class FindElements {
    private TreeNode parent;

    private void fillElements(TreeNode root, int value){
        if(root == null) return;
        root.val = value;
        fillElements(root.left, value * 2 + 1);
        fillElements(root.right, value * 2 + 2);
    }
    public FindElements(TreeNode root) {
        fillElements(root, 0);
        parent = root;
    }
    
    public boolean find(int target) {
        int target2 = target;
        Stack<Boolean> goleft = new Stack<>();
        while(target != 0){
            if(target % 2 == 0){
                goleft.push(false);
                target = (target - 2) / 2;
            }else{
                goleft.push(true);
                target/=2;
            }
        }
        TreeNode node = parent;
        while(!goleft.isEmpty() && node != null){
            if(goleft.peek()){
                node = node.left;
            }else{
                node = node.right;
            }
            goleft.pop();
        }
        return node != null;
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */