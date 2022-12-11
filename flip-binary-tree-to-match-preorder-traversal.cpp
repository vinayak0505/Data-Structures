#include "stack"
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
    vector<int> ans;
    bool _flipMatchVoyage(TreeNode* root, vector<int>& voyage,int &index) {
        if(root == nullptr) return true;
        if(root->val != voyage[index]) return false;
        index++;
        bool left = _flipMatchVoyage(root->left,voyage,index);
        if(left == false){
            bool right = _flipMatchVoyage(root->right,voyage,index);
            if(right == false) return false;
            ans.push_back(root->val);
            return _flipMatchVoyage(root->left,voyage,index);
        }
        return _flipMatchVoyage(root->right,voyage,index);
    }
public:
    vector<int> flipMatchVoyage(TreeNode* root, vector<int>& voyage) {
        int index = 0;
        auto pos = _flipMatchVoyage(root,voyage,index);
        if(pos) return ans;
        return {-1};
    }
};