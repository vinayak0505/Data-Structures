#include "string"
#include "vector"

using namespace std;

class Solution {
public:
    string getHint(string secret, string guess) {
        int bulls = 0, cows = 0;
        vector<int> count(10);
        for(int i = 0;i<secret.size();i++){
            if(secret[i] == guess[i]){
                bulls++;
                guess[i] = 'X';
                continue;
            }
            count[secret[i] - '0']++;
        }
        for(char c: guess){
            if(c != 'X' && count[c - '0'])
                cows++,count[c - '0']--;
        }
        return to_string(bulls) + "A" + to_string(cows) + "B";
    }
};