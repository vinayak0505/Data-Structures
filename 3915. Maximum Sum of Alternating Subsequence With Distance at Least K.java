class Solution {
    int size = ((int) 1e5) + 1;
    class SegmentTree{
        private long tree[];
        public SegmentTree(){
            tree = new long[size * 4];
        }

        private void update(int index, long l, long r, long pos, long value){
            if(l == r){
                tree[index] = Math.max(tree[index], value);
                return;
            }

            long mid = (l + r) / 2;
            if(mid >= pos){
                update(index * 2, l, mid, pos, value);
            }else{
                update(index * 2 + 1, mid + 1, r, pos, value);
            }
            tree[index] = Math.max(tree[index * 2], tree[index * 2 + 1]);
        }

        public void update(long pos, long value){
            update(1, 0, size - 1, pos, value);
        }

        public long query(int index, long l, long r, long from, long to){
            if(l > to || from > r){
                return 0;
            }
            if(from <= l && r <= to){
                return tree[index];
            }
            long mid = (l + r) / 2;
            return Math.max(
                query(index * 2, l, mid, from, to),
                query(index * 2 + 1, mid + 1, r, from, to)
            );
        }

        public long query(long from, long to){
            return query(1, 0, size - 1, from, to);
        }
    }

    public long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length;
        long greater[] = new long[n];
        long smaller[] = new long[n];
        long ans = 0;
        for(int i = 0;i<n;i++){
            smaller[i] = greater[i] = nums[i];
            ans = Math.max(ans, nums[i]);
        }
        SegmentTree gtree = new SegmentTree();
        SegmentTree stree = new SegmentTree();

        long value = 0;
        for(int i = n - 1; i>= 0;i--){

            if(i + k < n){
                stree.update(nums[i + k], smaller[i + k]);
                gtree.update(nums[i + k], greater[i + k]);
            }
            
            value = gtree.query(nums[i] + 1, size - 1);
            smaller[i] = Math.max(smaller[i], nums[i] + value);
            ans = Math.max(smaller[i], ans);
            
            value = stree.query(0, nums[i] - 1);
            greater[i] = Math.max(greater[i], nums[i] + value);
            
            ans = Math.max(greater[i], ans);
        }
        return ans;
    }
}