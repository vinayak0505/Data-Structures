
#include "string"
#include "vector"
#include "sstream"

using namespace std;
// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Codec {
    string ans;
    int i;
    void solve(TreeNode* root){
        if(root == nullptr) {
            ans += "n,";
            return;
        }
        ans += to_string(root->val) + ",";
        solve(root->left);
        solve(root->right);
    }

    TreeNode* solve(vector<int> &v){
        i++;
        if(v[i] == 1e5){
            return nullptr;
        }
        auto node = new TreeNode(v[i]);
        node->left = solve(v);
        node->right = solve(v);
        return node;
    }
public:
    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        ans = "";
        solve(root);
        return ans;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        vector<int> v;
        stringstream ss(data);
        string a;
        while(getline(ss,a,',')){
            if(a == "n") v.push_back(1e5);
            else v.push_back(stoi(a));
        }
        i = -1;
        return solve(v);
    }
};