import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    class State {
        double totalTime;
        int boatPosition;
        int mask;
        int stage;

        public State(double totalTime, int boatPosition, int mask, int stage) {
            this.totalTime = totalTime;
            this.boatPosition = boatPosition;
            this.mask = mask;
            this.stage = stage;
        }

        @Override
        public String toString() {
            return "totalTime " + totalTime + " boastPostion " + (boatPosition == 0 ? "left" : "right")
                    + " mask " + mask + " stage " + stage;
        }
    }

    public double minTime(int people, int limit, int stages, int[] time, double[] mul) {

        int all1s = (1 << people) - 1;

        double INF = 1e9 + 7;
        // boat position 0 -> left and 1 means right
        double dp[][][] = new double[2][stages][all1s + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < stages; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> Double.compare(a.totalTime, b.totalTime));
        queue.add(new State(0, 0, 0, 0));
        dp[0][0][0] = 0;

        while (!queue.isEmpty()) {
            State top = queue.poll();

            if (top.mask == all1s && top.boatPosition == 1)
                return top.totalTime;

            if (top.boatPosition == 1) {
                int peopleOnRight = top.mask;
                for (int i = 0; i < people; i++) {
                    if (((1 << i) & peopleOnRight) == 0)
                        continue;
                    double additionalTime = mul[top.stage] * time[i];
                    int newStage = (top.stage + (int) (additionalTime)) % stages;

                    if(dp[0][newStage][top.mask ^ (1 << i)] <= top.totalTime + additionalTime)
                        continue;
                    dp[0][newStage][top.mask ^ (1 << i)] = top.totalTime + additionalTime;

                    queue.add(new State(top.totalTime + additionalTime, 0, top.mask ^ (1 << i), newStage));
                }

                continue;
            }

            for (int newSelection = 1; newSelection <= all1s; newSelection++) {
                if ((top.mask & newSelection) != 0)
                    continue;
                if (Integer.bitCount(newSelection) > limit)
                    continue;

                int maxTime = 0;
                for (int i = 0; i < people; i++) {
                    if (((1 << i) & newSelection) == 0)
                        continue;
                    maxTime = Math.max(maxTime, time[i]);
                }

                double additionalTime = mul[top.stage] * maxTime;
                int newStage = (top.stage + (int) (additionalTime)) % stages;

                if (top.totalTime + additionalTime >= dp[1][newStage][(top.mask | newSelection)])
                    continue;
                dp[1][newStage][(top.mask | newSelection)] = top.totalTime + additionalTime;

                queue.add(new State(top.totalTime + additionalTime, 1, (top.mask | newSelection), newStage));

            }

        }

        return -1;

    }
}