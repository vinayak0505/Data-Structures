
// Definition for a binary tree node.
#include "math.h"

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
    int ans = 0;
    void distribute(TreeNode *parent, TreeNode *root)
    {
        if (root == nullptr)
            return;
        distribute(root, root->left);
        distribute(root, root->right);
        root->val--;
        if (root->val)
            parent->val += root->val;
        ans += abs(root->val);
    }

public:
    int distributeCoins(TreeNode *root)
    {
        distribute(nullptr, root);
        return ans;
    }
};