
#include "unordered_map"
#include "string"
#include "sstream"
using namespace std;

class Solution {
public:
    bool wordPattern(string pattern, string s) {
        unordered_map<char,string> mymap;
        unordered_map<string,char> mymap1;
        stringstream str(s);
        string temp;
        for(char c: pattern){
            getline(str,temp,' ');
            if(mymap.count(c) || mymap1.count(temp)){
                if(temp != mymap[c]) return false;
                if(mymap1[temp] != c) return false;
            }else mymap[c] = temp,mymap1[temp] = c;
        }
        if(str>>temp) return false;
        return true;
    }
};