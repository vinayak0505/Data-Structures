#include "unordered_map"
#include "vector"
#include "string"

using namespace std;

class Solution {
    int mod = 1e9 + 7;
    unordered_map<int,unordered_map<int,int>> dp;
    
    int solve(vector<unordered_map<char,int>>& count,string& target, int i,int j){
        if(j > i) return 0;
        if(j == target.size()) return 1;
        if(count.size() == i) return 0;
        if(dp[i].count(j)) return dp[i][j];

        long long ans = solve(count,target,i+1,j);
        if(count[i].count(target[j])){
            ans = ans + (long long)solve(count,target,i+1,j+1) * count[i][target[j]];
        }
        return dp[i][j] = (ans % mod);

    }
public:
    int numWays(vector<string>& words, string target) {
        auto count = vector<unordered_map<char,int>>(words.front().size());
        // 10^6;
        for(string& word: words){
            for(int i = 0;i<word.size();i++){
                count[i][word[i]]++;
            }
        }

        return solve(count,target,0,0);
    }
};