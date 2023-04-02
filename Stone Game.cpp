#include "unordered_map"
#include "vector"

using namespace std;

class Solution {
    unordered_map<int,int> dp;
    int gen_key(int i,int j){
        return i * 501 + j;
    }

    int win(vector<int>& piles,int i, int j){
        int k = gen_key(i,j);
        if(dp.count(k)) return dp[k];
        if(i == j) return piles[i];
        return dp[k] = max(win(piles,i+1,j) + piles[i],
                    win(piles,i,j-1) + piles[j]
                );
    }
public:
    bool stoneGame(vector<int>& piles) {
        int count = 0;
        for(int pile: piles) count += pile;
        int alice = win(piles,0,piles.size() - 1);
        int bob = count - alice;
        return alice > bob;
    }
};