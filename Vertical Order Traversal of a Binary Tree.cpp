
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
    vector<vector<int>> poso,nevo;
    void solve(TreeNode* root){
        queue<pair<TreeNode*,int>> q;
        q.push({root,0});
        int val;
        while(q.size()){
            int size = q.size();
            auto pos = poso,nev = nevo;
            while(size--){
                val = q.front().second;
                root = q.front().first;
                q.pop();
                if(val < 0){
                    int newv = -val - 1;
                    if(nev.size() == newv){
                        nev.push_back({});
                    }
                    nev[newv].push_back(root->val);
                }else{
                    if(pos.size() == val){
                        pos.push_back({});
                    }
                    pos[val].push_back(root->val);
                }
                if(root->left) q.push({root->left,val - 1});
                if(root->right) q.push({root->right,val + 1});
            }
            for(int i = 0;i<poso.size();i++){
                sort(pos[i].begin() + poso[i].size(),pos[i].end());
            }
            for(int i = 0;i<nevo.size();i++){
                sort(nev[i].begin() + nevo[i].size(),nev[i].end());
            }

            for(int i = poso.size();i < pos.size();i++){
                sort(pos[i].begin(),pos[i].end());
            }
            for(int i = nevo.size();i < nev.size();i++){
                sort(nev[i].begin(),nev[i].end());
            }
            poso = pos, nevo = nev;
        }
    }
public:
    vector<vector<int>> verticalTraversal(TreeNode* root) {
        solve(root);
        reverse(nevo.begin(),nevo.end());
        for(auto i: poso) nevo.push_back(i);
        return nevo;
    }
};