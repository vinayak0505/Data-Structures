#include "string"
#include "vector"

using namespace std;

class MagicDictionary {
    bool check(string& a,string& b){
        if(a.size() != b.size()) return false;
        if(a == b) return false;
        bool check = false;
        for(int i = 0;i<a.size();i++){
            if(a[i] != b[i]){
                if(check) return false;
                check = true;
            }
        }
        return true;
    }
public:
    MagicDictionary() {
        
    }
    vector<string> di;
    
    void buildDict(vector<string> d) {
        di = d;
    }
    
    bool search(string s) {
        for(auto &str: di) if(check(str,s)) return true;
        return false;
    }
};