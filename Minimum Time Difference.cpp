#include "string"
#include "vector"

using namespace std;

class Solution {

    int cal(string& a,string& b){
        int ans = 0;
        ans += (b[0] - a[0]) * 600;
        ans += (b[1] - a[1]) * 60;
        ans += (b[3] - a[3]) * 10;
        ans += (b[4] - a[4]);
        return min(ans,1440 - ans);
    }

public:
    int findMinDifference(vector<string>& t) {
        sort(t.begin(),t.end());
        int ans = cal(t.front(),t.back());
        for(int i = 1;i<t.size();i++){
            ans = min(ans,cal(t[i - 1],t[i]));
        }
        return ans;
    }
};