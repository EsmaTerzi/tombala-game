/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tombala_game;

/**
 *
 * @author nesma
 */
public class MultiLinkedList<T> {

    private Node<T> head;
    private int size;
    private int _row;
    private int _column;
    private int _currentRow;
    private int _currentCol;

    public MultiLinkedList(int row, int column) {
        this.head = null;
        this.size = 0;
        this._row = row;
        this._column = column;
        this._currentCol = 0;
        this._currentRow = 0;
    }

    public void add(T data) {

        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            this._currentCol++;
            this._currentRow++;

        } else {
            setNodeNext(newNode);
            setNodeDown(newNode);

        }
        size++;
    }

    private void setNodeNext(Node<T> newNode) {

        if (this._currentCol == this._column) {  // Eğer mevcut sütun, belirlenen sütun sınırını aşmışsa
            this._currentCol++;
            return;
        }
        Node<T> current = head;
        for (int j = 1; j < this._currentRow; j++) {// Mevcut satıra git
            current = current.down;
        }
        for (int i = 1; i < this._currentCol; i++) {// Mevcut sütuna git
            current = current.getNext();
        }
        current.next = newNode; // Yeni düğümü mevcut düğümün sağına ekle
        this._currentCol++;

    }

    private void setNodeDown(Node<T> newNode) {

        Node<T> current = head;
        if (this._currentCol > this._column) {// Eğer mevcut sütun sütun sınırını aşmışsa
            for (int i = 1; i < this._currentRow; i++) {//2.satırın sonu için
                current = current.down;
            }
            current.down = newNode;
            this._currentRow++;
            this._currentCol = 1;
        } else {
            if (this._currentRow == 1) {// Eğer mevcut satır birinci satırsa, metottan çık
                return;
            }

            for (int i = 1; i < this._currentRow - 1; i++) {//currentRow - 1 kadar aşağı inilir (yani bir satır eksik gidilir, çünkü mevcut satıra ekleme yapılacak).
                current = current.down;
            }
            for (int i = 1; i < this._currentCol; i++) {
                current = current.next;
            }
            current.down = newNode;

        }

    }

    // Çoklu bağlantılı listeyi konsola yazdıran metot
    public void printList() {
        Node<T> currentRow = head;
        while (currentRow != null) {
            Node<T> currentCol = currentRow;
            while (currentCol != null) {
                if (currentCol.hasStar == true) {
                    System.out.print(currentCol.getData() + "*" + "\t");
                } else {
                    System.out.print(currentCol.getData() + "\t");
                }
                currentCol = currentCol.getNext();
            }
            System.out.println();

            currentRow = currentRow.getDown();
        }
    }

    public int size() {
        return size;
    }

    public Node<T> getHead() {
        return head;
    }

    public void addWithFlag(T data, boolean flag) {
        Node<T> currentRow = head;
        while (currentRow != null) {
            Node<T> currentCol = currentRow;
            while (currentCol != null) {
                if (currentCol.data == data) {

                    currentCol.hasStar = true;
                }
                currentCol = currentCol.getNext();
            }

            currentRow = currentRow.getDown();
        }

    }

    public boolean contains(T data) {
        Node<T> currentRow = head;
        while (currentRow != null) {
            Node<T> currentCol = currentRow;
            while (currentCol != null) {
                if (currentCol.data == data) {
                    return true;
                }
                currentCol = currentCol.getNext();
            }

            currentRow = currentRow.getDown();
        }
        return false;
    }

    public T get(int index) {
        int _index = 0;
        Node<T> currentRow = head;
        while (currentRow != null) {
            Node<T> currentCol = currentRow;
            while (currentCol != null) {
                if (index == _index) {
                    return currentCol.data;
                }
                _index++;
                currentCol = currentCol.getNext();
            }
            currentRow = currentRow.getDown();
        }
        return null;
    }

    public int getCinkoCount() {
        int cinkoCount = 0;
        Node<T> currentRow = head;
        while (currentRow != null) {
            int starCount = 0;
            Node<T> currentCol = currentRow;
            while (currentCol != null) {
                if (currentCol.hasStar) {
                    starCount++;

                }
                if (starCount == 5) {
                    cinkoCount++;
                    break;

                }
                currentCol = currentCol.getNext();

            }

            currentRow = currentRow.getDown();
        }
        return cinkoCount;
    }

}
