package tictactoe;

import java.util.*;

public class Main {
    static void display(char[][] table) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j]+" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private enum gameState {
        NotFinished,
        XWins,
        OWins,
        Draw;
    }
    private static gameState checkResult(char[][] field) {
        if (field[0][0] == 'X' && field[0][1] == 'X' && field[0][2] == 'X' ||
                field[1][0] == 'X' && field[1][1] == 'X' && field[1][2] == 'X' ||
                field[2][0] == 'X' && field[2][1] == 'X' && field[2][2] == 'X' ||
                field[0][0] == 'X' && field[1][0] == 'X' && field[2][0] == 'X' ||
                field[0][1] == 'X' && field[1][1] == 'X' && field[2][1] == 'X' ||
                field[0][2] == 'X' && field[1][2] == 'X' && field[2][2] == 'X' ||
                field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X' ||
                field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {
            return gameState.XWins;
        } else if (field[0][0] == 'O' && field[0][1] == 'O' && field[0][2] == 'O' ||
                field[1][0] == 'O' && field[1][1] == 'O' && field[1][2] == 'O' ||
                field[2][0] == 'O' && field[2][1] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][0] == 'O' && field[2][0] == 'O' ||
                field[0][1] == 'O' && field[1][1] == 'O' && field[2][1] == 'O' ||
                field[0][2] == 'O' && field[1][2] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O' ||
                field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {
            return gameState.OWins;
        } else if (field[0][0] == ' ' || field[0][1] == ' ' || field[0][2] == ' ' ||
                field[1][0] == ' ' || field[1][1] == ' ' || field[1][2] == ' ' ||
                field[2][0] == ' ' || field[2][1] == ' ' || field[2][2] == ' ') {
            return gameState.NotFinished;
        } else {
            return gameState.Draw;
        }
    }
    private static void easyMove(char[][] board) {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        while (true) {
            int row = (int) random.nextInt(3);
            int col = (int) random.nextInt(3);
            if (board[row][col] == ' ') {
                board[row][col] = 'O';
                break;
            }
        }
    }

    public static void main(String[] args) {
        char[][] table = new char[3][3];
        Arrays.stream(table).forEach(a -> Arrays.fill(a, ' '));
        Scanner sc = new Scanner(System.in);

        display(table);

        while (checkResult(table).equals(gameState.NotFinished)) {
            int row = 0, col = 0;
            System.out.print("Enter the coordinates: ");
            try {
                row = sc.nextInt() - 1;
                col = sc.nextInt() - 1;
            }
            catch(InputMismatchException ex) {
                sc.next();
                System.out.println("You should enter numbers!");
                continue;
            }

            if(row>2 || row<0 || col>2 || col<0){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (table[row][col] == 'X' || table[row][col] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            else{
                table[row][col] = 'X';
                display(table);
            }

            if (checkResult(table).equals(gameState.NotFinished)) {
                easyMove(table);
                display(table);
            }

            switch (checkResult(table)) {
                case XWins : System.out.println("X wins");
                            break;
                case OWins : System.out.println("O wins");
                            break;
                case Draw : System.out.println("Draw");
                            break;
            }
        }
    }
}


