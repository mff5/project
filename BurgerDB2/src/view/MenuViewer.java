package view;

public class MenuViewer {
    public static void mainMenuView()   {
        System.out.println();
        System.out.println("버거 주문 프로그램");
        System.out.println("해당 번호를 입력하세요.");
        System.out.println("1. 버거 선택/삭제");
        System.out.println("2. 사이드 추가/삭제");
        System.out.println("3. 카트 보기/비우기");
        System.out.println("4. 결제");
        System.out.println("5. 관리자 로그인");
        System.out.println("6. 종료");
        System.out.print("번호 선택 : ");
    }
    public static void burgerMenuView() {
        System.out.println();
        System.out.println("버거 메뉴 번호를 입력하세요.");
        System.out.println("1. 버거 담기");
        System.out.println("2. 버거 삭제");
        System.out.println("3. 메인 메뉴");
        System.out.print("번호 선택 : ");
    }
    public static void sideMenuView()   {
        System.out.println();
        System.out.println("사이드 메뉴 번호를 입력하세요.");
        System.out.println("1. 사이드 담기");
        System.out.println("2. 사이드 삭제");
        System.out.println("3. 메인 메뉴");
        System.out.print("번호 선택 : ");
    }
    public static void cartMenuView()   {
        System.out.println();
        System.out.println("카트 메뉴 번호를 입력하세요.");
        System.out.println("1. 카트 보기");
        System.out.println("2. 카트 비우기");
        System.out.println("3. 메인 메뉴");
        System.out.print("번호 선택 : ");
    }
    public static void payMenuView()    {
        System.out.println();
        System.out.println("결제 메뉴 번호를 입력하세요.");
        System.out.println("1. 결제하기");
        System.out.println("2. 메인 메뉴");
        System.out.print("번호 선택 : ");
    }
    public static void adminMenuView()  {
        System.out.println();
        System.out.println("관리자 메뉴 번호를 입력하세요.");
        System.out.println("1. 버거 목록 추가하기");
        System.out.println("2. 버거 목록 수정하기");
        System.out.println("3. 버거 목록 삭제하기");
        System.out.println("4. 사이드 목록 추가하기");
        System.out.println("5. 사이드 목록 수정하기");
        System.out.println("6. 사이드 목록 삭제하기");
        System.out.println("7. 메인 메뉴");
        System.out.print("번호 선택 : ");
    }
    public static void exitMenuView()   {
        System.out.println();
        System.out.println("종료 메뉴 번호를 입력하세요.");
        System.out.println("1. 프로그램 종료");
        System.out.println("2. 시스템 종료");
        System.out.println("3. 메인 메뉴");
        System.out.print("번호 선택 : ");
    }
}
