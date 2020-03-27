package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private final Scanner scan = new Scanner(System.in);
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.disposableCups = 9;
        this.money = 550;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        do {
            coffeeMachine.action();
        } while (true);
    }

    void action() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        switch (scan.nextLine()) {
            case "buy": {
                buy();
                break;
            }
            case "fill": {
                fill();
                break;
            }
            case "take": {
                take();
                break;
            }
            case "remaining": {
                getStatusInfo();
                break;
            }
            case "exit": {
                System.exit(0);
            }
            default: {
                System.out.println("Unknown action!");
            }
        }
    }

    void getStatusInfo() {
        System.out.print("The coffee machine has:\n" +
                this.getWater() + " of water\n" +
                this.getMilk() + " of milk\n" +
                this.getCoffeeBeans() + " of coffee beans\n" +
                this.getDisposableCups() + " of disposable cups\n" +
                this.getMoney() + " of money");
    }

    void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (scan.nextLine()) {
            case "1": {
                Coffee espresso = new Espresso();
                makeCoffee(espresso);
                return;
            }
            case "2": {
                Coffee latte = new Latte();
                makeCoffee(latte);
                return;
            }
            case "3": {
                Coffee cappuccino = new Cappuccino();
                makeCoffee(cappuccino);
                return;
            }
            case "back": {
                return;
            }
            default: {
                System.out.println("Unknown operation");
            }
        }
    }

    void makeCoffee(Coffee coffee) {
        if (water >= coffee.getWaterPerCup()) {
            setWater(water - coffee.getWaterPerCup());
        } else {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (milk >= coffee.getMilkPerCup()) {
            setMilk(milk - coffee.getMilkPerCup());
        } else {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (coffeeBeans >= coffee.getCoffeeBeansPerCup()) {
            setCoffeeBeans(coffeeBeans - coffee.getCoffeeBeansPerCup());
        } else {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if (disposableCups > 0) {
            disposableCups--;
        } else {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        setMoney(money + coffee.getPrice());
    }

    void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        water += scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        disposableCups += scan.nextInt();
    }

    void take() {
        money = 0;
        System.out.printf("I gave you $%d", money);
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(int coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setDisposableCups(int disposableCups) {
        this.disposableCups = disposableCups;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

abstract class Coffee {
    private int waterPerCup;
    private int coffeeBeansPerCup;
    private int milkPerCup;
    private int price;

    public Coffee() {
    }

    public Coffee(int waterPerCup, int coffeeBeansPerCup, int milkPerCup, int price) {
        this.waterPerCup = waterPerCup;
        this.coffeeBeansPerCup = coffeeBeansPerCup;
        this.milkPerCup = milkPerCup;
        this.price = price;
    }

    public int getWaterPerCup() {
        return waterPerCup;
    }

    public int getCoffeeBeansPerCup() {
        return coffeeBeansPerCup;
    }

    public int getMilkPerCup() {
        return milkPerCup;
    }

    public int getPrice() {
        return price;
    }
}

class Espresso extends Coffee {
    public Espresso() {
        super(250, 16, 0, 4);
    }
}

class Latte extends Coffee {

    public Latte() {
        super(350, 20, 75, 7);
    }
}

class Cappuccino extends Coffee {

    public Cappuccino() {
        super(200, 12, 100, 6);
    }
}
