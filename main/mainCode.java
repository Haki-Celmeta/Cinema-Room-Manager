package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    static char[][] seats;
    static double purchasedTickets;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    static DecimalFormat format = new DecimalFormat("0.#");
    static int count10 = 0;
    static int count8 = 0;
    static int row;
    static int col;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();

        seats = new char[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++)
                seats[i][j] = 'S';
        }

        boolean flag = false;

        while(!flag){
            menu();
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    showSeats(rows, cols);
                    break;
                case 2:
                    bookTicket(rows, cols);
                    break;
                case 3:
                    showStatistics(rows, cols);
                    break;
                case 0:
                    flag = true;
                    break;
            }
        }
    }

    public static void menu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void showSeats(int rows, int cols){
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i = 1; i <= cols; i++)
            System.out.print(i + " ");

        System.out.println();
        for(int i = 0; i <= rows-1; i++){
            System.out.print(i+1 + " ");
            for(int j = 0; j <= cols-1; j++)
                System.out.print(seats[i][j] + " ");

            System.out.println();
        }
    }

    public static void bookTicket(int rows, int cols){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        col = scanner.nextInt();

        if((row-1 < 0 || col-1 < 0) || (row-1 > rows-1 || col-1 > cols-1)){
            System.out.println("Wrong input!");
        } else{
            if(seats[row-1][col-1] == 'B'){
                System.out.println("That ticket has already been purchased, please try another seat.");
                bookTicket(rows, cols);
            } else {
                seats[row-1][col-1] = 'B';
                if(rows * cols <= 60){
                    System.out.println("Ticket price: $" + 10);
                } else {
                    if(row <= rows/2){
                        System.out.println("Ticket price: $" + 10);
                        count10++;
                    }else {
                        System.out.println("Ticket price: $" + 8);
                        count8++;
                    }
                }
                purchasedTickets++;
            }
        }
    }

    public static void showStatistics(int rows, int cols){
        System.out.println("Number of purchased tickets: " + format.format(purchasedTickets));
        //percentage
        int totalSeats = rows * cols;
        double percent = (purchasedTickets / totalSeats) * 100;
        System.out.println("Percentage: " + df.format(percent) + "%");
        //CurrentIncome
        double currentIncome;
        int totalIncome;
        if(rows * cols <= 60){
            currentIncome = purchasedTickets * 10;
            totalIncome = rows * cols * 10;
        } else {
            currentIncome = count10*10 + count8*8;
            totalIncome = (rows/2)*10*cols + (rows - rows/2)*8*cols;
        }
        System.out.println("Current income: $" + format.format(currentIncome));
        //Total income
        System.out.println("Total income: $" + totalIncome);
    }
}

