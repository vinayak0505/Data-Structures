#include "unordered_map"
#include "vector"

using namespace std;

class SummaryRanges {
    unordered_map<int,int> mp;
public:
    SummaryRanges() {
        
    }
    
    void addNum(int value) {
        if(mp.count(value - 1)){
            mp[value] = mp[value - 1];
        }else mp[value] = value;
        if(mp.count(value + 1)){
            mp[value + 1] = mp[value];
        }
    }
    int parent(int value){
        if(mp[value] == value) return value;
        return mp[value] = parent(mp[value]);
    }
    
    vector<vector<int>> getIntervals() {
        unordered_map<int,vector<int>> ans;
        for(auto a:mp){
            int par = parent(a.second);
            if(ans.count(par) == 0)
                ans[par] = {par,par};
            auto &v = ans[par];
            v.front() = min(v.front(),a.first);
            v.back() = max(v.back(),a.first);
        }
        vector<vector<int>> sol;
        for(auto a: ans){
            sol.push_back(a.second);
        }
        sort(sol.begin(),sol.end());
        return sol;
    }
};

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges* obj = new SummaryRanges();
 * obj->addNum(value);
 * vector<vector<int>> param_2 = obj->getIntervals();
 */