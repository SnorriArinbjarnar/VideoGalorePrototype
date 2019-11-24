package main.is.ru.honn.UI;

import java.io.IOException;

/**
 * @author Snorri Arinbjarnar
 * @version 1.0
 *
 * VideoMax Galaxy - because renting your old videotapes
 * is more environmental friendly than to throw them
 */

public class VideoMax {

    public static void main(String[] args) throws IOException {

        Console console = new Console();
        console.VideoGalaxyWelcome();
        console.loginInput();
        console.userInput();

    }
}
