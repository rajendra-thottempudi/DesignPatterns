package practice;
import java.util.*;


class Card{
    char letter;
    int number;

    Card(char letter, int number){
        this.letter = letter;
        this.number = number;
    }
}

class Hand{
  List<Card> cards;

  Hand(List<Card> cards){
      this.cards = cards;
  }

  public List<Card> getCards(){
      return cards;
  }
}

interface Rule{
    public int getPriority();
    public boolean validate(Hand h);
}

class PairRule implements Rule{
    int priority;

    PairRule(int priority){
        this.priority = priority;
    }

    @Override
    public int getPriority(){
        return this.priority;
    }

    @Override
    public boolean validate(Hand h){
        Set<Integer> nums = new HashSet<>();
        for(Card c : h.getCards()){
            nums.add(c.number);
        }

        //atleast one pair
        return nums.size() <= 4;
    }
}

class FlushRule implements Rule{
    int priority;

    FlushRule(int priority){
        this.priority = priority;
    }

    @Override
    public int getPriority(){
        return this.priority;
    }

    @Override
    public boolean validate(Hand h){
        Set<Character> letters = new HashSet<>();
        for(Card c : h.getCards()){
            letters.add(c.letter);
        }

        //all cards of same letter/category
        return letters.size() == 1;
    }
}

class Poker{
    List<Hand> hands;

    List<Rule> rules;

    Poker(List<Hand> hands, List<Rule> rules){
        this.hands = hands;
        this.rules = rules;
    }

    public Hand getWinningHand(Rule luckyRule){
        //iterate over the hands and keep calculating best hand based on rules
        return hands.get(0);
    }
}


public class PokerGame{
    public static void main(String[] args){
        System.out.println(" I am playing poker ");
    }
}