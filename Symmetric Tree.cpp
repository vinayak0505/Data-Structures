#include "queue"

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
public:
    bool isSymmetric(TreeNode* root) {
        if(root == nullptr) return true;
        TreeNode* left = root->left,* right = root->right;
        queue<pair<TreeNode*,TreeNode*>> st;
        st.push({left,right});
        while(st.size()){
            auto data = st.front();
            st.pop();
            if(data.first && data.second){
                // cout<<data.first->val<<" "<<data.second->val<<endl;
                if(data.first->val != data.second->val) return false;
                st.push({data.first->left,data.second->right});
                st.push({data.first->right,data.second->left});
            }else if(data.first || data.second) return false;
        }
        return true;
    }
};