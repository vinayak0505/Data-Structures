#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    string predictPartyVictory(string senate) {
        int br = 0, bd = 0;
        vector<bool> ban = vector<bool>(senate.size());
        while(true){
            int r = 0,d = 0;
            for(int i = 0;i<ban.size(); i++){
                char s = senate[i];
                if(ban[i]) continue;
                if(s == 'R'){
                    if(br) {
                        br--;
                        ban[i] = true;
                    }
                    else{
                        r++;
                        bd++;
                    }
                }else{
                    if(bd) {
                        bd--;
                        ban[i] = true;
                    }
                    else{
                        d++;
                        br++;
                    }
                }
            }
            if(d == 0) return "Radiant";
            if(r == 0) return "Dire";
        }
        return "";
    }
};