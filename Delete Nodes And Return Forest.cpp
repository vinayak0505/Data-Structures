// Definition for a binary tree node.

#include "vector"

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
    vector<TreeNode*> ans;
    bool contains(int root,vector<int>& to_delete){
        for(int i:to_delete){
            if(i == root) return true;
        }
        return false;
    }
    TreeNode* _delNodes(TreeNode* root, vector<int>& to_delete) {
        if(root->left == nullptr && root->right == nullptr){
            if(contains(root->val,to_delete)){
                delete root;
                return nullptr;
            }
            return root;
        }
        if(root->left)
        root->left = _delNodes(root->left,to_delete);
        if(root->right)
        root->right = _delNodes(root->right,to_delete);
        if(contains(root->val,to_delete)){
            if(root->left) ans.push_back(root->left);
            if(root->right) ans.push_back(root->right);
            delete root;
            return nullptr;
        }
        return root;
    }
public:
    vector<TreeNode*> delNodes(TreeNode* root, vector<int>& to_delete) {
        if(root == nullptr || to_delete.size() == 0) return ans;
        if(!contains(root->val,to_delete))
        ans.push_back(root);
        _delNodes(root,to_delete);
        return ans;
    }
};