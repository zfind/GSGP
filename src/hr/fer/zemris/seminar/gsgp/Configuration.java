package hr.fer.zemris.seminar.gsgp;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zac on 06.05.17.
 */
public class Configuration {
    private Dataset dataset;
    private Variables variables;
    private Fitness fitness;
    private Crossover crossover;
    private Mutation mutation;
    private Reproduction reproduction;
    private Selection selection;
    private Population population;
    private Tree tree;
    private Algorithm algorithm;

    private Map<String, Object> config;

    public Configuration(File file) throws FileNotFoundException {
        InputStream input = new FileInputStream(file);
        Yaml yaml = new Yaml();
        Map<String, Object> object = (Map<String, Object>) yaml.load(input);
//        System.out.println(object);

        this.dataset = new Dataset(object);
        this.variables = new Variables(object);
        this.fitness = new Fitness(object);
        this.crossover = new Crossover(object);
        this.mutation = new Mutation(object);
        this.reproduction = new Reproduction(object);
        this.selection = new Selection(object);
        this.population = new Population(object);
        this.tree = new Tree(object);
        this.algorithm = new Algorithm(object);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Configuration config = new Configuration(new File("config/problem1.yaml"));
        StringBuilder sb = new StringBuilder();
        sb.append(config.getDatasetType());
        sb.append("\n");
        sb.append(config.getDatasetFile());
        sb.append("\n");
        sb.append(config.getVariablesSize());
        sb.append("\n");
        sb.append(Arrays.toString(config.getVariablesNames()));
        sb.append("\n");
        sb.append(config.getFitnessFunctionType());
        sb.append("\n");
        sb.append(config.getCrossoverOperatorType());
        sb.append("\n");
        sb.append(config.getCrossoverOperatorProbability());
        sb.append("\n");
        sb.append(config.getMutationOperatorType());
        sb.append("\n");
        sb.append(config.getMutationOperatorProbability());
        sb.append("\n");
        sb.append(config.getReproductionOperatorType());
        sb.append("\n");
        sb.append(config.getReproductionOperatorProbability());
        sb.append("\n");
        sb.append(config.getSelectionType());
        sb.append("\n");
        sb.append(config.getTournamentSelectionParameter());
        sb.append("\n");
        sb.append(config.getPopulationType());
        sb.append("\n");
        sb.append(config.getPopulationSize());
        sb.append("\n");
        sb.append(config.getInitialPopulationGeneratorType());
        sb.append("\n");
        sb.append(config.getInitialPopulationSize());
        sb.append("\n");
        sb.append(config.getInitialPopulationMinDepth());
        sb.append("\n");
        sb.append(config.getInitialPopulationMaxDepth());
        sb.append("\n");
        sb.append(config.getTreeMaxDepth());
        sb.append("\n");
        sb.append(config.getTreeMaxNodeCount());
        sb.append("\n");
        sb.append(config.getAlgorithmType());
        sb.append("\n");
        sb.append(config.getMaxGenerations());
        sb.append("\n");
        sb.append(config.getGoodFitnessThreshold());
        sb.append("\n");
        System.out.println(sb.toString());
    }

    public String getDatasetType() {
        return dataset.type;
    }

    public String getDatasetFile() {
        return dataset.filename;
    }

    public int getVariablesSize() {
        return variables.size;
    }

    public String[] getVariablesNames() {
        return variables.names;
    }

    public String getFitnessFunctionType() {
        return fitness.type;
    }

    public String getCrossoverOperatorType() {
        return crossover.type;
    }

    public double getCrossoverOperatorProbability() {
        return crossover.prob;
    }

    public String getMutationOperatorType() {
        return mutation.type;
    }

    public double getMutationOperatorProbability() {
        return mutation.prob;
    }

    public String getReproductionOperatorType() {
        return reproduction.type;
    }

    public double getReproductionOperatorProbability() {
        return reproduction.prob;
    }

    public String getSelectionType() {
        return selection.type;
    }

    public int getTournamentSelectionParameter() {
        return selection.tournamentSize;
    }

    public String getPopulationType() {
        return population.type;
    }

    public int getPopulationSize() {
        return population.size;
    }

