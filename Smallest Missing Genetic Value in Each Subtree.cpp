#include "vector"

using namespace std;

class Solution {
    vector<int> ans;
    vector<int> ones;
    vector<bool> contains;
    // hasOne ans
    bool getOne(
        int edge,
        const vector<vector<int>> &graph,
        const vector<int> &nums
    ){
        if(nums[edge] == 1) {
            ones.push_back(edge);
            return true;
        }

        for(int child:graph[edge]){
            if(getOne(child,graph,nums)){
                ones.push_back(edge);
                return true;
            }
        }

        return false;
    }

    void fill(int edge,const vector<vector<int>> &graph,
    vector<int>& nums){
        contains[nums[edge]] = true;
        for(int child:graph[edge]) fill(child,graph,nums);
    }

    void solve(int& from,int cur,int oc,
    const vector<vector<int>> &graph,vector<int>& nums){
        contains[nums[cur]] = true;
        for(int child:graph[cur]){
            if(child == oc) continue;
            fill(child,graph,nums);
        }
        while(from < contains.size() && contains[from]) from++;
        ans[cur] = from;
    }
public:
    vector<int> smallestMissingValueSubtree(vector<int>& parents, vector<int>& nums) {
        ans = vector<int>(nums.size(),1);
        contains = vector<bool>(nums.size() + 1);
        vector<vector<int>> graph(nums.size());
        for(int i = 1;i<nums.size();i++){
            graph[parents[i]].push_back(i);
        }
        getOne(0,graph,nums);
        int pre = -1;
        int from = 1;
        for(int i:ones){
            solve(from,i,pre,graph,nums);
            pre = i;
        }
        return ans;
    }
};