package lab2;
/**
 * Created by artiom on 21.05.14.
 */
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private int port;
    private String address;
    private String fileName = null;

    Client(){
        connect();
    }

    public void connect(){
        port = 8186;
        address = "127.0.0.1";
        System.out.println("Client is ready.");

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress,port);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Write a name of the file");
            fileName = keyboard.readLine().trim();

            File file = new File("src/main/java/lab2/"+fileName);

            byte[] arr = fileName.getBytes();
            out.writeUTF(file.getName());
            //-----------------------------------------------------------
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[(int) file.length()];
            int count;
            while ((count = bis.read(bytes)) > 0) {
                out.write(bytes, 0, count);//отправляем серверу данные, считанные в файле
            }

            out.flush();
            out.close();
            bis.close();
            socket.close();

            System.out.println("You have a successful sending");
        }
        catch (UnknownHostException e){
            System.err.println(e);
        }
        catch (IOException e){
            System.out.println("Impossible to create socket.");
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}