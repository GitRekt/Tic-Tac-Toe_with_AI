package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void display(char table[][]) {
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
    private static gameState state = gameState.NotFinished;
    private static gameState checkResult(char field[][]) {
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
    public static void main(String[] args) {
        char table[][] = new char[3][3];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the cells: ");
        String str = sc.nextLine();
        int c=0, x=0, o=0;
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            { char ch = str.charAt(c);
                if(ch!='_') {
                    if(ch == 'X') x++;
                    else if(ch == 'O') o++;
                    table[i][j] = ch;
                }
                else
                    table[i][j] = ' ';
                c++;
            }
        }

        display(table);

        boolean valid = false;
        while (!valid) {
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
                if(x==o){
                    table[row][col] = 'X';
                }
                else if(x>o){
                    table[row][col] = 'O';
                }
                display(table);

                switch (checkResult(table)) {
                    case XWins:
                        System.out.println("X wins");
                        valid = true;
                        break;
                    case OWins:
                        System.out.println("O wins");
                        valid = true;
                        break;
                    case Draw:
                        System.out.println("Draw");
                        valid = true;
                        break;
                    case NotFinished:
                        System.out.println("Game not finished");
                        break;
                }
            }

        }
    }
}

