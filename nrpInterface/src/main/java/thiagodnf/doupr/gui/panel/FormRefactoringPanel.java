package thiagodnf.doupr.gui.panel;

import thiagodnf.doupr.core.base.ClassObject;
import thiagodnf.doupr.core.base.ElementObject;
import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.core.refactoring.Refactoring;
import thiagodnf.doupr.core.util.ClassObjectUtils;
import thiagodnf.doupr.gui.component.JFilterComboBox;
import thiagodnf.doupr.gui.component.JSortedComboBox;
import thiagodnf.doupr.gui.component.JSortedList;
import thiagodnf.doupr.gui.util.GridBagUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FormRefactoringPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 8567251071067131442L;

	protected JComboBox<String> refactoringTypesComboBox;

	protected JComboBox<String> sourceClassComboBox;

	protected JComboBox<String> targetClassComboBox;

	protected JList<String> attributesList;

	protected JList<String> methodsList;

	protected List<ClassObject> classes;

	protected Refactoring refactoring;

	public FormRefactoringPanel(ProjectObject refactored, Refactoring refactoring) {

		this.refactoring = refactoring;
		this.classes = refactored.getClasses();

		// Create the components

		addComponents();

		// Initiate the components

		loadRefactoringTypesComboBox();
		loadSourceComboBox();
		loadTargetComboBox();
		loadAttributesAndMethods(getSelectedSourceClass());

		// Add Events

		this.refactoringTypesComboBox.addItemListener(this);
		this.sourceClassComboBox.addItemListener(this);

		adjustComponents();

		// Verify if the user is adding or editing a refactoring
		if (refactoring != null) {

			// Select the current refactoring name because we are editing it
			this.refactoringTypesComboBox.setSelectedItem(refactoring.getName());

			// The users cannot change the refactoring type when they are editing it
			this.refactoringTypesComboBox.setEnabled(false);

			if (refactoring.getClass1() != null && !refactoring.getClass1().isEmpty()) {
				sourceClassComboBox.setSelectedItem(refactoring.getClass1());
			}

			if (refactoring.getClass2() != null && !refactoring.getClass2().isEmpty()) {
				targetClassComboBox.setSelectedItem(refactoring.getClass2());
			}

			setSelectedValues(attributesList, refactoring.getAttributes());
			setSelectedValues(methodsList, refactoring.getMethods());

			setEnabled(refactoring.getName());
		} else {
			//The user is adding a new refactoring
			setEnabled(getSelectedRefactoring().getName());
		}
	}

	protected void addComponents() {

		setLayout(new GridBagLayout());

		this.refactoringTypesComboBox = new JSortedComboBox<String>();
		this.sourceClassComboBox = new JFilterComboBox<String>();
		this.targetClassComboBox = new JFilterComboBox<String>();
		this.attributesList = new JSortedList<String>();
		this.methodsList = new JSortedList<String>();

		GridBagUtils.setComponent(this, new JLabel("Refactoring Type"), 0, 0, "RIGHT", "BOTH");
		GridBagUtils.setComponent(this, refactoringTypesComboBox, 0, 1, "HORIZONTAL", "BOTH");

		GridBagUtils.setComponent(this, new JLabel("Source Class"), 1, 0, "RIGHT", "BOTH");
		GridBagUtils.setComponent(this, sourceClassComboBox, 1, 1, "HORIZONTAL", "BOTH");

		GridBagUtils.setComponent(this, new JLabel("Attributes"), 2, 0, "RIGHT", "BOTH");
		GridBagUtils.setComponent(this, new JScrollPane(attributesList), 2, 1, "HORIZONTAL", "BOTH");

		GridBagUtils.setComponent(this, new JLabel("Methods"), 3, 0, "RIGHT", "BOTH");
		GridBagUtils.setComponent(this, new JScrollPane(methodsList), 3, 1, "HORIZONTAL", "BOTH");

		GridBagUtils.setComponent(this, new JLabel("Target Class"), 4, 0, "RIGHT", "BOTH");
		GridBagUtils.setComponent(this, targetClassComboBox, 4, 1, "HORIZONTAL", "BOTH");
	}

	protected void setSelectedValues(JList<String> list, List<String> values) {

		List<Integer> selectedIndex = new ArrayList<>();

		for (String value : values) {

			int index = ((DefaultListModel<String>) list.getModel()).indexOf(value);

			if (index != -1) {
				selectedIndex.add(index);
			}
		}

		list.setSelectedIndices(selectedIndex.stream().mapToInt(x -> x).toArray());
	}

	protected void loadAttributesAndMethods(ClassObject cls) {

		if (cls == null) {
			return;
		}

		DefaultListModel<String> methodsModel = ((DefaultListModel<String>) this.methodsList.getModel());

		methodsModel.removeAllElements();

		for (ElementObject el : cls.getMethods()) {
			methodsModel.addElement(el.getName());
		}

		DefaultListModel<String> attributesModel = ((DefaultListModel<String>) this.attributesList.getModel());

		attributesModel.removeAllElements();

		for (ElementObject el : cls.getAttributes()) {
			attributesModel.addElement(el.getName());
		}

		this.methodsList.setVisibleRowCount(6);
		this.methodsList.setFixedCellHeight(20);
		this.attributesList.setVisibleRowCount(6);
		this.attributesList.setFixedCellHeight(20);
	}

	protected void loadRefactoringTypesComboBox() {

		// Add the all possible refactorings the users can choose
		this.refactoringTypesComboBox.addItem(new DecreaseFieldSecurity().getName());
		this.refactoringTypesComboBox.addItem(new DecreaseMethodSecurity().getName());
		this.refactoringTypesComboBox.addItem(new EncapsulateField().getName());
		this.refactoringTypesComboBox.addItem(new ExtractClass().getName());
		this.refactoringTypesComboBox.addItem(new ExtractSubClass().getName());
		this.refactoringTypesComboBox.addItem(new ExtractSuperClass().getName());
		this.refactoringTypesComboBox.addItem(new IncreaseFieldSecurity().getName());
		this.refactoringTypesComboBox.addItem(new IncreaseMethodSecurity().getName());
		this.refactoringTypesComboBox.addItem(new MoveField().getName());
		this.refactoringTypesComboBox.addItem(new MoveMethod().getName());
		this.refactoringTypesComboBox.addItem(new PullUpField().getName());
		this.refactoringTypesComboBox.addItem(new PullUpMethod().getName());
		this.refactoringTypesComboBox.addItem(new PushDownField().getName());
		this.refactoringTypesComboBox.addItem(new PushDownMethod().getName());

		// We always select the first index in the Combobox component
		this.refactoringTypesComboBox.setSelectedIndex(9);
	}

	protected void loadSourceComboBox() {

		sourceClassComboBox.removeAllItems();

		for (ClassObject cls : classes) {
			sourceClassComboBox.addItem(cls.getName());
		}

		// We always select the first index in the Combobox component
		if (sourceClassComboBox.getItemCount() > 0) {
			sourceClassComboBox.setSelectedIndex(0);
		}
	}

	protected void loadTargetComboBox() {

		targetClassComboBox.removeAllItems();

		for (ClassObject cls : classes) {
			targetClassComboBox.addItem(cls.getName());
		}

		// We always select the first index in the Combobox component
		if (targetClassComboBox.getItemCount() > 0) {
			targetClassComboBox.setSelectedIndex(0);
		}
	}

	public Refactoring getRefactoring() {

		ClassObject sourceClass = getSelectedSourceClass();
		ClassObject targetClass = getSelectedTargetClass();

		String class1 = sourceClass == null ? null : sourceClass.getName();
		String class2 = targetClass == null ? null : targetClass.getName();

		Refactoring refactoring = getSelectedRefactoring();

		refactoring.setClass1(class1);
		refactoring.setClass2(class2);
		refactoring.setAttributes(this.attributesList.getSelectedValuesList());
		refactoring.setMethods(this.methodsList.getSelectedValuesList());
		refactoring.setMustDefineActors(false);

		return refactoring;
	}

	protected Refactoring getSelectedRefactoring() {
		return RefactoringFactory.getRefactoring((String) refactoringTypesComboBox.getSelectedItem());
	}

	protected ClassObject getSelectedSourceClass() {
		return ClassObjectUtils.findByName(classes, (String) sourceClassComboBox.getSelectedItem());
	}

	protected ClassObject getSelectedTargetClass() {
		return ClassObjectUtils.findByName(classes, (String) targetClassComboBox.getSelectedItem());
	}

	protected void adjustComponents() {
		setEnabled(getSelectedRefactoring().getName());
		setMultiOrSingleSelection(getSelectedRefactoring().getName());
		setTargetClassEnabled(getSelectedRefactoring().getName());
	}

	protected void setTargetClassEnabled(String refactoringName) {

		List<String> refactorings = new ArrayList<>();

		refactorings.add(new EncapsulateField().getName());
		refactorings.add(new IncreaseFieldSecurity().getName());
		refactorings.add(new IncreaseMethodSecurity().getName());
		refactorings.add(new DecreaseFieldSecurity().getName());
		refactorings.add(new DecreaseMethodSecurity().getName());
		refactorings.add(new ExtractClass().getName());
		refactorings.add(new ExtractSubClass().getName());
		refactorings.add(new ExtractSuperClass().getName());

		if (refactorings.contains(refactoringName)) {
			this.targetClassComboBox.setEnabled(false);
			this.targetClassComboBox.removeAllItems();
		} else {
			this.targetClassComboBox.setEnabled(true);
		}
	}

	protected void setMultiOrSingleSelection(String refactoringName) {

		if (refactoringName.contains("Extract")) {
			attributesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			methodsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		} else {
			attributesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			methodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	}

	protected void setEnabled(String refactoringName) {

		this.attributesList.setVisible(false);
		this.methodsList.setVisible(false);

		if (refactoringName.contains("Field")) {
			this.attributesList.setVisible(true);
			((DefaultListModel<String>) this.methodsList.getModel()).removeAllElements();
		} else if (refactoringName.contains("Method")) {
			this.methodsList.setVisible(true);
			((DefaultListModel<String>) this.attributesList.getModel()).removeAllElements();
		} else {
			this.attributesList.setVisible(true);
			this.methodsList.setVisible(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent event) {

		if (event.getStateChange() == ItemEvent.SELECTED) {
			loadTargetComboBox();
			loadAttributesAndMethods(getSelectedSourceClass());
			adjustComponents();
		}
	}
}
