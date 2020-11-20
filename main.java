package com.namespace;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int loan = (int) readNumber("Enter Loan amount: ", 100_000, 1_000_000);
        float annualInterest = (float) readNumber("Enter Annual Interest in %: ", 1, 4);
        byte period = (byte) readNumber("Enter Loan Period in Years: ", 1,30);

        //Call mortgageCal method to calculate mortgage per month.
        double mortgage = mortgageCal(loan, annualInterest, period);
        
        //construct currency instance for formatting purpose.
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        
        //print out mortgage per month in currency format.
        System.out.println("Mortgage per month: " + curr.format(mortgage));
        
        //print out Balance Schedule.
        printBalanceSchedule(loan, annualInterest, period);
    }

        //Method - print Balance Schedule
        
        public static void printBalanceSchedule(int loan, float annualInterest, byte period) {
        
        System.out.println();
        System.out.println("Balance Schedule");
        System.out.println("_________________");
        
        //Loop balance per month for Balance Schedule
        for (short month = 1; month <= period *12; month++){
            double balance = balanceCal(loan, annualInterest, period, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
            }
    }

        //Method - readNumber
    
        public static double readNumber(String prompt, double min, double max){
        Scanner input = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = input.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + (int)min + " and " + (int)max);
            }
            return value;
        }
        
        //Method - Mortgage Calculator
        
        public static double mortgageCal(int loan, float annualInterest, byte period) {
        // r stands for monthly interest expressed as a float instead of percent
        // n stands for total number of monthly payments
            float r = annualInterest / 12 / 100;
            short n = (short) (period * 12);
            return loan
                    * ((r * (Math.pow(1 + r, n)))
                    / ((Math.pow(1 + r, n)) - 1));
        }
        
        //Method - Balance Calculator
        
        public static double balanceCal(int loan, float annualInterest, byte period, short numberOfPaymentsMade) {

            float r = annualInterest / 12 / 100;
            short n = (short) (period * 12);
            return loan
                    *((Math.pow(1+r,n)-Math.pow(1+r, numberOfPaymentsMade)))
                    /((Math.pow(1+r,n)-1));
        }
}
