import lab1.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created with IntelliJ IDEA.
 * User: Artiom
 * Date: 16.03.14
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */

public class JunitTest1 {
    ArrayList<Integer> theArrayLists;

    @Before
    public void setUp() throws Exception{
        theArrayLists = new ArrayList<Integer>();
    }

    @Test
    public void testAdd() throws Exception{
        assertTrue(theArrayLists.add(1));
    }

    @Test
    public void testGet() throws Exception{
        theArrayLists.add(2);
        assertEquals(2,(int)theArrayLists.get(0));
    }

    @Test
    public void testRemove() throws Exception{
        theArrayLists.add(3);
        assertEquals(3,(int)theArrayLists.remove(0));
    }

    @Test
    public void testRemoveObj() throws Exception{
        theArrayLists.add(4);
        theArrayLists.add(5);
        assertTrue(theArrayLists.remove((Integer) 4));
        assertFalse(theArrayLists.remove((Integer) 4));

    }

    @Test
    public void testSize() throws Exception{
        theArrayLists.add(4);
        theArrayLists.add(3);
        theArrayLists.add(2);
        theArrayLists.add(1);
        theArrayLists.remove(2);
        assertEquals(3,theArrayLists.size());
    }

    @Test
    public void testIsEmpty() throws Exception{
        assertTrue(theArrayLists.isEmpty());
        theArrayLists.add(6);
        assertFalse(theArrayLists.isEmpty());
    }

    @Test
    public void testClear() throws Exception{
        theArrayLists.add(7);
        theArrayLists.add(8);
        theArrayLists.clear();
        assertEquals(0,theArrayLists.size());
    }
}
