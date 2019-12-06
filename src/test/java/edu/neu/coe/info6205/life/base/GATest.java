package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.life.base.GA;
import io.jenetics.Gene;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.util.Factory;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GATest {
    @Test
    public void testTransfer() {
        GA.min = 6;
        Factory<Genotype<IntegerGene>> gtf =
                Genotype.of(IntegerChromosome.of(0,0,6));
        Genotype<IntegerGene> g = gtf.newInstance();
        String result = "0 0, 0 0, 0 0";
        assertEquals(result, GA.transfer(g));
    }

    @Test
    public void testEval(){
        GA.min = 10;
        Factory<Genotype<IntegerGene>> gtf =
                Genotype.of(IntegerChromosome.of(-2,2, 10));
        Genotype<IntegerGene> g = gtf.newInstance();
        String test = GA.transfer(g);
        assertEquals(Game.run(0L, test).generation, GA.eval(g));
    }

    @Test
    public void testGA(){
        GA.min = 6;
        long result = 4L;
        long test = GA.geneticAlgorithm(true);
        assertEquals(result, test);
    }

}
