class Solution {
    public String convert(String str, int b) {
        // Handle edge case if the string is just "0"
        if (str.equals("0") || str.isEmpty()) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        String currentStr = str;

        // Keep dividing currentStr by base b until it becomes "0"
        while (!currentStr.equals("0") && !currentStr.isEmpty()) {
            StringBuilder quotient = new StringBuilder();
            int remainder = 0;

            // Perform manual long division digit-by-digit (left-to-right)
            for (int i = 0; i < currentStr.length(); i++) {
                int currentDigit = currentStr.charAt(i) - '0';
                int currentValue = remainder * 10 + currentDigit;

                int qDigit = currentValue / b;
                remainder = currentValue % b;

                // Build quotient string (avoid adding leading zeros)
                if (quotient.length() > 0 || qDigit > 0) {
                    quotient.append(qDigit);
                }
            }

            // Save the remainder (this is one digit in base b)
            result.append(remainder);

            // Update currentStr to be the quotient for the next loop pass
            currentStr = quotient.length() == 0 ? "0" : quotient.toString();
        }

        // Remainders are collected from last to first, so reverse the string
        return result.reverse().toString();
    }

    int mod = (int) 1e9 + 7;

    int dp[][][];

    public int helper(String st, int i, boolean top, int base, int prev) {
        if (i == st.length()) {
            return 1;
        }

        if(dp[i][prev][top ? 0 : 1] != 0){
            return dp[i][prev][top ? 0 : 1];
        }

        int count = 0;

        if (top) {
            int till = Math.min(st.charAt(i) - '0', base - 1);
            for (int j = prev; j < till; j++) {
                count = (count + helper(st, i + 1, false, base, j)) % mod;
            }
            if (prev <= till) {
                count = (count + helper(st, i + 1, true, base, till)) % mod;
            }
        } else {
            for (int j = prev; j < base; j++) {
                count = (count + helper(st, i + 1, false, base, j)) % mod;
            }
        }

        return dp[i][prev][top ? 0 : 1] = count;
    }

    public String minOne(String str) {
        if (str == null || str.isEmpty())
            return "";
        if (str.equals("0"))
            return "-1";

        StringBuilder st = new StringBuilder(str);
        int i = st.length() - 1;

        while (i >= 0 && st.charAt(i) == '0') {
            st.setCharAt(i, '9');
            i--;
        }

        if (i >= 0) {
            st.setCharAt(i, (char) (st.charAt(i) - 1));
        }

        if (st.length() > 1 && st.charAt(0) == '0') {
            st.deleteCharAt(0);
        }

        return st.toString();
    }

    public int countNumbers(String l, String r, int b) {
        String from = convert(minOne(l), b);
        System.out.println(from);
        String to = convert(r, b);
        System.out.println(to);

        dp = new int[from.length()][b + 1][2];
        int countFrom = helper(from, 0, true, b, 0);
        System.out.println(countFrom);
        dp = new int[to.length()][b + 1][2];
        int countTo = helper(to, 0, true, b, 0);
        System.out.println(countTo);

        return (countTo - countFrom + mod) % mod;
    }
}