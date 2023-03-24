#include "vector"

using namespace std;

class Solution {
public:
    int maxDistToClosest(vector<int>& seats) {
        vector<int> right = vector<int>(seats.size(),seats.size()),
        left = vector<int>(seats.size(),seats.size());
        int pre = seats.size();
        for(int i = 0;i<seats.size();i++){
            pre++;
            right[i] = pre;
            if(seats[i]) pre = 0;
        }
        pre = seats.size();
        for(int i = seats.size() - 1;i >= 0;i--){
            pre++;
            left[i] = pre;
            if(seats[i]) pre = 0;
        }
        int ans = 0;
        for(int i = seats.size() - 1;i >= 0;i--){
            if(seats[i]) continue;
            ans = max(ans,min(left[i],right[i]));
        }
        return ans;
    }
};