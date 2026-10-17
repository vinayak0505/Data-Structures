import java.util.HashMap;

class Solution {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String ans[] = new String[n];
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String str = arr[i];
            for (int startIndex = 0; startIndex < str.length(); startIndex++) {
                StringBuilder sb = new StringBuilder();
                for (int end = startIndex; end < str.length(); end++) {
                    sb.append(str.charAt(end));
                    hm.put(sb.toString(), hm.getOrDefault(sb.toString(), 0) + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            String str = arr[i];
            for (int startIndex = 0; startIndex < str.length(); startIndex++) {
                StringBuilder sb = new StringBuilder();
                for (int end = startIndex; end < str.length(); end++) {
                    sb.append(str.charAt(end));
                    hm.put(sb.toString(), hm.get(sb.toString()) - 1);
                }
            }

            for (int startIndex = 0; startIndex < str.length(); startIndex++) {
                StringBuilder sb = new StringBuilder();
                for (int end = startIndex; end < str.length(); end++) {
                    sb.append(str.charAt(end));
                    if (hm.get(sb.toString()) == 0) {
                        if (ans[i] == null || ans[i].length() > sb.length()
                                || (ans[i].length() == sb.length() && ans[i].compareTo(sb.toString()) > 0)) {
                            ans[i] = sb.toString();
                        }
                    }
                }
            }

            if (ans[i] == null) {
                ans[i] = "";
            }

            for (int startIndex = 0; startIndex < str.length(); startIndex++) {
                StringBuilder sb = new StringBuilder();
                for (int end = startIndex; end < str.length(); end++) {
                    sb.append(str.charAt(end));
                    hm.put(sb.toString(), hm.get(sb.toString()) + 1);
                }
            }
        }
        return ans;
    }
}