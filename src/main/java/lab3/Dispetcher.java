package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.DataFormatException;
/**
 * Created by artiom on 15.05.14.
 */

public class Dispetcher {
    private BufferedReader in = null;
    private BufferedWriter out = null;
    private String path;
    private int threadCount;

    public Dispetcher(String path, String threadCount){
        this.path = path;
        this.threadCount = Integer.parseInt(threadCount);
    }

    public void solve(){
        try {
            in = new BufferedReader(new FileReader("src/main/java/lab3/"+path+".in"));
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            return;
        }

        try {
            out = new BufferedWriter(new FileWriter("src/main/java/lab3/"+path+".out"));
        } catch (IOException e) {
            System.out.println("Output file not found.");
            try {
                in.close();
            } catch (IOException e1) {
                System.out.println("Cannot close input buffer");
            }
            return;
        }

        String str;
        int count = 0;
        String[] arrayStr;
        int[] arrayInt;
        List<Integer> finalList = Collections.synchronizedList(new ArrayList<Integer>());
        ExecutorService executor;
        try {
            executor = Executors.newFixedThreadPool(threadCount);
        }catch (IllegalArgumentException e){
            System.out.println("threadCount is negative");
            return;
        }

        try {
            while ((str = in.readLine()) != null) {
                count++;
                arrayStr = str.split(" ");
                arrayInt = new int[arrayStr.length];
                for (int i = 0; i < arrayStr.length; i++) {
                    arrayInt[i] = Integer.parseInt(arrayStr[i]);
                }
                Runnable thread = new Worker(arrayInt, finalList);
                executor.execute(thread);
            }

            executor.shutdown();
            while (!executor.isTerminated())
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            if(finalList.size()==0){
                System.out.println("there is no data in a file");
                return;
            }

            int max = finalList.get(0);
            for (int element : finalList) {
                if (element > max) {
                    max = element;
                }
            }
            out.write(Integer.toString(max));
        } catch (IOException e) {
            System.out.println("Error in reading file");
        }

        finally {
            try {
                in.close();
            }catch (IOException e ){
                System.out.println("Cannot close input buffer");
            }
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("Cannot close output buffer");
            }
        }
    }

    public static void main(String[] args) throws IOException,InterruptedException{
        if (args.length < 2) {
            System.out.println("no filename and flow count in command line args");
        }
        else{
            Dispetcher disp = new Dispetcher(args[0], args[1]);
            disp.solve();
        }
    }
}
