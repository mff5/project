package SignIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class SignIn {
    public static ArrayList<Student> students = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)  {
        boolean exit = false;
        while (!exit) {
            int num = selectMenu();
            switch (num) {
                case 1 -> signIn();
                case 2 -> modify();
                case 3 -> delete();
                case 4 -> {
                    System.out.println("종료합니다.");
                    exit = true;
                }
                default -> System.out.println("다시 입력해주세요.");
            }
        }
    }
    public static int selectMenu() {
        System.out.println("1.회원가입");
        System.out.println("2.정보수정");
        System.out.println("3.정보삭제");
        System.out.println("4.종료");
        System.out.print("선택해주세요 >> ");
        while (!sc.hasNextInt() || sc.hasNext("0")) {
            sc.next();
            System.out.println("숫자를 입력해주세요");
            System.out.print("메뉴를 선택해주세요 >>");
        }
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }
    public static void signIn() {
        try(Connection con = SignInConnection.makeConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO students VALUES (?,?,?,?)")) {

            read();
            for (Student student : students)    {
                System.out.println(student);
            }
            String sd_num;
            while (true)    {
                System.out.print("학번을 입력해주세요(뒤로가기 -1):");
                String _sd_num = sc.nextLine();

                if (_sd_num.equalsIgnoreCase("-1"))  {
                    return;
                }

                if (students.stream().anyMatch(s->s.getSd_num().equals(_sd_num)))    {
                    System.out.println("중복된 학번입니다.");
                    continue;
                }
                sd_num = _sd_num;
                break;
            }

            System.out.print("이름을 입력해주세요:");
            String name = sc.nextLine();

            System.out.print("아이디를 입력해주세요:");
            String sd_id = sc.nextLine();

            System.out.print("비밀번호를 입력해주세요:");
            String sd_pw = sc.nextLine();

            pstmt.setString(1,sd_num);
            pstmt.setString(2,name);
            pstmt.setString(3,sd_id);
            pstmt.setString(4,sd_pw);

            int i = pstmt.executeUpdate();

            if (i==1)   {
                System.out.println("회원가입 성공");
            } else {
                System.out.println("회원가입 실패");
            }
        } catch (Exception e)   {
            System.out.println("회원가입중 오류 발생");
        }
    }
    public static void modify() {
        read();
        if (students.isEmpty()) {
            System.out.println("학생목록이 비어있습니다.");
            return;
        }
        for (Student student : students)    {
            System.out.println(student);
        }
        while (true)    {
            System.out.print("수정하실 학생의 학번을 입력해주세요(뒤로가기 -1): ");
            String sd_num = sc.nextLine();

            if (sd_num.equalsIgnoreCase("-1"))  {
                return;
            }
            if (!students.stream().anyMatch(s->s.getSd_num().equals(sd_num)))   {
                System.out.println("올바르지 않은 학번입니다.");
                continue;
            }
            try(Connection con = SignInConnection.makeConnection();
            PreparedStatement pstmt = con.prepareStatement("update students set name=?, sd_id=?, sd_pw=?")) {
                System.out.print("수정하실 이름을 입력해주세요: ");
                String name = sc.nextLine();
                System.out.print("수정하실 아이디를 입력해주세요: ");
                String id = sc.nextLine();
                System.out.print("수정하실 비밀번호를 입력해주세요: ");
                String pw = sc.nextLine();

                pstmt.setString(1, name);
                pstmt.setString(2, id);
                pstmt.setString(3, pw);

                int i = pstmt.executeUpdate();

                if (i==1)   {
                    System.out.println(sd_num+" 수정 성공");
                } else {
                    System.out.println(sd_num+" 수정 실패");
                }
                break;
            } catch (Exception e)   {
                System.out.println("수정중 오류발생");
            }
        }

    }
    public static void delete() {
        read();
        if (students.isEmpty()) {
            System.out.println("학생목록이 비어있습니다.");
            return;
        }
        for (Student student : students)    {
            System.out.println(student);
        }
        while (true)    {
            System.out.print("삭제하실 학생의 학번을 입력해주세요(뒤로가기 -1): ");
            String sd_num = sc.nextLine();

            if (sd_num.equalsIgnoreCase("-1"))  {
                return;
            }
            if (!students.stream().anyMatch(s->s.getSd_num().equals(sd_num)))   {
                System.out.println("올바르지 않은 학번입니다.");
                continue;
            }
            try(Connection con = SignInConnection.makeConnection();
            PreparedStatement pstmt = con.prepareStatement("delete from students where sd_num="+sd_num)) {
                int i = pstmt.executeUpdate();
                if (i==1)   {
                    System.out.println(sd_num+" 삭제 성공");
                } else {
                    System.out.println(sd_num+" 삭제 실패");
                }
                break;
            } catch (Exception e)   {
                System.out.println("삭제중 오류발생");
            }
        }
    }
    public static void read()   {
        try (Connection con = SignInConnection.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM students ORDER BY sd_num");
             ResultSet rs = pstmt.executeQuery()) {

            students = new ArrayList<>();

            while (rs.next()) {
                String sd_num = rs.getString("SD_NUM");
                String name = rs.getString("NAME");
                String sd_id = rs.getString("SD_ID");
                String sd_pw = rs.getString("SD_PW");
                Student student = new Student(sd_num, name, sd_id, sd_pw);
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println("students 읽기중 오류발생");
        }
    }
}
