package machine;

import java.util.Objects;
import java.util.Scanner;

enum State {
    CHOOSING_AN_ACTION, CHOOSING_A_TYPE_OF_COFFEE, FILL, OFF
}

enum Coffee {
    ESPRESSO, LATTE, CAPPUCCINO, NO_COFFEE
}

enum Ingredients {
    WATER, MILK, COFFEE_BEANS, DISPOSABLE_CUPS
}
class Machine {

    private int amountOfWater = 400;
    private int amountOfMilk = 540;
    private int amountOfCoffeeBeans = 120;
    private int amountOfDisposableCups = 9;
    private int amountOfMoney = 550;
    private State state = State.CHOOSING_AN_ACTION;
    private Ingredients ingredient = Ingredients.WATER;

    public State getState() {
        return state;
    }

    public void invitationToInteract() {
        if (state == State.CHOOSING_AN_ACTION) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        } else if (state == State.CHOOSING_A_TYPE_OF_COFFEE) {
            System.out.println("\nWhat do you want to buy? " +
                    "1 = espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        } else if (state == State.FILL) {
            if (ingredient == Ingredients.WATER) {
                System.out.println("Write how many ml of water you want to add:");
            } else if (ingredient == Ingredients.MILK) {
                System.out.println("Write how many ml of milk you want to add:");
            } else  if (ingredient == Ingredients.COFFEE_BEANS) {
                System.out.println("Write how many grams of coffee beans you want to add:");
            } else {
                System.out.println("Write how many disposable cups you want to add:");
            }
        }
    }

    public void work(String choice) {
        if (state == State.CHOOSING_AN_ACTION) {
            choosingAnAction(choice);
        } else if (state == State.CHOOSING_A_TYPE_OF_COFFEE) {
            choosingATypeOfCoffee(choice);
        } else if (state == State.FILL) {
            fillCoffeeMachine(choice, ingredient);
        }
    }

    private void fillCoffeeMachine(String choice, Ingredients ingredient) {
        switch (ingredient.name()) {
            case "WATER" -> {
                amountOfWater += Integer.parseInt(choice);
                this.ingredient = Ingredients.MILK;
            }
            case "MILK" -> {
                amountOfMilk += Integer.parseInt(choice);
                this.ingredient = Ingredients.COFFEE_BEANS;
            }
            case "COFFEE_BEANS" -> {
                amountOfCoffeeBeans += Integer.parseInt(choice);
                this.ingredient = Ingredients.DISPOSABLE_CUPS;
            }
            case "DISPOSABLE_CUPS" -> {
                amountOfDisposableCups += Integer.parseInt(choice);
                ingredient = Ingredients.WATER;
                state = State.CHOOSING_AN_ACTION;
            }
        }
    }

    private void choosingAnAction(String choiceOfAction) {
        switch (choiceOfAction) {
            case "buy" -> state = State.CHOOSING_A_TYPE_OF_COFFEE;
            case "fill" -> state = State.FILL;
            case "take" -> receiveMoney();
            case "remaining" -> printInfo();
            case "exit" -> state = State.OFF;
        }
    }

    private void printInfo() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", amountOfWater);
        System.out.printf("%d ml of milk\n", amountOfMilk);
        System.out.printf("%d g of coffee beans\n", amountOfCoffeeBeans);
        System.out.printf("%d disposable cups\n", amountOfDisposableCups);
        System.out.printf("$%d of money\n", amountOfMoney);
    }

    private void receiveMoney() {
        System.out.printf("\nI gave you $%d\n", amountOfMoney);
        amountOfMoney = 0;
    }

    private void choosingATypeOfCoffee(String choiceOfCoffee) {
        Coffee coffee = Coffee.NO_COFFEE;
        switch (choiceOfCoffee) {
            case "1" -> coffee = Coffee.ESPRESSO;
            case "2" -> coffee = Coffee.LATTE;
            case "3" -> coffee = Coffee.CAPPUCCINO;
            default -> {}
        }
        if (!coffee.equals(Coffee.NO_COFFEE)) {
            makeCoffee(coffee);
        }
        state = State.CHOOSING_AN_ACTION;
    }

    private void makeCoffee(Coffee coffee) {
        switch (coffee.name()) {
            case "ESPRESSO" -> checkAndChange(250, 0, 16, 4);
            case "LATTE" -> checkAndChange(350, 75, 20, 7);
            case "CAPPUCCINO" -> checkAndChange(200, 100, 12, 6);
        }
    }

    private void checkAndChange(int amountOfWater, int amountOfMilk, int amountOfCoffeeBeans, int amountOfMoney) {
        if (this.amountOfWater >= amountOfWater && this.amountOfMilk >= amountOfMilk &&
            this.amountOfCoffeeBeans >= amountOfCoffeeBeans && this.amountOfDisposableCups > 0) {
            this.amountOfWater -= amountOfWater;
            this.amountOfMilk -= amountOfMilk;
            this.amountOfCoffeeBeans -= amountOfCoffeeBeans;
            this.amountOfMoney += amountOfMoney;
            this.amountOfDisposableCups -= 1;
            System.out.println("I have enough resources, making you a coffee!");
        } else if (this.amountOfWater < amountOfWater) {
            System.out.println("Sorry, not enough water!");
        }
    }
}

public class CoffeeMachine {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Machine coffeeMachine = new Machine();
        String choice;
        
        do {
            coffeeMachine.invitationToInteract();
            choice = scanner.next();
            coffeeMachine.work(choice);
        } while (!coffeeMachine.getState().equals(State.OFF));
    }
}
