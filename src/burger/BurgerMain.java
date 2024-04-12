package burger;

import java.io.*;
import java.net.Socket;
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
    public static void main(String[] args)  {
        boolean exit = false;
        while (!exit)    {
                int num = selectMenu();
                switch (num) {
                    case 1 -> selectBurger();
                    case 2 -> removeBurger();
                    case 3 -> selectSide();
                    case 4 -> removeSide();
                    case 5 -> showAll();
                    case 6 -> clearAll();
                    case 7 -> exit= receipt();
                    case 8 -> admin();
                    case 9 -> {
                        System.out.println("종료합니다.");
                        exit = true;
                    }
                    case 10 -> {
                        systemOut();
                        exit = true;
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
        while (!sc.hasNextInt()|| sc.hasNext("0"))    {
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
        for (Burger b : burgers) {
            System.out.println(b);
        }
        System.out.print("버거를 선택해주세요(뒤로가기 -1): ");

        while (!sc.hasNextInt() || !sc.hasNextInt(burgers.size()+1) || sc.hasNext("0")) {
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
        MyBurger myBurger = new MyBurger(myBurgerNum, burger.getCode(), burger.getBgName(), burger.getPrice(), burger.getInfo());
        System.out.println("1.버거 사이즈업(+2000)\t2.버거 세트\t3.버거 사이즈업 + 세트\t4.선택안함");
        System.out.print("사이즈업/세트를 선택해주세요: ");


        while (!sc.hasNextInt() || !(sc.hasNextInt(5))|| sc.hasNext("0")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("사이즈업/세트를 선택해주세요: ");
        }
        int answer = sc.nextInt();
        sc.nextLine();


        if (answer == 1) {
            while (true) {
                System.out.print(myBurger.getBgName() + " 사이즈업을 하시겠습니까?(Y/N): ");
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
                System.out.print(myBurger.getBgName() + " 세트를 선택하시겠습니까?(Y/N): ");
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
                System.out.print(myBurger.getBgName() + " 사이즈업과 세트를 선택하시겠습니까?(Y/N): ");
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
        myBurgerNum++;
    }

    public static void removeBurger()   {
        if (myBurgers.isEmpty())    {
            System.out.println("선택하신 버거가 없습니다.");
            return;
        }
        for (MyBurger mb : myBurgers)  {
            System.out.println(mb);
        }
        System.out.print("삭제하실 버거를 선택해주세요(뒤로가기 -1): ");
        while (!sc.hasNextInt() || !sc.hasNextInt(myBurgers.size()+1)|| sc.hasNext("0"))    {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("삭제하실 버거를 선택해주세요(뒤로가기 -1): ");
        }
        int remove = sc.nextInt();
        sc.nextLine();
        if (remove==-1) {
            return;
        }
        if (myBurgers.removeIf(mb->mb.getOrderNum()==remove)){
            System.out.println("삭제가 완료되었습니다.");
        }
    }
    public static void selectSide()  {
        readSides();
        for (Side s : sides)    {
            System.out.println(s);
        }
        System.out.print("추가하실 사이드의 번호를 입력해주세요(뒤로가기 -1): ");
        while (!sc.hasNextInt() || !sc.hasNextInt(sides.size()+1)|| sc.hasNext("0"))    {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("추가하실 사이드의 번호를 입력해주세요(뒤로가기 -1): ");
        }
        int code = sc.nextInt();
        sc.nextLine();
        if (code==-1){
            return;
        }
        Side side = sides.stream().filter(s->s.getCode()==code).max(Comparator.comparing(Side::getCode)).get();
        MySide mySide = new MySide(mySideNum,side.getCode(),side.getSdName(),side.getPrice());
        mySides.add(mySide);
        mySideNum++;
    }
    public static void removeSide() {
        if (mySides.isEmpty())  {
            System.out.println("추가하신 사이드가 없습니다.");
            return;
        }
        for (MySide ms : mySides)  {
            System.out.println(ms);
        }
        System.out.print("삭제하실 사이드를 선택해주세요(뒤로가기 -1): ");
        while (!sc.hasNextInt() || !sc.hasNextInt(mySides.size()+1)|| sc.hasNext("0"))    {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("삭제하실 사이드를 선택해주세요(뒤로가기 -1): ");
        }
        int remove = sc.nextInt();
        sc.nextLine();
        if (remove==-1) {
            return;
        }
        if (mySides.removeIf(ms->ms.getOrderNum()==remove))  {
            System.out.println("삭제가 완료되었습니다.");
        }

    }
    public static void showAll()    {
        int total = 0;
        if (myBurgers.isEmpty())    {
            System.out.println("선택하신 버거가 없습니다.");
        } else {
            System.out.println("버거");
            for (MyBurger mb : myBurgers)   {
                System.out.println(mb);
                total += mb.getPrice();
            }
        }
        if (mySides.isEmpty())  {
            System.out.println("추가하신 사이드가 없습니다.");
        } else {
            System.out.println("사이드");
            for (MySide ms : mySides)   {
                System.out.println(ms);
                total += ms.getPrice();
            }
        }
        System.out.println(total+"원");
    }
    public static void clearAll()   {
        myBurgers.clear();
        mySides.clear();
        System.out.println("삭제가 완료되었습니다.");
    }
    public static boolean receipt()    {
        try(Socket client = new Socket("localhost",8000);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()))) {
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now.getYear()+"년 "+now.getMonthValue()+"월 "+now.getDayOfMonth()+"일 "+now.getHour()+"시 "
                    +now.getMinute()+"분 "+now.getSecond()+"초");
            showAll();
            if (!myBurgers.isEmpty())   {
                String burger = myBurgers.toString();
                pw.println(burger);
            }

            if (!mySides.isEmpty())   {
                String side = mySides.toString();
                pw.println(side);
            }

            pw.flush();

            if (myBurgers.isEmpty() && mySides.isEmpty())   {
                return false;
            }
        } catch (Exception e)   {
            System.out.println("예외 발생2");
        }
        return true;
    }
    public static void admin()  {
        Admin admin = new Admin();
        while (true)    {
            System.out.println("관리자 로그인");
            System.out.print("아이디를 입력해주세요(뒤로가기 -1): ");
            String id = sc.nextLine();
            if (id.equalsIgnoreCase("-1"))  {
                return;
            }
            System.out.print("비밀번호를 입력해주세요: ");
            String password = sc.nextLine();
            if (id.equalsIgnoreCase(admin.getId()) && password.equalsIgnoreCase(admin.getPassword())) {
                System.out.println("관리자 접속 완료");
                while (true)    {
                    System.out.print("1.버거 목록 추가\t2.사이드 목록 추가(뒤로가기 -1): ");
                    while (!sc.hasNextInt() || !sc.hasNextInt(3)|| sc.hasNext("0"))    {
                        sc.next();
                        System.out.println("숫자를 입력해주세요");
                        System.out.print("1.버거 목록 추가\t2.사이드 목록 추가(뒤로가기 -1): ");
                    }
                    int num = sc.nextInt();
                    sc.nextLine();
                    if (num==-1)    {
                        return;
                    }
                    if (num==1) {
                        try(PrintWriter pw1 = new PrintWriter(new FileWriter("burgers.txt",true))) {
                            readBurgers();
                            for (Burger b : burgers)    {
                                System.out.println(b);
                            }
                            while (true){
                                System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3): ");
                                while (!sc.hasNextInt()|| sc.hasNext("0"))    {
                                    sc.next();
                                    System.out.println("숫자를 입력해주세요");
                                    System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3): ");
                                }
                                int code = sc.nextInt();
                                sc.nextLine();
                                if (burgers.stream().map(Burger::getCode).toList().contains(code))      {
                                    continue;
                                }
                                pw1.println(code);
                                break;
                            }
                            System.out.print("제품명을 입력해주세요: ");
                            String name = sc.nextLine();
                            pw1.println(name);
                            System.out.print("가격을 입력해주세요: ");
                            while (!sc.hasNextInt()|| sc.hasNext("0"))    {
                                sc.next();
                                System.out.println("숫자를 입력해주세요");
                                System.out.print("가격을 입력해주세요: ");
                            }
                            int price = sc.nextInt();
                            sc.nextLine();
                            pw1.println(price);
                            System.out.print("상품설명을 입력해주세요: ");
                            String info = sc.nextLine();
                            pw1.println(info);

                        } catch (Exception e)   {
                            System.out.println("버거목록 추가중 예외 발생");
                        }


                    } else if (num==2){
                        try(PrintWriter pw2 = new PrintWriter(new FileWriter("sides.txt",true))) {
                            readSides();
                            for (Side s : sides)    {
                                System.out.println(s);
                            }
                            while (true){
                                System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3): ");
                                while (!sc.hasNextInt()|| sc.hasNext("0"))    {
                                    sc.next();
                                    System.out.println("숫자를 입력해주세요");
                                    System.out.print("중복되지 않는 코드번호를 입력해주세요(ex:1,2,3): ");
                                }
                                int code = sc.nextInt();
                                sc.nextLine();
                                if (sides.stream().map(Side::getCode).toList().contains(code))      {
                                    continue;
                                }
                                pw2.println(code);
                                break;
                            }
                            System.out.print("제품명을 입력해주세요: ");
                            String name = sc.nextLine();
                            pw2.println(name);
                            System.out.print("가격을 입력해주세요: ");
                            while (!sc.hasNextInt()|| sc.hasNext("0"))    {
                                sc.next();
                                System.out.println("숫자를 입력해주세요");
                                System.out.print("가격을 입력해주세요: ");
                            }
                            int price = sc.nextInt();
                            sc.nextLine();
                            pw2.println(price);

                        } catch (Exception e)   {
                            System.out.println("사이드목록 추가중 예외 발생");
                        }
                    }
                }
            } else{
                System.out.println("다시 입력해주십세요.");
            }
        }
    }
    public static void readBurgers()    {
        try(BufferedReader br = new BufferedReader(new FileReader("burgers.txt"))) {
            String line;
            String[] burgerPart = new String[4];
            burgers = new ArrayList<>();

            while ((line=br.readLine())!=null)    {
                burgerPart[0]=line;
                burgerPart[1]=br.readLine();
                burgerPart[2]=br.readLine();
                burgerPart[3]=br.readLine();
                Burger burger = new Burger(Integer.parseInt(burgerPart[0]),burgerPart[1],Integer.parseInt(burgerPart[2]),burgerPart[3]);
                burgers.add(burger);
            }
        } catch (Exception e)   {
            System.out.println("버거목록 읽기 예외 발생");
        }
    }
    public static void readSides()  {
        try(BufferedReader br = new BufferedReader(new FileReader("sides.txt"))) {
            String line;
            String[] sidePart = new String[3];
            sides= new ArrayList<>();

            while ((line=br.readLine())!=null)    {
                sidePart[0]=line;
                sidePart[1]=br.readLine();
                sidePart[2]=br.readLine();

                Side side = new Side(Integer.parseInt(sidePart[0]),sidePart[1],Integer.parseInt(sidePart[2]));
                sides.add(side);
            }
        } catch (Exception e)   {
            System.out.println("사이드목록 읽기 예외 발생");
        }
    }
    public static  void systemOut() {
        try(Socket client = new Socket("localhost",8000);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()))) {
            pw.println("exit");

            pw.flush();
        } catch (Exception e)   {
            System.out.println("예외 발생2");
        }
    }
}
