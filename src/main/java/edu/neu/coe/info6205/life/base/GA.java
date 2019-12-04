package edu.neu.coe.info6205.life.base;

import io.jenetics.*;
import io.jenetics.util.Factory;
import io.jenetics.engine.*;
import java.util.Random;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;

public class GA {
    static int len = 0;

    static long eval(Genotype<IntegerGene> gt) {
        Game.Behavior generations = Game.run(0L, transfer(gt));
        return generations.generation;
    }

    static void geneticAlgorithm(){
        while(len==0||len%2!=0) len = new Random().nextInt(4);

        Factory<Genotype<IntegerGene>> gtf =
                Genotype.of(IntegerChromosome.of(-8,8, len+6));

        Engine<IntegerGene, Long> engine = Engine
                .builder(GA::eval, gtf)
                .populationSize(500)
                .optimize(Optimize.MAXIMUM)
                .alterers(
                        new Mutator<>(0.3),
                        new SinglePointCrossover<>(0.3))
                .build();

        Phenotype<IntegerGene, Long> best = engine.stream()
                .limit(50)
                .collect(toBestPhenotype());

        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("The Best is"+ best);
        Genotype<IntegerGene> result = best.getGenotype();
        Game.Behavior generations = Game.run(0L, transfer(result), true);

        System.out.println("the longest generation is " + generations.generation);
    }

    static String transfer(Genotype<IntegerGene> gt){
        String s = "";
        s += gt.getChromosome().getGene(0).intValue();
        for (int i = 1; i < len+6; i++) {
            if (i % 2 == 0) s += ",";
            s += " ";
            s += gt.getChromosome().getGene(i).intValue();
        }
        return s;
    }
/*    static int eval(Genotype<IntegerGene> gt) {
        return gt.getChromosome()
                .as(IntegerChromosome.class)
                .intValue();
    }

    static long fitness(final String pattern) {
        final Game.Behavior generations = Game.run(0L, pattern);
        return generations.generation;
    }
    static String generateString(){
        int len = 0;
        while(len==0||len%2!=0) len = new Random().nextInt(40);
        Factory<Genotype<IntegerGene>> gtf =
                Genotype.of(IntegerChromosome.of(-4,5,len));
        Engine<IntegerGene, Integer> engine = Engine
                .builder(GA::eval, gtf)
                .populationSize(500)
                .optimize(Optimize.MAXIMUM)
                .alterers(
                        new Mutator<>(0.03),
                        new MeanAlterer<>(0.6))
                .build();
        Genotype<IntegerGene> result = engine.stream()
                .limit(100)
                .collect(EvolutionResult.toBestGenotype());
        String s = "";
        s += result.getChromosome().getGene(0).intValue();
        for (int i = 1; i < len; i++) {
            if (i % 2 == 0) s += ",";
            s += " ";
            s += result.getChromosome().getGene(i).intValue();
        }
        return s;
    }*/

}

