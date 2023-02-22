#include "vector"

using namespace std;

class Solution {

    bool check(int max_weight, int days, vector<int>& weights){
        int rem = max_weight;
        for(int i = 0;i<weights.size();i++){
            if(rem >= weights[i]){
                rem -= weights[i];
            }else{
                rem = max_weight - weights[i];
                days--;
                if(days == 0) return false;
            }
        }
        return true;
    }

public:
    int shipWithinDays(vector<int>& weights, int days) {
        int max_weight = 0,total_weight = 0;
        for(int weight: weights){
            max_weight = max(max_weight, weight);
            total_weight += weight;
        }
        int low = max_weight, high = total_weight, mid;
        int ans = low;
        while(low <= high){
            mid = (low + high)/2;
            if(check(mid, days, weights)){
                ans = mid;
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return ans;
    }
};