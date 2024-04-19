package sut;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class LogicBasedCoverageTest {

    @Test
    public void predicate1Test(){

        List<Integer> list = Arrays.asList(1, 2, 3,6,7,32,1,23,4,5);
        List<Integer> list2 = Arrays.asList(1, 2, 3,6,7,32,1,23,4,5);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,2);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertTrue(nTree2.equals(nTree));
        assertTrue(nTree.equals(nTree2));

    }

    @Test
    public void predicate3Test(){

        List<Integer> list = Arrays.asList(1, 2, 3,6,7,32,1,23,4,5);
        List<Integer> list2 = Arrays.asList(1,2,3,5,6,7,8,1,2,45,6,7);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,2);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree2.equals(nTree));
        assertFalse(nTree.equals(nTree2));

    }

    @Test
    public void predicate5Test(){

        List<Integer> list = Arrays.asList(1, 2, 3,6,7,32,1,23,4,5);
        List<Integer> list2 = Arrays.asList(1,2,45,6,7);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,2);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree2.equals(nTree));
        assertFalse(nTree.equals(nTree2));


    }

    @Test
    public void predicate6Test(){

        List<Integer> list = Arrays.asList(1, 31,6,7,23,4,5);
        List<Integer> list2 = Arrays.asList(1,12);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,2);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree2.equals(nTree));
        assertFalse(nTree.equals(nTree2));


    }

    @Test
    public void predicate7Test(){

        List<Integer> list = Arrays.asList(12,13,55,122,55,66);
        List<Integer> list2 = Arrays.asList(12,13,55,122,55,66);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertTrue(nTree2.equals(nTree));
        assertTrue(nTree.equals(nTree2));


    }

    @Test
    public void predicate8Test(){

        List<Integer> list = Arrays.asList(4,9,10,22);
        List<Integer> list2 = Arrays.asList(4,9,10,22,99);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree2.equals(nTree));
        assertFalse(nTree.equals(nTree2));


    }

}
