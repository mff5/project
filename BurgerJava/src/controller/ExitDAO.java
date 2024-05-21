package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ExitDAO {
    public void proExit()    {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt1 = con.prepareStatement("truncate table myBurgers");
             PreparedStatement pstmt2 = con.prepareStatement("truncate table mySides");
             PreparedStatement pstmt3 = con.prepareStatement("drop sequence myBurgersNum");
             PreparedStatement pstmt4 = con.prepareStatement("create sequence myBurgersNum start with 1 increment by 1");
             PreparedStatement pstmt5 = con.prepareStatement("drop sequence mySidesNum");
             PreparedStatement pstmt6 = con.prepareStatement("create sequence mySidesNum start with 1 increment by 1")) {

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            pstmt6.executeUpdate();
            System.out.println("프로그램이 종료되었습니다.");

        } catch (Exception e) {
            System.out.println("프로그램 종료중 오류 발생");
        }
    }
    public void sysExit()    {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt1 = con.prepareStatement("truncate table myBurgers");
             PreparedStatement pstmt2 = con.prepareStatement("truncate table mySides");
             PreparedStatement pstmt3 = con.prepareStatement("drop sequence myBurgersNum");
             PreparedStatement pstmt4 = con.prepareStatement("create sequence myBurgersNum start with 1 increment by 1");
             PreparedStatement pstmt5 = con.prepareStatement("drop sequence mySidesNum");
             PreparedStatement pstmt6 = con.prepareStatement("create sequence mySidesNum start with 1 increment by 1")) {

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            pstmt6.executeUpdate();
            System.out.println("시스템이 종료되었습니다.");

        } catch (Exception e)   {
            System.out.println("시스템 종료중 오류 발생");
        }
    }
}