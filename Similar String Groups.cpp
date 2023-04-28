#include "string"
#include "vector"
#include "unordered_set"

using namespace std;

class Solution {
public:

    bool same(string& s1, string& s2){
        int count = 0;
        for(int i = 0;i<s1.size();i++){
            if(s1[i] != s2[i]){
                count++;
                if(count > 2) return false;
            }
        }
        return count == 0 || count == 2;
    }

    int numSimilarGroups(vector<string>& strs) {
        int count = 0;
        unordered_set<string> vis;
        for(string& str: strs){
            if(vis.count(str)) continue;
            dfs(str,strs,vis);
            count++;
        }
        return count;
    }

    void dfs(string& s, vector<string>& strs, unordered_set<string> &vis){
        if(vis.count(s)) return;
        vis.insert(s);
        for(string& str: strs){
            if(same(s,str)){
                dfs(str,strs,vis);
            }
        }
    }
};