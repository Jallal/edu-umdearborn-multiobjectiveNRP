package thiagodnf.doupr.gui.panel;

import org.apache.log4j.Logger;
import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.comparator.SortObjectivesByRankingComparator;
import thiagodnf.doupr.evaluation.util.ObjectiveUtils;
import thiagodnf.doupr.evaluation.util.constants.ObjectiveConstants;
import thiagodnf.doupr.gui.component.JCheckBoxGroup;
import thiagodnf.doupr.gui.component.JOpaquePanel;
import thiagodnf.doupr.gui.component.JPanelForTabbedPane;
import thiagodnf.doupr.gui.model.table.ObjectivesTableModel;
import thiagodnf.doupr.gui.util.GridBagUtils;
import thiagodnf.doupr.gui.util.PreferencesUtils;
import vahid.ML.Clustering;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContinuePanel extends JOpaquePanel implements ChangeListener {

    protected static final Logger LOGGER = Logger.getLogger(ContinuePanel.class);
    private static final long serialVersionUID = 7331700491342438983L;
    protected List<Objective> objectives;

    protected List<Objective> selectedObjectives;

    protected JSpinner alphaSpinner;

    protected JSpinner rangeSpinner;

    protected double[] rankingForUserFeedback;

    protected double[] rankingForUserSelection;

    protected ObjectivesTableModel modelForSelectedObjectives;

    protected ObjectivesTableModel modelForAllObjectives;

    protected Clustering clustering;

    protected JCheckBoxGroup groupForClusters;

    public ContinuePanel(List<Objective> objectives, Clustering clustering, double[] rankingForUserFeedback, double[] rankingForUserSelection) {
        super(new BorderLayout());

        this.objectives = objectives;
        this.rankingForUserFeedback = rankingForUserFeedback;
        this.rankingForUserSelection = rankingForUserSelection;
        this.selectedObjectives = new ArrayList<>();
        this.clustering = clustering;

        addComponents();
        init();

        setPreferredSize(new Dimension(300, 300));

        calculateRanking();
    }

    // Method for getting the maximum value
    public static int getMax(int[] inputArray) {
        int maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    // Method for getting the index of maximum value
    public static int getMaxIdx(double[] inputArray) {
        int maxAt = 0;

        for (int i = 0; i < inputArray.length; i++) {
            maxAt = inputArray[i] > inputArray[maxAt] ? i : maxAt;
        }
        return maxAt;
    }

    public void init() {
        this.alphaSpinner.setValue(PreferencesUtils.getAlpha());
        this.rangeSpinner.setValue(PreferencesUtils.getRange());
    }

    public void addComponents() {

        this.alphaSpinner = new JSpinner(new SpinnerNumberModel(0.3, 0.0, 1.0, 0.05));
        this.alphaSpinner.addChangeListener(this);
        this.rangeSpinner = new JSpinner(new SpinnerNumberModel(0.05, 0.0, 1.0, 0.05));
        this.rangeSpinner.addChangeListener(this);

        this.modelForAllObjectives = new ObjectivesTableModel(objectives);
        this.modelForSelectedObjectives = new ObjectivesTableModel(objectives);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clustering", null, getPanelForClusters());
        tabbedPane.addTab("Selected", null, getPanelForObjectiveList(modelForSelectedObjectives));
        tabbedPane.addTab("General", null, getPanelForObjectiveList(modelForAllObjectives));
        tabbedPane.addTab("Settings", null, getPanelForSettings());

        add(tabbedPane);
    }

    protected void calculateRanking() {

        List<Objective> copy = ObjectiveUtils.copy(objectives);

        double[] rankings = new double[copy.size()];

        double minSelectedObjectives = 2;

        double alpha = getAlpha();

        for (int i = 0; i < copy.size(); i++) {
            rankings[i] = alpha * rankingForUserSelection[i] + (1.0 - alpha) * rankingForUserFeedback[i];
        }

        for (int i = 0; i < rankings.length; i++) {
            copy.get(i).getAttributes().put(ObjectiveConstants.RANKING, rankings[i]);
        }

        Collections.sort(copy, new SortObjectivesByRankingComparator());

        selectedObjectives.clear();

        double minRanking = 0.0;

        double range = getRange();

        for (int i = 0; i < copy.size(); i++) {

            double ranking = (double) copy.get(i).getAttributes().get(ObjectiveConstants.RANKING);

            if (i < minSelectedObjectives || (minRanking - ranking) <= range) {
                selectedObjectives.add(copy.get(i));
                minRanking = ranking;
            }
        }

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Selected Objectives:");

            for (Objective o : selectedObjectives) {
                LOGGER.info("\t" + o + " " + o.getAttributes().get(ObjectiveConstants.RANKING));
            }
        }

        modelForSelectedObjectives.setObjectives(selectedObjectives);
        modelForAllObjectives.setObjectives(copy);
    }

    public List<Objective> getAllObjectives() {
        return objectives;
    }

    public List<Objective> getSelectedObjectives() {
        return selectedObjectives;
    }

    public void setSelectedObjectives(List<Objective> selectedObjectives) {
        this.selectedObjectives = selectedObjectives;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        calculateRanking();
    }

    private JPanel getPanelForClusters() {

        JPanel panelCluster = new JPanelForTabbedPane(new GridBagLayout());

        int bestCluster = getMaxIdx(clustering.getClusterRanks());

        GridBagUtils.setComponent(panelCluster,
                new JLabel("Based on your evaluations, \n the best cluster is " + bestCluster), 0, 0, "LEFT", "NONE");

        JCheckBoxGroup groupForClusters = new JCheckBoxGroup("Clusters List:", clustering.clusterCenters.size());
        this.groupForClusters = groupForClusters;

        for (int i = 0; i < clustering.clusterCenters.size(); i++) {
//			GridBagUtils.setComponent(panelCluster, new JCheckBox("Cluster "+ i + " :"), i+1, 0, "LEFT", "NONE");
            boolean checked = false;
            if (i == bestCluster) {
                checked = true;
            }
            ;
            groupForClusters.add("Cluster " + i + " : \t" + String.format("%.3f", clustering.getClusterRanks()[i]), checked);
        }

        GridBagUtils.setComponent(panelCluster, groupForClusters, 1, 0, "LEFT", "NONE");

        JPanel container = new JOpaquePanel(new BorderLayout());

        container.add(panelCluster, BorderLayout.NORTH);

        return container;

    }

    public List<Integer> getSelectedClusters() {

        return this.groupForClusters.getSelectedIndex();
    }

    protected JPanel getPanelForObjectiveList(ObjectivesTableModel model) {

        JPanel panel = new JPanelForTabbedPane(new BorderLayout());

        JTable table = new JTable(model);

        table.getColumnModel().getColumn(2).setMaxWidth(80);

        panel.add(new JScrollPane(table));

        return panel;
    }

    protected JPanel getPanelForSettings() {

        JPanel panelForSettings = new JPanelForTabbedPane(new GridBagLayout());

        GridBagUtils.setComponent(panelForSettings, new JLabel("<htmL>&#945;:</html>"), 0, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panelForSettings, alphaSpinner, 0, 1, "LEFT", "HORIZONTAL");
        GridBagUtils.setComponent(panelForSettings, new JLabel("Range:"), 1, 0, "RIGHT", "NONE");
        GridBagUtils.setComponent(panelForSettings, rangeSpinner, 1, 1, "LEFT", "HORIZONTAL");

        JPanel container = new JOpaquePanel(new BorderLayout());

        container.add(panelForSettings, BorderLayout.NORTH);

        return container;
    }

    public double getRange() {
        return ((Number) this.rangeSpinner.getValue()).doubleValue();
    }

    public double getAlpha() {
        return ((Number) this.alphaSpinner.getValue()).doubleValue();
    }
}
