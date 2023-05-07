#include "vector"

using namespace std;

class Solution {
    int inf = 1e8;
    int getInd(int a,vector<int>& arr){
        int start = 0, end = arr.size() - 1,ans = 0,mid;
        while(end >= start){
            mid = (start + end) / 2;
            if(arr[mid] > a) end--;
            else start++;
        }
        arr[start] = a;
        return start;
    }
public:
    vector<int> longestObstacleCourseAtEachPosition(vector<int>& o) {
        vector<int> s, ans(o.size());
        s.push_back(o[0]);
        ans[0] = 1;
        for(int i = 1; i < o.size(); i++) {
            if(s.back() <= o[i]) {
                s.push_back(o[i]);
                ans[i] = s.size();
            } else ans[i] = getInd(o[i],s) + 1;
        }
        return ans;
    }
};