package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;



public class AdminDAO {
    public void insertBurger(int code, String name, int price, String info){

        String sql = "INSERT INTO burgers VALUES (lpad(?,5,'0'), ?, trim(to_char(?,'L999,999,999')), ?)";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, code);
            pstmt.setString(2, name);
            pstmt.setInt(3, price);
            pstmt.setString(4, info);

            pstmt.executeUpdate();
            System.out.println("버거 목록에 "+code+"번 " +name+"이/가 추가되었습니다.");
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
        String sql = "delete from burgers where code=lpad(?,5,'0')";

        try(Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, code);

            pstmt.executeUpdate();
            System.out.println("버거 목록에서 "+code+"번 버거가 삭제되었습니다.");
        } catch (Exception e)   {
            System.out.println("버거 목록 삭제중 오류 발생");
        }
    }


    public void insertSide(int code, String name, int price){
        String sql = "INSERT INTO sides VALUES (lpad(?,5,'0'), ?, trim(to_char(?,'L999,999,999')))";

        try(Connection con = DBUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, code);
            pstmt.setString(2, name);
            pstmt.setInt(3, price);

            pstmt.executeUpdate();
            System.out.println("사이드 목록에 "+code+"번 " +name+"이/가 추가되었습니다.");
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
        String sql = "DELETE FROM sides WHERE code =lpad(?,5,'0')";

        try(Connection con = DBUtil.getConnection();
        PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setInt(1, code);

            psmt.executeUpdate();
            System.out.println("사이드 목록에서 "+code+"번 사이드가 삭제되었습니다.");
        } catch (Exception e)   {
            System.out.println("사이드 목록 삭제중 오류 발생");
        }
    }
}
