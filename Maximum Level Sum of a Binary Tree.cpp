
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
    int maxLevelSum(TreeNode* root) {
        queue<TreeNode*> q;
        int sum = INT32_MIN;
        int ans = 1;
        int level = 1;
        q.push(root);
        while(q.size()){
            int size = q.size();
            int temp_sum = 0;
            while(size--){
                auto front = q.front();
                q.pop();
                temp_sum += front->val;
                if(front->left) q.push(front->left);
                if(front->right) q.push(front->right);
            }
            if(temp_sum > sum){
                sum = temp_sum;
                ans = level;
            }
            level++;
        }
        return ans;
    }
};