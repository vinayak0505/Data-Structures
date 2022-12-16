
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
    int left = 0,right = 0;
    int fill(TreeNode* root,int x){
        if(root == nullptr) return 0;
        if(root->val == x){
            left = fill(root->left,x);
            right = fill(root->right,x);
            return 0;
        }else {
            return fill(root->left,x) + fill(root->right,x) + 1;
        }
        return 0;
    }
public:
    bool btreeGameWinningMove(TreeNode* root, int n, int x) {
        fill(root,x);
        int par = n - left - right - 1;
        return (
            (left > right + par + 1) || 
            (right > par + left + 1) ||
            (par > left + right + 1)
        );
    }
};