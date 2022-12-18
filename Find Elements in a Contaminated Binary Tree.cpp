
#include "unordered_set";
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

class FindElements {
    unordered_set<int> st;
    void travel(TreeNode* root,int value){
        if(root == nullptr) return;
        st.insert(value);
        travel(root->left,value * 2 + 1);
        travel(root->right,value * 2 + 2);
    }
public:
    FindElements(TreeNode* root) {
        travel(root,0);
    }
    
    bool find(int target) {
        return st.count(target);
    }
};

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements* obj = new FindElements(root);
 * bool param_1 = obj->find(target);
 */