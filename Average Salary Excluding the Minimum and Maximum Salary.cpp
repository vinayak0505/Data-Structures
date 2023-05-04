#include "vector"

using namespace std;

class Solution {
public:
    double average(vector<int>& salary) {
        // first = min, second = max
        pair<int,int> excl = {INT32_MAX,INT32_MIN};
        double ans = 0;
        for(int i:salary){
            ans += i;
            excl.first = min(excl.first,i);
            excl.second = max(excl.second,i);
        }
        ans -= excl.first + excl.second;
        return ans/(salary.size() - 2);
    }
};