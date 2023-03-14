#include "vector"

using namespace std;
// Definition for Employee.
class Employee {
public:
    int id;
    int importance;
    vector<int> subordinates;
};

class Solution {
public:
    int getImportance(vector<Employee*>& employees, int id) {
        for(auto& employ: employees){
            if(employ->id != id) continue;
            int ans = employ->importance;
            for(auto subId: employ->subordinates){
                ans += getImportance(employees,subId);
            }
            return ans;
        }
        return 0;
    }
};