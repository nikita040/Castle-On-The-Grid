//An easy solution is to create a node/cell class that represents the start point and the target point.
//Then we define a hashset that will contains all visited nodes.
//And a queue to store nodes and an arraylist to store next moves.
//Then we iterate through the loop and add the near elements only when it is valid.


class Solution {
    class Cell {
    int r, c;

    public Cell(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public String getKey() {
        return r + "," + c;
    }

    @Override
    public boolean equals(Object obj) {
        Cell cell = (Cell) obj;
        return r == cell.r && c == cell.c;
    }
}

class SnakePos {
    Cell cell1, cell2;

    public SnakePos(Cell cell1, Cell cell2) {
        this.cell1 = cell1;
        this.cell2 = cell2;
    }

    public String getKey() {
        return cell1.getKey() + ":" + cell2.getKey();
    }

    @Override
    public boolean equals(Object obj) {
        SnakePos snakePos = (SnakePos) obj;
        return snakePos.cell1.equals(cell1) && snakePos.cell2.equals(cell2);
    }
}
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        SnakePos src = new SnakePos(new Cell(0, 0), new Cell(0, 1));
        SnakePos des = new SnakePos(new Cell(n - 1, n - 2), new Cell(n - 1, n - 1));

        HashSet<String> visited = new HashSet<>();
        Queue<SnakePos> q = new LinkedList<>();
        q.add(src);
        visited.add(src.getKey());
        
        ArrayList<SnakePos> next_moves = new ArrayList<>(4);
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                SnakePos top = q.remove();
                if (top.equals(des))
                    return steps;
                    next_moves.clear();
                next_moves.add(tryMoveRight(top, grid));
                next_moves.add(tryMoveDown(top, grid));
                next_moves.add(tryMoveRotateClock(top, grid));
                next_moves.add(tryMoveRotateAntiClock(top, grid));
                    
                    for (SnakePos next : next_moves) {
                    if (next != null && !visited.contains(next.getKey())) {
                        visited.add(next.getKey());
                        q.add(next);
                    }
                }
            }
            steps++;
        }

        return -1;
    }
    
    boolean valid(int r, int c, int[][] grid) {
        int n = grid.length;
        return r >= 0 && r < n && c >= 0 && c < n && grid[r][c] == 0;
    }

    boolean valid(Cell cell, int[][] grid) {
        return valid(cell.r, cell.c, grid);
    }

    boolean valid(SnakePos snakePos, int[][] grid) {
        return valid(snakePos.cell1, grid) && valid(snakePos.cell2, grid);
    }

    
    SnakePos tryMoveRight(SnakePos curr, int[][] grid) {
        SnakePos next = new SnakePos(
                new Cell(curr.cell1.r, curr.cell1.c + 1),
                new Cell(curr.cell2.r, curr.cell2.c + 1)
        );
        if (valid(next, grid))
            return next;
        return null;
    }
    
    SnakePos tryMoveDown(SnakePos curr, int[][] grid) {
        SnakePos next = new SnakePos(
                new Cell(curr.cell1.r + 1, curr.cell1.c),
                new Cell(curr.cell2.r + 1, curr.cell2.c)
        );
        if (valid(next, grid))
            return next;
        return null;
    }
    
    SnakePos tryMoveRotateClock(SnakePos curr, int[][] grid) {
        if (curr.cell1.r == curr.cell2.r) {
            SnakePos next = new SnakePos(
                    new Cell(curr.cell1.r, curr.cell1.c),
                    new Cell(curr.cell2.r + 1, curr.cell2.c - 1)
            );
            if (valid(next.cell2, grid)
                    && valid(new Cell(curr.cell1.r + 1, curr.cell1.c + 1), grid)) {
                return next;
            }
        }
        return null;
    }
    SnakePos tryMoveRotateAntiClock(SnakePos curr, int[][] grid) {
        if (curr.cell1.c == curr.cell2.c) {
            SnakePos next = new SnakePos(
                    new Cell(curr.cell1.r, curr.cell1.c),
                    new Cell(curr.cell2.r - 1, curr.cell2.c + 1)
            );
            if (valid(next.cell2, grid)
                    && valid(new Cell(curr.cell1.r + 1, curr.cell1.c + 1), grid)) {
                return next;
            }
        }
        return null;
    }
}
