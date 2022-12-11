
//   Definition for a binary tree node.
#include "algorithm"
using namespace std;
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

    int _max(int head_value, int left_max, int right_max)
    {
        return max({head_value, left_max + head_value, right_max + head_value});
    }

    /**
     * @return first - maximum value possible
     * @return second - maximum value possible starting from node
     */
    pair<int, int> _maxPathSum(const TreeNode *root)
    {
        pair<int, int> ans = {0, 0};
        if (root == nullptr)
            return ans;
        if (root->left == nullptr && root->right == nullptr)
            return {root->val, root->val};
        if (root->left == nullptr)
        {
            auto right = _maxPathSum(root->right);
            ans.second = max(right.second + root->val,root->val);
            ans.first = max(right.first,ans.second);
            return ans;
        }
        if (root->right == nullptr)
        {
            auto left = _maxPathSum(root->left);
            ans.second = max(left.second + root->val,root->val);
            ans.first = max(left.first,ans.second);
            return ans;
        }
        auto left = _maxPathSum(root->left);
        auto right = _maxPathSum(root->right);
        ans.second = _max(root->val, left.second, right.second);
        ans.first = max({left.first, right.first, ans.second,
                         root->val + left.second + right.second});
        return ans;
    }

public:
    int maxPathSum(TreeNode *root)
    {
        auto value = _maxPathSum(root);
        return value.first;
    }
};