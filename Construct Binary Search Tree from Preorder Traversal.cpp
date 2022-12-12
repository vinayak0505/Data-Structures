
// Definition for a binary tree node.
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

    int till(int val,vector<int>& preorder,int from,int end){
        while(from <= end && preorder[from] < val){
            from++;
        }
        return from;
    }
    TreeNode* bstFromPreorder(vector<int>& preorder,int start,int end) {
        if(start > end) return nullptr;
        int val = preorder[start];
        auto node = new TreeNode(val);
        int mid = till(val,preorder,start + 1,end);
        node->left = bstFromPreorder(preorder,start + 1,mid - 1);
        node->right = bstFromPreorder(preorder,mid, end);
        return node;
    }
public:
    TreeNode* bstFromPreorder(vector<int>& preorder) {
        return bstFromPreorder(preorder,0,preorder.size() - 1);
    }
};