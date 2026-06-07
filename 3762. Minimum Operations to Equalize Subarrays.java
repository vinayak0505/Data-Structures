import java.util.ArrayList;

class Solution {

    class SegmentTree{

        int n;
        int array[];

        private void build(int node, int left, int right, int[] nums){
            if(left == right){
                array[node] = nums[left];
                return;
            }

            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);

            if(array[node * 2] == array[node * 2 + 1]){
                array[node] = array[node * 2];
            }else{
                array[node] = -1;
            }
        }

        public SegmentTree(int[] nums, int k){
            n = nums.length;
            array = new int[n * 4];
            int newnums[] = new int[n];

            for(int i = 0; i<n;i++){
                newnums[i] = nums[i] % k;
            }

            build(1, 0, n - 1, newnums);
        }

        private Integer query(int node, int left, int right, int queryLeft, int queryRight){
            if(queryRight < left || right < queryLeft){
                return null;
            }

            if(queryLeft <= left && right <= queryRight){
                return array[node];
            }
            int mid = (left + right) / 2;
            Integer leftQuery = query(node * 2, left, mid, queryLeft, queryRight);
            Integer rightQuery = query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
            if(leftQuery == null){
                return rightQuery;
            }
            if(rightQuery == null){
                return leftQuery;
            }

            if(!leftQuery.equals(rightQuery)){ 
                return -1;
            }
            return leftQuery;
        }

        public boolean sameRem(int left, int right){
            Integer value = query(1, 0, n - 1, left, right);
            if(value == -1) return false;
            return true;
        }
    }

    class MergeSortSegmentTree{

        ArrayList<Integer> array[];
        ArrayList<Long> presum[];
        int n;

        public MergeSortSegmentTree(int[] nums){
            n = nums.length;
            array = new ArrayList[n * 4];
            presum = new ArrayList[n * 4];
            build(1, 0, n - 1, nums);
        }

        private void build(int node, int left, int right, int[] nums){
            if(left == right){
                array[node] = new ArrayList<>();
                presum[node] = new ArrayList<>();
                presum[node].add((long)nums[left]);
                array[node].add(nums[left]);
                return;
            }

            array[node] = new ArrayList<>();
            presum[node] = new ArrayList<>();
            
            int mid = (left + right) / 2;

            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);

            ArrayList<Integer> leftSubArray = array[node * 2];
            ArrayList<Integer> rightSubArray = array[node * 2 + 1];

            ArrayList<Integer> newArray = new ArrayList<>();
            int i = 0, j = 0;
            while(i < leftSubArray.size() && j < rightSubArray.size()){
                if(leftSubArray.get(i) <= rightSubArray.get(j)){
                    newArray.add(leftSubArray.get(i));
                    i++;
                }else{
                    newArray.add(rightSubArray.get(j));
                    j++;
                }
            }

            while(i < leftSubArray.size()){
                newArray.add(leftSubArray.get(i));
                i++;
            }

            while(j < rightSubArray.size()){
                newArray.add(rightSubArray.get(j));
                j++;
            }

            array[node] = newArray;

            for(int idx = 0; idx < newArray.size(); idx++){
                if(idx == 0){
                    presum[node].add((long) newArray.get(idx));
                }else{
                    presum[node].add((long) newArray.get(idx) + (long) presum[node].get(idx - 1));
                }
            }
        }

        private int upper_bond(ArrayList<Integer> array, int value){
            int low = 0, high = array.size() - 1, ans = array.size();
            while(low <= high){
                int mid = (low + high) / 2;
                if(array.get(mid) > value){
                    ans = mid;
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }
            return ans;
        }

        private long[] getSmaller(int node, int left, int right, int start, int end, int value){
            if(end < left || right < start){
                return new long[]{0, 0};
            }

            if(start <= left && right <= end){
                int index = upper_bond(array[node], value);
                return new long[]{index, index == 0 ? 0 : presum[node].get(index - 1)};
            }

            int mid = (left + right) / 2;
            long[] leftRes = getSmaller(node * 2, left, mid, start, end, value);
            long[] rightRes = getSmaller(node * 2 + 1, mid + 1, right, start, end, value);
            return new long[]{leftRes[0] + rightRes[0], leftRes[1] + rightRes[1]};
        }

        public int findMedian(int start, int end){
            int low = 0, high = (int)(1e9), ans = -1;

            while(low <= high){
                int mid = (high - low) / 2 + low;
                long[] smaller = getSmaller(1, 0, n - 1, start, end, mid);
                if(smaller[0] >= (end - start + 1 + 1) / 2){
                    ans = mid;
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }
            return ans;
        }
    
    }
    public long[] minOperations(int[] nums, int k, int[][] queries) {

        MergeSortSegmentTree mst = new MergeSortSegmentTree(nums);
        SegmentTree st = new SegmentTree(nums, k);
        int n = nums.length;

        long globalPresum[] = new long[n+1];
        globalPresum[0] = 0;
        for(int i = 1; i<=n; i++){
            globalPresum[i] = globalPresum[i - 1] + (long)nums[i - 1];
        }

        long ans[] = new long[queries.length];
        for(int i = 0; i<queries.length; i++){
            int l = queries[i][0];
            int r = queries[i][1];
            boolean min = st.sameRem(l, r);
            if(min == false){
                ans[i] = -1;
                continue;
            }

            int median = mst.findMedian(l, r);

            long[] smallerData = mst.getSmaller(1, 0, n - 1, l, r, median);
            long countSmaller = smallerData[0];
            long sumSmaller = smallerData[1];

            int totalElements = r - l + 1;
            long totalSum = globalPresum[r + 1] - globalPresum[l];

            long countLarger = totalElements - countSmaller;
            long sumLarger = totalSum - sumSmaller;

            long operationsSmaller = (countSmaller * median - sumSmaller) / k;
            long operationsLarger = (sumLarger - countLarger * median) / k;

            ans[i] = operationsSmaller + operationsLarger;
            
        }
        return ans;
    }
}