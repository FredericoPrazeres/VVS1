package sut;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainsLineBranchCoverageTest {

    @Test
    public void containsLineBranchCoverageTest1(){

        List<Integer> list = Collections.emptyList();
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list,3);

        assertFalse(nTree3.contains(2));

    }

    @Test
    public void containsLineBranchCoverageTest2(){

        List<Integer> list = Arrays.asList(1, 2, 3);
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list,3);

        System.out.println(nTree3);
        System.out.println("Teste começou");

        assertTrue(nTree3.contains(1));

    }

    @Test
    public void containsLineBranchCoverageTest3(){

        List<Integer> list = Collections.singletonList(1);
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list,3);
        System.out.println(nTree3);
        System.out.println("Teste começou");

        assertFalse(nTree3.contains(2));

    }

    @Test
    public void containsLineBranchCoverageTest4(){

        List<Integer> list = Arrays.asList(1, 2, 3,49,12,34,55,77,13,67);
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list,2);
        System.out.println(nTree3);
        System.out.println("Teste começou");

        assertTrue(nTree3.contains(34));

    }

    @Test
    public void containsLineBranchCoverageTest5(){

        List<Integer> list = Arrays.asList(1, 2, 3,49,12,34,55,77,13,67);
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list,3);
        System.out.println(nTree3);
        System.out.println("Teste começou");

        assertFalse(nTree3.contains(78));

    }


    @Test
    public void containsLineBranchCoverageTest6(){

        List<Integer> list = Arrays.asList(1, 2, 34,55,77,13,67);
        ArrayNTree<Integer> nTree3 = new ArrayNTree<>(list,3);
        System.out.println(nTree3);
        System.out.println("Teste começou");

        assertThrows(NullPointerException.class, () -> nTree3.contains(null));

    }



}
