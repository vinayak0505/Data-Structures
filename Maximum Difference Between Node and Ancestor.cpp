// Definition for a binary tree node.
#include "iostream"
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
    int maxAncestorDiff(TreeNode* root,int mi,int ma) {
        if(root == nullptr) return ma - mi;
        mi = min(mi,root->val);
        ma = max(ma,root->val);
        return max(maxAncestorDiff(root->left,mi,ma),
        maxAncestorDiff(root->right,mi,ma));
    }
public:
    int maxAncestorDiff(TreeNode* root) {
        return maxAncestorDiff(root,root->val,root->val);
    }
};