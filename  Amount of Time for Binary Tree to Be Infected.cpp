
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
    vector<int> cc;
    // infected max without infected
    pair<bool, int> fill2(TreeNode* root,int start){
        if(root == nullptr) return {false,0};
        pair<bool,int> left = fill2(root->left,start);
        pair<bool,int> right = fill2(root->right,start);
        if(root->val == start){
            cc[root->val] = max(left.second,right.second);
            return {true,0};
        }
        if(left.first){
            cc[root->val] = right.second;
            return {true,0};
        }
        if(right.first){
            cc[root->val] = left.second;
            return {true,0};
        }
        cc[root->val] = max(left.second,right.second);
        return {false,cc[root->val] + 1};
    }
    int ans1 = 0;
    void ans(TreeNode* root,int ps,int start){
        if(root == nullptr) return;
        if(root->val == start){
            ans1 = max(ps,cc[root->val]);
            return;
        }
        ans(root->left,max(ps,cc[root->val]) + 1,start);
        ans(root->right,max(ps,cc[root->val]) + 1,start);
    }
public:
    int amountOfTime(TreeNode* root, int start) {
        cc = vector<int>(1e5 + 1);
        fill2(root,start);
        ans(root,cc[root->val],start);
        return ans1;
    }
};