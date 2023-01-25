#include "vector"
#include "sstream"

using namespace std;

class Solution {
    vector<int> convert(string& val){
        stringstream ss(val);
        vector<int> ans;
        while(getline(ss,val,'.')){
            ans.push_back(stoi(val));
        }
        return ans;
    }

    int val(vector<int>& v,int index){
        if(index >= v.size()) return 0;
        return v[index];
    }
public:
    int compareVersion(string version1, string version2) {
        vector<int> v1 = convert(version1);
        vector<int> v2 = convert(version2);
        for(int i = 0;i<v1.size() || i < v2.size();i++){
            if(val(v1,i) > val(v2,i)) return 1;
            else if(val(v1,i) < val(v2,i)) return -1;
        }
        return 0;
    }
};