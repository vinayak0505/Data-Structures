
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
    bool isEvenOddTree(TreeNode* root) {
        if(root == nullptr) return true;
        queue<TreeNode*> q;
        q.push(root);
        bool even = true;
        while(q.size()){
            int size = q.size();
            int pre = even ? INT32_MIN : INT32_MAX;
            while(size--){
                if(even){
                    if(q.front()->val % 2 == 0 ||
                    q.front()->val <= pre) return false;
                }else {
                    if(q.front()->val % 2 == 1 || 
                    q.front()->val >= pre) return false;
                }
                pre = q.front()->val;
                if(q.front()->left) q.push(q.front()->left);
                if(q.front()->right) q.push(q.front()->right);
                q.pop();
            }
            even = !even;
        }
        return true;
    }
};