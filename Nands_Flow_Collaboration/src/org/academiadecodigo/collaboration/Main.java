package org.academiadecodigo.collaboration;

import org.academiadecodigo.collaboration.Logic.Game;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.GameOverScreen;
import org.academiadecodigo.collaboration.SimpleGraphics.Screens.StartMenuScreen;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
