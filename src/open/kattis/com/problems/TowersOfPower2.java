/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package open.kattis.com.problems;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.TreeSet;
import utilities.IProblem;
import utilities.SortedLinkedList;

/**
 *
 * @author dubem
 */
public class TowersOfPower2 implements IProblem {

    private static BigDecimal[] log2s = new BigDecimal[101];

    static {
        for (int index = 1; index <= 100; index++) {
            log2s[index] = new BigDecimal(Math.log(index) / Math.log(2));
        }
    }

    public class Number implements Comparable<Number>{

        private String expr;
        private BigDecimal powerBase2Sum;

        public Number(String expr, int num, int[] powers) {
            /*
                
             a^b^c
                
             (2^x)^(2^y)^(2^z)
             (2^x)^( (2^y)*(2^z) )
             (2^x)^(2^(y+z))
             2^(x * 2^(y+z))
             2^(2^m * 2^(y+z))
             2^(2^(m+y+z))
                
             */

            this.expr = expr;

            if (num == 1) {
                this.powerBase2Sum = BigDecimal.ZERO;
            } else {
                this.powerBase2Sum = this.getPowersBase2Sum(powers);
                
                double x = log2s[num].doubleValue();
                double logBase2Ofx = Math.log(x) / Math.log(2);
                this.powerBase2Sum = this.powerBase2Sum.add(new BigDecimal(logBase2Ofx));
            }

        }

        private BigDecimal getPowersBase2Sum(int[] powers) {
            BigDecimal ans = BigDecimal.ZERO;

            if (powers != null) {
                // All the numbers in the powers array has to be converted to 
                // Log2, then add them up from last to first
                for (int index = 0; index < powers.length; index++) {
                    int currentNum = powers[index];
                    BigDecimal currentNumLog2 = log2s[currentNum];
                    ans = ans.add(currentNumLog2);
                }
            }

            return ans;
        }
        
        public BigDecimal getRounded() {
            MathContext mc = new MathContext(16);
            return this.powerBase2Sum.round(mc);
        }

        @Override
        public String toString() {
            // return this.expr + "  2 ^ 2^ " + this.powerBase2Sum;
            // return "2^ 2^ " + this.getRounded();
            return this.expr;
        }

        @Override
        public int compareTo(Number num) {
            return this.getRounded().compareTo(num.getRounded());
        }
    }

    @Override
    public void Execute() {
        
        SortedLinkedList<Number> nums = new SortedLinkedList<>();
        nums.add(new Number("2^2^2", 2, new int[]{2, 2}));
        nums.add(new Number("3^4", 3, new int[]{4}));
        nums.add(new Number("15", 15, new int[]{}));
        nums.add(new Number("9^2", 9, new int[]{2}));

        System.out.println(nums);
        //nums.stream().forEach((num) -> {
        //    System.out.println(num);
        //}); // System.out.println(nums[1].compareTo(nums[3]));
    }

}
