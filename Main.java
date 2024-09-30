import java.util.*;

class details_node {
    String food_name;
    double food_price;
    int food_quantity;
    details_node next;
    details_node prev;

    details_node(String f, double p, int q) {
        food_name = f;
        food_price = p;
        food_quantity = q;
        next = null;
        prev = null;
    }
}

class Resto {
    details_node head;
    details_node headc;
    Scanner sc = new Scanner(System.in);

    void create_food_list(String f_name, double f_price, int f_q) {
        details_node nn = new details_node(f_name, f_price, f_q);
        if (head == null) {
            head = nn;
            return;
        } else {
            details_node ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = nn;
            nn.prev = ptr;
        }
    }

    void display_food_list() {
        details_node ptr = null;
        if (head == null) {
            System.out.println("Please create food menu to display");
        } else {
            ptr = head;
            System.out.println("Menu \t\t Price \t\t Quantity");
            System.out.println("---------------------------------------");
            while (ptr != null) {
                System.out.println(ptr.food_name + "\t\t " + ptr.food_price + "\t\t " + ptr.food_quantity);
                ptr = ptr.next;
            }
            System.out.println("x:------------------------");
        }
    }

    void delete_food_item(details_node head, String foodname_to_delete) {
        details_node ptr = null;

        System.out.println("Enter a food item name from the displayed menu to delete");
        foodname_to_delete = sc.nextLine();
        if (head == null) {
            System.out.println("----------------------------------------");
            System.out.println("Food list is empty, can't delete");
            System.out.println("-----------------------------------------");
        } else {
            if (head.food_name.equals(foodname_to_delete)) {
                head = head.next;
                if (head != null)
                    head.prev = null;
                System.out.println("Food item deleted successfully");
                return;
            } else {
                ptr = head;
                while (ptr != null && !ptr.food_name.equals(foodname_to_delete)) {
                    ptr = ptr.next;
                }

                if (ptr == null) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("Food item not found");
                    System.out.println("---------------------------------------------------");
                } else {
                    if (ptr.next != null)
                        ptr.next.prev = ptr.prev;
                    if (ptr.prev != null)
                        ptr.prev.next = ptr.next;
                    System.out.println("Food item deleted successfully");
                }
            }
        }
    }

    void update_item() {
        boolean itemfound = false;
        details_node ptr = null;
        int option;
        String food_name, update_name;
        double nprice;
        int nquantity;

        System.out.print("Enter a food item name from the displayed menu to update:");
        food_name = sc.nextLine();

        if (head == null) {
            System.out.println("--------------------------------------------------");
            System.out.println("Food list is empty, can't delete ");
            System.out.println("-----------------------------------------------------");
        } else {
            ptr = head;
            while (ptr != null) {
                if (ptr.food_name.equals(food_name)) {
                    itemfound = true;
                    System.out.println("-----------------------------------------");
                    System.out.println("Enter 1 to edit dish name");
                    System.out.println("Enter 2 to edit price");
                    System.out.println("Enter 3 to edit quantity");
                    System.out.println("Enter 4 to exit");
                    System.out.println("------------------------------------------");

                    System.out.print("Enter the option of operation you want to perform :");
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option == 4) {
                        break;
                    }
                    switch (option) {
                        case 1:
                            System.out.print("Enter a food name to update instead of " + food_name);
                            update_name = sc.nextLine();
                            ptr.food_name = update_name;
                            System.out.println("Food name " + food_name + " is updated to " + update_name + " successfully......");
                            break;
                        case 2:
                            System.out.print("Enter the new price to be updated:");
                            nprice = sc.nextDouble();
                            sc.nextLine();
                            ptr.food_price = nprice;
                            System.out.println("Price of " + food_name + " is updated to " + nprice + " successfully......");
                            break;
                        case 3:
                            System.out.println("Enter new quantity to be updated:-");
                            nquantity = sc.nextInt();
                            sc.nextLine();
                            ptr.food_quantity = nquantity;
                            System.out.println("Quantity of " + food_name + " is updated to " + nquantity + " successfully.......");
                            break;
                        default:
                            break;
                    }
                } else {
                    ptr = ptr.next;
                }
            }
            if (!itemfound) {
                System.out.println("-------------------------------------------");
                System.out.println("Food item not found");
                System.out.println("-------------------------------------------");
            }
        }
    }

    void create_order_food_list(String f_name, double f_price, int f_q) {
        details_node nn = new details_node(f_name, f_price, f_q);
        if (headc == null) {
            headc = nn;
            return;
        } else {
            details_node ptr = headc;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = nn;
            nn.prev = ptr;
        }
    }

    void placeorder() {
        int ans = 1;

        display_food_list();
        do {
            boolean itemfound = false;
            details_node ptr = null;

            String food_name;
            double quantity;

            System.out.println("Enter the food item you want to order");
            food_name = sc.nextLine();
            System.out.println("Enter the quantity you want to order");
            quantity = sc.nextDouble();
            sc.nextLine();

            double newprice;
            if (head == null) {
                System.out.println("--------------------------------------------");
                System.out.println("Food list is empty, can't delete");
                System.out.println("--------------------------------------------");
            } else {
                ptr = head;
                while (ptr != null) {
                    if (ptr.food_name.equals(food_name)) {
                        itemfound = true;
                        newprice = ptr.food_price * quantity;
                        int nquantity = (int) quantity;
                        create_order_food_list(ptr.food_name, newprice, nquantity);
                        System.out.println("Order added");
                        break;
                    } else {ptr = ptr.next;
                    }
                }
                if (!itemfound) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Food item not found");
                    System.out.println("------------------------------------------------");
                }
            }

            System.out.println("Enter 1 to place more orders or enter 2 to exit");
            ans = sc.nextInt();
            sc.nextLine();
        } while (ans == 1);
    }

    void vieworder() {
        details_node ptr = null;
        if (headc == null) {
            System.out.println("Order is not placed yet");
        } else {
            ptr = headc;

            System.out.println(" Menu \t\t Price \t\t Quantity ");
            System.out.println("----------------------------------------------------");
            while (ptr != null) {
                System.out.println(ptr.food_name + "\t\t " + ptr.food_price + "\t\t " + ptr.food_quantity);
                ptr = ptr.next;
            }
            System.out.println("-------------------------------------------------------");
        }
    }

    void delete_item_order() {
    }

    void finalbill() {
        System.out.println("-----------------------------------------------");
        System.out.println("Your order");
        System.out.println("------------------------------------------------");
        vieworder();
        double total = 0;
        details_node ptr = null;
        if (headc == null) {
            System.out.println("Order is not placed yet");
        } else {
            ptr = headc;
            while (ptr != null) {
                total += ptr.food_price;
                ptr = ptr.next;
            }

            System.out.println("------------------------------------------------");

        }
        System.out.print("Your total bill is :-" + total);
    }

    void admin() {
        int ch;
        
        System.out.println("\n-----------Welcome to Veg Cusine Admin Section ------------------");

        while (true) {
            System.out.println("1: Display food list");
            System.out.println("2: Update food item");
            System.out.println("3: Delete food item");
            System.out.println("4: Back to menu");
            System.out.print("Enter the option of operation you want to perform :");
            ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    display_food_list();
                    break;
                case 2:
                    update_item();
                    break;
                case 3:
                    delete_food_item(head, "");
                    break;
                case 4:
                    break;
                default:
                    System.out.print("Please enter a valid choice :");
            }
            if (ch == 4) {
                break;
            }
        }
    }

    void customer() {
        int ch;

        System.out.println("--------------Welcome to veg cuisine'S customer section-------------------");

        while (true) {
            System.out.println("1. Place your order");
            System.out.println("2. View your ordered list");
            System.out.println("3. Delete item from your order");
            System.out.println("4. Final Bill");
            System.out.println("5. Back to menu");
            System.out.println("Enter the option of operation you want to perform :");
            ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    placeorder();
                    break;
                case 2:
                    vieworder();
                    break;
                case 3:
                    delete_item_order();
                    break;
                case 4:
                    finalbill();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please enter a valid choice:");
            }
            if (ch == 5) {
                break;
            }
        }

    }
}

public class Main {
    public void displaymenu() {
        System.out.println("----------Welcome to Veg Cuisine-------------");
        System.out.println("1. Admin Section");
        System.out.println("2. Customer Section");
        System.out.println("3. Exit");
        System.out.print("Enter your choice:");
    }

    public static void main(String Args[]) {
        Scanner sc = new Scanner(System.in);

        Main m = new Main();
        Resto person = new Resto();

        person.create_food_list("vada", 15, 1);
        person.create_food_list("pohe", 20, 1);
        person.create_food_list("idli", 15, 1);
        person.create_food_list("dosa", 30, 1);
        person.create_food_list("puri", 10, 1);
        person.create_food_list("uggani",20,1);
        person.create_food_list("upma",20,1);
        person.create_food_list("parota",30,1);

        int ch;
        
        while (true) {
            m.displaymenu();
            ch = sc.nextInt();
            sc.nextLine();

            if (ch == 3) {
                System.out.println("======================ThankYou========================");
                break;
            }

            switch (ch) {
                case 1:
                    person.admin();
                    break;
                case 2:
                    person.customer();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please enter a valid choice ");
            }
        }
        sc.close();
    }
}