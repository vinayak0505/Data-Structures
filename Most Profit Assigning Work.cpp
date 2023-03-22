#include "vector"

using namespace std;

class Solution {
public:
    int maxProfitAssignment(vector<int>& d, vector<int>& p, vector<int>& w) {
        vector<pair<int,int>> ratio;
        for(int i = 0;i< d.size();i++){
            ratio.push_back({d[i],p[i]});
        }
        int m = 0;
        sort(ratio.begin(),ratio.end());
        for(auto &r: ratio){
            m = max(r.second,m);
            r.second = m;
        }
        sort(w.begin(),w.end());
        int r = 0;
        int ans = 0;
        for(int w: w){
            while(r < ratio.size() - 1 && ratio[r + 1].first <= w) r++;
            if(ratio[r].first <= w) ans += ratio[r].second;
        }
        return ans;
    }
};