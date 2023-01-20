#include "vector"
#include "unordered_map"
#include "string"

using namespace std;

class Solution {

    vector<string> ans;
    int total;
    bool solve(
        string edge,
        unordered_map<string,vector<string>>& mp,
        unordered_map<string,vector<bool>>& vis,
        int n
        ){
            if(n == total) return true;
            auto &child = mp[edge];
            auto &v = vis[edge];
            for(int i = 0;i<child.size();i++){
                if(v[i]) continue;
                v[i] = true;
                ans.push_back(child[i]);
                if(solve(child[i],mp,vis,n+1)) return true;
                v[i] = false;
                ans.pop_back();
            }
            return false;
        }
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        unordered_map<string,vector<string>> mp;
        total = tickets.size();
        unordered_map<string,vector<bool>> vis;
        for(auto ticket: tickets){
            mp[ticket.front()].push_back(ticket.back());
        }
        for(auto &m:mp){
            sort(m.second.begin(),m.second.end());
            vis[m.first] = vector<bool>(m.second.size());
        }
        ans.push_back("JFK");
        solve("JFK",mp,vis,0);
        return ans;
    }
};