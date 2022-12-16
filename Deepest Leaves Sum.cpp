#include "utility"

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

    // first - sum and second - node level
    pair<int,int>deepestLeavesSum(TreeNode* root,int depth){
        if(root->right == nullptr && root->left == nullptr){
            return {root->val,depth};
        }
        if(root->left == nullptr) 
        return deepestLeavesSum(root->right,depth + 1);
        if(root->right == nullptr) 
        return deepestLeavesSum(root->left,depth + 1);
        auto left = deepestLeavesSum(root->left,depth + 1);
        auto right = deepestLeavesSum(root->right,depth + 1);
        if(left.second == right.second){
            return {left.first + right.first,left.second};
        }
        if(left.second > right.second){
            return left;
        }
        return right;
    }
public:
    int deepestLeavesSum(TreeNode* root) {
        return deepestLeavesSum(root,0).first;
    }
};