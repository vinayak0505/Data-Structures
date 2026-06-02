class Solution {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        int ones = 0;
        int zeros = 0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i) == t.charAt(i)) continue;
            if(s.charAt(i) == '1') ones++;
            else zeros++;
        }
        long canBeSwapped = Math.min(ones, zeros);
        long rem = ones + zeros - canBeSwapped - canBeSwapped;
        long canBeFlipped = rem / 2;
        long onlyFlip = rem % 2;

        return (onlyFlip * flipCost) + 
        Math.min(canBeSwapped * swapCost, flipCost * 2 * canBeSwapped) + 
        Math.min(canBeFlipped * 2 * flipCost, (swapCost + crossCost) * canBeFlipped);
    }
}