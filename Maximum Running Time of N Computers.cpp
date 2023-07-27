#include "vector"

using namespace std;

class Solution {

    bool check(long long till, int n, vector<int>& batteries){
        long long sum = 0;
        for(int b: batteries){
            if(b < till) sum += b;
            else sum += till;
        }
        if(sum < till * n) return false;
        return true;
    }
public:
    long long maxRunTime(int n, vector<int>& batteries) {
        long long ans = 0, high = 1e5 * 1e9, low = 0;
        while(low <= high){
            long long mid = (high + low) / 2;
            if(check(mid,n,batteries)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }
};