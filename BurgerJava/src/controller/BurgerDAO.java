package controller;

import model.BurgerVO;
import model.MyBurgerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BurgerDAO {
    public void insertMyBurgers(BurgerVO bvo) {

        String sql = "insert into myBurgers values (lpad(myBurgersNum.nextval,5,'0'),lpad(?,5,'0'),?,trim(to_char(?,'L999,999,999')),?)";

        try(Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {

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
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM burgers ORDER BY CODE");
             ResultSet rs = pstmt.executeQuery()) {



            while (rs.next()) {
                int code = Integer.parseInt(rs.getString("CODE"));
                String name = rs.getString("NAME");
                int price = Integer.parseInt(rs.getString("PRICE").trim().replaceAll("[^\\d.]", ""));
                String info = rs.getString("INFO");
                BurgerVO burger = new BurgerVO(code, name, price, info);
                burgers.add(burger);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("버거목록 읽기 예외 발생");
        }
        return burgers;
    }
    public static ArrayList<MyBurgerVO> readMyBurgers() {
        ArrayList<MyBurgerVO> myBurgers = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM myBurgers ORDER BY orderNum");
             ResultSet rs = pstmt.executeQuery()) {



            while (rs.next()) {
                int orderNum = Integer.parseInt(rs.getString("ORDERNUM"));
                int code = Integer.parseInt(rs.getString("CODE"));
                String name = rs.getString("NAME");
                int price = Integer.parseInt(rs.getString("PRICE").trim().replaceAll("[^\\d.]", ""));
                String info = rs.getString("INFO");

                MyBurgerVO myBurger = new MyBurgerVO(orderNum,code, name, price, info);
                myBurgers.add(myBurger);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("카트 버거목록 읽기 예외 발생");
        }
        return myBurgers;
    }
}
