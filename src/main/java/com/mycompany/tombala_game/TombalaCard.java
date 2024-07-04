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
public class TombalaCard<T> {
    
    private MultiLinkedList<Integer> numbers;
    private int _cinkoCount;

    public TombalaCard() {
        numbers = new MultiLinkedList<>(3, 9);
        generateCard();
        _cinkoCount = 0;
    }

    public TombalaCard(int[][] generatedNumbers) {
        numbers = new MultiLinkedList<>(3, 9);
       
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                numbers.add(generatedNumbers[i][j]);
            }
        }  
        _cinkoCount = 0;
    }

    private void generateCard() {
        MultiLinkedList<Integer> tempNumbers = new MultiLinkedList<>(1, 15);
        int[] permutation = generatePermutation(90);

        Random random = new Random();
        while (tempNumbers.size() < 15) {
            int number = permutation[random.nextInt(permutation.length)];//0 ile permutation.length - 1 arasında rastgele bir tam sayı döndürür
            if (!tempNumbers.contains(number)) {
                tempNumbers.add(number);
            }
        }

        // Kartı 3 satır ve 9 sütun olacak şekilde düzenleme
        Node<Integer> currentNode = tempNumbers.getHead();
        for (int i = 0; i < 3; i++) {
            int[] randomIndices = generatePermutation(9);
            for (int j = 0; j < 9; j++) {

                if ((randomIndices[j] - 1) % 2 == 0) {
                    
                    int minNumber;
                    int maxNumber;
                    if(j==0){
                         minNumber = j * 10 + 1; // Sütunun minimum değeri- ilk sütunda 0 gelmemesi için
                    }
                    else{
                      minNumber = j * 10 ; // Sütunun minimum değeri
                    }
                    if(j==8){
                        maxNumber=(j + 1) * 10; // Sütunun maksimum değeri - son satırda 90 gelmesi için
                    }
                    else{
                      maxNumber = (j + 1) * 10-1; // Sütunun maksimum değeri
                    }
                    int number;
                    do {
                        number = random.nextInt(maxNumber - minNumber + 1) + minNumber;// minNumber ile maxNumber arasında rastgele bir sayı üret
                       
                    } while (tempNumbers.contains(number)); // Tekrarlanan sayıları MultiLinkedList kullanarak kontrol etme
                      // Üretilen sayının tempNumbers listesinde olup olmadığını kontrol et, 
                      // eğer varsa, yeni bir rastgele sayı üretmek için döngü devam eder
                      
                    if (currentNode != null) {
                        currentNode.data = number;
                        numbers.add(currentNode.data);
                        currentNode = currentNode.next;
                    }
                } else { 
                    numbers.add(-1);
                      
                }
            }

        }

    }

    public void printCard() {
        numbers.printList();
    }

    // 1'den n'a kadar rastgele permütasyon oluşturma
    private int[] generatePermutation(int n) {
        int[] permutation = new int[n];// Permütasyon dizisini oluştur ve 1'den n'e kadar doldur
        for (int i = 0; i < n; i++) {
            permutation[i] = i + 1;
        }
        Random rand = new Random();
        for (int i = 0; i < n; i++) {    // Diziyi karıştırma işlemi
            // Rastgele iki indeks seç
            int index1 = rand.nextInt(n);
            int index2 = rand.nextInt(n);
            
            // Seçilen iki indeksin elemanlarını yer değiştir
            int temp = permutation[index1];
            permutation[index1] = permutation[index2];
            permutation[index2] = temp;
        }
        return permutation;  // Seçilen iki indeksin elemanlarını yer değiştir
    }

    // Kartta belirli bir sayının bulunup bulunmadığını kontrol etme
    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public void addWithFlag(int number, boolean flag) {
        numbers.addWithFlag(number, flag);

    }

    public int checkCinko() {
        return numbers.getCinkoCount();

    }

    public int GetCinkoCount() {
        return _cinkoCount;
    }

    public void IncreaseCinkoCount() {
        _cinkoCount++;
    }
    
    private boolean isStarred(T data) {
        return data != null && data.toString().contains("*");
    }
}
