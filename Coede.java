import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Node{
        int row;
        int col;

        public Node(int r, int c){
            this.row = r;
            this.col = c;
        }
    }
    
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        int size = grid.length;
        Queue<Node> q = new LinkedList<>();

        char [][]color = new char[size][size];

        for(int i =0; i<size; i++){
            for(int j =0; j<size;j++){
                if(grid[i].charAt(j)=='X'){
                    color[i][j] ='X';
                }
                else{
                    color[i][j] ='w';
                }
                
            }
        }

        Node [][]p =new Node[size][size];

        q.add(new Node(startX,startY));
        color[startX][startY] ='g';
        int rowc = 0;
        int colc = 0;
        int y =1;
        
        while(!q.isEmpty()){
            Node temp = q.remove();
            
            rowc = temp.row;
            colc = temp.col;
            
            color[rowc][colc] ='r';
            

            for(int a = colc ;a<size; a++){
                if(color[rowc][a] =='w'){
                    q.add(new Node(rowc,a));
                    p[rowc][a]=temp;
                    color[rowc][a] ='g';

                    
                }
                else if(color[rowc][a] =='X'){
                    break;
                }
            }

            for(int b =colc; b>=0 ; b--){
                if(color[rowc][b] =='w'){
                    q.add(new Node(rowc,b));
                    p[rowc][b]=temp;
                    color[rowc][b] ='g';
                    
                }   
                else if(color[rowc][b] =='X'){
                    break;
                }
            }

            for(int c =rowc; c<size ; c++){
                if(color[c][colc] =='w'){
                    q.add(new Node(c,colc));
                    p[c][colc]=temp;
                    color[c][colc] ='g';
                    
                }   
                else if(color[c][colc] =='X'){
                    break;
                }
            }

            for(int d =rowc; d>=0 ; d--){
                if(color[d][colc] =='w'){
                    q.add(new Node(d,colc));
                    p[d][colc]=temp;
                    color[d][colc] ='g';
                    
                }
                else if(color[d][colc] =='X'){
                    break;
                }   
            }


        }

        
    
    
    
    int start = p[goalX][goalY].row;
    int end = p[goalX][goalY].col;
    
    int count =1;
    while(true){
        if(start== startX && end == startY){
            break;
        }
        int val1 = start;
        start = p[start][end].row;
        System.out.println(start);
        end = p[val1][end].col;
        System.out.println(end);
        
        count++;
        
    }

        return count;
   }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
