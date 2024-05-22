package controller;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class AdminDAO {
    public void insertBurger(int burgerCode, String burgerName, int burgerPrice, String burgerDescription,
                             String burgerCategory, int burgerStock, int burgerCalories, String burgerAllergy){

        try(Connection con = DBUtil.getConnection();
            CallableStatement cstmt = con.prepareCall("{call insert insert_burger(?,?,?,?,?,?,?,?)}")) {

            con.setAutoCommit(false);

            cstmt.setInt(1, burgerCode);
            cstmt.setString(2, burgerName);
            cstmt.setInt(3, burgerPrice);
            cstmt.setString(4, burgerDescription);
            cstmt.setString(5, burgerCategory);
            cstmt.setInt(6, burgerStock);
            cstmt.setInt(7, burgerCalories);
            cstmt.setString(8, burgerAllergy);

            if (cstmt.executeUpdate() == 1) {
                con.commit();
                System.out.println("버거 목록에 "+burgerCode+"번 "+burgerName+"이/가 추가되었습니다.");
            } else {
                con.rollback();
                System.out.println("버거 목록에 "+burgerCode+"번 "+burgerName+"추가가 실패했습니다.");
            }



        } catch (Exception e)   {
            System.out.println("버거 목록 추가중 오류 발생");
        }
    }
    public void updateBurger(int code, String name, int price, String info)   {

        String sql = "UPDATE burgers SET name=?, price=trim(to_char(?,'L999,999,999')), info=? where code=lpad(?,5,'0')";

        try(Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setInt(2, price);
            pstmt.setString(3,info);
            pstmt.setInt(4, code);

            pstmt.executeUpdate();
            System.out.println("버거 목록에서 "+code+"번 " +name+"이/가 수정되었습니다.");
        } catch (Exception e)   {
            System.out.println("버거 목록 수정중 오류 발생");
        }
    }
    public void deleteBurger(int code)  {

        try(Connection con = DBUtil.getConnection();
            CallableStatement cstmt = con.prepareCall("{call delete_burger(?)}")) {
            con.setAutoCommit(false);
            cstmt.setInt(1, code);

            if (cstmt.executeUpdate()==1)   {
                con.commit();
                System.out.println("버거 목록에서 "+code+"번 버거가 삭제되었습니다.");
            } else {
                con.rollback();
                System.out.println("버거 목록에서 "+code+"번 버거 삭제가 실패했습니다.");
            }

        } catch (Exception e)   {
            System.out.println("버거 목록 삭제중 오류 발생");
        }
    }


    public void insertSide(int sideCode, String sideName, int sidePrice, String sideDescription, String category,
                           int sideStock, int sideCalories, String sideAllergy){
        String sql = "INSERT INTO sides VALUES (lpad(?,5,'0'), ?, trim(to_char(?,'L999,999,999')))";

        try(Connection con = DBUtil.getConnection();
        CallableStatement cstmt = con.prepareCall("{call insert_side(?,?,?,?,?,?,?,?)}")) {



            cstmt.executeUpdate();
            System.out.println("사이드 목록에 "+sideCode+"번 " +sideName+"이/가 추가되었습니다.");
        } catch (Exception e)   {
            System.out.println("사이드 목록 추가중 오류 발생");
        }
    }
    public void updateSide(int code, String name, int price)   {
        String sql = "UPDATE sides SET name=?, price= trim(to_char(?,'L999,999,999')) where code=lpad(?,5,'0')";

        try(Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, code);

            pstmt.executeUpdate();
            System.out.println("사이드 목록에서 "+code+"번 " +name+"이/가 수정되었습니다.");
        } catch (Exception e)   {
            System.out.println("사이드 목록 수정중 오류 발생");
        }
    }
    public void deleteSide(int code)  {

        try(Connection con = DBUtil.getConnection();
        CallableStatement csmt = con.prepareCall("{call get_all_burgers()}")) {

            con.setAutoCommit(false);

            csmt.setInt(1, code);

            if (csmt.executeUpdate() == 1)  {
                con.commit();
                System.out.println("사이드 목록에서 "+code+"번 사이드가 삭제되었습니다.");
            } else {
                con.rollback();
                System.out.println("사이드 목록에서 "+code+"번 사이드 삭제가 실패했습니다.");
            }

        } catch (Exception e)   {
            System.out.println("사이드 목록 삭제중 오류 발생");
        }
    }
}
