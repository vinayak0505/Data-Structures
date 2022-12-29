#include "vector"
#include "utility"
#include "string"
#include "sstream"

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

    TreeNode* solve(int depth,const vector<pair<int,int>>&v, int &pos){
        if(pos == v.size() || v[pos].first != depth) return nullptr;
        auto val = new TreeNode(v[pos++].second);
        val->left = solve(depth + 1, v, pos);
        val->right = solve(depth + 1, v, pos);
        return val;
    }
public:
    TreeNode* recoverFromPreorder(string t) {
        vector<pair<int,int>> v;
        stringstream ss(t);
        int i = 0;
        while(getline(ss,t,'-')){
            if(t == ""){
                i++;
            }else {
                v.push_back({i,stoi(t)});
                i = 1;
            }
        }
        int pos = 0;
        return solve(0,v,pos);
    }
};