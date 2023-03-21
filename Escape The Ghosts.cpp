#include "vector"

using namespace std;

class Solution {

    int abs(int num){
        if(num < 0) return -num;
        return num;
    }

    

    int dis(vector<int> pointa, vector<int> pointb = {0,0}){
        return abs(pointa.front() - pointb.front()) +
        abs(pointa.back() - pointb.back());
    }

public:
    bool escapeGhosts(vector<vector<int>>& ghosts, vector<int>& target) {
        int d = dis(target);
        for(auto& ghost: ghosts){
            if(dis(ghost,target) <= d) return false;
        }
        return true;
    }
};