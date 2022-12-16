
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
public:
    int sumEvenGrandparent(TreeNode* root,
    TreeNode* parent = nullptr, TreeNode* gp = nullptr) {
        if(root == nullptr) return 0;
        int ans = 0;
        if(gp && ((gp->val & 1) == 0)) ans += root->val;
        ans += sumEvenGrandparent(root->left,root,parent);
        ans += sumEvenGrandparent(root->right,root,parent);
        return ans;
    }
};