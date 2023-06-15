
class Solution {

    long calc(long n)
    {
        return ((n * (n + 1)) / 2);
    }
    long getSum(int totalLen, int val)
    {
        long totalSum = 0;
        int decreasingLen = val;
        
        if (totalLen >= decreasingLen)
        {
            long currSum = calc(val);
            int remainingSum = totalLen - decreasingLen; //all 1's
            totalSum = currSum + remainingSum;
        }
        else 
        {
            long lastVal = decreasingLen - totalLen;
            totalSum = calc(val) - calc(lastVal);
        }
        return totalSum; 
    }

    bool pos(int value,int n,int i,long sum){
        if(value == 0) return true;
        sum -= value;
        sum -= getSum(i,value - 1);
        sum -= getSum(n - i - 1,value - 1);
        return sum >= 0;
    }

public:
    int maxValue(int n, int index, int maxSum) {
        int high = maxSum, low = 0;
        int ans = 0;
        while(high >= low){
            int mid = (high + low) / 2;
            if(pos(mid,n,index,maxSum)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }
};