#include "unordered_map"
#include "vector"

using namespace std;

class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        unordered_map<long long int,int> arr;
        long long int sum = 0;
        for(auto& bricks: wall){
            sum = 0;
            for(auto& brick: bricks){
                sum += brick;
                arr[sum]++;
            }
            arr[sum]--;
        }
        int ans = 0;
        for(auto a: arr) ans = max(a.second,ans);
        return wall.size() - ans;
    }
};