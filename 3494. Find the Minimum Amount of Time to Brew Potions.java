import java.util.Arrays;

class Solution {
    public long minTime(int[] skill, int[] mana) {
        int manaLength = mana.length;
        int skillLength = skill.length;

        long prevDp[] = new long[skillLength];
        long curDp[] = new long[skillLength];
        long count = 0;
        for (int i = 0; i < skillLength; i++) {
            count += skill[i] * mana[0];
            prevDp[i] = count;
        }

        for (int i = 1; i < manaLength; i++) {
            long diff = prevDp[0];
            count = 0;
            for (int j = 0; j < skillLength - 1; j++) {
                count += skill[j] * mana[i];
                diff = Math.max(diff, prevDp[j + 1] - count);
            }
            count = 0;
            for (int j = 0; j < skillLength; j++) {
                count += skill[j] * mana[i];
                curDp[j] = count + diff;
            }
            prevDp = Arrays.copyOf(curDp, skillLength);
        }
        return prevDp[skillLength - 1];

    }
}