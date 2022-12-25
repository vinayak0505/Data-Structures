
#include "utility"
// Definition for a binary tree node.
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

    int ans = 0;
    // count, sum
    pair<int,int> solve(TreeNode* root){
        if(root == nullptr) return {0,0};
        auto left = solve(root->left);
        auto right = solve(root->right);
        if((left.second + right.second == 0) || 
        (left.first + right.first + root->val)/(left.second + right.second + 1) 
        == root->val) {
            ans++;
        }
        return {(left.first + right.first + root->val),left.second + right.second + 1};
    }
public:
    int averageOfSubtree(TreeNode* root) {
        solve(root);
        return ans;
    }
};