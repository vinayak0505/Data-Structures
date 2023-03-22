#include "vector"

using namespace std;

class Solution {
public:
    int numMagicSquaresInside(vector<vector<int>>& grid) {
        int res=0;
        for(int i=0;i+2<grid.size();i++)
        {
            for(int j=0;j+2<grid[0].size();j++)
            {
                res+=fun(grid,i,j);
            }
        }
        return res;
    }
    int fun(vector<vector<int>>& grid,int i,int j)
    {
        vector<int> v(16,0);
        for(int x=i;x<i+3;x++)
        {
            for(int y=j;y<j+3;y++)
            {
                v[grid[x][y]]=1;
            }
        }
        for(int x=1;x<10;x++)
        {
            if(!v[x])
                return 0;
        }
        int sum=0,t=0;
        int p=i,q=j;
        for(int k=0;k<3;k++)
        {
            sum+=grid[p][q];
            p++;
            q++;
        }
        p=i,q=j+2;
        for(int k=0;k<3;k++)
        {
            t+=grid[p][q];
            p++;
            q--;
        }
        if(t!=sum)
            return 0;
        
        for(int x=i;x<i+3;x++)
        {
            t=0;
            for(int y=j;y<j+3;y++)
            {
                t+=grid[x][y];
            }
            if(t!=sum)
                return 0;
        }
        for(int y=j;y<j+3;y++)
        {
            t=0;
            for(int x=i;x<i+3;x++)
            {
                t+=grid[x][y];
            }
            if(t!=sum)
                return 0;
        }
        return 1;
    }
};