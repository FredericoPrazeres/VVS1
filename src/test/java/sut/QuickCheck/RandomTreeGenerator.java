package sut.QuickCheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import sut.ArrayNTree;

import java.util.ArrayList;
import java.util.List;

public class RandomTreeGenerator extends Generator<ArrayNTree<Integer>> {


    public RandomTreeGenerator(Class<ArrayNTree<Integer>> type) {
        super(type);
    }

    @Override
    public ArrayNTree<Integer> generate(SourceOfRandomness random, GenerationStatus status) {


        int size = 1+random.nextInt(25); // 25 é o size
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            elements.add(random.nextInt(1000));
        }
        return new ArrayNTree<>(elements, 3);
    }

}
