package hr.fer.zemris.seminar.gsgp;

import hr.fer.zemris.seminar.gsgp.dataset.BooleanDataset;
import hr.fer.zemris.seminar.gsgp.dataset.IDataset;
import hr.fer.zemris.seminar.gsgp.genetic.GeneticProgramming;
import hr.fer.zemris.seminar.gsgp.genetic.GeometricSemanticGP;
import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.BooleanCostFunction;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.genetic.initializer.RampedHalfInitializer;
import hr.fer.zemris.seminar.gsgp.genetic.initializer.SemanticRampedHalfInitializer;
import hr.fer.zemris.seminar.gsgp.genetic.operator.*;
import hr.fer.zemris.seminar.gsgp.genetic.selection.ISelection;
import hr.fer.zemris.seminar.gsgp.genetic.selection.ISemanticSelection;
import hr.fer.zemris.seminar.gsgp.genetic.selection.SemanticTournamentSelection;
import hr.fer.zemris.seminar.gsgp.genetic.selection.TournamentSelection;
import hr.fer.zemris.seminar.gsgp.tree.INodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.TerminalNode;
import hr.fer.zemris.seminar.gsgp.tree.TerminalNodeFactory;
import hr.fer.zemris.seminar.gsgp.tree.TreeFactory;
import hr.fer.zemris.seminar.gsgp.tree.bool.BoolNodeFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by zac on 28.04.17.
 */
