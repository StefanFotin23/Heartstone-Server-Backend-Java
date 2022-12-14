package Service;

import Cards.*;
import Gameplay.*;
import fileio.*;
import java.util.ArrayList;
import static Constants.Constants.*;

public class InputParse {
    public static ArrayList<ArrayList<Card>> parseDecksInput (DecksInput playerDecks) {
        ArrayList<ArrayList<Card>> decks = new ArrayList<>();
        for (int i = 0; i < playerDecks.getNrDecks(); i++) {
            //we build a list of cards (a deck)
            ArrayList<Card> cards = new ArrayList<>();
            for (int j = 0; j < playerDecks.getNrCardsInDeck(); j++) {
                Card card = null;
                //if the card is normal minion
                if (playerDecks.getDecks().get(i).get(j).getName().equals(SENTINEL) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(GOLIATH) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(BERSERKER) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(WARDEN))
                {
                    card = new Minion(playerDecks.getDecks().get(i).get(j).getName(),
                            playerDecks.getDecks().get(i).get(j).getDescription(),
                            playerDecks.getDecks().get(i).get(j).getHealth(),
                            playerDecks.getDecks().get(i).get(j).getAttackDamage(),
                            playerDecks.getDecks().get(i).get(j).getMana(),
                            playerDecks.getDecks().get(i).get(j).getColors());
                }

                //if the card is special ability minion
                if (playerDecks.getDecks().get(i).get(j).getName().equals(DISCIPLE) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(THE_CURSED_ONE) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(MIRAJ) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(THE_RIPPER))
                {
                    card = new SpecialAbilityMinion(playerDecks.getDecks().get(i).get(j).getName(),
                            playerDecks.getDecks().get(i).get(j).getDescription(),
                            playerDecks.getDecks().get(i).get(j).getHealth(),
                            playerDecks.getDecks().get(i).get(j).getAttackDamage(),
                            playerDecks.getDecks().get(i).get(j).getMana(),
                            playerDecks.getDecks().get(i).get(j).getColors());
                }

                //if the card is environment
                if (playerDecks.getDecks().get(i).get(j).getName().equals(FIRESTORM) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(WINTERFELL) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(HEART_HOUND))
                {
                    card = new Environment(playerDecks.getDecks().get(i).get(j).getName(),
                            playerDecks.getDecks().get(i).get(j).getDescription(),
                            playerDecks.getDecks().get(i).get(j).getMana(),
                            playerDecks.getDecks().get(i).get(j).getColors());
                }

                //if the card is hero
                if (playerDecks.getDecks().get(i).get(j).getName().equals(LORD_ROYCE) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(EMPRESS_THORINA) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(KING_MUDFACE) ||
                        playerDecks.getDecks().get(i).get(j).getName().equals(GENERAL_KOCIORAW))
                {
                    card = new Hero(playerDecks.getDecks().get(i).get(j).getName(),
                            playerDecks.getDecks().get(i).get(j).getDescription(),
                            playerDecks.getDecks().get(i).get(j).getMana(),
                            playerDecks.getDecks().get(i).get(j).getColors());
                }

                //add the card to deck
                //debug message if card is not initialised
                if (card == null) {
                    System.out.println("Card " + playerDecks.getDecks().get(i).get(j).getName() + " is null!!!");
                }
                cards.add(card);
            }
            //add the card deck to decks (the deck collection)
            decks.add(cards);
        }
        //create the deck collection for the player
        return decks;
    }

    public static ArrayList<Game> parseGamesInput(Input input) {
        ArrayList<Game> games = new ArrayList<>();
        //for every game
        for (int i = 0; i < input.getGames().size(); i++) {
            //get game actions
            ArrayList<Actions> actions = new ArrayList<>();
            //for every action
            for (int j = 0; j < input.getGames().get(i).getActions().size(); j++) {
                //add the commands to the list
                Actions action = new Actions(input.getGames().get(i).getActions().get(j));
                actions.add(action);
            }
            //get the other start game attributes
            StartGameInput aux = input.getGames().get(i).getStartGame();
            Game game = new Game(aux.getPlayerOneDeckIdx(), aux.getPlayerTwoDeckIdx(), aux.getShuffleSeed(),
                    new Hero(aux.getPlayerOneHero()), new Hero(aux.getPlayerTwoHero()), aux.getStartingPlayer(),
                    actions);
            games.add(game);
        }
        return games;
    }
}
