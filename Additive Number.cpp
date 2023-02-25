#include "string"
#include "vector"

using namespace std;

class Solution {
    bool check(string& num,string& from,int i){
        return from.substr(i,num.size()) == num;
    }
    bool _isAdditiveNumber(long long int a,long long int b,
    long long int i, string& num) {
        while(i<num.size()){
            string newstr = to_string(a + b);
            if(check(newstr,num,i)){
                b += a;
                a = b - a;
                i += newstr.size();
            }else return false;
        }
        return true;
    }
public:
    bool isAdditiveNumber(string num) {
        if(num.size() < 3) return false;
        for(int i = 0;i<num.size() - 2;i++){
            for(int j = i + 1;j < num.size() - 1;j++){
                long long int a = stoll(num.substr(0,i + 1));
                if(a && num[0] == '0') continue;
                long long int b = stoll(num.substr(i + 1,j - i));
                if(b && num[i + 1] == '0') continue;
                if(_isAdditiveNumber(a,b,j + 1,num)) return true;
            }
        }
        return false;
    }
};