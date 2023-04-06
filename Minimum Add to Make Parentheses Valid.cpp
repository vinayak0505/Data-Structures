#include "string"

using namespace std;

class Solution {
public:
    int minAddToMakeValid(string s) {
        int ans = 0;
        int count = 0;
        for(char c: s){
            if(c == '('){
                count++;
            }else{
                if(count) count--;
                else ans++;
            }
        }
        return ans + count;
    }
};