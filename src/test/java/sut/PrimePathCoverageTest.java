package sut;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrimePathCoverageTest {

    @Test
    public void primePathCoverageTest1(){

        List<Integer> list = Collections.singletonList(4);
        List<Integer> list2 = Collections.singletonList(3);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);

        assertFalse(nTree.equals(nTree2));
        assertFalse(nTree2.equals(nTree));


    }
    @Test
    public void primePathCoverageTest2(){

        List<Integer> list = Collections.emptyList();
        List<Integer> list2 = Collections.singletonList(3);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);

        assertFalse(nTree.equals(nTree2));
        assertFalse(nTree2.equals(nTree));


    }

    @Test
    public void primePathCoverageTest3(){

        ArrayNTree<Integer> nTree= new ArrayNTree<>(3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(3);

        assertTrue(nTree.equals(nTree2));
        assertTrue(nTree2.equals(nTree));


    }


}
