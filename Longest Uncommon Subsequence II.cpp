#include "vector"
#include "unordered_map"
#include "string"

using namespace std;
class Solution {
    vector<string> words;

    bool canFormByDeleting(string word, string str)
    {
        int word_i = 0, str_i = 0;
        while (word_i < word.size() && str_i < str.size())
        {
            if (word[word_i] == str[str_i])
                word_i++;
            str_i++;
        }
        return word_i == word.size();
    }
public:
    int findLUSlength(vector<string>& strs) {
        unordered_map<int,unordered_map<string,int>> mp;
        for(auto &str: strs){
            mp[str.size()][str]++;
        }
        for(int i = 10;i>0;i--){
            auto &newmp = mp[i];
            for(auto &nmp: newmp){
                if(nmp.second == 1) {
                    bool found = false;
                    for(string &word: words) 
                        if(canFormByDeleting(nmp.first,word)) {
                            found = true;
                            break;
                        }
                    if(found == false) return nmp.first.size();
                }
                words.push_back(nmp.first);
            }
        }
        return -1;
    }
};