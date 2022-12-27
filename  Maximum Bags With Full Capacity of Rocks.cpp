
#include "vector"
#include "algorithm"

using namespace std;

class Solution {
public:
    int maximumBags(vector<int>& c, vector<int>& r, int a) {
        vector<int> rc(c.size());
        for(int i = 0;i<rc.size();i++){
            rc[i] = c[i] - r[i];
        }
        sort(rc.begin(),rc.end());
        int i = 0;
        while(i<c.size()){
            if(a >= rc[i]){
                a -= rc[i];
                i++;
            }else break;
        }
        return i;
    }
};