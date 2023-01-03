#include "vector"
using namespace std;
class Solution {

    int sum(
        int node,
        int par,
        const vector<vector<int>>& graph,
        const vector<int>& nums
    ){
        int ans = nums[node];
        for(int child:graph[node]){
            if(par == child) continue;
            ans += sum(child,node,graph,nums);
        }
        return ans;
    }
    int count;
    int solve(
        int node,
        int par,
        const vector<vector<int>>& graph,
        const vector<int>& nums,
        int val
    ){
        int ans = nums[node];
        if(ans > val) return -1;
        for(int child: graph[node]){
            if(child == par) continue;
            int a = solve(child,node,graph,nums,val);
            if(a == -1) return -1;
            ans += a;
            if(ans > val) return -1;
        }
        if(ans != val) return ans;
        count++;
        return 0;
    }
public:
    int componentValue(vector<int>& nums, vector<vector<int>>& edges) {

        vector<vector<int>> graph(nums.size());
        for(auto &edge: edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }

        int total = sum(0,-1,graph,nums);
        vector<int> rem;
        for(int i = 1;i * i<=total;i++){
            if(total%i) continue;
            rem.push_back(total/i);
            count = 0;
            if(solve(0,-1,graph,nums,i) == 0) return count - 1;
        }
        for(int j = rem.size() - 1; j>0; j--){
            int i = rem[j];
            count = 0;
            if(solve(0,-1,graph,nums,i) == 0) return count - 1;
        }
        return 0;
    }
};