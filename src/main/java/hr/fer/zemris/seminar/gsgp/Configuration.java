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
        this.algorithm = new Algorithm(object);
        this.variables = new Variables(object);
        this.fitness = new Fitness(object);
        this.crossover = new Crossover(object);
        this.mutation = new Mutation(object);
        this.reproduction = new Reproduction(object);
        this.selection = new Selection(object);
        this.population = new Population(object);
        this.tree = new Tree(object);
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
//        sb.append(config.getPopulationType());
//        sb.append("\n");
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
        return dataset.trainFilename;
    }

    public String getTestDatasetFile() {
        return dataset.testFilename;
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

//    public String getPopulationType() {
//        return population.type;
//    }

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


    private static void error(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

    private class Dataset {
        String type;
        String trainFilename;
        String testFilename;

        Dataset(Map<String, Object> config) {
            Map<String, String> dataset = (Map<String, String>) config.get("dataset");
            this.type = dataset.get("type");
            if (type == null || !(type.equals("boolean") || type.equals("numeric"))) {
                error("Dataset mora imati definiran tip: {boolean, numeric}");
            }
            this.trainFilename = dataset.get("file");
            if (trainFilename == null) {
                error("Dataset mora imati definiranu datoteku za treniranje");
            }
            this.testFilename = dataset.get("test");
        }
    }

    private class Variables {
        int size;
        String[] names;

        Variables(Map<String, Object> config) {
            Map<String, Object> variables = (Map<String, Object>) config.get("variables");
            this.size = (int) variables.get("size");
            if (size < 1) {
                error("Broj varijabli mora biti najmanje 1");
            }
            List<String> names = (List<String>) variables.get("names");
            if (names != null) {
                this.names = new String[names.size()];
                for (int i = 0; i < names.size(); i++) {
                    this.names[i] = names.get(i);
                }
            }
        }
    }

    private class Fitness {
        String type;

        Fitness(Map<String, Object> config) {
            Map<String, Object> fitness = (Map<String, Object>) config.get("fitness");
            this.type = (String) fitness.get("type");
            if (type == null) {
                error("Funkcija dobrote mora imati definiran tip");
            }
            if (getDatasetType().equals("boolean") && !(type.equals("BooleanCostFunction"))) {
                error("Funkcija dobrote za boolean dataset mora imati tip: {BooleanCostFunction}");
            } else if (getDatasetType().equals("numeric") && !(type.equals("RMSECostFunction"))) {
                error("Funkcija dobrote za numeric dataset mora imati tip: {RMSECostFunction}");
            }
        }
    }

    private class Crossover {
        String type;
        double prob;

        Crossover(Map<String, Object> config) {
            Map<String, Object> crossover = (Map<String, Object>) config.get("crossover");
            this.type = (String) crossover.get("type");
            if (getAlgorithmType().equals("GP")
                    && !(type.equals("SubtreeCrossoverOperator"))) {
                error("Operator krizanja za GP algoritam mora imati tip: {SubtreeCrossoverOperator}");
            } else if (getAlgorithmType().equals("GSGP")) {
                if (getDatasetType().equals("boolean") && ! (type.equals("SGXB"))) {
                    error("Operator krizanja za boolean dataset i GSGP algoritam mora imati tip: {SGXB}");
                } else if (getDatasetType().equals("numeric") && !(type.equals("SGXE"))) {
                    error("Operator krizanja za numeric dataset i GSGP algoritam mora imati tip: {SGXE}");
                }
            }
            if (crossover.get("prob") == null) {
                error("Operator krizanja mora imati definiranu vjerojatnost");
            }
            this.prob = (double) crossover.get("prob");
            if (prob < 0. || prob > 1.) {
                error("Operator krizanja mora imati vjerojatnost izmedu 0. i 1.");
            }
        }
    }

    private class Mutation {
        String type;
        double prob;

        Mutation(Map<String, Object> config) {
            Map<String, Object> mutation = (Map<String, Object>) config.get("mutation");
            this.type = (String) mutation.get("type");
            if (getAlgorithmType().equals("GP")
                    && !(type.equals("SubtreeMutationOperator") || type.equals("ShuffleChildrenMutationOperator")
                            || type.equals("SwapWithSameArityMutationOperator"))) {
                error("Operator mutacije za GP algoritam mora imati tip: {SubtreeMutationOperator, ShuffleChildrenMutationOperator, SwapWithSameArityMutationOperator}");
            } else if (getAlgorithmType().equals("GSGP")) {
                if (getDatasetType().equals("boolean") && ! (type.equals("SGMB"))) {
                    error("Operator mutacije za boolean dataset i GSGP algoritam mora imati tip: {SGMB}");
                } else if (getDatasetType().equals("numeric") && !(type.equals("SGMR"))) {
                    error("Operator mutacije za numeric dataset i GSGP algoritam mora imati tip: {SGMR}");
                }
            }
            if (mutation.get("prob") == null) {
                error("Operator mutacije mora imati definiranu vjerojatnost");
            }
            this.prob = (double) mutation.get("prob");
            if (prob < 0. || prob > 1.) {
                error("Operator mutacije mora imati vjerojatnost izmedu 0. i 1.");
            }
        }
    }

    private class Reproduction {
        String type;
        double prob;

        Reproduction(Map<String, Object> config) {
            Map<String, Object> reproduction = (Map<String, Object>) config.get("reproduction");
            this.type = (String) reproduction.get("type");
            if (getAlgorithmType().equals("GP") && ! (type.equals("ReproductionOperator"))) {
                error("Operator reprodukcije za GP algoritam mora imati definiran tip: {ReproductionOperator}");
            } else if (getAlgorithmType().equals("GSGP")&& !(type.equals("SemanticReproductionOperator"))) {
                error("Operator reprodukcije za GSGP algoritam mora imati definiran tip: {SemanticReproductionOperator");
            }
            if (reproduction.get("prob") == null) {
                error("Operator reprodukcije mora imati definiranu vjerojatnost");
            }
            this.prob = (double) reproduction.get("prob");
            if (prob < 0. || prob > 1.) {
                error("Operator reprodukcije mora imati vjerojatnost izmedu 0. i 1.");
            }
        }
    }

    private class Selection {
        String type;
        int tournamentSize;
//        Object param;

        Selection(Map<String, Object> config) {
            Map<String, Object> selection = (Map<String, Object>) config.get("selection");
            this.type = (String) selection.get("type");
            if (type == null ) {
                error("Operator selekcije mora imati definiran tip");
            }
            if (getAlgorithmType().equals("GP") && ! (type.equals("TournamentSelection"))) {
                error("Operator selekcije za GP algoritam mora imati definiran tip: {TournamentSelection}");
            } else if (getAlgorithmType().equals("GSGP") && !(type.equals("SemanticTournamentSelection"))) {
                error("Operator selekcije za GSGP algoritam mora imati definiran tip: {SemanticTournamentSelection}");
            }
            if (type.contains("Tournament") && selection.get("tournament_size") == null) {
                error("Operator turnirske selekcije mora imati definiranu velicinu turnira");
            }
            this.tournamentSize = (int) selection.get("tournament_size");
        }
    }

    private class Population {
//        String type;
        int size;
        String initialGeneratorType;
        int initialSize;
        int initialMinDepth;
        int initialMaxDepth;

        Population(Map<String, Object> config) {
            Map<String, Object> population = (Map<String, Object>) config.get("population");
//            this.type = (String) population.get("type");
            if (population.get("size") == null) {
                error("Populacija mora imati definiranu velicinu");
            }
            this.size = (int) population.get("size");
            Map<String, Object> initial = (Map<String, Object>) population.get("initial");
            this.initialGeneratorType = (String) initial.get("generator");
            if (initialGeneratorType == null) {
                error("Populacija mora imati definiran generator pocetne populacije");
            }
            if (getAlgorithmType().equals("GP") && !(initialGeneratorType.equals("RampedHalfInitializer"))) {
                error("Generator pocetne populacije za GP algoritam mora imati definiran tip: {RampedHalfInitializer}");
            } else if (getAlgorithmType().equals("GSGP") && !(initialGeneratorType.equals("SemanticRampedHalfInitializer"))) {
                error("Generator pocetne populacije za GSGP algoritam mora imati definiran tip: {SemanticRampedHalfInitializer}");
            }
            if (initial.get("size") == null) {
                error("Generator pocetne populacije mora imati definiranu velicinu pocetne populacije");
            }
            this.initialSize = (int) initial.get("size");
            if (initial.get("min_depth") == null || initial.get("max_depth") == null) {
                error("Generator pocetne populacije mora imati definiranu minimalnu i maksimalnu dubinu stabla");
            }
            this.initialMinDepth = (int) initial.get("min_depth");
            this.initialMaxDepth = (int) initial.get("max_depth");
        }
    }

    private class Tree {
        int maxNodeCount;
        int maxDepth;

        Tree(Map<String, Object> config) {
            Map<String, Object> tree = (Map<String, Object>) config.get("tree");
            if (getAlgorithmType().equals("GP") && (tree.get("max_depth") == null || tree.get("max_node_count") == null)) {
                error("Algoritam GP mora imati maksimalnu dubinu stabla i maksimalnu velicinu cvorova u stablu");
            }
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
            if (type == null || !(type.equals("GP") || type.equals("GSGP"))) {
                error("Algoritam mora imati definiran tip: {GP, GSGP}");
            }
            if (algorithm.get("generations") == null || algorithm.get("stop_fitness") == null) {
                error("Algoritam mora imati definiran maksimalni broj generacija i vrijednost zadovoljavajuce dobrote");
            }
            this.maxGenerations = (int) algorithm.get("generations");
            this.stopFitness = (double) algorithm.get("stop_fitness");
        }
    }
}
