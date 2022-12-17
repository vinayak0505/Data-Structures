
#include <vector>

using namespace std;

class Solution {

    bool createTree(int pos, 
    vector<int>& leftChild, 
    vector<int>& rightChild,
    vector<bool>& vis,
    vector<bool>& tempvis){
        if(pos == -1) return true;
        if(tempvis[pos]) return false;
        if(vis[pos]) return true;
        vis[pos] = true;
        tempvis[pos] = true;

        // simply reaturing value form left node and right node
        bool ans = (createTree(leftChild[pos],leftChild,rightChild,vis,tempvis) &&
        createTree(rightChild[pos],leftChild,rightChild,vis,tempvis));
        return ans;
    }
public:
    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        vector<bool> vis(n,false);
        vector<bool> tv(n,false);
        for(int i = 0;i<n;i++){
            if(vis[i]) continue;
            if(createTree(i,leftChild,rightChild,vis,tv) == false) return false;
            tv = vector(n,false);
            vis[i] = false;
        }
        bool flag = false;
        for(auto i:vis) if(i == false){
            if(flag) return false; 
            else flag = true;
        }
        return true;
    }
};