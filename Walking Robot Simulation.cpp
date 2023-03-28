#include "vector"
#include "set"

using namespace std;

class Solution {
    void changedir(int& dir,int c){
        if(c == -1){
            dir = (dir + 1) % 4;
        }else{
            dir = (dir + 3) % 4;
        }
    }
// a = x b = y
    bool pos(int dir,int& a,int& b,set<vector<int>>& mp){
        switch(dir){
            case 0: b++;break;
            case 1: a++;break;
            case 2: b--;break;
            case 3: a--;break;
        }
        bool p = mp.count({a,b}) == 0;
        if(!p){
            switch(dir){
                case 0: b--;break;
                case 1: a--;break;
                case 2: b++;break;
                case 3: a++;break;
            }
        }
        return p;
    }
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        int ans = 0;
        int cura = 0, curb = 0;
        int dir = 0;
        set<vector<int>> mp(obstacles.begin(), obstacles.end());
        for(int c: commands){
            if(c < 0){
                changedir(dir,c);
            }else{
                while(c-- && pos(dir,cura,curb,mp));
                ans = max(ans, cura * cura + curb * curb);
            }
        }
        return ans;
    }
};