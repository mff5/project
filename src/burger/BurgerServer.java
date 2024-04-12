package burger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BurgerServer {
    public static void main(String[] args) {

        int num = 0;
        while (true)    {

            try(ServerSocket server = new ServerSocket(8000);
                Socket socket = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

                num++;
                String line;

                while ((line = br.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit"))  {
                        return;
                    }
                    System.out.println("결제코드: "+num + ": \n" + line);
                    System.out.println(br.readLine());
                    System.out.println();

                }

            } catch (Exception e)   {
                System.out.println("예외 발생");
            }
        }
    }
}