    public String getInitialPopulationGeneratorType() {
        return population.initialGeneratorType;
    }

    public int getInitialPopulationSize() {
        return population.initialSize;
    }

    public int getInitialPopulationMinDepth() {
        return population.initialMinDepth;
    }

    public int getInitialPopulationMaxDepth() {
        return population.initialMaxDepth;
    }

    public int getTreeMaxNodeCount() {
        return tree.maxNodeCount;
    }

    public int getTreeMaxDepth() {
        return tree.maxDepth;
    }

    public String getAlgorithmType() {
        return algorithm.type;
    }

    public int getMaxGenerations() {
        return algorithm.maxGenerations;
    }

    public double getGoodFitnessThreshold() {
        return algorithm.stopFitness;
    }


    private class Dataset {
        String type;
        String filename;

        Dataset(Map<String, Object> config) {
            Map<String, String> dataset = (Map<String, String>) config.get("dataset");
            this.type = dataset.get("type");
            this.filename = dataset.get("file");
        }
    }

    private class Variables {
        int size;
        String[] names;

        Variables(Map<String, Object> config) {
            Map<String, Object> variables = (Map<String, Object>) config.get("variables");
            this.size = (int) variables.get("size");
            List<String> names = (List<String>) variables.get("names");
            this.names = new String[names.size()];
            for (int i = 0; i < names.size(); i++) {
                this.names[i] = names.get(i);
            }
        }
    }

    private class Fitness {
        String type;

        Fitness(Map<String, Object> config) {
            Map<String, Object> fitness = (Map<String, Object>) config.get("fitness");
            this.type = (String) fitness.get("type");
        }
    }

    private class Crossover {
        String type;
        double prob;

        Crossover(Map<String, Object> config) {
            Map<String, Object> crossover = (Map<String, Object>) config.get("crossover");
            this.type = (String) crossover.get("type");
            this.prob = (double) crossover.get("prob");
        }
    }

    private class Mutation {
        String type;
        double prob;

        Mutation(Map<String, Object> config) {
            Map<String, Object> mutation = (Map<String, Object>) config.get("mutation");
            this.type = (String) mutation.get("type");
            this.prob = (double) mutation.get("prob");
        }
    }

    private class Reproduction {
        String type;
        double prob;

        Reproduction(Map<String, Object> config) {
            Map<String, Object> reproduction = (Map<String, Object>) config.get("reproduction");
            this.type = (String) reproduction.get("type");
            this.prob = (double) reproduction.get("prob");
        }
    }

    private class Selection {
        String type;
        int tournamentSize;
//        Object param;

        Selection(Map<String, Object> config) {
            Map<String, Object> selection = (Map<String, Object>) config.get("selection");
            this.type = (String) selection.get("type");
            this.tournamentSize = (int) selection.get("tournament_size");
        }
    }

    private class Population {
        String type;
        int size;
        String initialGeneratorType;
        int initialSize;
        int initialMinDepth;
        int initialMaxDepth;

        Population(Map<String, Object> config) {
            Map<String, Object> population = (Map<String, Object>) config.get("population");
            this.type = (String) population.get("type");
            this.size = (int) population.get("size");
            Map<String, Object> initial = (Map<String, Object>) population.get("initial");
            this.initialGeneratorType = (String) initial.get("generator");
            this.initialSize = (int) initial.get("size");
            this.initialMinDepth = (int) initial.get("min_depth");
            this.initialMaxDepth = (int) initial.get("max_depth");
        }
    }

    private class Tree {
        int maxNodeCount;
        int maxDepth;

        Tree(Map<String, Object> config) {
            Map<String, Object> tree = (Map<String, Object>) config.get("tree");
            this.maxDepth = (int) tree.get("max_depth");
            this.maxNodeCount = (int) tree.get("max_node_count");
        }
    }

    private class Algorithm {
        String type;
        int maxGenerations;
        double stopFitness;

        Algorithm(Map<String, Object> config) {
            Map<String, Object> algorithm = (Map<String, Object>) config.get("algorithm");
            this.type = (String) algorithm.get("type");
            this.maxGenerations = (int) algorithm.get("generations");
            this.stopFitness = (double) algorithm.get("stop_fitness");
        }
    }
}
