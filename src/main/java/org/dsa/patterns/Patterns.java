package org.dsa.patterns;


import java.util.Collections;
import java.util.PriorityQueue;

public class Patterns {

    public static void main(String[] args) {

//        nForest(4);
//        rightTriangle(4);
//        rightTriangleNums(5);
//        numsTriangle(5);
//        reverseNforest(5);
//        triangle(5);
//        reverseTriangle(5);
//        diamond(5);
//        sideTriangle(5);
//        binaryRightTriangle(5);
        vShapeNums(7);
    }

    //    * * * *
    //    * * * *
    //    * * * *
    //    * * * *
    public static void nForest(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //    *
    //    * *
    //    * * *
    //    * * * *
    public static void rightTriangle(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //    1
    //    1 2
    //    1 2 3
    //    1 2 3 4
    public static void rightTriangleNums(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j+1 + " ");
            }
            System.out.println();
        }
    }

    //    1
    //    2 2
    //    3 3 3
    //    4 4 4 4
    public static void numsTriangle(int n) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    //    * * * *
    //    * * *
    //    * *
    //    *
    public static void reverseNforest(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //          *
    //        * * *
    //      * * * * *
    //    * * * * * * *
    public static void triangle(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //    * * * * * * *
    //      * * * * *
    //        * * *
    //          *
    public static void reverseTriangle(int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (2 * n) - (2 * i + 1); j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //          *
    //        * * *
    //      * * * * *
    //    * * * * * * *
    //    * * * * * * *
    //      * * * * *
    //        * * *
    //          *
    public static void diamond(int n) {

        triangle(n);
        reverseTriangle(n);
    }

    //    *
    //    * *
    //    * * *
    //    * * * *
    //    * * *
    //    * *
    //    *
    public static void sideTriangle(int n) {
        for (int i = 1; i <= 2*n-1; i++) {
            int stars = i;
            if(i > n) stars = 2*n - i;
            for (int j = 1; j <= stars; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //    1
    //    0 1
    //    1 0 1
    //    0 1 0 1 0
    //    1 0 1 0 1 0
    public static void binaryRightTriangle(int n) {
        for (int i = 1; i <= n; i++) {
            int start;
            start = i%2 == 1 ? 1 : 0;
            for (int j = 1; j <= i; j++) {
                System.out.print(start + " ");
                start = 1 - start;
            }
            System.out.println();
        }
    }

    //    1             1
    //    1 2         2 1
    //    1 2 3     3 2 1
    //    1 2 3 4 4 3 2 1
    public static void vShapeNums(int n) {
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }

            for (int j = 1; j <= 2 * (n - i); j++) {
                System.out.print(" ");
            }

            for (int j = i; j > 0; j--) {
                System.out.print(j);
            }
            System.out.println();
        }

    }


}
