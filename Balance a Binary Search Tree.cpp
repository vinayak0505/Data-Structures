
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
    vector<int> arr;
    void fillArr(TreeNode* root){
        if(root == nullptr) return;
        fillArr(root->left);
        arr.push_back(root->val);
        fillArr(root->right);
    }

    TreeNode *createTree(int start, int end){
        if(start > end) return nullptr;
        int mid = (start + end)/2;
        auto p = new TreeNode(arr[mid]);
        p->left = createTree(start,mid - 1);
        p->right = createTree(mid + 1,end);
        return p;
    }
public:
    TreeNode* balanceBST(TreeNode* root) {
        fillArr(root);
        return createTree(0,arr.size() - 1);
    }
};