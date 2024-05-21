package controller;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class ExitManager {
    public boolean proOut()   {
        ExitDAO ed = new ExitDAO();
        ed.proExit();

        return true;
    }
    public boolean sysOut()   {
       try(Socket client = new Socket("localhost", 8000);
           PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()))) {

           pw.println("exit");
           pw.flush();

       } catch (Exception e)    {
           System.out.println("종료중 오류 발생");
       }
        ExitDAO ed = new ExitDAO();
        ed.sysExit();

        return true;
    }
}
