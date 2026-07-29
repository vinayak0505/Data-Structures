class Solution {

    private int countBinaryPalindromesForLengthOnLimit(int length, long limit) {
        // boolean isOdd = (length & 1) == 1;

        StringBuilder str = new StringBuilder(Long.toBinaryString(limit));
        String firstHalf = str.substring(0, (length + 1) / 2);

        String removeStart = firstHalf.replaceFirst("1", "0");
        int ans = Integer.parseInt(removeStart, 2);

        boolean isPalindromeValid = true;
        for (int i = (length + 1) / 2; i < length; i++) {
            char originalBit = str.charAt(i);
            char mirroredBit = str.charAt(length - 1 - i);

            if (originalBit != mirroredBit) {
                if (originalBit < mirroredBit) {
                    isPalindromeValid = false; // Mirrored palindrome exceeds limit
                }
                break; // First mismatch determines if it's smaller or larger
            }
        }
        if(isPalindromeValid){
            ans++;
        }
        
        return ans ;

    }

    private int findUpperBoundOfAllOnes(long n) {
        int ans = 0;
        long num = 1;
        while (num < n) {
            ans++;
            num = (num << 1) + 1;
        }
        return ans;
    }

    private int countBinaryPalindromesForLength(int n) {
        if (n == 0 || n == 1 || n == 2)
            return 1;
        n--;
        n /= 2;
        return (int) Math.pow(2, n);
    }

    public int countBinaryPalindromes(long n) {
        if (n == 0)
            return 1;
        int ans = 0;
        int upperBoundOfAllOnes = findUpperBoundOfAllOnes(n);
        for (int i = upperBoundOfAllOnes; i >= 0; i--) {
            ans += countBinaryPalindromesForLength(i);
        }
        return ans + countBinaryPalindromesForLengthOnLimit(upperBoundOfAllOnes + 1, n);
    }
}