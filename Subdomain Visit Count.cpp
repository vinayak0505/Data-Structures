#include "vector"
#include "string"
#include "unordered_map"

using namespace std;

class Solution {
public:
    vector<string> subdomainVisits(vector<string>& cp) {
        unordered_map<string,int>mpp;
        vector<string>res;
        int n = cp.size();
        
        for(int i = 0; i < n; ++i)
        {   
            int j = cp[i].size() - 1;
            string temp;
            int val = stoi(cp[i]);

            while(cp[i][j]!=' ')
            {
               if(cp[i][j]=='.')
               {  
                 string c = temp;
                 reverse(c.begin(),c.end());
                 mpp[c] += val; 
               } 

               temp.push_back(cp[i][j]);
               j--; 
            }

            reverse(temp.begin(),temp.end());
            mpp[temp] += val;
        }
        
        for(auto it : mpp) res.push_back(to_string(it.second) +" "+it.first);
        
        return res;
    }
};
