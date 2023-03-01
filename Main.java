//Programmer: John Bedlington
//Date: 2/23/2023
//Class: CS &145
//Assignment: Assignment 1: Word search
//This program will create a basic word search generator

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

        boolean generated = false;
        Scanner console = new Scanner(System.in);
        String choice;
        WordSearch search = new WordSearch();

        do { //do while loop for user interface
            printIntro();
            choice = console.next();
            if(choice.equals("g")){ //gen new word search
                System.out.println("Enter words line by line until you are finished at which point type a single \"q\"");
                String newWord = console.next();
                ArrayList<String> wordGrid = new ArrayList<String>();
                do{
                    wordGrid.add(newWord);
                    newWord = console.next();
                }
                while(!newWord.equals("q"));
                String[] words = new String[wordGrid.size()];
                wordGrid.toArray(words);
                search.generate(words);
                generated = true;
            }
            else if(choice.equals("p")){ //prints word search
                if(generated){
                    print(search);
                }
            }
            else if(choice.equals("s")){ //prints solution
                if(generated){
                    showSolution(search);
                }
            }
        }
        while(!choice.equals("q"));
    }//end of main method

    public static void printIntro(){
        System.out.println("Welcome to my word search generator.");
        System.out.println("This programs will allow you to generate your own word search puzzle");
        System.out.println("Please select and option: ");
        System.out.println("Generate a new word search (g)");
        System.out.println("Print out your word search (p)");
        System.out.println("Show the solution to your word search(s)");
        System.out.println("Quit the program (q)");
    }//end of printIntro method

    public static void print(WordSearch ws){
        System.out.println(ws);
    }//end of print method

    public static void showSolution(WordSearch ws){
        System.out.println(ws.toSolution());
    }//end of showSolution method
}//end of class
