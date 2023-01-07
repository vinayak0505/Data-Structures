#include "unordered_map"
#include "string"
#include "vector"

using namespace std;

class Solution {
    unordered_map<string,pair<double,string>> mp;
    void solve(const string& edge,const string& par,double v,unordered_map<string,vector<pair<string,double>>>&graph){
        if(mp.count(edge)) return;
        mp[edge] = {v,par};
        for(auto child:graph[edge]){
            solve(child.first,par,v * child.second,graph);
        }
    }
public:
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        unordered_map<string,vector<pair<string,double>>> graph;
        for(int i = 0;i< values.size();i++){
            graph[equations[i].front()].push_back({equations[i].back(),values[i]});
            graph[equations[i].back()].push_back({equations[i].front(),1/values[i]});
        }
        for(auto &a:graph){
            solve(a.first,a.first,1,graph);
        }
        vector<double> ans;
        for(auto &query:queries){
            if(mp.count(query.back()) == 0 || mp.count(query.back()) == 0){
                ans.push_back(-1);
                continue;
            }
            if(mp[query.front()].second != mp[query.back()].second){
                ans.push_back(-1);
                continue;
            }
            ans.push_back(
                mp[query.back()].first/mp[query.front()].first
            );
        }
        return ans;
    }
};