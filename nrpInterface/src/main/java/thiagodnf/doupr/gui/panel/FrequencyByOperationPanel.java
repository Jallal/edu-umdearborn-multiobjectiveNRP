package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.gui.model.table.FrequencyByOperationTableModel;
import thiagodnf.doupr.optimization.solution.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FrequencyByOperationPanel extends AbstractPanel {

	private static final long serialVersionUID = -1309641045520160317L;

	protected JTable table;

	protected List<Solution> paretoFront;

	public FrequencyByOperationPanel(List<Solution> paretoFront) {

		this.paretoFront = paretoFront;

		addComponents();
	}

	public void addComponents() {

		setLayout(new BorderLayout());

		this.table = new JTable(new FrequencyByOperationTableModel(paretoFront));
		//this.table.sort(2, JTable.SORT_DESCENDING);
		this.table.getColumnModel().getColumn(2).setMaxWidth(45);

		// add the table to the frame
		this.add(new JScrollPane(table));
	}

	@Override
	public void load(ProjectObject project, List<Refactoring> refactorings) {
		// Not implemented
	}
}
