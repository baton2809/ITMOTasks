package lab1;
import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Artiom
 * Date: 26.03.14
 * Time: 18:47
 *
 * to tun use filename without an extension
 */
public class Main {
    FastIO io;

    public static void main(String[] args) throws IOException,ArgException {
        if (args.length < 1) {
            throw new ArgException("no filename in command line args");
        }
        new Main().run(args[0]);
    }

    /**
     * my own exception's class implemented to show my cum-savvy of using various ways of exception
     */
    static class ArgException extends Exception{
        private Exception _hidden;

        public ArgException(String er){
            super(er);
        }

        public ArgException(String er,Exception e){
            super(er);
            _hidden=e;
        }

        public Exception get_hidden(){
            return _hidden;
        }
    }

    public void run(String fileName) throws IOException,ArgException{
        try{
            io = new FastIO(fileName);
            solve();
        }
        catch (ArgException ex){
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            io.close();
        }
    }

    public void solve() throws ArgException{
        ArrayList<Integer> theArrayList = new ArrayList<Integer>();
        String line = io.nextLine();
        while(line!=null){
            String[] command = line.split(" ");
            if(command[0].equals("add")){
                int e;
                try{
                    //if(command[1]==null)throw new ArgException("no argument has founded");
                    e = Integer.parseInt(command[1]);
                } catch (IllegalArgumentException ex){
                    throw new ArgException("invalid or inappropriate argument");
                } catch (ArrayIndexOutOfBoundsException ex){
                    throw new ArgException("no argument has founded");
                }
                theArrayList.add(e);
                io.println("Element was appended into ArrayList: " + e);
            }
            if(command[0].equals("remove")){
                int e;
                try{
                    e = Integer.parseInt(command[1]);
                } catch (IllegalArgumentException ex){
                    throw new ArgException("invalid or inappropriate argument");
                } catch (ArrayIndexOutOfBoundsException ex){
                    throw new ArgException("no argument has founded");
                }
                io.println("Was deleted the element: "+theArrayList.get(e));
                theArrayList.remove(e);
            }
            if(command[0].equals("get")){
                int e;
                try{
                    e = Integer.parseInt(command[1]);
                } catch (IllegalArgumentException ex){
                    throw new ArgException("invalid or inappropriate argument");
                } catch (ArrayIndexOutOfBoundsException ex){
                    throw new ArgException("no argument has founded");
                }
                io.println("That element was gazed in ArrayList: "+theArrayList.get(e));
            }
            if(command[0].equals("size")){
                io.println("The size of ArrayList made/composed: "+theArrayList.size());
            }

            if(command[0].equals("iterator")){
                Iterator<Integer> iter = theArrayList.iterator();
                io.print("The For-Each Loop displayed all elements: ");
                while(iter.hasNext()){
                    io.print(iter.next()+" ");
                }
            io.println();
            }
            if(command[0].equals("isEmpty")){
                io.println("Whether the ArrayList is Empty: "+theArrayList.isEmpty());
            }
            line = io.nextLine();
        }
    }

    class FastIO extends PrintWriter{
        BufferedReader in;
        StringTokenizer stok;

        FastIO(){
            super(System.out);
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        FastIO(String s) throws FileNotFoundException{
            super("".equals(s)?"output.txt":s+".out");
            in = new BufferedReader(new FileReader("".equals(s)?"input.txt":s+".in"));
        }

        public void close(){
            super.close();
            try{
                in.close();
            } catch (IOException ignored){

            }
        }

        String next(){
            while(stok!=null || !stok.hasMoreTokens()){
                try{
                    stok = new StringTokenizer(in.readLine());
                } catch (Exception e){
                    return null;
                }
            }
            return stok.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            try {
                return in.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        String nextString(){
            return next().toString();
        }

        char[] nextCharArray() {
            return next().toCharArray();
        }
    }
}
