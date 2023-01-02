#include "string"

using namespace std;

class Solution {
    bool toUpper(string &word){
        for(char w:word) if(w != toupper(w)) return false;
        return true;
    }
    bool toLower(string &word){
        for(int i = 1;i<word.size();i++){
            char w = word[i];
            if(w != tolower(w)) return false;
        }
        return true;
    }
public:
    bool detectCapitalUse(string word) {
        return toUpper(word) || toLower(word);
    }
};