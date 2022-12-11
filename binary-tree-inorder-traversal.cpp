
// Definition for a binary tree node.
#include "stack"
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
    void insertLeft(TreeNode* head, 
                    stack<TreeNode*> &st, 
                    vector<int> &ans){
        while(head){
            st.push(head);
            head = head->left;
        }
    }
public:
    vector<int> inorderTraversal(TreeNode* root) {
        vector<int> ans;
        stack<TreeNode*> st;
        insertLeft(root,st,ans);
        while(st.size()){
            auto top = st.top();
            st.pop();
            ans.push_back(top->val);
            insertLeft(top->right,st,ans);
        }
        return ans;
    }
};