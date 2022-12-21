
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
    int ans = 0;
    void solve(TreeNode* root, int max){
        if(root == nullptr) return;
        if(root->val >= max){
            ans++;
            max = root->val;
        }
        solve(root->left, max);
        solve(root->right, max);
    }
public:
    int goodNodes(TreeNode* root) {
        solve(root,-1e5);
        return ans;
    }
};