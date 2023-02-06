#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        vector<int> res;
        unordered_map<int,int> freq;
        int maxFreq=0;
        for(int elem:nums){
            freq[elem]++;
            maxFreq = max(maxFreq,freq[elem]);
        }
        vector<vector<int>> vals(maxFreq+1);
        for(auto f:freq){
            vals[f.second].push_back(f.first);
        }
        int it=maxFreq;
        while(k>0){
            if(vals[it].size()==0){
                it--;
                continue;
            }
            res.push_back(vals[it][vals[it].size()-1]);
            vals[it].pop_back();
            k--;
        }
        return res;
    }
};