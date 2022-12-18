
#include "vector";

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
    void travel(TreeNode* root, vector<int> &arr){
        if(root == nullptr) return;
        travel(root->left,arr);
        arr.push_back(root->val);
        travel(root->right,arr);
    }
    vector<int> merge(vector<int> const &a1,vector<int> const &a2){
        vector<int> ans;
        int i = 0,j = 0;
        while(i<a1.size() && j < a2.size()){
            if(a1[i] < a2[j]) ans.push_back(a1[i++]);
            else ans.push_back(a2[j++]);
        }
        while(i < a1.size()) ans.push_back(a1[i++]);
        while(j < a2.size()) ans.push_back(a2[j++]);
        return ans;
    }
public:
    vector<int> getAllElements(TreeNode* root1, TreeNode* root2) {
        vector<int> a1,a2;
        travel(root1,a1);
        travel(root2,a2);
        return merge(a1,a2);
    }
};