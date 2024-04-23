package sut.QuickCheck;


import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;
import sut.ArrayNTree;

import java.util.Collections;
import java.util.List;


import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class TreeQuickCheckTest {

    @Property(trials = 20)
    public void propertyShufflingDoesNotBreakInvariants(@From(RandomTreeGenerator.class) ArrayNTree<Integer> tree) {
        List<Integer> shuffled = tree.toList();
        Collections.shuffle(shuffled);
        ArrayNTree<Integer> shuffledTree = new ArrayNTree<>(shuffled, 3);
        assertEquals(tree.size(), shuffledTree.size());
        assertEquals(tree.height(), shuffledTree.height());
        assertEquals(tree, shuffledTree);
        assertEquals(tree.countLeaves(), shuffledTree.countLeaves());


    }

    @Property(trials = 20)
    public void propertyRemoveAllElementsTreeIsEmpty(@From(RandomTreeGenerator.class) ArrayNTree<Integer> tree) {
        tree.toList().forEach(tree::delete);
        assertTrue(tree.isEmpty());
    }

    @Property(trials = 20)
    public void propertyInsertingThenRemovingSameElementDoesNotModifyOtherElements(@From(RandomTreeGenerator.class) ArrayNTree<Integer> tree) {

        ArrayNTree<Integer> clonedTree = tree.clone();
        int  el = 1004;
        clonedTree.insert(el);
        clonedTree.delete(el);
        assertEquals(clonedTree, tree);
    }

    @Property(trials = 20)
    public void propertyInsertingAllElementsAgainProducesNoEffect(@From(RandomTreeGenerator.class) ArrayNTree<Integer> tree) {
        ArrayNTree<Integer> clonedTree = tree.clone();
        tree.toList().forEach(clonedTree::insert);
        assertEquals(tree, clonedTree);
    }

    @Property(trials = 20)
    public void propertyInsertingAnElementAlreadyThereSeveralTimesOverProducesNoEffect(
            @From(RandomTreeGenerator.class) ArrayNTree<Integer> tree, @From(RandomTreeGenerator.class) ArrayNTree<Integer> t) {

        ArrayNTree<Integer> newTree = tree.clone();
        int el = 1005;
        newTree.insert(el);

        tree.insert(el);
        tree.insert(el);
        tree.insert(el);
        assertEquals(newTree, tree);
    }
}