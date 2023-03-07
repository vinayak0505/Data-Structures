#include "vector"

using namespace std;

class Solution {

    long long count(long long time,vector<int>& times){
        if(time == 0) return 0;
        long long ans = 0;
        for(long long t: times) ans += time / t;
        return ans; 
    }
public:
    long long minimumTime(vector<int>& time, int totalTrips) {
        long long end = INT_MAX;
        for(int t:time) end = min((int)end,t);

        end *= totalTrips;
        long long start = 0;
        long long ans = 0,mid;
        while(start <= end){
            mid = (end + start) / 2;
            if(totalTrips <= count(mid,time)){
                ans = mid;
                end = mid - 1;
            }else start = mid + 1;
        }
        return start;
    }
};