
#include "vector"
#include "unordered_map"

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
    unordered_map<int,int> mp;
    int treeSum(TreeNode *root){
        if(root == nullptr) return 0;
        root->val += treeSum(root->left);
        root->val += treeSum(root->right);
        mp[root->val]++;
        return root->val;
    }
    
public:
    vector<int> findFrequentTreeSum(TreeNode* root) {
        treeSum(root);
        vector<int> ans;
        int count = 0;
        for(auto m:mp){
            if(m.second > count){
                ans = vector<int>();
                count = m.second;
            }
            if(m.second == count){
                ans.push_back(m.first);
            }
        }
        return ans;
    }
};