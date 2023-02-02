#include "vector"

using namespace std;

class Solution {
public:
    int hIndex(vector<int>& citations) {
        sort(citations.begin(),citations.end(),greater<int>());
        int ans = 0;
        for(int citation:citations){
            if(citation > ans) ans++;
            else break;
        }
        return ans;
    }
};