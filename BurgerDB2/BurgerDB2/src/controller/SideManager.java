package controller;

import model.SideVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SideManager {
    public static Scanner sc = new Scanner(System.in);
    public void selectSide()    {
        ArrayList<SideVO> sides = SideDAO.readSides();
        if (sides.isEmpty()) {
            System.out.println("등록된 사이드가 없습니다.");
            return;
        }
        for (SideVO s : sides) {
            System.out.println(s);
        }


        int sideCode;
        while (true) {
            System.out.print("사이드를 선택해주세요(ex:1,2,3), 뒤로가기(-1): ");
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

            if (sides.stream().map(SideVO::getCode).toList().contains(code)) {
                sideCode = code;
                break;
            }
        }



        SideVO side = sides.stream().filter(s -> s.getCode() == sideCode).max(Comparator.comparing(SideVO::getCode)).get();

        SideDAO sd = new SideDAO();
        sd.insertMySides(side);

    }
    public void removeSide()    {
        ArrayList<MySideVO> mySides = SideDAO.readMySides();

        if (mySides.isEmpty()) {
            System.out.println("추가하신 사이드가 없습니다.");
            return;
        }
        for (MySideVO ms : mySides) {
            System.out.println(ms);
        }



        int mySideNum;
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

            if (mySides.stream().map(MySideVO::getOrderNum).toList().contains(code)) {
                mySideNum = code;
                break;
            }
        }





        SideDAO sd = new SideDAO();
        sd.deleteMySides(mySideNum);
    }

}
