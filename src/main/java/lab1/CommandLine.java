package lab1;
import java.io.*;
import java.util.Iterator;
import java.util.zip.DataFormatException;

/**
 * Created with IntelliJ IDEA.
 * User: Artiom
 * Date: 26.03.14
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
public class CommandLine {
    BufferedReader in;
    PrintWriter out;
    static final String FILE_NAME = "input";

    public static void main(String[] args) throws IOException, DataFormatException {
        if (args.length < 1) {
            throw new DataFormatException("no filename in command line args");
        }
        new CommandLine().run(args[0]);
    }

    void run(String fileName) throws IOException, DataFormatException {
        in = new BufferedReader(new FileReader(fileName+ ".in"));
        out = new PrintWriter(new File(fileName + ".out"));

        try {
            solve();
        } finally {
            try {
                in.close();
            } finally {
                if (out.checkError()) {
                    throw new IOException("error in output");
                }
                out.close();
            }
        }
    }

    public void solve() throws IOException,DataFormatException{
        ArrayList<Integer> theArrayList = new ArrayList<Integer>();
        String line = in.readLine();
        while(line!=null){
            String[] command = line.split(" ");
            if(command[0].equals("add")){
                int e;
                try{
                    //if(command[1]==null)throw new ArgException("no argument has founded");
                    e = Integer.parseInt(command[1]);
                } catch (IllegalArgumentException ex){
                    throw new IllegalArgumentException("invalid or inappropriate argument");
                } catch (ArrayIndexOutOfBoundsException ex){
                    throw new ArrayIndexOutOfBoundsException("no argument has founded");
                }
                theArrayList.add(e);
                out.println("Element was appended into ArrayList: " + e);
            }
            if(command[0].equals("remove")){
                int e;
                try{
                    e = Integer.parseInt(command[1]);
                } catch (IllegalArgumentException ex){
                    throw new IllegalArgumentException("invalid or inappropriate argument");
                } catch (ArrayIndexOutOfBoundsException ex){
                    throw new ArrayIndexOutOfBoundsException("no argument has founded");
                }
                out.println("Was deleted the element: "+theArrayList.get(e));
                theArrayList.remove(e);
            }
            if(command[0].equals("get")){
                int e;
                try{
                    e = Integer.parseInt(command[1]);
                } catch (IllegalArgumentException ex){
                    throw new IllegalArgumentException("invalid or inappropriate argument");
                } catch (ArrayIndexOutOfBoundsException ex){
                    throw new ArrayIndexOutOfBoundsException("no argument has founded");
                }
                out.println("That element was gazed in ArrayList: "+theArrayList.get(e));
            }
            if(command[0].equals("size")){
                out.println("The size of ArrayList made/composed: "+theArrayList.size());
            }

            if(command[0].equals("iterator")){
                Iterator<Integer> iter = theArrayList.iterator();
                out.print("The For-Each Loop displayed all elements: ");
                while(iter.hasNext()){
                    out.print(iter.next()+" ");
                }
                out.println();
            }
            if(command[0].equals("isEmpty")){
                out.println("Whether the ArrayList is Empty: "+theArrayList.isEmpty());
            }

            if(command[0].equals("clear")){
                theArrayList.clear();
                out.println("The ArrayList has been cleared: true");
            }
            line = in.readLine();
        }
    }
}