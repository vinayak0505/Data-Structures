
#include "vector"
#include "unordered_set"
#include "unordered_map"

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
    unordered_map<int,TreeNode*> mp;
    int head = 0;

    void findHead(vector<vector<int>>& descriptions){
        unordered_set<int> st;
        for(auto &description:descriptions){
            st.insert(description[1]);
        }
        for(auto &description:descriptions){
            if(st.count(description[0]) == 0){
                head = description[0];
                return;
            }
        }
    }

    void fill(vector<vector<int>>& descriptions){
        for(auto &d:descriptions){
            if(mp.count(d[0]) == 0){
                mp[d[0]] = new TreeNode(d[0]);
            }
            if(mp.count(d[1]) == 0){
                mp[d[1]] = new TreeNode(d[1]);
            }
            if(d[2]) mp[d[0]]->left = mp[d[1]];
            else mp[d[0]]->right = mp[d[1]];
        }
    }
public:
    TreeNode* createBinaryTree(vector<vector<int>>& descriptions) {
        fill(descriptions);
        findHead(descriptions);
        return mp[head];
    }
};