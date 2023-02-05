#include "vector"
#include "string"

using namespace std;
class Solution {

    int convertToBit(string & word){
        int a = 0;
        for(char c:word){
            a |= (1 << (c - 'a'));
        }
        return a;
    }
public:
    int maxProduct(vector<string>& words) {
        vector<int> bits;
        for(string& word:words){
            bits.push_back(convertToBit(word));
        }

        int ans =0;
        for(int i = 0;i<words.size();i++){
            for(int j = i + 1;j<words.size();j++){
                if((bits[i] & bits[j]) == 0){
                    ans = max(ans,(int)(words[i].size() * words[j].size()));
                }
            }
        }
        return ans;
    }
};