class Solution {
    class SegmentedTree {
        private int[] array;
        private int n;

        private void build(int[] nums, int index, int left, int right){
            if(left == right){
                array[index] = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            build(nums, index * 2, left, mid);
            build(nums, index * 2 + 1, mid + 1, right);

            array[index] = gcd(array[index * 2], array[index * 2 + 1]);
        }

        public SegmentedTree(int[] nums){
            n = nums.length;
            array = new int[n * 4];
            build(nums, 1, 0, n - 1);
        }

        private void update(int updateIndex, int index, int value, int left, int right){
            if(left == right){
                array[index] = value;
                return;
            }
            int mid = (left + right) / 2;
            if(updateIndex <= mid){
                update(updateIndex, index * 2,value, left, mid);
            }else{
                update(updateIndex, index * 2 + 1,value, mid + 1, right);
            }

            array[index] = gcd(array[index * 2], array[index * 2 + 1]);
        }

        public void update(int index, int value){
            update(index, 1,value, 0, n - 1);
        }

        private int query(int node, int nodeLeft, int nodeRight, int left, int right){
            if(nodeRight < left || right < nodeLeft) return 0;
            if(left <= nodeLeft && right >= nodeRight){
                return array[node];
            }
            int nodeMid = (nodeLeft + nodeRight) / 2;
            return gcd(query(node * 2, nodeLeft, nodeMid, left, right), 
            query(node * 2 + 1, nodeMid + 1, nodeRight, left, right));
        }

        public int query(int left, int right){
            return query(1, 0, n - 1, left, right);
        }
    }
    private int gcd(int a, int b){
        if(a == 0) return b;
        return gcd(b % a, a);
    }

    public int countGoodSubseq(int[] nums, int p, int[][] queries) {
        int ans = 0;
        int validNumCount = 0;
        int n = nums.length;

        for(int i = 0;i<nums.length; i++){
            if(nums[i] != 0 && nums[i] % p == 0) {
                validNumCount++;
            }else{
                nums[i] = 0;
            }
        }

        SegmentedTree segmentedTree = new SegmentedTree(nums);

        for(int[] query: queries){
            int idx = query[0];
            int val = query[1];

            if(nums[idx] != 0 && nums[idx] % p == 0){
                validNumCount--;
            }

            if(val % p == 0){
                nums[idx] = val;
                validNumCount++;
            }else{
                nums[idx] = 0;
            }

            segmentedTree.update(idx, nums[idx]);

            if(segmentedTree.query(0, n - 1) == p){
                if(validNumCount < n){
                    ans++;
                    continue;
                }
                for(int i = 0; i < n; i++){
                    if(gcd(
                        segmentedTree.query(0, i - 1),
                        segmentedTree.query(i + 1, n - 1)
                    ) == p) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}