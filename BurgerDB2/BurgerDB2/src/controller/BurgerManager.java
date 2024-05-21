package controller;

import model.BurgerVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BurgerManager {
    public static Scanner sc = new Scanner(System.in);
    public void selectBurger(){
        ArrayList<BurgerVO> burgers = BurgerDAO.readBurgers();
        if (burgers.isEmpty()) {
            System.out.println("버거 목록에 등록된 버거가 없습니다.");
            return;
        }
        for (BurgerVO b : burgers) {
            System.out.println(b);
        }

        int burgerCode;
        while (true) {
            System.out.print("버거를 선택해주세요(ex:1,2,3), 뒤로가기(-1): ");
            while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("존재하는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            }
            int code = sc.nextInt();
            sc.nextLine();

            if (code == -1) {
                return;
            }

            if (burgers.stream().map(BurgerVO::getCode).toList().contains(code)) {
                burgerCode = code;
                break;
            }
        }




        BurgerVO burger = burgers.stream().filter(b -> b.getCode() == burgerCode).max(Comparator.comparing(BurgerVO::getCode)).get();
        System.out.println("1.버거 사이즈업(+2000)\t2.버거 세트\t3.버거 사이즈업 + 세트\t4.선택안함");
        System.out.print("사이즈업/세트를 선택해주세요: ");


        while (!sc.hasNext("[1-4]")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("사이즈업/세트를 선택해주세요: ");
        }
        int answer = sc.nextInt();
        sc.nextLine();


        if (answer == 1) {
            while (true) {
                System.out.print(burger.getName() + " 사이즈업을 하시겠습니까?(Y/N): ");
                String sizeUp = sc.nextLine();
                if (!(sizeUp.equalsIgnoreCase("Y") || sizeUp.equalsIgnoreCase("N"))) {
                    continue;
                }
                if (sizeUp.equalsIgnoreCase("Y")) {
                    burger.sizeUp();
                    break;
                } else if (sizeUp.equalsIgnoreCase("N")) {
                    burger.dan();
                    break;
                }
            }
        } else if (answer == 2) {
            while (true) {
                System.out.print(burger.getName() + " 세트를 선택하시겠습니까?(Y/N): ");
                String set = sc.nextLine();
                if (!(set.equalsIgnoreCase("Y") || set.equalsIgnoreCase("N"))) {
                    continue;
                }
                if (set.equalsIgnoreCase("Y")) {
                    burger.set();
                    break;
                } else if (set.equalsIgnoreCase("N")) {
                    burger.dan();
                    break;
                }
            }
        } else if (answer == 3) {
            while (true) {
                System.out.print(burger.getName() + " 사이즈업과 세트를 선택하시겠습니까?(Y/N): ");
                String sizeUpNSet = sc.nextLine();
                if (!(sizeUpNSet.equalsIgnoreCase("Y") || sizeUpNSet.equalsIgnoreCase("N"))) {
                    continue;
                }
                if (sizeUpNSet.equalsIgnoreCase("Y")) {
                    burger.sizeUpNSet();
                    break;
                } else if (sizeUpNSet.equalsIgnoreCase("N")) {
                    burger.dan();
                    break;
                }
            }
        } else if (answer == 4) {
            burger.dan();
        }

        BurgerDAO bd = new BurgerDAO();
        bd.insertMyBurgers(burger);

    }
    public void removeBurger()  {
        ArrayList<MyBurgerVO> myBurgers = BurgerDAO.readMyBurgers();
        if (myBurgers.isEmpty()) {
            System.out.println("선택하신 버거가 없습니다.");
            return;
        }
        for (MyBurgerVO mb : myBurgers) {
            System.out.println(mb);
        }

        int myBurgerNum;
        while (true) {
            System.out.print("삭제하실 버거의 코드를 입력해주세요(뒤로가기 -1): ");
            while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("존재하는 주문번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            }
            int code = sc.nextInt();
            sc.nextLine();

            if (code == -1) {
                return;
            }

            if (myBurgers.stream().map(MyBurgerVO::getOrderNum).toList().contains(code)) {
                myBurgerNum = code;
                break;
            }
        }



        BurgerDAO bd = new BurgerDAO();
        bd.deleteMyBurgers(myBurgerNum);
    }


}
