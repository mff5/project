package controller;

import model.BurgerVO;
import model.CartVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class BurgerDAO {
    public void insertCart(BurgerVO bvo) {

        try(Connection con = DBUtil.getConnection();
        CallableStatement pstmt = con.prepareCall("{}")) {

            pstmt.setInt(1, bvo.getCode());
            pstmt.setString(2, bvo.getName());
            pstmt.setInt(3, bvo.getPrice());
            pstmt.setString(4, bvo.getInfo());

            pstmt.executeUpdate();
            System.out.println("카트에 " +bvo.getCode()+"번 "+bvo.getName()+"이/가 추가되었습니다.");
        } catch (Exception e)   {
            e.printStackTrace();
            System.out.println("카트에 버거 추가중 오류 발생");
        }
    }
    public void deleteMyBurgers(int myBurgerNum)   {

        String sql = "delete from myBurgers where orderNum=lpad(?,5,'0')";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, myBurgerNum);

            pstmt.executeUpdate();
            System.out.println("카트에서 "+myBurgerNum+"번 버거가 삭제되었습니다.");
        } catch (Exception e)   {
            System.out.println("카트에서 버거 삭제중 오류 발생");
        }
    }
    public static ArrayList<BurgerVO> readBurgers() {
        ArrayList<BurgerVO> burgers = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             CallableStatement cstmt = con.prepareCall("{call get_burgers(?)}")) {

            cstmt.registerOutParameter(1, Types.REF_CURSOR);

            cstmt.executeUpdate();

            try {

            }


            int burgerCode;
            String burgerName;
            int burgerPrice;
            String burgerDescription;
            String burgerCategory;
            int burgerStock;
            Date burgerCreatedAt;
            Date burgerUpdatedAt;
            int burgerCalories;
            String burgerAllergy;





        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("버거목록 읽기 예외 발생");
        }
        return burgers;
    }
}
