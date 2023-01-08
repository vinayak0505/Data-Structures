#include "vector"
#include "unordered_map"

using namespace std;

class Solution {

    double slope(int a,int b){
        if(b == 0) return 1e9 + 1;
        return (double) a/b;
    }
public:
    int maxPoints(vector<vector<int>>& points) {
        int n = points.size();
        if (n < 3) {
            return n;
        }
        int result = 2;
        for (int i = 0; i < n; i++) {
            unordered_map<double, int> mp;
            for (int j = 0; j < n; j++) {
                if(j == i) continue;
                    mp[slope(points[j][1] - points[i][1], 
                    points[j][0] - points[i][0])]++;
            }
            for (auto [h, count] : mp) {
                result = max(result, count + 1);
            }
        }
        return result;
    }
};