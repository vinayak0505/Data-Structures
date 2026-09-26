class Solution {

    private int makeInMinOperation(int ans, int occ[]) {
        int dpIfZero[] = new int[26];
        int dpIfAns[] = new int[26];
        int lastIndexWithOcc = -1;

        if (occ[0] == 0) {
            dpIfAns[0] = 0;
        } else {
            dpIfAns[0] = Math.abs(occ[0] - ans);
            lastIndexWithOcc = 0;
        }

        dpIfZero[0] = occ[0];
        int steps = 0;

        for (int i = 1; i < 26; i++) {
            if (occ[i] == 0)
                continue;
            int prevMinValue = 0;
            if (lastIndexWithOcc != -1) {
                prevMinValue = Math.min(dpIfZero[lastIndexWithOcc], dpIfAns[lastIndexWithOcc]);
            }
            if (i - 1 != lastIndexWithOcc) {
                dpIfAns[i] = prevMinValue + Math.abs(occ[i] - ans);
            } else {
                if (occ[i] >= ans) {
                    dpIfAns[i] = prevMinValue + occ[i] - ans;
                } else {
                    if (occ[i - 1] >= ans) {
                        dpIfAns[i] = dpIfAns[i - 1] + ans - occ[i] - Math.min(ans - occ[i], occ[i - 1] - ans);
                    } else {
                        dpIfAns[i] = dpIfZero[i - 1] + ans - occ[i] - Math.min(ans - occ[i], occ[i - 1] - 0);
                        dpIfAns[i] = Math.min(dpIfAns[i], dpIfAns[i - 1] + ans - occ[i]);
                    }
                }
            }
            dpIfZero[i] = prevMinValue + occ[i];
            lastIndexWithOcc = i;
            steps = Math.min(dpIfAns[i], dpIfZero[i]);
        }

        return steps;
    }

    public int makeStringGood(String s) {
        int occ[] = new int[26];
        for (char c : s.toCharArray()) {
            occ[c - 'a']++;
        }

        int ans = s.length();

        for (int posAns = 0; posAns <= s.length(); posAns++) {
            ans = Math.min(ans, makeInMinOperation(posAns, occ));
        }
        return ans;
    }
}