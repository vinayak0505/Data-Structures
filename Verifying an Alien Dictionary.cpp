#include "vector"
#include "string"

using namespace std;
class Solution {
    int mapper[26];
public:
    bool isAlienSorted(vector<string>& words, string order) {
        for(int i = 0;i<26;i++){
            mapper[order[i] - 'a'] = i;
        }
        for(int i = 0;i<words.size() - 1;i++){
            for(int j = 0;j<words[i].size();j++){
                if(words[i + 1].size() == j) return false;
                if(mapper[words[i + 1][j] - 'a'] > mapper[words[i][j] - 'a']) break;
                if(mapper[words[i + 1][j] - 'a'] < mapper[words[i][j] - 'a']) 
                return false;
            }
        }
        return true;
    }
};