#include "string"
#include "unordered_map"

using namespace std;

class Solution {
public:
    string fractionToDecimal(long long n, long long d) {
        if(n == 0) return "0";
        bool isNev = false;
        if(n < 0) isNev = !isNev, n *= -1;
        if(d < 0) isNev = !isNev, d *= -1;
        string ans = to_string(n / d);
        n %= d;
        n *= 10;
        if(n) ans += '.';
        unordered_map<int,int> s;
        int i = 0;
        while(n){
            i++;
            if(s.count(n)) {
                ans += ')';
                ans.insert(s[n] + ans.find("."),"(");
                break;
            }
            s[n] = i;
            ans += to_string(n / d);
            n %= d;
            n *= 10;
        }
        if(isNev){
            return "-" + ans;
        }
        return ans;
    }
};