/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poj.org.problems;

import java.util.Scanner;
import java.util.Stack;
import utilities.IProblem;

/**
 *
 * @author dubem
 */
public class No1330 implements IProblem {

    final int MAX_NUMBER_OF_NODES = 100001;
    // The index is the child
    // The value at a given index is the ancestor
    int[] Ancestors = new int[MAX_NUMBER_OF_NODES];

    public void insert(int parent, int child) {

        if (parent == 0 || child == 0) {
            throw new IllegalArgumentException("Values has to be greater that 0");
        }
        this.Ancestors[child] = parent;
    }

    public Stack<Integer> getAncestors(int child) {

        if (child == 0) {
            throw new IllegalArgumentException("Value has to be greater that 0");
        }

        Stack<Integer> ancestors = new Stack<>();
        // Since a node is also it's own ancestor
        int currentAncestor = child;

        while (currentAncestor != 0) {
            ancestors.push(currentAncestor);
            currentAncestor = this.Ancestors[currentAncestor];
        }

        return ancestors;
    }

    public int getNearestCommonAncestor(int child1, int child2) {

        if (child1 == 0 || child2 == 0) {
            throw new IllegalArgumentException("Values has to be greater that 0");
        }

        int nearestCommonAncestor = -1;
        Stack<Integer> anc1 = this.getAncestors(child1);
        Stack<Integer> anc2 = this.getAncestors(child2);

        if (!anc1.empty() && !anc2.empty()) {

            // Get the top most ancestor
            int child1Anc = anc1.pop();
            int child2Anc = anc2.pop();

            while (child1Anc != 0 && child2Anc != 0) {
                if (child1Anc == child2Anc) {
                    nearestCommonAncestor = child1Anc;
                }
                try {
                    child1Anc = anc1.pop();
                    child2Anc = anc2.pop();
                } catch (Exception ex) {
                    return nearestCommonAncestor;
                }
            }
        }

        return nearestCommonAncestor;
    }

    @Override
    public void Execute() {
        No1330 client = new No1330();

        client.insert(1, 14);
        client.insert(8, 5);
        client.insert(10, 16);
        client.insert(5, 9);
        client.insert(4, 6);
        client.insert(8, 4);
        client.insert(4, 10);
        client.insert(1, 13);
        client.insert(6, 15);
        client.insert(10, 11);
        client.insert(6, 7);
        client.insert(10, 2);
        client.insert(16, 3);
        client.insert(8, 1);
        client.insert(16, 12);

        Scanner scanner = new Scanner(System.in);
        int child1;
        int child2;
        
        while (true) {
            System.out.print("Enter first child: ");
            child1 = scanner.nextInt();

            System.out.print("Enter second child: ");
            child2 = scanner.nextInt();

            System.out.println(client.getNearestCommonAncestor(child1, child2));
            System.out.println("");
        }
    }
}
