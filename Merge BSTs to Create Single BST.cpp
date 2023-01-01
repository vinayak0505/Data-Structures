
// Definition for a binary tree node.
#include "vector"
#include "unordered_map"

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
    bool valid = true;
    vector<TreeNode*> trees;
    unordered_map<int,int> mp;
    vector<bool> vis;
    int count = 0;

    void merge(TreeNode* edge,int mi,int ma){
        if(edge == nullptr) return;
        if(ma <= edge->val || edge->val <= mi){
            valid = false;
            return;
        }
        if(mp.count(edge->val)){
            int pos = mp[edge->val];
            if(vis[pos]) return;
            if(edge != trees[pos]){
                auto tree = trees[pos];
                vis[pos] = true;
                count++;
                if(tree->left && edge->left){
                    valid = false;
                    return;
                }
                if(tree->right && edge->right){
                    valid = false;
                    return;
                }
                edge->right = tree->right ? tree->right : edge->right;
                edge->left = tree->left ? tree->left : edge->left;
            }
        }
        merge(edge->left,mi,edge->val);
        merge(edge->right,edge->val,ma);
    }

    void check(TreeNode* edge,int mi,int ma){
        if(edge == nullptr) return;
        if(ma <= edge->val || edge->val <= mi){
            valid = false;
            return;
        }
        check(edge->left,mi,edge->val);
        check(edge->right,edge->val,ma);
    }
public:
    TreeNode* canMerge(vector<TreeNode*>& trees) {
        TreeNode* head;
        this->trees = trees;
        vis = vector<bool>(trees.size());
        for(int i = 0;i<trees.size();i++){
            mp[trees[i]->val] = i;
        }
        for(int i = 0;i<trees.size();i++){
            if(vis[i]) continue;
            head = trees[i];
            merge(head,INT32_MIN,INT32_MAX);
            if(valid == false) return nullptr;
        }
        if(count != trees.size() - 1) return nullptr;
        check(head,INT32_MIN,INT32_MAX);
        if(valid) return head;
        return nullptr;
    }
};