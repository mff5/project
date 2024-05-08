package SignIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class LogIn {
    public static ArrayList<Student> students = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)  {

        read();
        String sd_num;

        while (true)    {
            System.out.print("학번을 입력해주세요:");
            String _sd_num = sc.nextLine();
            if (!students.stream().anyMatch(s->s.getSd_num().equalsIgnoreCase(_sd_num))) {
                System.out.println("존재하지 않는 학번입니다.");
                continue;
            } else {
                sd_num = _sd_num;
                try(Connection con = SignInConnection.makeConnection();
                    PreparedStatement pstmt = con.prepareStatement("select * from students where sd_num="+sd_num);
                    ResultSet rs = pstmt.executeQuery()) {


                    System.out.print("이름을 입력해주세요:");
                    String name = sc.nextLine();

                    System.out.print("아이디를 입력해주세요:");
                    String sd_id = sc.nextLine();

                    System.out.print("비밀번호를 입력해주세요:");
                    String sd_pw = sc.nextLine();

                    String _name = null;
                    String _sd_id = null;
                    String _sd_pw = null;
                    while (rs.next())   {
                        _name = rs.getString("NAME");
                        _sd_id = rs.getString("SD_ID");
                        _sd_pw = rs.getString("SD_PW");
                    }
                    if (name.equals(_name) && sd_id.equals(_sd_id) && sd_pw.equals(_sd_pw)){
                        System.out.println("로그인 성공");
                    } else {
                        System.out.println("로그인 실패");
                    }
                } catch (Exception e)   {
                    System.out.println("회원가입중 오류 발생");
                }
            }
        }
    }
    public static void read()   {
        try (Connection con = SignInConnection.makeConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT sd_num FROM students ORDER BY sd_num");
             ResultSet rs = pstmt.executeQuery()) {

            students = new ArrayList<>();

            while (rs.next()) {
                String sd_num = rs.getString("SD_NUM");
                Student student = new Student(sd_num);
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println("students 읽기중 오류발생");
        }
    }
}
