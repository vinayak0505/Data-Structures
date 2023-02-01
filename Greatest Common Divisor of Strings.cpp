#include "string"

using namespace std;

class Solution {
    int hcf(int x,int y){
        if(y == 0) return x;
        return hcf(y,x%y);
    }
public:
    string gcdOfStrings(string str1, string str2) {
        int common = hcf(str1.size(),str2.size());
        for(int i = 0;i<str1.size();i++){
            if(str1[i] != str1[i%common]) return "";
        }
        for(int i = 0;i<str2.size();i++){
            if(str2[i] != str1[i%common]) return "";
        }
        return str1.substr(0,common);
    }
};