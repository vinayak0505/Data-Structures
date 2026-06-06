import java.util.Arrays;

class Solution {

    class SegmentTree{
        char s[];
        int n;
        int[] segment;

        private void build(int index, int left, int right){
            if(left == right){
                if(left == 0 || s[left] != s[left - 1]) return;
                segment[index] = 1;
                return;
            }

            int mid = (left + right) / 2;
            build(index * 2, left, mid);
            build(index * 2 + 1, mid + 1, right);

            segment[index] = segment[index * 2] + segment[index * 2 + 1];
        }

        public SegmentTree(String s){
            this.s = s.toCharArray();
            n = s.length();

            segment = new int[4 * n];

            build(1, 0, n - 1);

        }

        public void update(int node, int left, int right, int index){
            if(index < left || right < index) return;

            if(left == right){
                if(left == 0 || s[left] != s[left - 1]) {
                    segment[node] = 0;
                }else{
                    segment[node] = 1;
                }
                return;
            }

            int mid = (left + right) / 2;
            update(node * 2, left, mid, index);
            update(node * 2 + 1, mid + 1, right, index);

            segment[node] = segment[node * 2] + segment[node * 2 + 1];
        }

        public void update(int index) {
            s[index] = ((s[index] == 'A') ? 'B' : 'A');

            update(1, 0, n - 1, index);
            if(index < n){
            update(1, 0, n - 1, index + 1);
            }

        }

        private int query(int node, int left, int right, int queryleft, int queryright){
            if(right < queryleft || queryright < left){
                return 0;
            }

            if (queryleft <= left && right <= queryright) {
                return segment[node];
            }
            
            if(left == right) return segment[node];

            int mid = (left + right) / 2;
            return query(node * 2, left, mid, queryleft, queryright) +
            query(node * 2 + 1, mid + 1, right, queryleft, queryright);

        }

        public int query(int l, int r){
            return query(1, 0, n - 1, l, r) - (l > 0 && s[l] == s[l - 1] ? 1 : 0);
        }
    }
    public int[] minDeletions(String s, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        SegmentTree st = new SegmentTree(s);
        int j = 0;
        for(int[] query: queries){
            if(query[0] == 1){
                st.update(query[1]);
            }else{
                ans[j++] = st.query(query[1], query[2]);
            }
        }
        return Arrays.copyOf(ans, j);
    }
}