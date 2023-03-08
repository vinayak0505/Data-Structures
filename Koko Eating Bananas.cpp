#include "vector"

using namespace std;
class Solution {
    int count(int speed,vector<int>& piles){
        if(speed == 0) return INT_MAX;
        int ans = 0;
        for(int pile: piles)  {
            ans += pile / speed;
            if(pile % speed) ans++;
        }
        return ans; 
    }
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int end = 0;

        for(int pile:piles) end = max(end,pile);

        int start = 0;
        int ans = 0,mid;
        
        while(start <= end){
            mid = (end + start) / 2;
            if(h < count(mid,piles)){
                start = mid + 1;
            }else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
};