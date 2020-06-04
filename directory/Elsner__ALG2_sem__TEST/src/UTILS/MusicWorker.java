/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

/**
 *
 * @author CryHeroCZ
 */
public class MusicWorker {

    private Music song;
    private Scanner sc = new Scanner(System.in);

    /**
     * Metoda načítající soubor hudby.
     *
     * @param musicFilepath umístění souboru hudby
     */
    public void initSong(String musicFilepath) throws MusicNotFoundException {

        File musicFile = new File(Writer.dataDirectory, musicFilepath);
        if (musicFile.exists()) {
            song = TinySound.loadMusic(musicFile);
        } else {
            throw new MusicNotFoundException("Soubor hudby nenalezen: ");
        }

    }

    /**
     * Metoda spouštějící hudbu.
     *
     * @param time čas, po který bude písnička hrát
     */
    public void playSong(int time) {

        song.play(true);
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < time; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            if (sc.hasNext()) {
                song.stop();
                break;
            }
        }

    }

    /**
     * Metoda přehrávající hudbu v prvotním menu.
     *
     * @return choice vrací volbu, použitou pro další chod programu
     */
    public String playMenuSong() {
        String choice = "5";

        song.play(true);

        while (true) {

            if (sc.hasNextLine()) {
                song.stop();
                choice = sc.nextLine();
                return choice;

            } else {

            }

        }
    }
}
