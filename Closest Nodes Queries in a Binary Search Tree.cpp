
using namespace std;
#include "vector"

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
    vector<int> v;

    void fill(TreeNode* root){
        if(root == nullptr) return;
        v.push_back(root->val);
        fill(root->left);
        fill(root->right);
    }

    int findg(int value){
        int start = 0,end = v.size() - 1;
        int ans = -1;
        while(start <= end){
            int mid = (start + end)/2;
            if(v[mid] >= value){
                ans = v[mid];
                end = mid - 1;
            }else start = mid + 1;
        }
        return ans;
    }

    int finds(int value){
        int start = 0,end = v.size() - 1;
        int ans = -1;
        while(start <= end){
            int mid = (start + end)/2;
            if(v[mid] <= value){
                ans = v[mid];
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return ans;
    }
public:
    vector<vector<int>> closestNodes(TreeNode* root, vector<int>& queries) {
        fill(root);
        sort(v.begin(),v.end());
        vector<vector<int>> ans(queries.size());
        for(int i = 0;i<queries.size();i++){
            ans[i] = {finds(queries[i]),findg(queries[i])};
        }
        return ans;
    }
};