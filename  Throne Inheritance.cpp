#include "unordered_map"
#include "unordered_set"
#include "vector"
#include "string"

using namespace std;

class ThroneInheritance {
    unordered_map<string,vector<string>> inmap;
    unordered_set<string> st;
    string root;
public:
    ThroneInheritance(string kingName) {
        root = kingName;
    }
    
    void birth(string parentName, string childName) {
        inmap[parentName].push_back(childName);
    }
    
    void death(string name) {
        st.insert(name);
    }
    
    vector<string> getInheritanceOrder() {
        vector<string> ans;
        dfs(root,ans);
        return ans;
    }

    void dfs(string &root,vector<string> &ans){
        if(st.count(root) == 0)
            ans.push_back(root);
        for(auto &child:inmap[root])dfs(child,ans);
    }
};

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance* obj = new ThroneInheritance(kingName);
 * obj->birth(parentName,childName);
 * obj->death(name);
 * vector<string> param_3 = obj->getInheritanceOrder();
 */