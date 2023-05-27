#include "string";
#include "vector";

using namespace std;

class Solution {
public:
    string stoneGameIII(vector<int>& piles) {
        vector<int> alice(piles.size() + 1,INT_MIN / 2);
        alice.back() = 0;
        for(int i = piles.size() - 1;i >= 0;i--){
            int sum = 0;
            for(int j = i;j-i < 3 && j<piles.size();j++){
                sum += piles[j];
                alice[i] = max(alice[i],sum - alice[j + 1]);
            }
        }
        if(alice.front() > 0) return "Alice";
        if(alice.front() < 0) return "Bob";
        return "Tie";
    }
};