
#include "iostream";

using namespace std;
// Definition for a binary tree node.
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
    // first = left second = right
    int ans = 0;
    pair<int, int> _longestZigZag(TreeNode *root)
    {
        if (root == nullptr)
            return {0, 0};
        auto left = _longestZigZag(root->left);
        left.second++;
        ans = max(ans, left.second);
        auto right = _longestZigZag(root->right);
        right.first++;
        ans = max(ans, right.first);
        return {left.second, right.first};
    }

public:
    int longestZigZag(TreeNode *root)
    {
        if (root == nullptr)
            return 0;
        _longestZigZag(root);
        return ans - 1;
    }
};