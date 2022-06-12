package machine;
import java.util.Scanner;

public class CoffeeMachine {

    int water_left;
    int milk_left;
    int coffee_beans_left;
    int cups_left;
    int money;
    private String input;
    private machine.CoffeeMachine.Status status = machine.CoffeeMachine.Status.MAIN_MENU;

    CoffeeMachine(int water_left, int milk_left, int coffee_beans_left, int cups_left, int money) {
        this.water_left = water_left;
        this.milk_left = milk_left;
        this.coffee_beans_left = coffee_beans_left;
        this.cups_left = cups_left;
        this.money = money;
    }

    enum Status {
        MAIN_MENU,
        BUY, FILL,
        WATER_INPUT,
        MILK_INPUT,
        BEANS_INPUT,
        CUPS_INPUT,
        EXIT
    }

    machine.CoffeeMachine.Status getStatus() {
        return this.status;
    }

    private void fill() {
        switch (this.status) {
            case FILL:
                System.out.println("\nWrite how many ml of water do you want to add:");
                this.status = machine.CoffeeMachine.Status.WATER_INPUT;
                break;
            case WATER_INPUT:
                this.water_left += Integer.parseInt(this.input);
                System.out.println("Write how many ml of milk do you want to add:");
                this.status = machine.CoffeeMachine.Status.MILK_INPUT;
                break;
            case MILK_INPUT:
                this.milk_left += Integer.parseInt(this.input);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                this.status = machine.CoffeeMachine.Status.BEANS_INPUT;
                break;
            case BEANS_INPUT:
                this.coffee_beans_left += Integer.parseInt(this.input);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                this.status = machine.CoffeeMachine.Status.CUPS_INPUT;
                break;
            case CUPS_INPUT:
                this.cups_left += Integer.parseInt(this.input);
                System.out.println();
                MainMenu();
                break;
            default:
                System.out.println("Unknown fill state");
                MainMenu();
                break;
        }
    }

    void ft_Input(String input) {
        this.input = input;

        switch (this.status) {
            case MAIN_MENU:
                processCommand();
                break;
            case WATER_INPUT:
            case MILK_INPUT:
            case BEANS_INPUT:
            case CUPS_INPUT:
                fill();
                break;
            case BUY:
                buy();
                break;
            default:
                System.out.println("Unknown input state");
                this.status = machine.CoffeeMachine.Status.MAIN_MENU;
                break;
        }
    }

    private void mac_take() {
        System.out.printf("I gave you $%d\n\n", money);
        this.money = 0;
        MainMenu();
    }

    private void processCommand() {
        switch (input) {
            case "buy":
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                        "back - to main menu: ");
                this.status = machine.CoffeeMachine.Status.BUY;
                break;
            case "fill":
                this.status = machine.CoffeeMachine.Status.FILL;
                fill();
                break;
            case "take":
                mac_take();
                break;
            case "remaining":
                mach_print();
                break;
            case "exit":
                this.status = machine.CoffeeMachine.Status.EXIT;
                break;
            default:
                System.out.println("Unknown command");
                MainMenu();
                break;
        }
    }

    private void mach_print() {
        System.out.printf("\nThe coffee machine has:\n");
        System.out.printf("%d ml of water\n", this.water_left);
        System.out.printf("%d ml of milk\n", this.milk_left);
        System.out.printf("%d g of coffee beans\n", this.coffee_beans_left);
        System.out.printf("%d disposable cups\n", this.cups_left);
        System.out.printf("$%d of money\n\n", this.money);

        MainMenu();
    }

    public static boolean ft_check_stuff(int thing_left, int thing_needed, String message) {
        if (thing_left / thing_needed == 0) {
            System.out.printf(message);
            return (true);
        }
        return (false);
    }

    private boolean min_am_coffee(String type_of_coffee) {
        switch (type_of_coffee) {
            case ("1"):
                if (ft_check_stuff(this.water_left, 250, "Sorry, not enough water!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.coffee_beans_left, 16, "Sorry, not enough coffee beans!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.cups_left, 1, "Sorry, not enough coffee cups!\n\n")) {
                    return (false);
                }
                break;
            case ("2"):
                if (ft_check_stuff(this.water_left, 350, "Sorry, not enough water!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.milk_left, 75, "Sorry, not enough milk!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.coffee_beans_left, 20, "Sorry, not enough coffee beans!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.cups_left, 1, "Sorry, not enough coffee cups!\n\n")) {
                    return (false);
                }
                break;
            case ("3"):
                if (ft_check_stuff(this.water_left, 200, "Sorry, not enough water!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.milk_left, 100, "Sorry, not enough milk!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.coffee_beans_left, 12, "Sorry, not enough coffee beans!\n\n")) {
                    return (false);
                }
                if (ft_check_stuff(this.cups_left, 1, "Sorry, not enough coffee cups!\n\n")) {
                    return (false);
                }
                break;
            default:
                return (false);
        }
        System.out.printf("I have enough resources, making you a coffee!\n\n");
        return (true);
    }

    private void buy() {
        switch (this.status) {
            case BUY:
                boolean enough = min_am_coffee(this.input);

                switch (this.input) {
                    case "1": // espresso
                        if (enough) {
                            this.water_left -= 250;
                            this.coffee_beans_left -= 16;
                            this.cups_left--;
                            this.money += 4;
                        }
                        break;
                    case "2": // latte
                        if (enough) {
                            this.water_left -= 350;
                            this.milk_left -= 75;
                            this.coffee_beans_left -= 20;
                            this.cups_left--;
                            this.money += 7;
                        }
                        break;
                    case "3": // cappuccino
                        if (enough) {
                            this.water_left -= 200;
                            this.milk_left -= 100;
                            this.coffee_beans_left -= 12;
                            this.cups_left--;
                            this.money += 6;
                        }
                        break;
                    case "back":
                        this.status = machine.CoffeeMachine.Status.MAIN_MENU;
                        break;
                    default:
                        System.out.println("Unknown buy command");
                        break;
                }
                MainMenu();
                break;
            default:
                System.out.println("Unknown buy state");
                MainMenu();
                break;
        }
    }

    private void MainMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        this.status = machine.CoffeeMachine.Status.MAIN_MENU;
    }

    private void startupSequence() {
        MainMenu();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        machine.CoffeeMachine coffeeMachine = new machine.CoffeeMachine(400, 540, 120, 9, 550);

        coffeeMachine.startupSequence();
        while (coffeeMachine.getStatus() != machine.CoffeeMachine.Status.EXIT) {
            coffeeMachine.ft_Input(scanner.next());
        }
    }

}