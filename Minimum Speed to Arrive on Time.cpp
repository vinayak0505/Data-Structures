#include<vector>

using namespace std;

class Solution {

    bool check(vector<int>& dist, double hour,int speed){
        if(speed == 0) return false;
        double ans = 0;
        for(int i = 0;i<dist.size();i++){
            int d = dist[i];
            if((i != dist.size() - 1) && ((double)d/speed != (double)(d/speed))){
                ans += d / speed;
                ans++;
            }else{
                ans += (double)d / speed;
            }
        }
        return ans <= hour;
    }
public:
    int minSpeedOnTime(vector<int>& dist, double hour) {
        if((double)(int)hour == hour){
            if(hour < dist.size()) return -1;
        }else{
            if((int)hour < dist.size() - 1) return -1;
        }
        int ans = 0, low = 0, high = 1e7;
        while(low <= high){
            int mid = (low + high) / 2;
            int c = check(dist,hour,mid);
            if(c){
                ans = mid;
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
};