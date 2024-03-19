package sut;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LineBranchCoverageTest {

    @Test
    public void BranchCoverageTest(){

        // T1
        NTree<Integer> nTree = null;
        NTree<Integer> nTree2 = null;
        assertEquals(nTree,nTree2);

        // T2

        nTree = new ArrayNTree<>(4,5);
        nTree2 = new ArrayNTree<>(4,5);






    }


}
