package sut;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class LineBranchCoverageTest {

    @Test
    public void BranchCoverageTest(){

        // T1

        List<Integer> listNull = null;
        List<Integer> listNull2 = null;
        assertEquals(listNull,listNull2);

        // T2

        List<Integer> list = Arrays.asList(39,10,22,34,55);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,2);

        assertNotEquals(nTree, nTree2);

        // T3

        List<Integer> list3 = Arrays.asList(1, 2, 3);
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list3,3);

        assertEquals(nTree2,nTree3);

        // T4
        ArrayNTree<Integer> nTree4 = new ArrayNTree<>(3);
        ArrayNTree<Integer> nTree5 = new ArrayNTree<>(3);

        //assertEquals(nTree4,nTree5); //NPE

        // T5

        assertNotEquals(nTree3,null);

        //

        assertEquals(nTree2,nTree2);


    }




}
