#include "vector"

using namespace std;

class Solution {
public:
    vector<vector<int>> reconstructQueue(vector<vector<int>>& people) {
        sort(people.begin(),people.end(),[](vector<int>&a,vector<int>&b){
            if(a.front() > b.front()) return true;
            if(a.front() < b.front()) return false;
            if(a.back() < b.back()) return true;
            return false;
        });
        vector<vector<int>> ans;
        for(auto i:people){
            if(i.back() == ans.size()){
                ans.push_back(i);
            }else{
                ans.insert(ans.begin() + i.back(),i);
            }
        }
        return ans;
    }
};