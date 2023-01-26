#include "vector"
#include "string"

using namespace std;

class Solution {

public:
    string largestNumber(vector<int>& nums) {
        vector<string> n;
        for(int i:nums) n.push_back(to_string(i));
        sort(n.begin(),n.end(),[](string&a,string& b){
            return a + b > b + a;
        });
        string ans;
        for(auto& a:n) ans += a;
        if(ans.front() == '0') return "0";
        return ans;
    }
};