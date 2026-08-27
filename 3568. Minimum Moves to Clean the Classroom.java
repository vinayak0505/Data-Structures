import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    private static final int[][] DIRECTIONS = {
            { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
    };

    private static class State {
        final int row;
        final int column;
        final int energy;
        final int collected;
        final int moves;

        State(int row, int column, int energy, int collected, int moves) {
            this.row = row;
            this.column = column;
            this.energy = energy;
            this.collected = collected;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int rows = classroom.length;
        int columns = classroom[0].length();
        char[][] grid = new char[rows][columns];
        int[][] itemIndex = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            grid[row] = classroom[row].toCharArray();
            for (int column = 0; column < columns; column++) {
                itemIndex[row][column] = -1;
            }
        }

        int itemCount = 0;
        int startRow = 0;
        int startColumn = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (grid[row][column] == 'S') {
                    startRow = row;
                    startColumn = column;
                }
                if (grid[row][column] == 'L') {
                    itemIndex[row][column] = itemCount++;
                }
            }
        }
        if (itemCount == 0) {
            return 0;
        }

        int allCollected = (1 << itemCount) - 1;
        boolean[][][][] visited = new boolean[rows][columns][energy + 1][allCollected + 1];
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(startRow, startColumn, energy, 0, 0));
        visited[startRow][startColumn][energy][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.collected == allCollected) {
                return current.moves;
            }
            if (current.energy == 0) {
                continue;
            }

            for (int[] direction : DIRECTIONS) {
                int nextRow = current.row + direction[0];
                int nextColumn = current.column + direction[1];
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns) {
                    continue;
                }

                if (grid[nextRow][nextColumn] == 'X') {
                    continue;
                } else {
                    int nextEnergy = grid[nextRow][nextColumn] == 'R'
                            ? energy
                            : current.energy - 1;
                    int collected = current.collected;
                    if (itemIndex[nextRow][nextColumn] != -1) {
                        collected |= 1 << itemIndex[nextRow][nextColumn];
                    }
                    if (visited[nextRow][nextColumn][nextEnergy][collected]) {
                        continue;
                    }
                    visited[nextRow][nextColumn][nextEnergy][collected] = true;
                    queue.add(new State(nextRow, nextColumn, nextEnergy, collected, current.moves + 1));
                }
            }
        }

        return -1;
    }
}