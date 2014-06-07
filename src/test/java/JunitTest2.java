import lab1.*;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.ListIterator;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Artiom
 * Date: 16.03.14
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */
public class JunitTest2 {
    ArrayList<Integer> theArrayLists;

    @Before
    public void setUp() throws Exception{
        theArrayLists = new ArrayList<Integer>();
    }

    @Test
    public void testHasNext() throws Exception{
        theArrayLists.add(1);
        ListIterator<Integer> iterator = theArrayLists.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testNextArrayEquals() throws Exception{
        theArrayLists.add(2);
        theArrayLists.add(3);
        Integer[] coa = { 2, 3 };
        Integer[] exp = new Integer[2];
        int t=0;
        ListIterator<Integer> iterator = theArrayLists.iterator();
        while (iterator.hasNext()) {
            exp[t]=iterator.next();
            t++;
        }
        assertArrayEquals(coa,exp);
    }

    @Test
    public void testNext() throws Exception{
        theArrayLists.add(2);
        theArrayLists.add(3);
        ListIterator<Integer> iterator = theArrayLists.iterator();
        assertEquals(2,(int)iterator.next());
        assertEquals(3, (int) iterator.next());
    }

    @Test
    public void testPrevious() throws Exception{
        theArrayLists.add(2);
        theArrayLists.add(3);
        theArrayLists.add(4);
        Integer[] coa = { 4, 3, 2 };
        Integer[] exp = new Integer[3];
        int t=0;
        ListIterator<Integer> iterator = theArrayLists.iterator();
        while (iterator.hasNext()) { iterator.next(); }
        while (iterator.hasPrevious()) {
            exp[t]=iterator.previous();
            t++;
        }
        assertArrayEquals(coa,exp);
    }

    @Test
    public void testHasPrevious() throws Exception{
        theArrayLists.add(4);
        theArrayLists.add(5);
        ListIterator<Integer> iterator = theArrayLists.iterator();
        assertFalse(iterator.hasPrevious());
        iterator.next(); iterator.next();
        assertEquals(5,(int)iterator.previous());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSet(){
        theArrayLists.add(4);
        ListIterator<Integer> iterator = theArrayLists.iterator();
        iterator.set(5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd(){
        theArrayLists.add(4);
        ListIterator<Integer> iterator = theArrayLists.iterator();
        iterator.add(5);
    }

    @Test(expected = EmptyStackException.class)
    public void testRemoveEmpty(){
        theArrayLists.remove(0);
    }
}
