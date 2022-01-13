pa
import java.util.Scanner;

public class CoffeeMachine {
    private Scanner scanner;

    /*
    When PRIVATE it is only accessible within the class (here just to protection - probably not necessary)
     */
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    /*
    This is CONSTRUCTOR which allows to modify the value of the actual variables from CoffeeMachine class
     */
    private CoffeeMachine() {
        this.scanner = new Scanner(System.in);
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
    }

    /*
    Main method, creates an object (machine) and takes to the method where is the condition
    IF user_input THEN adequate_function
     */
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        boolean status;
        do {
            status = machine.processAction();
        } while (status);
    }

    /*
    Method which prints the state (content) of the machine
     */
    public void printState() {
        System.out.println("The machine has:\n" +
                String.format("%d of water\n", this.water) +
                String.format("%d of milk\n", this.milk) +
                String.format("%d of beans\n", this.beans) +
                String.format("%d of cups\n", this.cups) +
                String.format("%d of money", this.money));
    }

    /*
    This method asks user to choose an option and then if it is "back" then the function stops ("return")
     */
    private void processBuy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String type = scanner.nextLine();

        if (type.equals("back")) return;

        this.buy(Integer.valueOf(type));
    }

    /*
    This two methods prints text, gets input and invokes method adequate to action
     */
    private void processFill() {
        System.out.println("Write how many ml of water do you want to add:");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee do you want to add:");
        int beans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = scanner.nextInt();

        this.fill(water, milk, beans, cups);
    }

    private void processTake() {
        System.out.println(String.format("I gave you $%d", this.money));
        this.take();
    }

    /*
    This method fills the machine
     */
    private void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    /*
    This method takes the money from the machine
     */
    private void take() {
        this.money = 0;
    }

    /*
    In this method there are parameters of making coffee and method checking the resources
     */
    private void buy(int type){
        int water = 0;
        int milk = 0;
        int beans = 0;
        int money = 0;

        switch (type) {
            case 1: {
                water = 250;
                milk = 0;
                beans = 16;
                money = 4;
                break;
            }
            case 2: {
                water = 350;
                milk = 75;
                beans = 20;
                money = 7;
                break;
            }
            case 3: {
                water = 200;
                milk = 100;
                beans = 12;
                money = 6;
                break;
            }
            default:
                break;
        }

        if (this.water < water) {
            System.out.println("Sorry, not enough water!");
        } else if (this.milk < milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.beans < beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (this.cups < cups) {
            System.out.println("Sorry, not enough disposable cups");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            this.cups--;
            this.money += money;
        }
    }

    /*
    This method is working on CoffeeMachine object where the resources are defined.
    That is why when we changing the value of any resource it does not work on copy but original variable
     */
    public boolean processAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.nextLine();

        switch (action) {
            case "buy": {
                this.processBuy();
                break;
            }
            case "fill": {
                this.processFill();
                break;
            }
            case "take": {
                this.processTake();
                break;
            }
            case "remaining": {
                this.printState();
                break;
            }
            case "exit": {
                return false;
            }
            default: {
                break;
            }
        }
        return true;
    }
}