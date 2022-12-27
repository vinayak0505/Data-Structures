#include "utility"
#include "vector"

using namespace std;

class Solution {

    // first pasenges, second petrol
    pair<int,long long> solve(const vector<vector<int>> &graph,int parent,int edge,int s){
        pair<int,long long> ans = {1,0};
        for(int child:graph[edge]){
            if(child == parent) continue;
            auto val = solve(graph,edge,child,s);
            ans.second += val.second;
            ans.second += val.first/s + (val.first%s != 0);
            ans.first += val.first;
        }
        return ans;
    }
public:
    long long minimumFuelCost(vector<vector<int>>& roads, int seats) {
        vector<vector<int>> graph(roads.size() + 1);
        for(auto &road:roads){
            graph[road[0]].push_back(road[1]);
            graph[road[1]].push_back(road[0]);
        }
        auto ans = solve(graph,-1,0,seats);
        return ans.second;
    }
};