package edu.neu.coe.info6205.life.base;

import io.jenetics.*;
import io.jenetics.util.Factory;
import io.jenetics.engine.*;
import io.jenetics.util.IntRange;
import io.jenetics.util.RandomRegistry;

import java.util.Random;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;

public class GA {
    static int len = 0;
    static int min = 6;

    static long eval(Genotype<IntegerGene> gt) {
        Game.Behavior generations = Game.run(0L, transfer(gt));
        return generations.generation;
    }

    static void geneticAlgorithm(){
        while(len==0||len%2!=0) len = new Random().nextInt(10);

        Factory<Genotype<IntegerGene>> gtf =
                Genotype.of(IntegerChromosome.of(-8,8, len+min));
        Engine<IntegerGene, Long> engine = Engine
                .builder(GA::eval, gtf)
                .populationSize(500)
                .optimize(Optimize.MAXIMUM)
                .alterers(
                        new Mutator<>(0.3),
                        new SinglePointCrossover<>(0.3))
                .build();

        Genotype<IntegerGene> best = engine.stream()
                .limit(50)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("The Best is"+ best);

        Game.Behavior generations =  Game.run(0L, transfer(best), true);

        System.out.println("\n\n");
        System.out.println("the longest generation is " + generations);
    }


    static String transfer(Genotype<IntegerGene> gt){
        String s = "";
        s += gt.getChromosome().getGene(0).intValue();
        for (int i = 1; i < len+min; i++) {
            if (i % 2 == 0) s += ",";
            s += " ";
            s += gt.getChromosome().getGene(i).intValue();
        }
        return s;
    }

    //For Test
    static long geneticAlgorithm(boolean test){
        Factory<Genotype<IntegerGene>> gtf =
                Genotype.of(IntegerChromosome.of(-8,8, min));

        Engine<IntegerGene, Long> engine = Engine
                .builder(GA::eval, gtf)
                .populationSize(500)
                .optimize(Optimize.MAXIMUM)
                .alterers(
                        new Mutator<>(0.3),
                        new SinglePointCrossover<>(0.3))
                .build();

        Genotype<IntegerGene> best = engine.stream()
                .limit(50)
                .collect(EvolutionResult.toBestGenotype());

        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("The Best is"+ best);
        Game.Behavior generations = Game.run(0L, transfer(best), true);

        System.out.println("the longest generation is " + (generations.generation-1));

        return generations.generation-1;
    }
}

