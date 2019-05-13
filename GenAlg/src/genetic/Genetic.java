/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author xabie
 */
public class Genetic {
    private static final int SCR_X = 800;
    private static final int SCR_Y = 800;
    
    private static final int DELAY = 0;
    private static final int POPULATION = 20;

    private static final int PROBLEM_SIZE = 80;

    private static final int CROSSOVER_RATE = 95;
    private static final int MUTATION_RATE = 1;
    
    private ArrayList<Agent> population;
    private int generation;
    
    public void print() {
        int i = 0;
        System.out.println("Results for generation "+ generation);
        for (Agent a: population) {
            //System.out.println("A["+i+"]:"+a.getString()+" fitness: "+getFitness(a));
            i++;
        }
        //Result.print();
        
        System.out.println("Fittest was: " + getFittest().getString());
        System.out.println("AVG Fitness: " + avgFitness());
    }
    
    private double avgFitness() {
        int acc = 0;
        for (Agent a: population) {
            acc+=getFitness(a);
        }
        return acc/population.size();
    }
    
    public Agent tournament() {
        ArrayList<Agent> lotto = new ArrayList<>();
      
        for (Agent a: population) {
            for (int i = 0; i < getFitness(a); i++) lotto.add(a);
        }
        
        Random r = new Random();
        return lotto.get(r.nextInt(lotto.size()));
    }
    
    public void mutate(Agent a) {
        Random r = new Random();
        for (int i = 0; i < a.getBits().length; i++) {
            if (r.nextInt(a.getBits().length) == 0) a.mutate(i);
        }
    }
    
    public Agent crossover(Agent father, Agent mother) {
       
        int array[] = new int[Result.getSize()];
        Random r = new Random();
        
        for (int i = 0; i < Result.getSize(); i++) {
            if (r.nextInt(2) > 0) {
                array[i] = father.getBits()[i];
            } else {
                array[i] = mother.getBits()[i];
            }
        }
        
        Agent child = new Agent(array);
        return child;
    }
    
    public void evolve() {
        ArrayList<Agent> newp = new ArrayList<>();
        newp.add(getFittest());
        
        Random r = new Random();
        
        while (newp.size() < population.size()) {
            Agent father = tournament();
            Agent mother = tournament();
            
            if (r.nextInt(100-CROSSOVER_RATE)==0) {
                Agent child = crossover(father, mother);
                    if (r.nextInt(100-MUTATION_RATE)==0) {
                        mutate(child);
                    }               
                newp.add(child);
            }

        }
        
        population = newp;
        generation++;
    }
    
    public Agent getFittest() {
        Agent fittest = population.get(0);
        
        for (Agent a: population) {
            if (getFitness(a) > getFitness(fittest)) fittest = a;
        }
        
        return fittest;
    }
    
    public int getFitness(Agent a) {
        int fitness = 0;
        for (int i = 0; i < Result.getSize(); i++) {
            if (a.getBits()[i] == Result.getBits()[i]) fitness++;
        }
        return fitness;
    }
    
    public boolean hasCorrect() {
        for (Agent a: population) {
            if (Result.isCorrect(a.getBits())) return true;
        }
        return false;
    }
    
    public Genetic(int number) {
        population = new ArrayList<>();
        generation = 0;
        
        for (int i = 0; i < number; i++) {
            Agent a = new Agent(Result.getSize());
            population.add(a);
        }
    }
    
    public int getGeneration() {
        return generation;
    }
    
    public Agent getCorrect() {
        if (hasCorrect()) {
            for (Agent a: population) {
                if (Result.isCorrect(a.getBits())) return a;
            }          
        }
        return null;
    }
    
    public static void main(String[] args) {
        Result.newResult(PROBLEM_SIZE);

        Graph graph = new Graph(SCR_X, SCR_Y, PROBLEM_SIZE);
        JFrame frame = new JFrame("Alg");
        frame.setSize(SCR_X, SCR_Y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(graph);
        frame.setVisible(true);
        
        Genetic g = new Genetic(POPULATION);
        try {
            while (!g.hasCorrect()) {
                g.print();
                g.evolve();
                graph.update(g.avgFitness());

                Thread.sleep(DELAY);
            }
        } catch (InterruptedException ie) {}
        
        System.out.println("Generation "+g.getGeneration() + "has correct one");
        System.out.println(g.getCorrect().getString());
    }
    
}
