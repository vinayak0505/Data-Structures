#include "unordered_map"
#include "vector"

using namespace std;

class Solution {
public:
    bool isNStraightHand(vector<int>& hand, int groupSize) {
        if(((int) hand.size()) % groupSize) return false;
        unordered_map<int,int> mp;
        for(int i: hand) mp[i]++;
        sort(hand.begin(),hand.end());
        for(int i: hand){
            if(mp[i] == 0) continue;
            for(int j = 0;j<groupSize;j++){
                if(mp[i + j] == 0) return false;
                mp[i + j]--;
            }
        }
        return true;
    }
};