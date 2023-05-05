#include "string"

using namespace std;

class Solution {
    bool is(char c){
        switch(c){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            return true;
        }
        return false;
    }
public:
    int maxVowels(string s, int k) {
        int max_ans = 0;
        int ans = 0;
        for(int i = 0;i<s.size();i++){
            if(is(s[i])) ans++;
            if(i - k >= 0 && is(s[i - k])) ans--;
            else max_ans = max(ans,max_ans);
        }
        return max_ans;
    }
};