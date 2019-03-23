package thiagodnf.doupr.gui.util;

import thiagodnf.doupr.gui.sys.PreferencesManager;
import thiagodnf.doupr.gui.util.constants.PreferencesConstants;

import java.util.ArrayList;
import java.util.List;

public class PreferencesUtils {

    public static int getPopulationSize() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.POPULATION_SIZE, 10);
    }

    public static void setPolulationSize(int populationSize) {
        PreferencesManager.getInstance().save(PreferencesConstants.POPULATION_SIZE, populationSize);
    }

    public static String getAlgorithm() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.ALGORITHM, "NSGA-II");
    }

    public static void setAlgorithm(String algorithm) {
        PreferencesManager.getInstance().save(PreferencesConstants.ALGORITHM, algorithm);
    }

    public static int getMaxEvaluation() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.MAX_EVALUTION, 100);
    }

    public static void setMaxEvaluation(int maxEvaluation) {
        PreferencesManager.getInstance().save(PreferencesConstants.MAX_EVALUTION, maxEvaluation);
    }

    public static int getMinRefactorings() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.MIN_REFACTORINGS, 2);
    }

    public static void setMinRefactorings(int minRefactorings) {
        PreferencesManager.getInstance().save(PreferencesConstants.MIN_REFACTORINGS, minRefactorings);
    }

    public static int getMaxRefactorings() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.MAX_REFACTORINGS, 10);
    }

    public static void setMaxRefactorings(int maxRefactorings) {
        PreferencesManager.getInstance().save(PreferencesConstants.MAX_REFACTORINGS, maxRefactorings);
    }

    public static double getCrossoverProbability() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.CROSSOVER_PROBABILITY, 0.9);
    }

    public static void setCrossoverProbability(double maxRefactorings) {
        PreferencesManager.getInstance().save(PreferencesConstants.CROSSOVER_PROBABILITY, maxRefactorings);
    }

    public static double getMutationProbability() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.MUTATION_PROBABILITY, 0.1);
    }

    public static void setMutationProbability(double mutationProbability) {
        PreferencesManager.getInstance().save(PreferencesConstants.MUTATION_PROBABILITY, mutationProbability);
    }

    @SuppressWarnings("unchecked")
    public static List<String> getObjectives() {
        return (List<String>) PreferencesManager.getInstance().restoreObject(PreferencesConstants.OBJECTIVES, new ArrayList<>());
    }

    public static void setObjectives(List<String> objectives) {
        PreferencesManager.getInstance().saveObject(PreferencesConstants.OBJECTIVES, objectives);
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRefactorings() {
        return (List<String>) PreferencesManager.getInstance().restoreObject(PreferencesConstants.REFACTORINGS, new ArrayList<>());
    }

    public static void setRefactorings(List<String> refactorings) {
        PreferencesManager.getInstance().saveObject(PreferencesConstants.REFACTORINGS, refactorings);
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRecentFiles() {
        return (List<String>) PreferencesManager.getInstance().restoreObject(PreferencesConstants.RECENT_FILES, new ArrayList<>());
    }

    public static void setRecentFiles(List<String> recentFiles) {
        PreferencesManager.getInstance().saveObject(PreferencesConstants.RECENT_FILES, recentFiles);
    }

    public static String getLanguage() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.LANGUAGE, "English");
    }

    public static void setLanguage(String language) {
        PreferencesManager.getInstance().save(PreferencesConstants.LANGUAGE, language);
    }

    public static String getLocale() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.LOCALE, "en-US");
    }

    public static void setLocale(String locale) {
        PreferencesManager.getInstance().save(PreferencesConstants.LOCALE, locale);
    }

    public static String getLookAndFeel() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.LOOK_AND_FEEL, LookAndFeelUtils.getSystemLookAndFeel());
    }

    public static void setLookAndFeel(String name) {
        PreferencesManager.getInstance().save(PreferencesConstants.LOOK_AND_FEEL, name);
    }

    public static String getLoggingLevel() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.LOGGING_LEVEL, "INFO");
    }

    public static void setLoggingLevel(String level) {
        PreferencesManager.getInstance().save(PreferencesConstants.LOGGING_LEVEL, level);
    }

    public static boolean getSaveOptimizationPreferences() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.SAVE_OPTIMIZATION_PREFERENCES, true);
    }

    public static void setSaveOptimizationPreferences(boolean state) {
        PreferencesManager.getInstance().save(PreferencesConstants.SAVE_OPTIMIZATION_PREFERENCES, state);
    }

    public static boolean getSaveContinuePreferences() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.SAVE_CONTINUE_PREFERENCES, true);
    }

    public static void setSaveContinuePreferences(boolean state) {
        PreferencesManager.getInstance().save(PreferencesConstants.SAVE_CONTINUE_PREFERENCES, state);
    }

    public static double getRange() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.RANGE, 0.05);
    }

    public static void setRange(double range) {
        PreferencesManager.getInstance().save(PreferencesConstants.RANGE, range);
    }

    public static double getAlpha() {
        return PreferencesManager.getInstance().restore(PreferencesConstants.ALPHA, 0.3);
    }

    public static void setAlpha(double alpha) {
        PreferencesManager.getInstance().save(PreferencesConstants.ALPHA, alpha);
    }
}
