package sut;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EdgePairCoverageTest {

    @Test
    public void edgePairCoverageTest1(){

        // 1-2-3-5-6-7-8-9-10

        List<Integer> list = Arrays.asList(1,2,4);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,2);

        assertFalse(nTree.equals(nTree2));
        assertFalse(nTree2.equals(nTree));
    }

    @Test
    public void edgePairCoverageTest2(){

        // Nós cobertos 1-2-3-5-6-7-8-9-11-13

        List<Integer> list = Arrays.asList(1,2,3,4);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);

        assertFalse(nTree.equals(nTree2));
        assertFalse(nTree2.equals(nTree));
    }

    @Test
    public void edgePairCoverageTest3(){

        // Nós cobertos 1-2-3-5-6-7-8-9-11-13

        List<Integer> list = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);

        assertTrue(nTree.equals(nTree2));
        assertTrue(nTree2.equals(nTree));
    }



}
