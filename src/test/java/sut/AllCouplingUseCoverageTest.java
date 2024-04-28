package sut;
import static org.junit.Assert.*;
import org.junit.Test;
public class AllCouplingUseCoverageTest {


    /* Apagar elemento de uma árvore vazia

        Delete: 1-2

     */

    @Test
    public void testDeleteFromEmptyTree() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(3);
        tree.delete(5);
        assertTrue(tree.isEmpty());
    }

    /* Apagar elemento que não se encontra na árvore

        Delete: 1-3-4-5

     */
    @Test
    public void testDeleteElementNotPresent() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(3);
        tree.insert(1);
        tree.delete(5);
        assertEquals(1, tree.size());
        assertTrue(tree.contains(1));
    }

    /* Apagar elemento que é a raiz

        Delete: 1-3-6-9
        Propose: 1-2-3-5-8-9-10
        Compact: 1-2-3-4-5-6-7
    */

    @Test
    public void testDeleteRootElement() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(3);
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.delete(5);
        assertFalse(tree.contains(5));
        assertEquals(2, tree.size());
        assertEquals(Integer.valueOf(3), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());
    }

    /* Apagar elemento que não é a raiz

        Delete: 1-3-6-7-8
        Propose: 1-2-3-5-8-9-10
        Compact: 1-2-3-4-5-6-7

     */
    @Test
    public void testDeleteNonRootElement() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(3);
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.delete(3);
        assertFalse(tree.contains(3));
        assertEquals(2, tree.size());
        assertEquals(Integer.valueOf(5), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());
    }

    /* Apagar o único elemento

        Delete: 1-3-4-5

     */
    @Test
    public void testDeleteOnlyElement() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(3);
        tree.insert(5);
        tree.delete(5);
        assertTrue(tree.isEmpty());
    }

    /* Apagar um elemento múltiplas vezes

        Delete: 1-3-4-5

     */
    @Test
    public void testDeleteMultipleTimes() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(3);
        tree.insert(5);
        tree.delete(5);
        tree.delete(5);
        tree.delete(5);
        assertTrue(tree.isEmpty());
    }


    /* Apagar elementos numa árvore com capacidade máxima

        Delete: 1-3-6-9-10-12-14
        Propose: 1-2-3-5-8-9-10
        Compact: 1-2-3-5-6-7

     */
    @Test
    public void testDeleteMaxCapacity() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(2);
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(6);
        tree.delete(5);
        assertFalse(tree.contains(5));
        assertEquals(3, tree.size());
        assertEquals(Integer.valueOf(3), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());
    }

    /* Apagar elementos de uma árvore que com múltiplos níveis

        Delete: 1-3-6-7-8
        Propose: 1-2-3-5-8-9-10
        Compact: 1-2-3-5-6-
     */
    @Test
    public void testDeleteMultipleLevels() {
        ArrayNTree<Integer> tree = new ArrayNTree<>(2);
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(6);
        tree.insert(4);
        tree.delete(3);
        tree.delete(6);
        assertFalse(tree.contains(3));
        assertFalse(tree.contains(6));
        assertEquals(3, tree.size());
        assertEquals(Integer.valueOf(4), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());
    }


}
