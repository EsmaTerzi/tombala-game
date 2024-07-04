/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.tombala_game;

/**
 *
 * @author nesma
 */
public class Main {

    public static void main(String[] args) {
        //Bir kart matrix olarak verilebilir diğer kart random olarak oluşturulur
        int[][] card1 = {{5, -1, 22, -1, 45, -1, 60, 73, -1},
        {-1, 10, -1, 31, 47, 58, 68, -1, -1},
        {-1, 17, 26, 38, -1, -1, -1, 79, 86}};

        // Tombala oyunu oluşturma
        TombalaGame game = new TombalaGame(2, card1); // İki karttan oluşan bir Tombala oyunu

        // 90 sayı çekme
        for (int i = 0; i < 90; i++) {
            game.drawNumber(); // Sayı çekme ve kartlarda kontrol
            if (game.checkBingo() == 1 || game.checkBingo() == 2) {//Tek bingo veya aynı anda iki bingo için
                System.out.println("Oyun bitti!");
                break;
            }
        }
    }
}
