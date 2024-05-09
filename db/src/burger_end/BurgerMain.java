package burger;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BurgerMain {
    public static int myBurgerNum = 1;
    public static int mySideNum = 1;
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Burger> burgers = new ArrayList<>();
    public static ArrayList<MyBurger> myBurgers = new ArrayList<>();
    public static ArrayList<Side> sides = new ArrayList<>();
    public static ArrayList<MySide> mySides = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        while (!exit) {
            int num = selectMenu();
            switch (num) {
                case 1 -> selectBurger();
                case 2 -> removeBurger();
                case 3 -> selectSide();
                case 4 -> removeSide();
                case 5 -> showAll();
                case 6 -> clearAll();
                case 7 -> receipt();
                case 8 -> admin();
                case 9 -> {
                    System.out.println("종료합니다.");
                    exit = exit();
                }
                case 10 -> {
                    System.out.println("시스템을 종료합니다.");
                    exit = systemOut();
                }
                default -> System.out.println("다시 입력해주세요.");
            }
        }
    }

    public static int selectMenu() {

        System.out.println("1.버거 선택\t\t2.버거 삭제");
        System.out.println("3.사이드 추가\t\t4.사이드 삭제");
        System.out.println("5.내 상품\t\t6.전체 비우기");
        System.out.println("7.결제\t\t\t8.관리자 로그인");
        System.out.println("9.종료\t\t\t10.시스템 종료");
        System.out.print("메뉴를 선택해주세요 >>");
        while (!sc.hasNextInt() || sc.hasNext("0")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("메뉴를 선택해주세요 >>");
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }

    public static void selectBurger() {
        readBurgers();
        if (burgers.isEmpty()) {
            System.out.println("등록된 버거가 없습니다.");
            return;
        }
        for (Burger b : burgers) {
            System.out.println(b);
        }
        System.out.print("버거를 선택해주세요(뒤로가기 -1): ");

        while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(burgers.size() + 1)) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("버거를 선택해주세요(뒤로가기 -1): ");
        }
        int code = sc.nextInt();
        sc.nextLine();
        if (code == -1) {
            return;
        }
        Burger burger = burgers.stream().filter(b -> b.getCode() == code).max(Comparator.comparing(Burger::getCode)).get();
        MyBurger myBurger = new MyBurger(myBurgerNum, burger.getCode(), burger.getName(), burger.getPrice(), burger.getInfo());
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
                System.out.print(myBurger.getName() + " 사이즈업을 하시겠습니까?(Y/N): ");
                String sizeUp = sc.nextLine();
                if (!(sizeUp.equalsIgnoreCase("Y") || sizeUp.equalsIgnoreCase("N"))) {
                    continue;
                }
                if (sizeUp.equalsIgnoreCase("Y")) {
                    myBurger.sizeUp();
                    break;
                } else if (sizeUp.equalsIgnoreCase("N")) {
                    myBurger.dan();
                    break;
                }
            }
        } else if (answer == 2) {
            while (true) {
                System.out.print(myBurger.getName() + " 세트를 선택하시겠습니까?(Y/N): ");
                String set = sc.nextLine();
                if (!(set.equalsIgnoreCase("Y") || set.equalsIgnoreCase("N"))) {
                    continue;
                }
                if (set.equalsIgnoreCase("Y")) {
                    myBurger.set();
                    break;
                } else if (set.equalsIgnoreCase("N")) {
                    myBurger.dan();
                    break;
                }
            }
        } else if (answer == 3) {
            while (true) {
                System.out.print(myBurger.getName() + " 사이즈업과 세트를 선택하시겠습니까?(Y/N): ");
                String sizeUpNSet = sc.nextLine();
                if (!(sizeUpNSet.equalsIgnoreCase("Y") || sizeUpNSet.equalsIgnoreCase("N"))) {
                    continue;
                }
                if (sizeUpNSet.equalsIgnoreCase("Y")) {
                    myBurger.sizeUpNSet();
                    break;
                } else if (sizeUpNSet.equalsIgnoreCase("N")) {
                    myBurger.dan();
                    break;
                }
            }
        } else if (answer == 4) {
            myBurger.dan();
        }
        myBurgers.add(myBurger);

        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("insert into myBurgers values (?,?,?,?,?)")) {
            pstmt.setInt(1, myBurger.getOrderNum());
            pstmt.setInt(2, myBurger.getCode());
            pstmt.setString(3, myBurger.getName());
            pstmt.setInt(4, myBurger.getPrice());
            pstmt.setString(5, myBurger.getInfo());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("장바구니 버거 추가중 오류 발생");
        }
        myBurgerNum++;
    }

    public static void removeBurger() {
        if (myBurgers.isEmpty()) {
            System.out.println("선택하신 버거가 없습니다.");
            return;
        }
        for (MyBurger mb : myBurgers) {
            System.out.println(mb);
        }
        System.out.print("삭제하실 버거를 선택해주세요(뒤로가기 -1): ");
        while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(myBurgers.size() + 1)) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("삭제하실 버거를 선택해주세요(뒤로가기 -1): ");
        }
        int remove = sc.nextInt();
        sc.nextLine();
        if (remove == -1) {
            return;
        }
        if (myBurgers.removeIf(mb -> mb.getOrderNum() == remove)) {
            System.out.println("삭제가 완료되었습니다.");
        }
        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("delete from myBurgers where orderNum=" + remove)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("장바구니 버거 삭제중 오류 발생");
        }
    }

    public static void selectSide() {
        readSides();
        if (sides.isEmpty()) {
            System.out.println("등록된 사이드가 없습니다.");
            return;
        }
        for (Side s : sides) {
            System.out.println(s);
        }
        System.out.print("추가하실 사이드의 번호를 입력해주세요(뒤로가기 -1): ");
        while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(sides.size() + 1)) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("추가하실 사이드의 번호를 입력해주세요(뒤로가기 -1): ");
        }
        int code = sc.nextInt();
        sc.nextLine();
        if (code == -1) {
            return;
        }
        Side side = sides.stream().filter(s -> s.getCode() == code).max(Comparator.comparing(Side::getCode)).get();
        MySide mySide = new MySide(mySideNum, side.getCode(), side.getName(), side.getPrice());
        mySides.add(mySide);

        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("insert into mySides values (?,?,?,?)")) {
            pstmt.setInt(1, mySide.getOrderNum());
            pstmt.setInt(2, mySide.getCode());
            pstmt.setString(3, mySide.getName());
            pstmt.setInt(4, mySide.getPrice());

            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("장바구니 사이드 추가중 오류 발생");
        }
        mySideNum++;
    }

    public static void removeSide() {
        if (mySides.isEmpty()) {
            System.out.println("추가하신 사이드가 없습니다.");
            return;
        }
        for (MySide ms : mySides) {
            System.out.println(ms);
        }
        System.out.print("삭제하실 사이드를 선택해주세요(뒤로가기 -1): ");
        while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(mySides.size() + 1)) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("삭제하실 사이드를 선택해주세요(뒤로가기 -1): ");
        }
        int remove = sc.nextInt();
        sc.nextLine();
        if (remove == -1) {
            return;
        }
        if (mySides.removeIf(ms -> ms.getOrderNum() == remove)) {
            System.out.println("삭제가 완료되었습니다.");
        }

        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("delete from mySides where orderNum=" + remove)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("장바구니 사이드 삭제중 오류 발생");
        }
    }

    public static void showAll() {
        int total = 0;
        if (myBurgers.isEmpty()) {
            System.out.println("선택하신 버거가 없습니다.");
        } else {
            System.out.println("버거");
            for (MyBurger mb : myBurgers) {
                System.out.println(mb);
                total += mb.getPrice();
            }
        }
        if (mySides.isEmpty()) {
            System.out.println("추가하신 사이드가 없습니다.");
        } else {
            System.out.println("사이드");
            for (MySide ms : mySides) {
                System.out.println(ms);
                total += ms.getPrice();
            }
        }
        System.out.println(total + "원");
    }

    public static void clearAll() {
        myBurgers.clear();
        mySides.clear();
        System.out.println("삭제가 완료되었습니다.");
        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM myBurgers");
             PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM mySides")) {
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
        } catch (Exception e) {
            System.out.println("장바구니 비우기중 오류 발생");
        }
        myBurgerNum = 1;
        mySideNum = 1;
    }

    public static void receipt() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear() + "년 " + now.getMonthValue() + "월 " + now.getDayOfMonth() + "일 " + now.getHour() + "시 "
                + now.getMinute() + "분 " + now.getSecond() + "초");
        showAll();

        if (!myBurgers.isEmpty() || !mySides.isEmpty()) {
            int total = 0;
            try (Socket client = new Socket("localhost", 8000);
                 PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                 Connection con = SQLBurgerConnect.makeConnection();
                 PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO receipt VALUES (lpad(receiptNum.nextval,7,'0'),?,?)");
                 PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM myBurgers");
                 PreparedStatement pstmt3 = con.prepareStatement("DELETE FROM mySides")) {

                pw.println(now);

                if (!myBurgers.isEmpty()) {
                    pw.println("버거");
                    for (MyBurger mb : myBurgers) {
                        pw.println(mb.toString2());
                        total += mb.getPrice();
                    }
                }

                if (!mySides.isEmpty()) {
                    pw.println("사이드");
                    for (MySide ms : mySides) {
                        pw.println(ms.toString2());
                        total += ms.getPrice();
                    }
                }

                pw.println(total + "원");

                pstmt1.setString(1, now.toString());
                pstmt1.setInt(2, total);
                pstmt1.executeUpdate();
                pw.flush();

                myBurgers.clear();
                mySides.clear();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
                myBurgerNum = 1;
                mySideNum = 1;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("결제중 오류 발생");
            }
        }
    }

    public static void admin() throws Exception {
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
                edit();
                return;
            } else {
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    public static void edit() throws Exception {
        while (true) {
            System.out.print("1.버거 추가\t2.사이드 추가\t3.버거 삭제\t4.사이드 삭제\t5.정보 수정\t(뒤로가기 -1): ");
            while (!sc.hasNext("(?:[1-5]|-1)")) {
                sc.next();
                System.out.println("숫자를 입력해주세요");
                System.out.print("1.버거 추가\t2.사이드 추가\t3.버거 삭제\t4.사이드 삭제\t5.정보 수정\t(뒤로가기 -1): ");
            }
            int num = sc.nextInt();
            sc.nextLine();

            if (num == -1) {
                return;
            }

            if (num == 1) {
                readBurgers();
                for (Burger b : burgers) {
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
                        edit();
                        return;
                    }

                    if (burgers.stream().map(Burger::getCode).toList().contains(code)) {
                        continue;
                    }
                    burgerCode = code;
                    break;
                }
                System.out.print("제품명을 입력해주세요: ");
                String burgerName = sc.nextLine();

                System.out.print("가격을 입력해주세요: ");
                while (!sc.hasNext("[1-9]\\d*")) {
                    sc.next();
                    System.out.println("숫자를 입력해주세요");
                    System.out.print("가격을 입력해주세요: ");
                }
                int burgerPrice = sc.nextInt();
                sc.nextLine();

                System.out.print("상품설명을 입력해주세요: ");
                String burgerInfo = sc.nextLine();

                try (Connection con = SQLBurgerConnect.makeConnection();
                     PreparedStatement pstmt = con.prepareStatement("INSERT INTO burgers VALUES (?, ?, ?, ?)")) {
                    pstmt.setInt(1, burgerCode);
                    pstmt.setString(2, burgerName);
                    pstmt.setInt(3, burgerPrice);
                    pstmt.setString(4, burgerInfo);

                    pstmt.executeUpdate();

                } catch (Exception e) {
                    System.out.println(burgerName + " 추가중 오류 발생");
                }


            } else if (num == 2) {
                readSides();
                for (Side s : sides) {
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
                        edit();
                        return;
                    }
                    if (sides.stream().map(Side::getCode).toList().contains(code)) {
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

                try (Connection con = SQLBurgerConnect.makeConnection();
                     PreparedStatement pstmt = con.prepareStatement("INSERT INTO sides VALUES (?, ?, ?)")) {
                    pstmt.setInt(1, sideCode);
                    pstmt.setString(2, sideName);
                    pstmt.setInt(3, sidePrice);

                    pstmt.executeUpdate();

                } catch (Exception e) {
                    System.out.println(sideName + "번 사이드 추가중 오류 발생");
                }


            } else if (num == 3) {
                readBurgers();
                if (burgers.isEmpty()) {
                    System.out.println("등록된 버거가 없습니다.");
                    edit();
                    return;
                }
                for (Burger b : burgers) {
                    System.out.println(b);
                }
                int burgerCode;
                while (true) {
                    System.out.print("삭제하실 버거의 코드를 입력해주세요(뒤로가기 -1): ");
                    while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(burgers.size() + 1)) {
                        sc.next();
                        System.out.println("숫자를 입력해주세요");
                        System.out.print("존재하는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
                    }
                    int code = sc.nextInt();
                    sc.nextLine();

                    if (code == -1) {
                        edit();
                        return;
                    }

                    if (burgers.stream().map(Burger::getCode).toList().contains(code)) {
                        burgerCode = code;
                        break;
                    }
                }

                try (Connection con = SQLBurgerConnect.makeConnection();
                     PreparedStatement pstmt = con.prepareStatement("delete from burgers where code=?")) {
                    pstmt.setInt(1, burgerCode);

                    int i = pstmt.executeUpdate();

                    if (i == 1) {
                        burgers.removeIf(b -> b.getCode() == burgerCode);
                        System.out.println(burgerCode + "번 버거 삭제 완료");
                    } else {
                        System.out.println(burgerCode + "번 버거 삭제 실패");
                    }
                } catch (Exception e) {
                    System.out.println(burgerCode + "번 버거 삭제중 오류발생");
                }

            } else if (num == 4) {
                readSides();
                if (sides.isEmpty()) {
                    System.out.println("등록된 사이드가 없습니다.");
                    edit();
                    return;
                }
                for (Side s : sides) {
                    System.out.println(s);
                }
                int sideCode;
                while (true) {
                    System.out.print("삭제하실 사이드의 코드를 입력해주세요(뒤로가기(-1): ");
                    while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(sides.size() + 1)) {
                        sc.next();
                        System.out.println("숫자를 입력해주세요");
                        System.out.print("존재하는 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
                    }
                    int code = sc.nextInt();
                    sc.nextLine();

                    if (code == -1) {
                        edit();
                        return;
                    }

                    if (sides.stream().map(Side::getCode).toList().contains(code)) {
                        sideCode = code;
                        break;
                    }
                }

                try (Connection con = SQLBurgerConnect.makeConnection();
                     PreparedStatement pstmt = con.prepareStatement("DELETE FROM sides WHERE code =" + sideCode)) {
                    int i = pstmt.executeUpdate();
                    if (i == 1) {
                        sides.removeIf(s -> s.getCode() == sideCode);
                        System.out.println(sideCode + "번 사이드 삭제 완료");
                    } else {
                        System.out.println(sideCode + "번 사이드 삭제 실패");
                    }
                } catch (Exception e) {
                    System.out.println(sideCode + "번 사이드 삭제중 오류발생");
                }
            } else if (num == 5) {
                System.out.print("1.버거 수정\t2.사이드 수정(뒤로가기 -1): ");
                while (!sc.hasNext("-?1|2")) {
                    sc.next();
                    System.out.println("숫자를 입력해주세요");
                    System.out.print("1.버거 수정\t2.사이드 수정: ");
                }
                int modify = sc.nextInt();
                sc.nextLine();

                if (modify == -1) {
                    edit();
                    return;
                }

                if (modify == 1) {
                    readBurgers();
                    if (burgers.isEmpty()) {
                        System.out.println("등록된 버거가 없습니다.");
                        edit();
                        return;
                    }
                    for (Burger b : burgers) {
                        System.out.println(b);
                    }
                    int burgerCode;
                    while (true) {

                        System.out.print("수정하실 버거의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
                        while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)")|| !sc.hasNextInt(burgers.size() + 1)) {
                            sc.next();
                            System.out.println("숫자를 입력해주세요");
                            System.out.print("수정하실 버거의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
                        }
                        int code = sc.nextInt();
                        sc.nextLine();

                        if (code == -1) {
                            edit();
                            return;
                        }

                        if (burgers.stream().map(Burger::getCode).toList().contains(code)) {
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

                    try (Connection con = SQLBurgerConnect.makeConnection();
                         PreparedStatement pstmt = con.prepareStatement("UPDATE burgers SET name=?, price=?, info=? where code=" + burgerCode)) {

                        pstmt.setString(1, burgerName);
                        pstmt.setInt(2, burgerPrice);
                        pstmt.setString(3, burgerInfo);

                        pstmt.executeUpdate();

                    } catch (Exception e) {
                        System.out.println(burgerCode + " 수정중 오류 발생");
                    }

                } else if (modify == 2) {
                    readSides();
                    if (sides.isEmpty()) {
                        System.out.println("등록된 사이드가 없습니다.");
                        edit();
                        return;
                    }
                    for (Side s : sides) {
                        System.out.println(s);
                    }
                    int sideCode;
                    while (true) {
                        System.out.print("수정하실 사이드의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
                        while (!sc.hasNext("(?:-1)|(?:[1-9]\\d*)") || !sc.hasNextInt(sides.size() + 1)) {
                            sc.next();
                            System.out.println("숫자를 입력해주세요");
                            System.out.print("수정하실 사이드의 코드번호를 입력해주세요(ex:1,2,3), 뒤로가기(-1): ");
                        }
                        int code = sc.nextInt();
                        sc.nextLine();

                        if (code == -1) {
                            edit();
                            return;
                        }

                        if (sides.stream().map(Side::getCode).toList().contains(code)) {
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


                    try (Connection con = SQLBurgerConnect.makeConnection();
                         PreparedStatement pstmt = con.prepareStatement("UPDATE sides SET name=?, price=? where code=" + sideCode)) {

                        pstmt.setString(1, sideName);
                        pstmt.setInt(2, sidePrice);

                        pstmt.executeUpdate();

                    } catch (Exception e) {
                        System.out.println(sideCode + " 수정중 오류 발생");
                    }

                }
            }
        }
    }


    public static void readBurgers() {
        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM burgers ORDER BY CODE");
             ResultSet rs = pstmt.executeQuery()) {

            burgers = new ArrayList<>();

            while (rs.next()) {
                int code = rs.getInt("CODE");
                String name = rs.getString("NAME");
                int price = rs.getInt("PRICE");
                String info = rs.getString("INFO");
                Burger burger = new Burger(code, name, price, info);
                burgers.add(burger);
            }
        } catch (Exception e) {
            System.out.println("버거목록 읽기 예외 발생");
        }
    }

    public static void readSides() {
        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sides ORDER BY CODE");
             ResultSet rs = pstmt.executeQuery()) {

            sides = new ArrayList<>();

            while (rs.next()) {
                int code = rs.getInt("CODE");
                String name = rs.getString("NAME");
                int price = rs.getInt("PRICE");

                Side side = new Side(code, name, price);
                sides.add(side);
            }
        } catch (Exception e) {
            System.out.println("사이드목록 읽기 예외 발생");
        }
    }

    public static boolean exit() {
        myBurgers.clear();
        mySides.clear();
        try (Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("delete from myBurgers");
             PreparedStatement pstmt1 = con.prepareStatement("delete from mySides")) {
            pstmt.executeUpdate();
            pstmt1.executeUpdate();
        } catch (Exception e) {
            System.out.println("종료중 오류 발생");
        }
        return true;
    }

    public static boolean systemOut() {
        try (Socket client = new Socket("localhost", 8000);
             PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
             Connection con = SQLBurgerConnect.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("delete from myBurgers");
             PreparedStatement pstmt1 = con.prepareStatement("delete from mySides")) {

            pstmt.executeUpdate();
            pstmt1.executeUpdate();
            pw.println("exit");
            pw.flush();
        } catch (Exception e) {
            System.out.println("시스템 종료중 오류 발생");
        }
        return true;
    }
}
