#include "string"
#include "vector"
#include "algorithm"

using namespace std;

class Solution {

    int accumulate(vector<int>& arr){
        int ans = 0;
        for(int i: arr) ans += i;
        return ans;
    }
public:
    string shiftingLetters(string s, vector<int>& shifts) {
        for(int &shift: shifts) shift %= 26;
        int count = accumulate(shifts);
        for(int i = 0;i<s.size();i++){
            int val = s[i] - 'a';
            val += count;
            count -= shifts[i];
            s[i] = (val % 26) + 'a';
        }
        return s;
    }
};