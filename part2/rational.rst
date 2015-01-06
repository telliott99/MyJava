.. _rational:

##############
RationalNumber
##############

Here is an example I cooked up for a homework problem.  I think the problem is a good use of the object-oriented paradigm:  a RationalNumber class.  The first part of the class is the constructor and some utility methods.  The middle part implements arithmetic operations.  And we depend heavily on keeping all fractions in lowest terms, using Euclid's algorithm. 

``RationalNumber.java``.

.. sourcecode:: java

    public class RationalNumber {
        // instance variables are n and d
        // for (n)umerator and (d)enominator
        int n;
        int d;

        // local variables in methods and constructor
        // are always num, den
        public RationalNumber(int num, int den){
            if (den == 0) {
                throw new IllegalArgumentException();
            }
            // reduces fraction to lowest terms
            reduceTerms(num, den);
        }
        public RationalNumber() {
            // with no args, 
            // construct a default RationalNumber
            this(1,1);
        }
        public int getNumerator() { return n; }
        public int getDenominator() { return d; }
        public String toString() {
            return String.format("%d/%d", n, d);
        }
        public boolean equals(RationalNumber r) {
            // assumes always lowest terms
            if (n != r.n) { return false; };
            if (d != r.d) { return false; }
            return true;
        }
        //=========================================
        public RationalNumber add(RationalNumber r) {
            // local variables in constructor and methods
            // are always num and den
            // a/c + b/d = (ad + bc)/cd
            int den = d * r.d;
            int lhs = n * r.d;
            int rhs = r.n * d;
            int num = lhs + rhs;
            return new RationalNumber(num,den);
        }
        public RationalNumber subtract(RationalNumber r) {
            // a/c - b/d = (ad + bc)/cd
            int den = d * r.d;
            int lhs = n * r.d;
            int rhs = r.n * d;
            int num = lhs - rhs;
            return new RationalNumber(num,den);
        }
        public RationalNumber multiply(RationalNumber r) {
            // a/c multiplied by b/d = ab/cd
            int num, den;
            num = n * r.n;
            den = d * r.d;
            return new RationalNumber(num,den);
        }
        public RationalNumber divide(RationalNumber r) {
            // a/c divided by b/d = ad/bc
            if (r.n == 0) {
                throw new IllegalArgumentException();
            }
            int num, den;
            num = n * r.d;
            den = d * r.n;
            return new RationalNumber(num,den);
        }
        //=========================================
        // greatest common divisor of a and b
        // en.wikipedia.org/wiki/Euclidean_algorithm
        private int gcd(int a, int b) {
            while (b != 0) {
                int t = b;
                b = a % b;
                a = t;
            }
            return a;
        }
        // modifies the instance variables
        private void reduceTerms(int num, int den) {
            int f = gcd(num,den);
            n = num/f;
            d = den/f;
        }
    }
    
``Test.java``

.. sourcecode:: java

    public class Test { 
        public static void main(String[] args) {
            RationalNumber p,q,r,s,t,u;
            p = new RationalNumber(6,10);
            q = new RationalNumber(2,3);
            r = new RationalNumber();
            System.out.println(p);
            System.out.println(q);
            System.out.println(r);
            r = p.multiply(q);
            System.out.println(r);
         }
    }

.. sourcecode:: bash

    > javac Test.java 
    > java Test
    3/5
    2/3
    1/1
    2/5
    >

