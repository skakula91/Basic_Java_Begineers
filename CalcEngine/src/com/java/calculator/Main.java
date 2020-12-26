package com.java.calculator;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	   double[] leftVals = {100.0d, 25.0d,225.0d,11.0d};
	   double[] rightVals = {50.0d, 92.0d, 17.0d, 3.0d};

	   char[] opCodes = {'d','a','s','m'};

	   double[] result = new double[opCodes.length];

	   if(args.length == 0)
       {
           for(int i = 0; i < opCodes.length; i++)
           {
               result[i] = execute(opCodes[i],leftVals[i],rightVals[i]);
           }

           for(double res : result)
           {
               System.out.println(res);
           }
       }
	   else if(args.length == 1 && args[0].equals("interactive"))
       {
           executeInteractively();
       }
	   else if(args.length == 3)
       {
           handleCommandLine(args);
       }
	   else
       {
           System.out.println("Please provide an operation code and 2 numeric values");
       }
	   //ConvertItToRadix();
    }

    private static void ConvertItToRadix()
    {
        int iVal = 32;
        String s1 = String.format("%d",iVal);
        String s2 = String.format("%x",iVal);

        // # tells what is the base value
        String s3 = String.format("%#x",iVal);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    private static void handleCommandLine(String[] args)
    {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);

        double result = execute(opCode, leftVal,rightVal);
        System.out.println(result);
    }

    private static void executeInteractively()
    {
        System.out.println("Enter an operation and two numbers:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        performOperation(parts);
    }

    private static void performOperation(String[] parts) {
        char opCode =  parts[0].charAt(0);

        if(opCode == 'w')
        {
            handleWhen(parts);
        }
        else {
            double leftVal = valueFromWord(parts[1]);
            double rightVal = valueFromWord(parts[2]);

            double result = execute(opCode, leftVal, rightVal);
            displayResult(opCode, leftVal, rightVal, result);
        }
    }

    private static void handleWhen(String[] parts)
    {
        DateTimeFormatter usDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = parts[1];
        LocalDate startDate = LocalDate.parse(date,usDateFormatter);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate, daysToAdd,newDate);
        System.out.println(output);
    }

    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
//        StringBuilder builder = new StringBuilder(20);
//        builder.append(leftVal);
//        builder.append(" ");
//        builder.append(symbol);
//        builder.append(" ");
//        builder.append(rightVal);
//        builder.append(" = ");
//        builder.append(result);
//        String output = builder.toString();
        //.3f displays 3 decimal places
        String output = String.format("%f %c %f = %.3f", leftVal, symbol,rightVal,result);
        System.out.println(output);
    }

    private static double execute(char opCode, double leftVal, double rightVal)
    {
        double result;
        switch (opCode) {
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'm':
                result = leftVal * rightVal;
                break;
            case 'd':
                result = rightVal != 0 ? leftVal / rightVal : 0.0d;
                break;
            default:
                System.out.println("Invalid opCode: " + opCode);
                result = 0.0d;
                break;
        }
        return result;
    }

    private static char symbolFromOpCode(char opCode) {
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';
        for(int index = 0; index < opCodes.length; index++) {
            if(opCode == opCodes[index]) {
                symbol = symbols[index];
                break;
            }
        }

        return symbol;
    }

    private static double valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };
        double value = 0d;
        for(int index = 0; index < numberWords.length; index++) {
            if(word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        return value;
    }
}
