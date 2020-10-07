package array;

// https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation

public class GameOfLife {
    int die = 2;
    int live = 3;

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                int count = count(i,j,board);
                if(board[i][j] == 0 && count == 3){
                    board[i][j] = 3;
                }else if(board[i][j] == 1){
                    if(count == 2 || count == 3) continue;
                    if(count < 2) board[i][j] = 2;
                    if(count > 3){
                        board[i][j] = die;
                    }
                }
            }
        }

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 2) {
                    board[i][j] = 0;
                }else if(board[i][j] == 3){
                    board[i][j] = 1;
                }
            }
        }
    }

    private int count(int i , int j , int[][] board){
        int ctr = 0;
        int[][] dirs = new int[][]{{1,0},{-1,0},{-1,-1},{1,1},{0,1},{0,-1},{-1,1},{1,-1}};
        for(int[] dir : dirs){
            int r = dir[0]+i;
            int c = dir[1] + j;
            if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && (board[r][c] == 1 || board[r][c] == die)){
                ctr = ctr + 1;
            }
        }
        return ctr;
    }
}
