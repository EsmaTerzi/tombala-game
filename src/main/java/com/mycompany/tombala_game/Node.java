/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tombala_game;

/**
 *
 * @author nesma
 */
public class Node<T> {
    
    T data;
    Node<T> next;
    Node<T> down;
    boolean hasStar; // Yıldız bayrağı

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.down = null;
        this.hasStar = false; // Başlangıçta yıldız bayrağı false olarak ayarlanır
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getDown() {
        return down;
    }

    public boolean hasStar() {
        return hasStar;
    }

    
}
