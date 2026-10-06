import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Solution {

    private HashSet<Integer> makeDiff(int num, int maxLen) {
        HashSet<Integer> ans = new HashSet<>();
        StringBuilder st = new StringBuilder(String.format("%0" + maxLen + "d", num));
        ans.add(num);
        for (int i = 0; i < st.length(); i++) {
            for (int j = i + 1; j < st.length(); j++) {
                if (st.charAt(i) == st.charAt(j))
                    continue;
                char a = st.charAt(i);
                char b = st.charAt(j);
                st.setCharAt(i, b);
                st.setCharAt(j, a);
                ans.add(Integer.parseInt(st.toString()));
                st.setCharAt(i, a);
                st.setCharAt(j, b);
            }
        }
        return ans;
    }

    public int countPairs(int[] nums) {
        int ans = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int len = 0;
            int num = nums[i];
            while (num > 0) {
                len++;
                num /= 10;
            }
            maxLen = Math.max(len, maxLen);
        }
        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> st = makeDiff(nums[i], maxLen);
            HashSet<Integer> newst = new HashSet<>();
            for (int s : st) {
                ArrayList<Integer> arrayList;
                if (mp.containsKey(s) == false) {
                    arrayList = new ArrayList<>();
                    mp.put(s, arrayList);
                } else {
                    arrayList = mp.get(s);
                }
                newst.addAll(arrayList);
                arrayList.add(i);
            }
            ans += newst.size();
        }
        return ans;
    }
}