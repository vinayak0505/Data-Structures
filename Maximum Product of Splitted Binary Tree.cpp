
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
    const int mod = 1e9 + 7;
    long long ans = 0;
    
    TreeNode* treeSum(TreeNode* root){
        if(root == nullptr) return nullptr;
        auto left = treeSum(root->left);
        auto right = treeSum(root->right);
        auto newnode = new TreeNode(root->val + value(left) + value(right),
        left,right);
        return newnode;
    }

    int value(TreeNode* node){
        if(node == nullptr) return 0;
        return node->val;
    }

    long long solve(TreeNode* root,TreeNode* sumroot, long long val){
        if(root == nullptr) return 0;
        long long left = solve(root->left,
        sumroot->left,val + root->val + value(sumroot->right));
        long long right = solve(root->right,
        sumroot->right,val + root->val + value(sumroot->left));
        if(left > right) ans = max(ans,left * (right + root->val + val));
        else ans = max(ans,right * (left + root->val + val));
        return left + right + root->val;
    }

public:
    int maxProduct(TreeNode* root) {
        solve(root,treeSum(root),0);
        return ans % mod;
    }
};