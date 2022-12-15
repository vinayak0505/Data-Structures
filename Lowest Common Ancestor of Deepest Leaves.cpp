
// Definition for a binary tree node.

#include "utility"

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

    pair<TreeNode*,int> lcaDeepestLeaves(TreeNode* root,int depth){
        if(root->left == nullptr && root->right == nullptr)
        return {root,depth};
        pair<TreeNode*,int> left = {root,depth},right = {root,depth};
        if(root->left) left = lcaDeepestLeaves(root->left,depth+1);
        if(root->right) right = lcaDeepestLeaves(root->right,depth+1);
        if(left.second == right.second) return {root,left.second};
        return left.second > right.second ? left : right;
    }
public:
    TreeNode* lcaDeepestLeaves(TreeNode* root) {
        return lcaDeepestLeaves(root,0).first;
    }
};