import controller.DBUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BurgerServer {
    public static void main(String[] args) {
        System.out.println("시스템 시작");
        int num = 0;
        while (true)    {

            try(ServerSocket server = new ServerSocket(8000);
                Socket socket = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new FileWriter("2024.txt",true))){

                num++;
                String line;
                System.out.println("결제코드: "+num);
                bw.write("결제코드: "+num);
                bw.newLine();
                while ((line = br.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit"))  {
                        System.out.println("시스템 종료");
                        try(Connection con = DBUtil.getConnection();
                            PreparedStatement pstmt1 = con.prepareStatement("truncate table receipt");
                            PreparedStatement pstmt2 = con.prepareStatement("drop sequence receiptNum");
                            PreparedStatement pstmt3 = con.prepareStatement("create sequence receiptNum start with 1 increment by 1")) {
                            pstmt1.executeUpdate();
                            pstmt2.executeUpdate();
                            pstmt3.executeUpdate();

                            bw.write("시스템 종료");
                            bw.newLine();
                            bw.newLine();
                            bw.newLine();
                            bw.newLine();
                            bw.newLine();
                            bw.newLine();
                        } catch (Exception e) {
                            System.out.println("시스템 종료중 오류 발생");
                        }
                        return;
                    }
                    System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                }
                System.out.println();
                bw.newLine();
                bw.flush();

            } catch (Exception e)   {
                e.printStackTrace();
                System.out.println("예외 발생");
            }
        }
    }
}