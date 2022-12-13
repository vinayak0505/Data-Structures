
// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {

    int bstToGst(TreeNode* root, int val){
        if(root == nullptr) return 0;
        int right = bstToGst(root->right,val);
        int left = bstToGst(root->left,right + val + root->val);
        root->val += right + val;
        return left + root->val - val;
    }
public:
    TreeNode* bstToGst(TreeNode* root) {
        bstToGst(root,0);
        return root;
    }
};