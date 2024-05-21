import controller.AdminManager;
import controller.BurgerManager;
import controller.CartManager;
import controller.ExitManager;
import controller.PayManager;
import controller.SideManager;

import view.MenuViewer;
import view.ADMIN_CHOICE;
import view.BURGER_CHOICE;
import view.CART_CHOICE;
import view.EXIT_CHOICE;
import view.MENU_CHOICE;
import view.PAY_CHOICE;
import view.SIDE_CHOICE;

import java.util.Scanner;

public class BurgerMain {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        int choiceNum;


        boolean exit = false;

        while (!exit) {
            try {
                MenuViewer.mainMenuView();
                choiceNum = sc.nextInt();
                sc.nextLine();


                switch (choiceNum) {
                    case MENU_CHOICE.BURGER:
                        burgerMenu();
                        break;
                    case MENU_CHOICE.SIDE:
                        sideMenu();
                        break;
                    case MENU_CHOICE.CART:
                        cartMenu();
                        break;
                    case MENU_CHOICE.PAY:
                        payMenu();
                        break;
                    case MENU_CHOICE.ADMIN:
                        adminMenu();
                        break;
                    case MENU_CHOICE.EXIT:
                        exit = exitMenu();
                        break;
                    default:
                        System.out.println("해당 메뉴 번호만 입력하세요.");
                }
            } catch (Exception e)   {
                System.out.println("메인메뉴 선택중 오류 발생");
            }
        }
    }

    public static void burgerMenu() {
        int choice;

        BurgerManager burgerManager = new BurgerManager();
        MenuViewer.burgerMenuView();
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case BURGER_CHOICE.INSERT:
                burgerManager.selectBurger();
                break;
            case BURGER_CHOICE.DELETE:
                burgerManager.removeBurger();
                break;
            case BURGER_CHOICE.MAIN:
                return;
            default:
                System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }

    public static void sideMenu() {
        int choice;

        SideManager sideManager = new SideManager();
        MenuViewer.sideMenuView();
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case SIDE_CHOICE.INSERT:
                sideManager.selectSide();
                break;
            case SIDE_CHOICE.DELETE:
                sideManager.removeSide();
                break;
            case SIDE_CHOICE.MAIN:
                return;
            default:
                System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }

    public static void cartMenu() {
        int choice;

        CartManager cartManager = new CartManager();
        MenuViewer.cartMenuView();
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case CART_CHOICE.VIEW:
                cartManager.showCart();
                break;
            case CART_CHOICE.DELETE:
                cartManager.clearCart();
                break;
            case CART_CHOICE.MAIN:
                return;
            default:
                System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }

    public static void payMenu() {
        int choice;

        PayManager payManager = new PayManager();
        MenuViewer.payMenuView();
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case PAY_CHOICE.PAY:
                payManager.pay();
            case PAY_CHOICE.MAIN:
                return;
            default:
                System.out.println("해당 메뉴 번호만 입력하세요.");
        }
    }

    public static void adminMenu() throws Exception {
        Admin admin = new Admin();
        while (true) {
            System.out.println("관리자 로그인");
            System.out.print("아이디를 입력해주세요(뒤로가기 -1): ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("-1")) {
                return;
            }
            System.out.print("비밀번호를 입력해주세요: ");
            String password = sc.nextLine();
            if (id.equalsIgnoreCase(admin.getId()) && password.equalsIgnoreCase(admin.getPassword())) {
                System.out.println("관리자 접속 완료");
                break;
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }

        while (true)    {
            int choice;

            AdminManager adminManager = new AdminManager();
            MenuViewer.adminMenuView();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case ADMIN_CHOICE.B_INSERT:
                    adminManager.addBurger();
                    break;
                case ADMIN_CHOICE.B_UPDATE:
                    adminManager.modifyBurger();
                    break;
                case ADMIN_CHOICE.B_DELETE:
                    adminManager.removeBurger();
                    break;
                case ADMIN_CHOICE.S_INSERT:
                    adminManager.addSide();
                    break;
                case ADMIN_CHOICE.S_UPDATE:
                    adminManager.modifySide();
                    break;
                case ADMIN_CHOICE.S_DELETE:
                    adminManager.removeSide();
                    break;
                case ADMIN_CHOICE.MAIN:
                    return;
                default:
                    System.out.println("해당 메뉴 번호만 입력하세요.");
            }
        }

    }

    public static boolean exitMenu() {
        int choice;

        ExitManager exitManager = new ExitManager();
        MenuViewer.exitMenuView();
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case EXIT_CHOICE.PROOUT:
                return exitManager.proOut();
            case EXIT_CHOICE.SYSOUT:
                return exitManager.sysOut();
            case EXIT_CHOICE.MAIN:
                return false;
            default:
                System.out.println("해당 메뉴 번호만 입력하세요.");

        }
        return false;
    }
}
