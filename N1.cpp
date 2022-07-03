#include <iostream>
#include<string.h>
#include<queue>
#define INF 0x3f3f3f3f
using namespace std;

int maze[14][14];
int dist[(1<<14)][14][14];
int dirs[4][2] = {{0,1},{0,-1},{1,0},{-1,0}};
int n;
char c;
struct node{
    int x,y,cval;
};


bool safe(int x, int y){
    return x>=0 && y>=0 && x<n && y<n && maze[x][y]!=-2;
}


int solve(int n, int cnt){
    queue<node> q;
    dist[0][0][0]=0;
    q.push({0,0,0});
    while(!q.empty()){
        node front = q.front();q.pop();
        for(int k=0;k<4;++k){
            node ns;
            ns.x = front.x+dirs[k][0];ns.y = front.y+dirs[k][1]; ns.cval = front.cval;
            if(safe(ns.x,ns.y) == false) continue;


            if(maze[ns.x][ns.y]>=0) 
                ns.cval|=(1<<(maze[ns.x][ns.y]));

            if(dist[ns.cval][ns.x][ns.y]>dist[front.cval][front.x][front.y]+1){
                dist[ns.cval][ns.x][ns.y] = dist[front.cval][front.x][front.y]+1;
                if(ns.x == n-1 && ns.y == n-1 && ns.cval == (1<<cnt)-1){
                    return dist[(1<<cnt)-1][ns.x][ns.y];
                }
                q.push(ns);
            }


        }
    }
    return -1;
}
int main() {
	int t;
	cin>>t;
	while(t--){
	    int cnt=0;
	    cin>>n;
	    for(int i=0;i<n;++i){
	        for(int j=0;j<n;++j){
	            cin>>c;
	            if(c == '.') maze[i][j] = -1;
	            else if(c == '#') maze[i][j] = -2;
	            else if(c == '*') maze[i][j] = cnt++;
	        }
	    }
	    memset(dist,INF,sizeof(dist));
	    cout<<solve(n,cnt)<<'\n';
	}
	return 0;
}