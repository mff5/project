package controller;


import model.BurgerVO;
import model.SideVO;

import java.util.ArrayList;
import java.util.Scanner;


public class AdminManager {
    public static Scanner sc = new Scanner(System.in);
    public void addBurger() {
        ArrayList<BurgerVO> burgers = BurgerDAO.readBurgers();
        if (burgers.isEmpty())  {
            System.out.println("버거 목록에 등록된 버거가 없습니다.");
        }

        for (BurgerVO b : burgers) {
            System.out.println(b);
        }
        int burgerCode;
        while (true) {
            System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            }
            int code = sc.nextInt();
            sc.nextLine();

            if (code == -1) {
                return;
            }

            if (burgers.stream().map(BurgerVO::getBurgerCode).toList().contains(code)) {
                continue;
            }
            burgerCode = code;
            break;
        }
        System.out.print("버거명을 입력해주세요: ");
        String burgerName = sc.nextLine();

        System.out.print("가격을 입력해주세요: ");
        while (!sc.hasNext("[1-9]\\d*")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("가격을 입력해주세요: ");
        }
        int burgerPrice = sc.nextInt();
        sc.nextLine();

        System.out.print("버거설명을 입력해주세요: ");
        String burgerDescription = sc.nextLine();

        System.out.print("버거 카테고리를 입력해주세요: ");
        String burgerCategory = sc.nextLine();

        System.out.print("버거 재고를 입력해주세요: ");
        while (!sc.hasNext("[1-9]\\d*")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("가격을 입력해주세요: ");
        }
        int burgerStock = sc.nextInt();
        sc.nextLine();

        System.out.print("버거 칼로리를 입력해주세요: ");
        while (!sc.hasNext("[1-9]\\d*")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("가격을 입력해주세요: ");
        }
        int burgerCalories = sc.nextInt();
        sc.nextLine();

        System.out.print("버거 알레르기 정보를 입력해주세요: ");
        String burgerAllergy = sc.nextLine();

        AdminDAO ad = new AdminDAO();
        ad.insertBurger(burgerCode, burgerName, burgerPrice, burgerDescription, burgerCategory, burgerStock,
                burgerCalories, burgerAllergy);
    }





    public void modifyBurger()   {
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

            System.out.print("수정하실 버거의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("수정하실 버거의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
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

        System.out.print("수정하실 제품명을 입력해주세요: ");
        String burgerName = sc.nextLine();

        System.out.print("수정하실 가격을 입력해주세요: ");
        while (!sc.hasNext("[1-9]\\d*")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("가격을 입력해주세요: ");
        }
        int burgerPrice = sc.nextInt();
        sc.nextLine();

        System.out.print("수정하실 상품설명을 입력해주세요: ");
        String burgerInfo = sc.nextLine();

        AdminDAO ad = new AdminDAO();
        ad.updateBurger(burgerCode, burgerName, burgerPrice, burgerInfo);
    }






    public void removeBurger()  {
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
            System.out.print("삭제하실 버거의 코드를 입력해주세요(뒤로가기 -1): ");
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

            if (burgers.stream().anyMatch(b->b.getBurgerCode() == code)) {
                burgerCode = code;
                break;
            }
        }

        AdminDAO ad = new AdminDAO();
        ad.deleteBurger(burgerCode);
    }











    public void addSide()   {
        ArrayList<SideVO> sides = SideDAO.readSides();
        if (sides.isEmpty())    {
            System.out.println("사이드 목록에 등록된 사이드가 없습니다.");
        }
        for (SideVO s : sides) {
            System.out.println(s);
        }
        int sideCode;
        while (true) {
            System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            }
            int code = sc.nextInt();
            sc.nextLine();

            if (code == -1) {
                return;
            }
            if (sides.stream().map(SideVO::getCode).toList().contains(code)) {
                continue;
            }
            sideCode = code;
            break;
        }
        System.out.print("제품명을 입력해주세요: ");
        String sideName = sc.nextLine();

        System.out.print("가격을 입력해주세요: ");
        while (!sc.hasNext("[1-9]\\d*")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("가격을 입력해주세요: ");
        }
        int sidePrice = sc.nextInt();
        sc.nextLine();

        AdminDAO ad = new AdminDAO();
        ad.insertSide(sideCode, sideName, sidePrice);
    }

    public void modifySide()    {
        ArrayList<SideVO> sides = SideDAO.readSides();
        if (sides.isEmpty()) {
            System.out.println("사이드 목록에 등록된 사이드가 없습니다.");
            return;
        }
        for (SideVO s : sides) {
            System.out.println(s);
        }
        int sideCode;
        while (true) {
            System.out.print("수정하실 사이드의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("수정하실 사이드의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
            }
            int code = sc.nextInt();
            sc.nextLine();

            if (code == -1) {
                return;
            }

            if (sides.stream().map(SideVO::getCode).toList().contains(code)) {
                sideCode = code;
                break;
            }
        }


        System.out.print("수정하실 제품명을 입력해주세요: ");
        String sideName = sc.nextLine();

        System.out.print("수정하실 가격을 입력해주세요: ");
        while (!sc.hasNext("[1-9]\\d*")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("가격을 입력해주세요: ");
        }
        int sidePrice = sc.nextInt();
        sc.nextLine();

        AdminDAO ad = new AdminDAO();
        ad.updateSide(sideCode, sideName, sidePrice);
    }
    public void removeSide()    {
        ArrayList<SideVO> sides = SideDAO.readSides();
        if (sides.isEmpty()) {
            System.out.println("사이드 목록에 등록된 사이드가 없습니다.");
            return;
        }
        for (SideVO s : sides) {
            System.out.println(s);
        }
        int sideCode;
        while (true) {
            System.out.print("삭제하실 사이드의 코드를 입력해주세요(뒤로가기(-1): ");
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

            if (sides.stream().anyMatch(s->s.getSideCode()==code)) {
                sideCode = code;
                break;
            }
        }

        AdminDAO ad = new AdminDAO();
        ad.deleteSide(sideCode);
    }
}
