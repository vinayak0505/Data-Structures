#include "iostream"
using namespace std;

class Solution
{
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2)
    {
        int y2 = min(by2, ay2);
        int y1 = max(by1, ay1);
        int x2 = min(bx2, ax2);
        int x1 = max(bx1, ax1);
        int ans = (ax2 - ax1) * (ay2 - ay1);
        ans += (bx2 - bx1) * (by2 - by1);
        if (y1 > y2 || x1 > x2)
            return ans;
        return ans - (x2 - x1) * (y2 - y1);
    }
};