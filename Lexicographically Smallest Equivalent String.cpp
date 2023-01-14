#include "vector"
#include "string"

using namespace std;

class Union{
    vector<char> par;
    public:
    Union(){
        par = vector<char>(26,'A');
    }  

    char find(char a){
        if(par[a - 'a'] == 'A') return a;
        return par[a - 'a'] = find(par[a - 'a']);
    }

    void join(char a, char b){
        a = find(a);
        b = find(b);
        if(a == b) return;
        if(a < b) par[b - 'a'] = a;
        else par[a - 'a'] = b;
    }
};

class Solution {
public:
    string smallestEquivalentString(string s1, string s2, string baseStr) {
        Union un;
        for(int i = 0;i<s1.size();i++){
            un.join(s1[i],s2[i]);
        }
        for(char &c:baseStr){
            c = un.find(c);
        }
        return baseStr;
    }
};