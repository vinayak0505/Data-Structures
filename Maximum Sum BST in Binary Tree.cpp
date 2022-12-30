
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
    class Data{
        public:
        bool isBst = true;
        int sum = 0;
        int min = 0;
        int max = 0;

        Data(){}
        Data(int num){
            sum = min = max = num;
            isBst = false;
        }
    };
    int max(int a,int b){
        if(a > b) return a;
        return b;
    }
    int ans = 0;
    Data solve(TreeNode* root) {
        if(root == nullptr) return Data();
        auto v = Data(root->val);
        auto left = solve(root->left);
        auto right = solve(root->right);
        if(left.isBst && right.isBst && 
        (root->left == nullptr || left.max < root->val)
         && (root->right == nullptr || root->val < right.min)){
            v.isBst = true;
            if(root->left) v.min = left.min;
            if(root->right) v.max = right.max;
            v.sum += left.sum + right.sum;
            ans = max(ans,v.sum);
            return v;
        }
        return v;
    }
public:
    int maxSumBST(TreeNode* root) {
        solve(root);
        return ans;
    }
};