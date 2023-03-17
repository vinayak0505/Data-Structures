#include "vector"
#include "string"

using namespace std;

class Solution {
    vector<string> ans;
    void change(string& s,int i = 0){
        if(i == s.size()){
            ans.push_back(s);
            return;
        }
        change(s,i+1);
        if('a' <= s[i] && s[i] <= 'z'){
            s[i] += 'A' - 'a';
            change(s,i+1);
            s[i] += 'a' - 'A';
        }
        if('A' <= s[i] && s[i] <= 'Z'){
            s[i] += 'a' - 'A';
            change(s,i+1);
            s[i] += 'A' - 'a';
        }
    }
public:
    vector<string> letterCasePermutation(string s) {
        change(s);
        return ans;
    }
};