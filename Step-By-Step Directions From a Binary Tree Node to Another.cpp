
#include "string"

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

    bool findPath(TreeNode* root, int node,string& ans){
        if(root == nullptr) return false;
        if(root->val == node) return true;
        ans += 'L';
        if(findPath(root->left,node,ans)){
            return true;
        }
        ans.pop_back();
        ans += 'R';
        if(findPath(root->right,node,ans)){
            return true;
        }
        ans.pop_back();
        return false;
    }
public:
    string getDirections(TreeNode* root, int startValue, int destValue) {
        string a,b;
        findPath(root,startValue,a);
        findPath(root,destValue,b);
        int i = 0;
        for(;i<a.size() && b.size();i++){
            if(a[i] != b[i]) break;
        }
        string ans;
        for(int j = i;j<a.size();j++){
            ans += 'U';
        }
        for(;i<b.size();i++){
            ans += b[i];
        }
        return ans;
    }
};