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
    TreeNode* reverseOddLevels(TreeNode* root) {
        queue<TreeNode*> q;
        q.push(root);
        bool odd = true;
        while(q.size()){
            int size = q.size();
            vector<TreeNode*> v;
            while(size--){
                if(q.front()->left) q.push(q.front()->left);
                if(q.front()->right) q.push(q.front()->right);
                if(odd){
                    if(q.front()->left) v.push_back(q.front()->left);
                    if(q.front()->right) v.push_back(q.front()->right);
                }
                q.pop();
            }
            if(odd){
                for(int i = 0;i<v.size()/2;i++){
                    swap(v[i]->val,v[v.size() - i - 1]->val);
                }
            }
            odd = !odd;
        }
        return root;
    }
};