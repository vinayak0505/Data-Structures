#include "vector"
#include "string"

using namespace std;
class Solution {
    bool check(vector<int> &a1,vector<int> &a2){
        for(int i = 0;i<26;i++){
            if(a1[i] != a2[i]) return false;
        }
        return true;
    }
public:
    vector<int> findAnagrams(string s2, string s1) {
        if(s1.size() > s2.size()) return {};
        vector<int> arr1(26), arr2(26);
        int count = 0,count2 = 0;
        for(char c: s1) count += (c - 'a'), arr1[c - 'a']++;
        int i = 0;
        for(;i<s1.size() - 1;i++){
            count2 += (s2[i] - 'a'),arr2[s2[i] - 'a']++;
        }
        vector<int> ans;
        for(;i<s2.size();i++){
            arr2[s2[i] - 'a']++;
            count2 += (s2[i] - 'a');
            if(count2 == count && check(arr1,arr2)) ans.push_back(i - s1.size() + 1);
            arr2[s2[i + 1- s1.size()] - 'a']--;
            count2 -= s2[i + 1 - s1.size()] - 'a';
        }
        return ans;
    }
};