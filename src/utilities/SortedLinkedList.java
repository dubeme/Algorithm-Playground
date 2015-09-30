/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author dubem
 * @param <T>
 */
public class SortedLinkedList<T extends Comparable<T>> {

    public class Node<T> {

        T value;
        Node<T> next;
        Node<T> prev;

        private Node(T value) {
            this.value = value;

        }
    }

    private Node<T> root;

    public void add(T value) {

        Node<T> newNode = new Node<>(value);

        if (root == null) {
            root = newNode;
        } else {

            if (root.value.compareTo(value) > 0) {
                newNode.next = root;
                root.prev = newNode;
                root = newNode;
            } else {
                Node<T> temp = root;
                boolean isLessOrEqual;

                while (temp != null) {

                    isLessOrEqual = temp.value.compareTo(value) <= 0;

                    if (isLessOrEqual) {
                        if (temp.next != null) {
                            temp = temp.next;
                        } else {
                            newNode.prev = temp;
                            temp.next = newNode;
                            break;
                        }
                    } else {
                        // Step back once
                        temp = temp.prev;
                        newNode.prev = temp;
                        newNode.next = temp.next;
                        temp.next = newNode;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> temp = root;

        while (temp != null) {
            str.append(temp.value.toString()).append("\n");
            temp = temp.next;
        }

        return str.toString();
    }

}
