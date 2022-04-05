package minesweeper;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final private Scanner scanner = new Scanner(System.in);
    public static void main(String args[]) {
        int firstValue = 11;
        int secondValue = 11;
        Character[][] Grid = new Character[firstValue][secondValue];
        System.out.print("How many mines do you want on the field? ");
        int n = scanner.nextInt();
        fillGrid(Grid);
        setCeils(Grid, firstValue, secondValue, n);

        calculateCeils(Grid, firstValue, secondValue);

        Character[][] View = new Character[firstValue][secondValue];
        fillGrid(View);

        printCeils(View);
        int countStars = 0;
        int countX = n;
        while(n > 0 || countStars != countX) {
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            //String[] command = scanner.nextLine().split(" ");
            int y = scanner.nextInt();
            int x = scanner.nextInt();
            String word = scanner.next();
            System.out.println();
            if(word.equals("free")) {
                if(Grid[x][y] == 'X') {
                    View[x][y] = '*';
                    printCeils(View);
                    System.out.println("You stepped on a mine and failed!");
                    break;
                }
                if (Grid[x][y] != 'X' && Grid[x][y] != '/' && Grid[x][y] != '*') {
                    FreeDot(View, Grid, x, y);
                }
            } else if (word.equals("mine")) {
                if(View[x][y] == '.') {
                    View[x][y] = '*';
                } else if(View[x][y] == '*') {
                    View[x][y] = '.';
                }
            }

            printCeils(View);
            //printCeils(Grid);
            if(checkWin(View, Grid, n)) {
                System.out.println("Congratulations! You found all the mines!");
                break;
            }
        }
    }

    public static boolean checkWin(Character[][] Arr, Character[][] MainArr, int n) {
        int nn = 0;
        for (int i = 1; i < (Arr.length - 1); i++) {
            for (int j = 1; j < (Arr[i].length - 1); j++) {
                if (MainArr[i][j] == '*' && Arr[i][j] == '*') {
                    nn ++;
                }
            }
        }
        if (nn == n) {
            return true;
        } else {
            return false;
        }
    }
    public static void FreeDot(Character[][] Arr, Character[][] MainArr, int xVal, int yVal) {
        if (MainArr[xVal][yVal] != null) {
            if (MainArr[xVal][yVal] == '.') {
                Arr[xVal][yVal] = '/';
                MainArr[xVal][yVal] = '/';
            }
            if (MainArr[xVal][yVal] != 'X' && MainArr[xVal][yVal] != '/' && MainArr[xVal][yVal] != '.') {
                Arr[xVal][yVal] = MainArr[xVal][yVal];
            }
        }
        if (MainArr[xVal - 1][yVal - 1] != null) {
            if (MainArr[xVal - 1][yVal - 1] == '.') {
                Arr[xVal - 1][yVal - 1] = '/';
                MainArr[xVal - 1][yVal - 1] = '/';
                FreeDot(Arr, MainArr, xVal - 1, yVal -1);
            }
            if (MainArr[xVal - 1][yVal - 1] != 'X' && MainArr[xVal - 1][yVal - 1] != '/' && MainArr[xVal - 1][yVal - 1] != '.') {
                Arr[xVal - 1][yVal - 1] = MainArr[xVal - 1][yVal - 1];
            }
        }
        if (MainArr[xVal][yVal - 1] != null) {
            if (MainArr[xVal][yVal - 1] == '.') {
                Arr[xVal][yVal - 1] = '/';
                MainArr[xVal][yVal - 1] = '/';
                FreeDot(Arr, MainArr, xVal, yVal -1);
            }
            if (MainArr[xVal][yVal - 1] != 'X' && MainArr[xVal][yVal - 1] != '/' && MainArr[xVal][yVal - 1] != '.') {
                Arr[xVal][yVal - 1] = MainArr[xVal][yVal - 1];
            }
        }
        if (MainArr[xVal + 1][yVal - 1] != null) {
            if (MainArr[xVal + 1][yVal - 1] == '.') {
                Arr[xVal + 1][yVal - 1] = '/';
                MainArr[xVal + 1][yVal - 1] = '/';
                FreeDot(Arr, MainArr, xVal + 1, yVal -1);
            }
            if (MainArr[xVal + 1][yVal - 1] != 'X' && MainArr[xVal + 1][yVal - 1] != '/' && MainArr[xVal + 1][yVal - 1] != '.') {
                Arr[xVal + 1][yVal - 1] = MainArr[xVal + 1][yVal - 1];
            }
        }
        if (MainArr[xVal - 1][yVal + 1] != null) {
            if (MainArr[xVal - 1][yVal + 1] == '.') {
                Arr[xVal - 1][yVal + 1] = '/';
                MainArr[xVal - 1][yVal + 1] = '/';
                FreeDot(Arr, MainArr, xVal - 1, yVal +1);
            }
            if (MainArr[xVal - 1][yVal + 1] != 'X' && MainArr[xVal - 1][yVal + 1] != '/' && MainArr[xVal - 1][yVal + 1] != '.') {
                Arr[xVal - 1][yVal + 1] = MainArr[xVal - 1][yVal + 1];
            }
        }
        if (MainArr[xVal][yVal + 1] != null) {
            if (MainArr[xVal][yVal + 1] == '.') {
                Arr[xVal][yVal + 1] = '/';
                MainArr[xVal][yVal + 1] = '/';
                FreeDot(Arr, MainArr, xVal, yVal +1);
            }
            if (MainArr[xVal][yVal + 1] != 'X' && MainArr[xVal][yVal + 1] != '/' && MainArr[xVal][yVal + 1] != '.') {
                Arr[xVal][yVal + 1] = MainArr[xVal][yVal + 1];
            }
        }
        if (MainArr[xVal + 1][yVal + 1] != null) {
            if (MainArr[xVal + 1][yVal + 1] == '.') {
                Arr[xVal + 1][yVal + 1] = '/';
                MainArr[xVal + 1][yVal + 1] = '/';
                FreeDot(Arr, MainArr, xVal + 1, yVal +1);
            }
            if (MainArr[xVal + 1][yVal + 1] != 'X' && MainArr[xVal + 1][yVal + 1] != '/' && MainArr[xVal + 1][yVal + 1] != '.') {
                Arr[xVal + 1][yVal + 1] = MainArr[xVal + 1][yVal + 1];
            }
        }
        if (MainArr[xVal - 1][yVal] != null) {
            if (MainArr[xVal - 1][yVal] == '.') {
                Arr[xVal - 1][yVal] = '/';
                MainArr[xVal - 1][yVal] = '/';
                FreeDot(Arr, MainArr, xVal - 1, yVal);
            }
            if (MainArr[xVal - 1][yVal] != 'X' && MainArr[xVal - 1][yVal] != '/' && MainArr[xVal - 1][yVal] != '.') {
                Arr[xVal - 1][yVal] = MainArr[xVal - 1][yVal];
            }
        }
        if (MainArr[xVal + 1][yVal] != null) {
            if (MainArr[xVal + 1][yVal] == '.') {
                Arr[xVal + 1][yVal] = '/';
                MainArr[xVal + 1][yVal] = '/';
                FreeDot(Arr, MainArr, xVal + 1, yVal);
            }
            if (MainArr[xVal + 1][yVal] != 'X' && MainArr[xVal + 1][yVal] != '/' && MainArr[xVal + 1][yVal] != '.') {
                Arr[xVal + 1][yVal] = MainArr[xVal + 1][yVal];
            }
        }
    }

    public static void calculateCeils(Character[][] Arr, int firstValue, int secondValue) {
        int count = 0;
        for (int i = 1; i < (Arr.length - 1); i++) {
            for (int j = 1; j < (Arr[i].length - 1); j++) {
                if (Arr[i][j] == '.') {
                    if (Arr[i - 1][j - 1] != null) {
                        if (Arr[i - 1][j - 1] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i][j - 1] != null) {
                        if (Arr[i][j - 1] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i + 1][j - 1] != null) {
                        if (Arr[i + 1][j - 1] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i - 1][j + 1] != null) {
                        if (Arr[i - 1][j + 1] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i][j + 1] != null) {
                        if (Arr[i][j + 1] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i + 1][j + 1] != null) {
                        if (Arr[i + 1][j + 1] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i - 1][j] != null) {
                        if (Arr[i - 1][j] == 'X') {
                            count++;
                        }
                    }
                    if (Arr[i + 1][j] != null) {
                        if (Arr[i + 1][j] == 'X') {
                            count++;
                        }
                    }

                    if (count > 0) {
                        Arr[i][j] = (char) (count + '0');
                        count = 0;
                    }
                }
            }
        }
    }


    public static void setCeils(Character[][] Arr, int firstValue, int secondValue, int number) {
        while(number > 0) {
            Random random = new Random();
            int x = random.nextInt(firstValue - 2) + 1;
            int y = random.nextInt(secondValue - 2) + 1;
            if(Arr[x][y] == '.') {
                Arr[x][y] = 'X';
                number -= 1;
            }
        }
    }

    private static void fillGrid(Character[][] Arr) {
        for (int i = 1; i < (Arr.length - 1); i++) {
            for (int j = 1; j < (Arr[i].length - 1); j++) {
                Arr[i][j] = '.';
            }
        }
    }

    public static void printCeils(Character[][] Arr) {
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        for (int i = 1; i < (Arr.length - 1); i++) {
            System.out.print(i + "|");
            for (int j = 1; j < (Arr[i].length - 1); j++) {
                System.out.print(Arr[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-|---------|");
    }

    public static void changeXtoDot(Character[][] Arr, Character[][] MainArr) {
        for (int i = 1; i < (Arr.length - 1); i++) {
            for (int j = 1; j < (Arr[i].length - 1); j++) {
                if (MainArr[i][j] == 'X') {
                    Arr[i][j] = '.';
                } else {
                    Arr[i][j] = MainArr[i][j];
                }
            }
        }
    }
}
