#include "vector"

using namespace std;

class Solution {
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        int ans = 0;
        sort(points.begin(), points.end());
        int a = points.front().back();
        for(auto &point: points) {
            if(point.front() > a){
                ans++;
                a = point.back();
            }else {
                a = min(a,point.back());
            }
        }
        return ans + 1;
    }
};