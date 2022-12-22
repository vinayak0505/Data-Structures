
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
    int ans = 0;
    
    vector<int> solve(TreeNode* root,int d,int p){
        if(root == nullptr) return {};
        if(root->left == nullptr && root->right == nullptr){
            return {d};
        }
        return sol(solve(root->left,d+1,p),solve(root->right,d+1,p),p + d + d);
        
    }

    vector<int> sol(vector<int> a, vector<int>b,int d){
        vector<int> v = b;
        for(int i = 0;i<a.size();i++){
            v.push_back(a[i]);
            for(int j = 0;j<b.size();j++){
                if(d >= a[i] + b[j]) ans++;
            }
        }

        return v;
    }
public:
    int countPairs(TreeNode* root, int distance) {
        solve(root,0,distance);
        return ans;
    }
};