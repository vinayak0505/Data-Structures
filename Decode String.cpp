#include "string"

using namespace std;

class Solution {
    int i = 0;

    string multi(int count,string& s){
        string ans;
        while(count--){
            ans += s;
        }
        return ans;
    }
public:
    string decodeString(string& s,int c = 1) {
        int count = 0;
        string value;
        while(i < s.size()){
            if(s[i] == '['){
                i++;
                value += decodeString(s,count);
                count = 0;
            }else if(s[i] == ']'){
                i++;
                return multi(c,value);
            }else if(s[i] >= '0' && s[i] <= '9'){
                count *= 10;
                count += s[i] - '0';
                i++;
            }else {
                value += s[i++];
            }
        }
        return value;
    }
};