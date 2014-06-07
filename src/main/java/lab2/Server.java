package lab2;
import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by artiom on 21.05.14.
 */
public class Server {
    private int port;
    private byte[] byteFilename;

    Server(){
        connect();
    }

    public void connect(){
        port = 8186;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Waiting for a client...");
            Socket incoming = ss.accept();
            System.out.println("Got a client ");

            DataInputStream in = new DataInputStream(incoming.getInputStream());
            String fileName = in.readUTF();
            System.out.println("You have received the string of the file name: "+fileName);
            //-----------------------------------------------------------------------------
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/main/java/lab2/out/"+fileName));//создаем выходной файл
            int bufferSize = incoming.getReceiveBufferSize();
            byte[] bytes = new byte[bufferSize];
            int count;
            while ((count = in.read(bytes)) > 0) {
                bos.write(bytes, 0, count);//считанное записываем в выходной файл
            }

            bos.flush();
            bos.close();
            in.close();
            incoming.close();
            ss.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Server();
    }
}
