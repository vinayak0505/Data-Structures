#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    bool canReorderDoubled(vector<int>& arr) {
        unordered_map<int,int> mp,nmp;
        vector<int> v,nv;
        int count;
        for(int a: arr) {
            if(a > 0) {
                count = mp[a]++;
                if(count == 0) v.push_back(a); 
            }
            else {
                count = nmp[-a]++;
                if(count == 0) nv.push_back(-a);
            }
        }
        sort(v.begin(),v.end());
        sort(nv.begin(),nv.end());

        for(int i: v){
            if(mp[i * 2] < mp[i]) return false;
            mp[i * 2] -= mp[i];
        }

        for(int i: nv){
            if(nmp[i * 2] < nmp[i]) return false;
            nmp[i * 2] -= nmp[i];
        }

        return true;
    }
};