
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
    bool _sufficientSubset(TreeNode* root, int limit){
        if(root == nullptr) return false;
        if(root->right == nullptr && root->left == nullptr){
            return limit <= root->val;
        }
        auto left = _sufficientSubset(root->left,limit - root->val);
        auto right = _sufficientSubset(root->right,limit - root->val);
        if(left == false) root->left = nullptr;
        if(right == false) root->right = nullptr;
        return left || right;    
    }

public:
    TreeNode* sufficientSubset(TreeNode* root, int limit) {
        auto ans = _sufficientSubset(root,limit);
        if(ans) return root;
        return nullptr;
    }
};