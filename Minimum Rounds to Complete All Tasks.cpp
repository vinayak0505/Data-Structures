#include "vector"
#include "unordered_map"

using namespace std;
class Solution {
public:
    int minimumRounds(vector<int>& tasks) {
        unordered_map<int,int> mp;
        for(int task: tasks) mp[task]++;
        int ans = 0;
        for(auto m:mp){
            if(m.second == 1) return -1;
            ans += m.second / 3;
            if(m.second % 3) ans++;
        }
        return ans;
    }
};