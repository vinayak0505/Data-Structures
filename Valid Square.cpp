#include "vector"

using namespace std;

class Solution {

    int sq(int a){
        return a * a;
    }

    bool isPerTri(
        vector<int>& p1, 
        vector<int>& p2, 
        vector<int>& p3
    ){
        int d1 = sq(p2.back() - p1.back()) + sq(p2.front() - p1.front());
        int d2 = sq(p2.back() - p3.back()) + sq(p2.front() - p3.front());
        int d3 = sq(p1.back() - p3.back()) + sq(p1.front() - p3.front());
        if(d1 + d2 == d3 && d1 == d2) return true;
        if(d2 + d3 == d1 && d2 == d3) return true;
        if(d3 + d1 == d2 && d3 == d1) return true;
        return false;
    }
public:
    bool validSquare(
        vector<int>& p1, 
        vector<int>& p2, 
        vector<int>& p3, 
        vector<int>& p4
    ) {
        if(!isPerTri(p1,p2,p3)) return false;
        if(!isPerTri(p2,p3,p4)) return false;
        if(!isPerTri(p3,p4,p1)) return false;
        if(!isPerTri(p4,p1,p2)) return false;
        return true;
    }
};