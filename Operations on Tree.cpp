
#include "vector";

using namespace std;
class LockingTree {
    vector<int> parent;
    vector<int> locked;
    vector<vector<int>> children;
public:
    LockingTree(vector<int>& parent):parent(parent) {
        locked = vector<int>(parent.size(), -1);
        children = vector<vector<int>>(parent.size());
        for(int i = 0;i<children.size();i++){
            if(parent[i] == -1) continue;
            children[parent[i]].push_back(i);
        }
    }
    
    bool lock(int num, int user) {
        if(locked[num] != -1) return false;
        locked[num] = user; 
        return true;
    }
    
    bool unlock(int num, int user) {
        if(locked[num] == user){
            locked[num] = -1;
            return true;
        }
        return false;
    }
    
    bool lockChild(int user){
        if(user == -1) return false;
        for(int i : children[user]){
            if(locked[i] != -1 || lockChild(i)) 
                return true;
        }
        return false;
    }

    bool lockParent(int user){
        if(user == -1) return true;
        if(locked[user] != -1 || lockParent(parent[user]) == false) 
            return false;
        return true;
    }
    bool upgrade(int num, int user) {
        if(lockParent(num) && lockChild(num)){
            locked[num] = user;
            return true;
        }
        return false;
    }
};

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree* obj = new LockingTree(parent);
 * bool param_1 = obj->lock(num,user);
 * bool param_2 = obj->unlock(num,user);
 * bool param_3 = obj->upgrade(num,user);
 */