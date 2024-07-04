/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tombala_game;

import java.util.Random;

/**
 *
 * @author nesma
 */
public class TombalaGame {
    
    private MultiLinkedList<TombalaCard> cards;
    private MultiLinkedList<Integer> drawnNumbers; // Çekilen sayıların takip edileceği multilinklist

    public TombalaGame(int numOfCards, int[][] card) {
        cards = new MultiLinkedList<>(1, numOfCards);
        int needCard = numOfCards;
        if (card != null) {
            cards.add(new TombalaCard<>(card));
            needCard--;
        }

        drawnNumbers = new MultiLinkedList<>(1, 90);

        // Belirtilen sayıda TombalaCard oluşturup kartlara ekleme
        for (int i = 0; i < needCard; i++) {
            cards.add(new TombalaCard<>());
        }

    }

    // 1'den n'a kadar rastgele permütasyon oluşturma
    private int[] generatePermutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int index1 = rand.nextInt(n);
            int index2 = rand.nextInt(n);

            int temp = permutation[index1];
            permutation[index1] = permutation[index2];
            permutation[index2] = temp;
        }
        return permutation;
    }

    // Bir sonraki rastgele sayıyı al
    public void drawNumber() {
       
        checkCinko();

        Random random = new Random();
        int drawnNumber;
        int[] permutation = generatePermutation(90); // 1'den 90'a kadar olan sayıların permütasyonunu oluştur

        do {
            drawnNumber = permutation[random.nextInt(90)]; // Çekilen sayıyı belirle
        } while (drawnNumbers.contains(drawnNumber)); // Eğer daha önce çekilen sayı varsa, tekrar çek

        drawnNumbers.add(drawnNumber); // Çekilen sayıyı listeye ekle
        System.out.println("Cekilen Sayi: " + drawnNumber); // Çekilen sayıyı yazdır

       int sameNumCount=0;
        for (int i = 0; i < cards.size(); i++) {
            
            TombalaCard card = cards.get(i);
            
            if (card != null && card.contains(drawnNumber)) {
                
                card.addWithFlag(drawnNumber,true); // Silinen sayının orijinal indeksine yıldızlı şekilde ekle
                
                sameNumCount++;
               
            }
        }if(sameNumCount==2){
                   System.out.println("Iki kart uzerinde var."); 
                }
                if(sameNumCount==1){
                    System.out.println("Bir kart uzerinde var.");
                }
        printCards();
        System.out.println();

    }

    // Kartları bastır
    public void printCards() {
        //System.out.println("Kartlar:");
        Node<TombalaCard> currentCardNode = cards.getHead();

        while (currentCardNode != null) {
            for(int i=1;i<3;i++){
           
            System.out.println("Kart: " + i);
            currentCardNode.data.printCard();
            System.out.println("----------------------");
            currentCardNode = currentCardNode.next;
        }
        }
    }

    public void checkCinko() {
        for (int i = 0; i < cards.size(); i++) {
            TombalaCard card = cards.get(i);           
            if (card != null && card.checkCinko() > card.GetCinkoCount()) {
                System.out.println("Cinko! Kart " + (i + 1) + " icin " + card.checkCinko() + ". cinko tamamlandi");
                card.IncreaseCinkoCount();
                
            }
        }
        System.out.println();
    }

    public int checkBingo() {
        int count=0;
        for (int i = 0; i < cards.size(); i++) {
            
            TombalaCard card = cards.get(i);
            {
                if (card != null && card.checkCinko() == 3) {
                    System.out.println("Bingo! Kart " + (i + 1) + " icin bingo tamamlandi");
                    count++;
                   
                }  
            }
        }
        return count;
    }
}
