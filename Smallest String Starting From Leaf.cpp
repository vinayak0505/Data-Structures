#include "iostream"

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
    char toChar(int val){
        return 'a' + val;
    }
    string cmin(string a,string b){
        int to = min(a.size(),b.size());
        for(int i = 0;i<to;i++){
            if(a[i] == b[i]) continue;
            if(a[i] < b[i]) return b;
            return a;
        }
        if(a.size() > b.size()) return a;
        return b;
    }
public:
    string smallestFromLeaf(TreeNode* root) {
        if(root == nullptr) return ""; 
        return cmin(smallestFromLeaf(root->left),smallestFromLeaf(root->right)) + toChar(root->val);
    }
};