public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Krivi zadatak");
            System.exit(-1);
        }

        Configuration config = null;
        try {
            config = new Configuration(new File(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("Konfiguracijska datoteka nije pronadena");
            System.exit(-1);
        }

//        IOptimizationAlgorithm algorithm;

        if (config.getDatasetType().equals("boolean")) {
            IDataset<Boolean> dataset = new BooleanDataset(config.getDatasetFile());

            String[] varNames = config.getVariablesNames();
            TerminalNode<Boolean>[] terminals = TerminalNodeFactory.getTerminals(varNames);
            INodeFactory<Boolean> nodeFactory = new BoolNodeFactory(terminals);
            TreeFactory<Boolean> treeFactory = new TreeFactory<>(nodeFactory,
                    config.getTreeMaxDepth(), config.getTreeMaxNodeCount());

            IFitnessFunction<Boolean> fitnessFunction = null;
            if (config.getFitnessFunctionType().equals("BooleanCostFunction")) {
                fitnessFunction = new BooleanCostFunction(dataset);
            }

            if (config.getAlgorithmType().equals("GP")) {

                ICrossoverOperator crossoverOperator = null;
                if (config.getCrossoverOperatorType().equals("SubtreeCrossoverOperator")) {
                    crossoverOperator = new SubtreeCrossoverOperator(treeFactory);
                }

                IMutationOperator mutationOperator = null;
                if (config.getMutationOperatorType().equals("SubtreeMutationOperator")) {
                    mutationOperator = new SubtreeMutationOperator(treeFactory, config.getTreeMaxDepth());
                }

                IReproductionOperator reproductionOperator = null;
                if (config.getReproductionOperatorType().equals("ReproductionOperator")) {
                    reproductionOperator = new ReproductionOperator();
                }

                ISelection selection = null;
                if (config.getSelectionType().equals("TournamentSelection")) {
                    selection = new TournamentSelection(config.getTournamentSelectionParameter());
                }

                List<Solution> initialPopulation = null;
                if (config.getInitialPopulationGeneratorType().equals("RampedHalfInitializer")) {
                    initialPopulation = RampedHalfInitializer.getInitPopulation(
                            config.getInitialPopulationSize(),
                            config.getInitialPopulationMinDepth(),
                            config.getInitialPopulationMaxDepth(),
                            fitnessFunction, treeFactory);
                }
                GeneticProgramming algorithm = new GeneticProgramming(fitnessFunction,
                        crossoverOperator, mutationOperator, reproductionOperator, selection,
                        initialPopulation,
                        config.getCrossoverOperatorProbability(), config.getMutationOperatorProbability(),
                        config.getReproductionOperatorProbability(), config.getPopulationSize(),
                        config.getMaxGenerations(), config.getGoodFitnessThreshold());

                Solution solution = algorithm.run();

                System.out.println(solution.getCost() + " " + solution.getTree().getNodeCount());
                System.out.println(solution.getTree().toString());

            } else if (config.getAlgorithmType().equals("GSGP")) {
                ISemanticCrossoverOperator crossoverOperator = null;
                if (config.getCrossoverOperatorType().equals("SGXB")) {
                    crossoverOperator = new SGXB(treeFactory);
                }

                ISemanticMutationOperator mutationOperator = null;
                if (config.getMutationOperatorType().equals("SGMB")) {
                    mutationOperator = new SGMB(treeFactory);
                }

//                ISemanticReproductionOperator reproductionOperator = null;
//                if (config.getReproductionOperatorType().equals("ReproductionOperator")) {
//                    reproductionOperator = new SemanticReproductionOperator();
//                }

                ISemanticSelection selection = null;
                if (config.getSelectionType().equals("SemanticTournamentSelection")) {
                    selection = new SemanticTournamentSelection(config.getTournamentSelectionParameter());
                }

                List<SemanticSolution> initialPopulation = null;
                if (config.getInitialPopulationGeneratorType().equals("SemanticRampedHalfInitializer")) {
                    initialPopulation = SemanticRampedHalfInitializer.getInitPopulation(
                            config.getInitialPopulationSize(),
                            config.getInitialPopulationMinDepth(),
                            config.getInitialPopulationMaxDepth(),
                            fitnessFunction, treeFactory);
                }
                GeometricSemanticGP algorithm = new GeometricSemanticGP(fitnessFunction,
                        crossoverOperator, mutationOperator,
//                        reproductionOperator,
                        selection,
                        initialPopulation,
                        config.getCrossoverOperatorProbability(), config.getMutationOperatorProbability(),
                        config.getReproductionOperatorProbability(), config.getPopulationSize(),
                        config.getMaxGenerations(), config.getGoodFitnessThreshold());

                SemanticSolution solution = algorithm.run();

                System.out.println(solution.getCost() + " " + solution.getTree().getNodeCount());
                System.out.println(solution.getTree().toString());
            }
        }

    }

//    public static void problem1() {
//        IDataset<Boolean> dataset = new BooleanDataset("data/f2.txt");
//
//        String[] varNames = new String[]{"a", "b", "c", "d", "e"};
//        TerminalNode<Boolean>[] terminals = TerminalNodeFactory.getTerminals(varNames);
//        INodeFactory<Boolean> nodeFactory = new BoolNodeFactory(terminals);
//        TreeFactory<Boolean> treeFactory = new TreeFactory<>(nodeFactory);
//
//        IFitnessFunction<Boolean> fitnessFunction = new BooleanCostFunction(dataset);
//        ICrossoverOperator crossoverOperator = new SubtreeCrossoverOperator(treeFactory);
//        IMutationOperator mutationOperator = new SubtreeMutationOperator(treeFactory);
//        IReproductionOperator reproductionOperator = new ReproductionOperator();
//        ISelection selection = new TournamentSelection(10);
//
//        List<Solution> initialPopulation = RampedHalfInitializer.getInitPopulation(
//                500, 2, 6,
//                fitnessFunction, treeFactory);
//
//        GeneticProgramming alg = new GeneticProgramming(fitnessFunction,
//                crossoverOperator, mutationOperator, reproductionOperator, selection,
//                initialPopulation);
//
//        Solution solution = alg.run();
//
//        System.out.println(solution.getCost() + " " + solution.getTree().getNodeCount());
//        System.out.println(solution.getTree().toString());
//    }
//
//    public static void problem2() {
//        IDataset<Double> dataset = new NumericDataset("data/n1.txt");
//
//        String[] varNames = new String[]{"x"};
//        TerminalNode<Double>[] terminals = TerminalNodeFactory.getTerminals(varNames);
//        INodeFactory<Double> nodeFactory = new NumericNodeFactory(terminals);
//        TreeFactory<Double> treeFactory = new TreeFactory<>(nodeFactory);
//
//        IFitnessFunction<Double> fitnessFunction = new RMSECostFunction(dataset);
//        ICrossoverOperator crossoverOperator = new SubtreeCrossoverOperator(treeFactory);
//        IMutationOperator mutationOperator = new SubtreeMutationOperator(treeFactory);
//        IReproductionOperator reproductionOperator = new ReproductionOperator();
//        ISelection selection = new TournamentSelection();
//
//        List<Solution> initialPopulation = RampedHalfInitializer.getInitPopulation(
//                500, 2, 6,
//                fitnessFunction, treeFactory);
//
//        GeneticProgramming alg = new GeneticProgramming(fitnessFunction,
//                crossoverOperator, mutationOperator, reproductionOperator, selection,
//                initialPopulation);
//
//        Solution solution = alg.run();
//
//        System.out.println(solution.getCost() + " " + solution.getTree().getNodeCount());
//        System.out.println(solution.getTree().toString());
//    }
//
//    public static void test2() {
//        IDataset<Double> dataset = new NumericDataset("data/n1.txt");
//
//        String[] varNames = new String[]{"x"};
//        TerminalNode<Double>[] terminals = TerminalNodeFactory.getTerminals(varNames);
//        INodeFactory<Double> nodeFactory = new NumericNodeFactory(terminals);
//        TreeFactory<Double> treeFactory = new TreeFactory<>(nodeFactory);
//
//        IFitnessFunction<Double> fitnessFunction = new RMSECostFunction(dataset);
//
//        Double[] input = new Double[]{new Double(-1.)};
//        Node<Double> x_mul_x = new MultiplicationNode(
//                new Node[]{
//                        nodeFactory.getTermNodeInstance(0),
//                        nodeFactory.getTermNodeInstance(0),
//                });
//        System.out.println(x_mul_x.evaluate(input));
//        Node<Double> one = new ProtectedDivisionNode(
//                new Node[]{
//                        nodeFactory.getTermNodeInstance(0),
//                        nodeFactory.getTermNodeInstance(0),
//                });
//        System.out.println(one.evaluate(input));
//        Node<Double> x_plus_one = new AdditionNode(
//                new Node[]{
//                        nodeFactory.getTermNodeInstance(0),
//                        one
//                });
//        System.out.println(x_plus_one.evaluate(input));
//        Node<Double> root = new AdditionNode(
//                new Node[]{
//                        x_mul_x,
//                        x_plus_one
//                });
//
//        System.out.println(root.evaluate(input));
//
//        Double[] output = new Double[dataset.size()];
//        for (int i = 0; i < dataset.size(); i++) {
////            childrenOutputVector[i] = expression.evaluate(Boolean.class, dataset.getInput(i));
//            output[i] = root.evaluate(dataset.getInput(i));
//            double real = dataset.getOutput(i);
//            System.out.println(dataset.getInput(i)[0] + "\t" + output[i] + "\t" + real
//                    + (output[i] == real ? "" : " x"));
//        }
//
//        System.out.println(fitnessFunction.evaluate(root));
//    }
//
//    public static void problem3() {
//        IDataset<Boolean> dataset = new BooleanDataset("data/f2.txt");
//
//        String[] varNames = new String[]{"a", "b", "c", "d", "e"};
//        TerminalNode<Boolean>[] terminals = TerminalNodeFactory.getTerminals(varNames);
//        INodeFactory<Boolean> nodeFactory = new BoolNodeFactory(terminals);
//        TreeFactory<Boolean> treeFactory = new TreeFactory<>(nodeFactory);
//
//        IFitnessFunction<Boolean> fitnessFunction = new BooleanCostFunction(dataset);
//        ISemanticCrossoverOperator crossoverOperator = new SGXB(treeFactory);
//        ISemanticMutationOperator mutationOperator = new SGMB(treeFactory);
//        ISemanticSelection selection = new SemanticTournamentSelection();
//
//        List<SemanticSolution> initialPopulation = SemanticRampedHalfInitializer.getInitPopulation(
//                500, 2, 6,
//                fitnessFunction, treeFactory);
////        Collections.sort(initialPopulation);
////        initialPopulation = initialPopulation.subList(500, 1000);
//
//        GeometricSemanticGP alg = new GeometricSemanticGP(fitnessFunction,
//                crossoverOperator, mutationOperator, selection,
//                initialPopulation);
//
//        SemanticSolution solution = alg.run();
//        Node<Boolean> tree = solution.getTree();
//        double cost = fitnessFunction.evaluate(tree);
//
//        System.out.println(solution.getCost() + " " + cost + " " + solution.getTree().getNodeCount());
//    }
//
//    public static void problem4() {
//        IDataset<Double> dataset = new NumericDataset("data/n1.txt");
//
//        String[] varNames = new String[]{"x"};
//        TerminalNode<Double>[] terminals = TerminalNodeFactory.getTerminals(varNames);
//        INodeFactory<Double> nodeFactory = new NumericNodeFactory(terminals);
//        TreeFactory<Double> treeFactory = new TreeFactory<>(nodeFactory);
//
//        IFitnessFunction<Double> fitnessFunction = new RMSECostFunction(dataset);
//        ISemanticCrossoverOperator crossoverOperator = new SGXE(treeFactory);
//        ISemanticMutationOperator mutationOperator = new SGMR(treeFactory);
//        ISemanticSelection selection = new SemanticTournamentSelection();
//
//        List<SemanticSolution> initialPopulation = SemanticRampedHalfInitializer.getInitPopulation(
//                500, 2, 6,
//                fitnessFunction, treeFactory);
//
//        GeometricSemanticGP alg = new GeometricSemanticGP(fitnessFunction,
//                crossoverOperator, mutationOperator, selection,
//                initialPopulation);
//
//        SemanticSolution solution = alg.run();
//        Node<Double> tree = solution.getTree();
//        double cost = fitnessFunction.evaluate(tree);
//
//        System.out.println(solution.getCost() + " " + cost + " " + solution.getTree().getNodeCount());
//    }
//
//    public static void main(String[] args) {
//
//        if (args.length != 1) {
//            System.out.println("Krivi zadatak");
//            System.exit(-1);
//        }
//
//        int taskId = Integer.parseInt(args[0]);
//        if (taskId == 1) {
//            problem1();
//        } else if (taskId == 2) {
//            problem2();
//        } else if (taskId == 3) {
//            problem3();
//        } else if (taskId == 4) {
//            problem4();
//        } else {
//            System.out.println("Krivi broj zadatka");
//            System.exit(-1);
//        }
//
////        test2();
//
//    }

}
