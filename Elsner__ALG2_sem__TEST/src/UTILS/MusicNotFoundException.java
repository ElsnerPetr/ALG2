package UTILS;

/**
 * Třída chyby která se zobrazí, pokud program nenalzne soubor s hudbou.
 *
 * @author Petr Elsner
 */
public class MusicNotFoundException extends Exception {

    //konstruktor
    public MusicNotFoundException(String hudba) {

        super(hudba);

    }

}
