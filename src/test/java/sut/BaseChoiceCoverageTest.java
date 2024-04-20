package sut;


import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseChoiceCoverageTest {


    @Test
    public void test1(){

        List<Integer> list = Arrays.asList(455,144,22,11,66,638);
        List<Integer> list2 = Collections.emptyList();
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,2);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);

        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree.equals(nTree2));

    }

    @Test
    public void test2(){

        List<Integer> list = Collections.emptyList();
        List<Integer> list2 = Collections.emptyList();
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,2);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree.equals(nTree2));

    }



    @Test
    public void test3(){

        List<Integer> list = Arrays.asList(599,144,169,123,233,66);
        List<Integer> list2 = Arrays.asList(50,13,400,12,5,976);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,2);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree.equals(nTree2));

    }


    @Test
    public void test4(){

        List<Integer> list = Arrays.asList(599,144,169,123,233,66);
        List<Integer> list2 = Arrays.asList(50,144,599,12,5,169);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,2);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,3);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree.equals(nTree2));

    }

    @Test
    public void test5(){

        List<Integer> list = Arrays.asList(50,144,169,12,5,33);
        List<Integer> list2 = Arrays.asList(50,144,33,12,5,169);
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,5);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertTrue(nTree.equals(nTree2));

    }


    @Test
    public void test6(){

        List<Integer> list = Arrays.asList(50,144,169,12,5,33);
        List<Integer> list2 = Collections.emptyList();
        ArrayNTree<Integer> nTree= new ArrayNTree<>(list,3);
        ArrayNTree<Integer> nTree2 = new ArrayNTree<>(list2,5);
        System.out.println(nTree);
        System.out.println(nTree2);

        assertFalse(nTree.equals(nTree2));

    }


}
