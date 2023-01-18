#include "vector"
using namespace std;
class Solution {
public:
    int edgeScore(vector<int>& edges) {
        vector<long long> sum(edges.size());
        for(int i = 0;i<edges.size();i++){
            sum[edges[i]] += i;
        }
        return max_element(sum.begin(),sum.end()) - sum.begin();
    }
};