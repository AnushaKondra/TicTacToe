import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe
 */
public class TicTacToe {

    static ArrayList<Integer> player_pos = new ArrayList<>();
    static ArrayList<Integer> cpu_pos = new ArrayList<>();

    public static void main(String[] args) {        
        try (Scanner sc = new Scanner(System.in)) {
            char[][] board = {{' ','|',' ','|',' '},
                            {'-','+','-','+','-'},
                            {' ','|',' ','|',' '},
                            {'-','+','-','+','-'},
                            {' ','|',' ','|',' '},};
            
            System.out.println("Board:");
            printBoard(board);
            while(true){
                System.out.println("Enter position (1 - 9): ");
                int playerchoice = sc.nextInt();
                while(player_pos.contains(playerchoice) || cpu_pos.contains(playerchoice))
                {
                    System.out.println("Enter valid position");
                    playerchoice = sc.nextInt();
                }
                place(board, playerchoice, "player");
                Random rand = new Random();
                int cpuchoice = rand.nextInt(9)+1;
                while(player_pos.contains(cpuchoice) || cpu_pos.contains(cpuchoice))
                {
                    //System.out.println("Enter valid position");
                    cpuchoice = rand.nextInt(9)+1;
                }
               // System.out.println(player_pos+" "+cpuchoice);
                place(board,cpuchoice,"cpu");
                printBoard(board);
                String res = check();
               // System.out.println("res "+res);
                if(res.equals("You Won") || res.equals("You Lost:(") || res.equals("Tie")){
                    System.out.println(res);
                    return;
                }   
            }
        }

    }

    public static void printBoard(char[][] board){
        for(char[] row : board){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }
  
    public static void place(char[][] board,int pos,String user){
        char symbol = 'X';

        if(user.equals("cpu")){
            symbol = 'O';
        }

        if(symbol == 'X')
            player_pos.add(pos);
        else 
            cpu_pos.add(pos);
        switch(pos){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default : 
                break;
        }
    }

    public static String check(){
        List<Integer> row1 = Arrays.asList(1,2,3);
        List<Integer> row2 = Arrays.asList(4,5,6);
        List<Integer> row3 = Arrays.asList(7,8,9);
        List<Integer> col1 = Arrays.asList(1,4,7);
        List<Integer> col2 = Arrays.asList(2,5,8);
        List<Integer> col3 = Arrays.asList(3,6,9);
        List<Integer> d1 = Arrays.asList(1,5,9);
        List<Integer> d2 = Arrays.asList(7,5,3);

        List<List<Integer>> winnning_condition = new ArrayList<>();
        winnning_condition.add(row1);
        winnning_condition.add(row2);
        winnning_condition.add(row3);
        winnning_condition.add(col1);
        winnning_condition.add(col2);
        winnning_condition.add(col3);
        winnning_condition.add(d1);
        winnning_condition.add(d2);
        
        for(List<Integer> temp : winnning_condition){
            if(player_pos.containsAll(temp))
                return "You Won";
            else if(cpu_pos.containsAll(temp))
                return "You Lost:(";
            else if(player_pos.size() +  cpu_pos.size() == 9)
                return "Tie";
        }
        return "";
    }   

}