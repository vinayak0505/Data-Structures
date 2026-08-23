class Solution {

    public int minMoves(int sx, int sy, int tx, int ty) {
        if (sx == 0 && sy == 0 && tx == 0 && ty == 0)
            return 0;
        if (sx == 0 && sy == 0)
            return -1;

        int ans = 0;
        while (sx < tx || sy < ty) {
            ans++;
            if (tx > ty || (sx == 0 && tx == ty)) {
                if (tx / 2 >= ty){
                    if(tx % 2 == 1) return -1;
                    tx /= 2;
                }
                else
                    tx -= ty;
            } else {
                if (ty / 2 >= tx){
                    if(ty % 2 == 1) return -1;
                    ty /= 2;
                }
                else
                    ty -= tx;
            }
        }

        if (sx == tx && sy == ty)
            return ans;
        return -1;
    }
}