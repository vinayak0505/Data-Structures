
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
    int ans = 0;

    /**
    * @return 0 need camera, 1 has security, 2 = has camera,3 null
    */
    int solve(TreeNode* root){
        if(root == nullptr) return 3;
        int left = solve(root->left);
        int right = solve(root->right);
        if(left == 0 || right == 0){
            ans++;
            return 2;
        }
        if(left == 2 || right == 2) return 1;
        return 0;
    }
public:
    int minCameraCover(TreeNode* root) {
        int a = solve(root);
        return (a == 0) + ans;
    }
};