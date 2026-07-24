import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
* Test program for CS318 Programming Project 2A. This program tests for the
* correct result of Pass 1 of the assembler. It does not test the RuntimeException
* that should be thrown by Pass 1; you should write your own tests to verify
* your code throws this exception as expected.
*
* The Assembler_P1.java file that you submit for Programming Assignment 2A should
* pass all of these tests, with no modifications made to this file.
*
* A good strategy for working on this assignment is to work on passing each
* of the tests in order. Comment out Tests 2 through 4, and work on passing
* Test 1. Then work on passing Test 2, etc.
*
* When your Assembler.java file is completed, it must run without any
* modifications to this file.
*
* @author Christine Reilly
* Copyright 2024 Christine F. Reilly creilly@skidmore.edu
*/
public class TestAssembler_P1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        ArrayList<LabelOffset> lo;
        LabelOffset l;

        ////////// TEST 1 //////////
        System.out.println("Starting Test 1");
        lo = Assembler_P1.assemble("testProg1.s", "testProg1.data", "testProg1.code");
        compareFiles("testProg1.data", "correct_testProg1.data");
        compareFiles("testProg1.code", "correct_testProg1.code");

        // Check the label-offset list
        System.out.println("**** Checking Label-Offset list");
        if(lo.size() != 1) {
            System.out.println("ERROR: incorrect size of label-offset list: " + lo.size());
        }

        l = lo.get(0);
        if(!(l.label.equals("main"))) {
            System.out.println("ERROR: incorrect label at index 0: " + l.label);
        }
        if(l.offset != 0) {
            System.out.println("ERROR: incorrect offset at index 0: " + l.offset);
        }

        System.out.println("Finished Test 1\n");

        ////////// TEST 2 //////////
        System.out.println("Starting Test 2");
        lo = Assembler_P1.assemble("testProg2.s", "testProg2.data", "testProg2.code");
        compareFiles("testProg2.data", "correct_testProg2.data");
        compareFiles("testProg2.code", "correct_testProg2.code");

        // Check the label-offset list
        System.out.println("**** Checking Label-Offset list");
        if(lo.size() != 1) {
            System.out.println("ERROR: incorrect size of label-offset list: " + lo.size());
        }

        l = lo.get(0);
        if(!(l.label.equals("main"))) {
            System.out.println("ERROR: incorrect label at index 0: " + l.label);
        }
        if(l.offset != 0) {
            System.out.println("ERROR: incorrect offset at index 0: " + l.offset);
        }

        System.out.println("Finished Test 2\n");


        ////////// TEST 3 //////////
        System.out.println("Starting Test 3");
        lo = Assembler_P1.assemble("testProg3.s", "testProg3.data", "testProg3.code");
        compareFiles("testProg3.data", "correct_testProg3.data");
        compareFiles("testProg3.code", "correct_testProg3.code");
       
        // Check the label-offset list
        System.out.println("**** Checking Label-Offset list");
        if(lo.size() != 3) {
            System.out.println("ERROR: incorrect size of label-offset list: " + lo.size());
        }

        l = lo.get(0);
        if(!(l.label.equals("main"))) {
            System.out.println("ERROR: incorrect label at index 0: " + l.label);
        }
        if(l.offset != 0) {
            System.out.println("ERROR: incorrect offset at index 0: " + l.offset);
        }

        l = lo.get(1);
        if(!(l.label.equals("if"))) {
            System.out.println("ERROR: incorrect label at index 1: " + l.label);
        }
        if(l.offset != 28) {
            System.out.println("ERROR: incorrect offset at index 1: " + l.offset);
        }

        l = lo.get(2);
        if(!(l.label.equals("afterif"))) {
            System.out.println("ERROR: incorrect label at index 2: " + l.label);
        }
        if(l.offset != 32) {
            System.out.println("ERROR: incorrect offset at index 2: " + l.offset);
        }        

        System.out.println("Finished Test 3\n");


        ////////// TEST 4 //////////
        System.out.println("Starting Test 4");
        lo = Assembler_P1.assemble("testAllProg.s", "testAllProg.data", "testAllProg.code");
        compareFiles("testAllProg.data", "correct_testAllProg.data");
        compareFiles("testAllProg.code", "correct_testAllProg.code");

        // Check the label-offset list
        System.out.println("**** Checking Label-Offset list");
        if(lo.size() != 5) {
            System.out.println("ERROR: incorrect size of label-offset list: " + lo.size());
        }

        l = lo.get(0);
        if(!(l.label.equals("main"))) {
            System.out.println("ERROR: incorrect label at index 0: " + l.label);
        }
        if(l.offset != 0) {
            System.out.println("ERROR: incorrect offset at index 0: " + l.offset);
        }

        l = lo.get(1);
        if(!(l.label.equals("labelA"))) {
            System.out.println("ERROR: incorrect label at index 1: " + l.label);
        }
        if(l.offset != 4) {
            System.out.println("ERROR: incorrect offset at index 1: " + l.offset);
        }

        l = lo.get(2);
        if(!(l.label.equals("labelB"))) {
            System.out.println("ERROR: incorrect label at index 2: " + l.label);
        }
        if(l.offset != 16) {
            System.out.println("ERROR: incorrect offset at index 2: " + l.offset);
        }    

        l = lo.get(3);
        if(!(l.label.equals("labelC"))) {
            System.out.println("ERROR: incorrect label at index 3: " + l.label);
        }
        if(l.offset != 44) {
            System.out.println("ERROR: incorrect offset at index 3: " + l.offset);
        }    

        l = lo.get(4);
        if(!(l.label.equals("labelD"))) {
            System.out.println("ERROR: incorrect label at index 4: " + l.label);
        }
        if(l.offset != 60) {
            System.out.println("ERROR: incorrect offset at index 4: " + l.offset);
        }    

        System.out.println("Finished Test 4\n");
    }

    /**
    * Performs a line-by-line comparison of the contents of two files. Prints
    * a messages if there is a difference on a line of the two files.
    *
    * @param file1 One of the files with contents to compare.
    * @param file2 The other file with contents to compare.
    */
    public static void compareFiles(String file1, String file2) throws FileNotFoundException {
        Scanner input1 = new Scanner(new File(file1));
        Scanner input2 = new Scanner(new File(file2));
        String line1, line2;
        int lineNum = 1;

        System.out.println("**** Comparing files: " + file1 + " and " + file2);

        // Read both files until reach the end of one
        while(input1.hasNextLine() && input2.hasNextLine()) {

            // get the next line from both files
            // remove any leading or trailing whitespace
            line1 = input1.nextLine().trim();
            line2 = input2.nextLine().trim();

            // Print an error message if lines have different contents
            if(!line1.equals(line2)) {
                System.out.println("ERROR files not same on line number " + lineNum);
                System.out.println(file1 + ": " + line1);
                System.out.println(file2 + ": " + line2);
            }
            lineNum++;
        }

        // Print an error message if one of the files has more lines
        if(input1.hasNextLine()) {
            System.out.println("ERROR compareFiles file not finished: " + file1);
        }
        if(input2.hasNextLine()) {
            System.out.println("ERROR compareFiles file not finished: " + file2);
        }
    }
}
