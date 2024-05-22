package controller;

import model.SideVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SideDAO {
    public void insertMySides(SideVO svo) {

        String sql = "insert into mySides values (lpad(mySidesNum.nextval,5,'0'),lpad(?,5,'0'),?,trim(to_char(?,'L999,999,999')))";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, svo.getCode());
            pstmt.setString(2, svo.getName());
            pstmt.setInt(3, svo.getPrice());

            pstmt.executeUpdate();
            System.out.println("카트에 "+svo.getCode()+"번 "+svo.getName()+"이/가 추가되었습니다.");
        } catch (Exception e)   {
            System.out.println("카트에 사이드 추가중 오류 발생");
        }
    }
    public void deleteMySides(int mySideNum) {

        String sql = "delete from mySides where orderNum=lpad(?,5,'0')";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1,mySideNum);

            pstmt.executeUpdate();
            System.out.println("카트에서 "+mySideNum+"번 사이드가 삭제되었습니다.");
        } catch (Exception e) {
            System.out.println("카트에서 사이드 삭제중 오류 발생");
        }
    }
    public static ArrayList<SideVO> readSides() {
        ArrayList<SideVO> sides = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sides ORDER BY CODE");
             ResultSet rs = pstmt.executeQuery()) {


            while (rs.next()) {
                int code = Integer.parseInt(rs.getString("CODE"));
                String name = rs.getString("NAME");
                int price = Integer.parseInt(rs.getString("PRICE").trim().replaceAll("[^\\d.]", ""));

                SideVO side = new SideVO(code, name, price);
                sides.add(side);
            }
        } catch (Exception e) {
            System.out.println("사이드목록 읽기 예외 발생");
        }
        return sides;
    }
}
