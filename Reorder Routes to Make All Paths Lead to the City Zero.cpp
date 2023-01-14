#include "vector"

using namespace std;

class Solution {
    int ans = 0;
    void join(
        int edge,
        int par,
        vector<vector<int>>& from,
        vector<vector<int>>& to)
    {
        for(int child: from[edge]){
            if(child == par) continue;
            join(child,edge,from,to);
        }
        for(int child: to[edge]){
            if(child == par) continue;
            ans++;
            join(child,edge,from,to);
        }
    }
public:
    int minReorder(int n, vector<vector<int>>& connections) {
        vector<vector<int>> from(n);
        vector<vector<int>> to(n);

        for(auto c:connections){
            to[c.front()].push_back(c.back());
            from[c.back()].push_back(c.front());
        }
        join(0,-1,from,to);
        return ans;
    }
};