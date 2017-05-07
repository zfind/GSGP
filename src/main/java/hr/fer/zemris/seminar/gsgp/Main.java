package hr.fer.zemris.seminar.gsgp;

import hr.fer.zemris.seminar.gsgp.dataset.BooleanDataset;
import hr.fer.zemris.seminar.gsgp.dataset.IDataset;
import hr.fer.zemris.seminar.gsgp.dataset.NumericDataset;
import hr.fer.zemris.seminar.gsgp.genetic.GeneticProgramming;
import hr.fer.zemris.seminar.gsgp.genetic.GeometricSemanticGP;
import hr.fer.zemris.seminar.gsgp.genetic.SemanticSolution;
import hr.fer.zemris.seminar.gsgp.genetic.Solution;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.BooleanCostFunction;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.IFitnessFunction;
import hr.fer.zemris.seminar.gsgp.genetic.fitness.RMSECostFunction;
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
import hr.fer.zemris.seminar.gsgp.tree.numeric.NumericNodeFactory;

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
        } else if (config.getDatasetType().equals("numeric")) {
            IDataset<Double> dataset = new NumericDataset(config.getDatasetFile());

            String[] varNames = config.getVariablesNames();
            TerminalNode<Double>[] terminals = TerminalNodeFactory.getTerminals(varNames);
            INodeFactory<Double> nodeFactory = new NumericNodeFactory(terminals);
            TreeFactory<Double> treeFactory = new TreeFactory<>(nodeFactory,
                    config.getTreeMaxDepth(), config.getTreeMaxNodeCount());

            IFitnessFunction<Double> fitnessFunction = null;
            if (config.getFitnessFunctionType().equals("RMSECostFunction")) {
                fitnessFunction = new RMSECostFunction(dataset);
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
                if (config.getCrossoverOperatorType().equals("SGXE")) {
                    crossoverOperator = new SGXE(treeFactory);
                }

                ISemanticMutationOperator mutationOperator = null;
                if (config.getMutationOperatorType().equals("SGMR")) {
                    mutationOperator = new SGMR(treeFactory);
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
    
}
