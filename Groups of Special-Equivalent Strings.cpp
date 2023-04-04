#include "vector"
#include "unordered_map"
#include "string"

using namespace std;

class Solution {

    unordered_map<char,int> convert(string& word){
        bool odd = false;
        unordered_map<char,int> ans;
        for(char c: word){
            if(odd) ans[c]++;
            else ans[c + 'A' - 'a' ]++;
            odd = !odd;
        }
        return ans;
    }

    bool same(unordered_map<char,int>& a, unordered_map<char,int>& b){
        for(char i = 'a',j= 'A';i<='z';i++,j++){
            if(a[i] != b[i] || a[j] != b[j]) return false;
        }
        return true;
    }
public:
    int numSpecialEquivGroups(vector<string>& words) {
        vector<unordered_map<char,int>> count;
        for(string word: words){
            count.push_back(convert(word));
        }
        int ans = 0;
        vector<bool> found(count.size());
        for(int i = 0;i<words.size();i++){
            if(found[i]) continue;
            for(int j = i + 1;j < words.size();j++){
                if(same(count[i],count[j])) found[j] = true;
            }
            ans++;
        }
        return ans;
    }
};