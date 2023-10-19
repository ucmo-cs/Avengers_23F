package com.avengers.project;

public class getRandomNumber {
    //random number generator program
    public static void main(String[] args){
        //Generate random number for project
        System.out.println("generating number");
        //for loop
        for (int i = 0; i < 49; i++){
            double ranNumber = Math.random() * 50;
            System.out.println(Math.round(ranNumber));
        }

        //I would like to store the numbers into a database, looking into how to do that
    }
}
