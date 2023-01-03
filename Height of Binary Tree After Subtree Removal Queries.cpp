#include "unordered_map"
#include "vector"

using namespace std;
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
    unordered_map<int,int> dp;
    unordered_map<int,pair<int,int>> height;

    int solve(TreeNode* root){
        if(root == nullptr) return 0;
        int left = solve(root->left);
        int right = solve(root->right);
        height[root->val] = {left,right};
        return max(left,right) + 1;
    }

    void solve(TreeNode* root,int depth,int m){
        if(root == nullptr) return;
        dp[root->val] = m;
        solve(root->left,depth+1,
        max(m,depth + 1 + height[root->val].second));
        solve(root->right,depth+1,
        max(m,depth + 1 + height[root->val].first));
        return;
    }
public:
    vector<int> treeQueries(TreeNode* root, vector<int> q) {
        solve(root);
        solve(root,0,0);
        for(int &i:q) i = dp[i] - 1;
        return q;
    }
};