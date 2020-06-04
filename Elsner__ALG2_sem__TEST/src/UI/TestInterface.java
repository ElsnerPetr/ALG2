package UI;

import UTILS.TestWorker;

/**
 * Interface
 *
 * @author Petr Elsner
 */
public interface TestInterface {

    /**
     * Metoda, spouštějící specifikované UI.
     *
     * @param testWorker umožňuje použítí všech přítomných metod
     */
    public void UI(TestWorker testWorker);
}
