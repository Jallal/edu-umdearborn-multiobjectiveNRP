Project Name,Effectiveness,Extendibility,Flexibility,Functionality,Reusability,Understandability,Coupling,Cohesion,Complexity,Abstraction,Messaging,Cohesion,Encapsulation,Design Size,Coupling,Composition,Inheritance,Hierarchies,Complexity,Polymorphism,Standard Cohesion,Standard Coupling,Standard Complexity
ProjectObject [packages=[PackageObject [name=net.sourceforge.ganttproject.action, classes=[
Public class net.sourceforge.ganttproject.action.ResourceActionSet {
	PRIVATE final net.sourceforge.ganttproject.roles.RoleManager myRoleManager
	PRIVATE final net.sourceforge.ganttproject.resource.ResourceManager myManager
	PRIVATE final net.sourceforge.ganttproject.resource.ResourceContext myContext
	PRIVATE final net.sourceforge.ganttproject.GanttProject myProjectFrame
	PRIVATE javax.swing.AbstractAction[] myActions

	PUBLIC AbstractAction[] getActions(){}
}, 
Public class net.sourceforge.ganttproject.action.NewTaskAction {
	PRIVATE final net.sourceforge.ganttproject.IGanttProject myProject

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PUBLIC void languageChanged(net.sourceforge.ganttproject.language.GanttLanguage$Event){}
	PRIVATE void setText(net.sourceforge.ganttproject.language.GanttLanguage){}
}, 
Public class net.sourceforge.ganttproject.action.NewHumanAction extends net.sourceforge.ganttproject.action.ResourceAction{
	PRIVATE final net.sourceforge.ganttproject.roles.RoleManager myRoleManager
	PRIVATE final int MENU_MASK
	PRIVATE javax.swing.JFrame myProjectFrame

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PUBLIC void languageChanged(){}
	PRIVATE JFrame getProjectFrame(){}
}, 
Public class net.sourceforge.ganttproject.action.NewArtefactAction {
	PRIVATE net.sourceforge.ganttproject.action.NewArtefactAction$ActiveActionProvider myProvider
	PRIVATE javax.swing.Icon myIconOnMouseOver

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PUBLIC Icon getIconOnMouseOver(){}
}, 
Public class net.sourceforge.ganttproject.action.ImportResources {
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager
	PRIVATE final net.sourceforge.ganttproject.resource.ResourceManager myResourceManager
	PRIVATE final net.sourceforge.ganttproject.GanttProject myproject
	PRIVATE final net.sourceforge.ganttproject.roles.RoleManager myRoleManager

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PRIVATE File getResourcesFile(){}
}, 
Public class net.sourceforge.ganttproject.action.DeleteHumanAction extends net.sourceforge.ganttproject.action.ResourceAction{
	PRIVATE final net.sourceforge.ganttproject.resource.ResourceContext myContext
	PRIVATE static final java.lang.String ICON_URL
	PRIVATE final int MENU_MASK
	PRIVATE net.sourceforge.ganttproject.GanttProject myProjectFrame

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PRIVATE GanttProject getProjectFrame(){}
	PRIVATE void deleteResources(net.sourceforge.ganttproject.resource.ProjectResource[]){}
	PRIVATE String getDisplayName(net.sourceforge.ganttproject.resource.ProjectResource[]){}
	PRIVATE ResourceContext getContext(){}
}, 
Public interface abstract net.sourceforge.ganttproject.action.RolloverAction {

	PUBLIC abstract Icon getIconOnMouseOver(){}
}, 
NA class abstract net.sourceforge.ganttproject.action.ResourceAction {
	PRIVATE net.sourceforge.ganttproject.resource.ResourceManager myManager

	PROTECTED ResourceManager getManager(){}
	PROTECTED GanttLanguage getLanguage(){}
}]], PackageObject [name=net.sourceforge.ganttproject.gui.options, classes=[
Public class net.sourceforge.ganttproject.gui.options.SettingsDialog extends net.sourceforge.ganttproject.gui.GeneralDialog{
	PUBLIC boolean reinit
	PUBLIC javax.swing.JButton restoreButton

	PUBLIC void constructSections(){}
	PUBLIC void valueChanged(javax.swing.event.TreeSelectionEvent){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
}, 
Public class abstract net.sourceforge.ganttproject.gui.options.GeneralOptionPanel {
	PROTECTED net.sourceforge.ganttproject.language.GanttLanguage language
	PROTECTED javax.swing.Box vb
	PROTECTED boolean bHasChange
	PRIVATE java.awt.Frame appli
	PRIVATE java.lang.String myTitle
	PRIVATE java.lang.String myComment

	PUBLIC Component getComponent(){}
	PUBLIC abstract boolean applyChanges(boolean){}
	PUBLIC abstract void initialize(){}
	PUBLIC boolean askForApplyChanges(){}
	PUBLIC String getTitle(){}
	PUBLIC String getComment(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.HTMLSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JButton buttonXsl
	PUBLIC javax.swing.JTextField tfXsl
	PUBLIC javax.swing.JCheckBox cbDefault
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC String getXslDirectory(boolean){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
}, 
Public class net.sourceforge.ganttproject.gui.options.ExportSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JCheckBox cbName
	PUBLIC javax.swing.JCheckBox cbComplete
	PUBLIC javax.swing.JCheckBox cbRelations
	PUBLIC javax.swing.JCheckBox cb3dBorder
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC boolean getExportName(){}
	PUBLIC boolean getExportComplete(){}
	PUBLIC boolean getExportRelations(){}
	PUBLIC boolean getExport3DBorder(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.TopPanel {

}, 
Public class net.sourceforge.ganttproject.gui.options.WelcomeSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.CSVSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JRadioButton bFixedSize
	PUBLIC javax.swing.JRadioButton bSeparatedText
	PUBLIC javax.swing.JRadioButton bDoubleDot
	PUBLIC javax.swing.JRadioButton bDotComa
	PUBLIC javax.swing.JRadioButton bComa
	PUBLIC javax.swing.JRadioButton bSpace
	PUBLIC javax.swing.JRadioButton bOther
	PUBLIC javax.swing.JComboBox cbTextSeparator
	PUBLIC javax.swing.JTextField tfOther
	PUBLIC javax.swing.JCheckBox cbTaskID
	PUBLIC javax.swing.JCheckBox cbTaskName
	PUBLIC javax.swing.JCheckBox cbStartDate
	PUBLIC javax.swing.JCheckBox cbEndDate
	PUBLIC javax.swing.JCheckBox cbTaskPercent
	PUBLIC javax.swing.JCheckBox cbTaskDuration
	PUBLIC javax.swing.JCheckBox cbTaskWebLink
	PUBLIC javax.swing.JCheckBox cbTaskResources
	PUBLIC javax.swing.JCheckBox cbTaskNotes
	PUBLIC javax.swing.JCheckBox cbResID
	PUBLIC javax.swing.JCheckBox cbResName
	PUBLIC javax.swing.JCheckBox cbResMail
	PUBLIC javax.swing.JCheckBox cbResPhone
	PUBLIC javax.swing.JCheckBox cbResRole
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PRIVATE void addUsingGBL(java.awt.Container,java.awt.Component,java.awt.GridBagConstraints,int,int,int,int){}
	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PUBLIC void unselectOther(javax.swing.JRadioButton){}
	PUBLIC void enableSeparatedButton(boolean){}
	PUBLIC boolean getFixed(){}
	PUBLIC boolean getTaskID(){}
	PUBLIC boolean getTaskName(){}
	PUBLIC boolean getTaskSD(){}
	PUBLIC boolean getTaskED(){}
	PUBLIC boolean getTaskPercent(){}
	PUBLIC boolean getTaskDuration(){}
	PUBLIC boolean getTaskWebLink(){}
	PUBLIC boolean getTaskResources(){}
	PUBLIC boolean getTaskNotes(){}
	PUBLIC boolean getResourceID(){}
	PUBLIC boolean getResourceName(){}
	PUBLIC boolean getResourcePhone(){}
	PUBLIC boolean getResourceMail(){}
	PUBLIC boolean getResourceRole(){}
	PUBLIC boolean separatCharHasChange(){}
	PUBLIC String getTextSeparat(){}
	PUBLIC String getSeparat(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.ColorSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JButton bTaskColor
	PUBLIC javax.swing.JButton bResourceColor
	PUBLIC javax.swing.JButton bResourceOverloadColor
	PRIVATE net.sourceforge.ganttproject.GanttProject appli
	PROTECTED static javax.swing.JColorChooser colorChooser
	PUBLIC javax.swing.Box vb1
	PUBLIC javax.swing.Box vb2

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.ParametersSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JCheckBox cbAutomatic
	PUBLIC javax.swing.JCheckBox cbDrag
	PUBLIC javax.swing.JCheckBox cbRedLine
	PUBLIC javax.swing.JSpinner spLockDAV
	PUBLIC javax.swing.JTextField tfTaskPrefix
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC boolean getAutomatic(){}
	PUBLIC boolean getDragTime(){}
	PUBLIC boolean getRedLine(){}
	PUBLIC int getLockDAVMinutes(){}
	PUBLIC String getTaskNamePrefix(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.LanguageSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PRIVATE javax.swing.JComboBox cbLanguage
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC void itemStateChanged(java.awt.event.ItemEvent){}
	PUBLIC void changeLanguage(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.ProjectSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JTextField tfName
	PUBLIC javax.swing.JTextField tfOrganization
	PUBLIC javax.swing.JTextField tfWebLink
	PUBLIC javax.swing.JTextArea taDescr
	PRIVATE final net.sourceforge.ganttproject.IGanttProject myProject

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC String getProjectName(){}
	PUBLIC String getProjectOrganization(){}
	PUBLIC String getWebLink(){}
	PUBLIC String getProjectDescription(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.PDFSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC javax.swing.JButton buttonXslFo
	PUBLIC javax.swing.JTextField tfXslFo
	PUBLIC javax.swing.JCheckBox cbDefault
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
	PUBLIC String getXslFoFile(boolean){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
}, 
Public class net.sourceforge.ganttproject.gui.options.RolesSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PUBLIC net.sourceforge.ganttproject.gui.RolesTableModel myRolesModel
	PUBLIC javax.swing.JTable rolesTable
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.options.LnFSettingsPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{
	PRIVATE javax.swing.JComboBox cbLnf
	PRIVATE javax.swing.JCheckBox cbSmallIcon
	PRIVATE javax.swing.JComboBox cbButtonType
	PRIVATE javax.swing.JCheckBox cbShowStatus
	PRIVATE net.sourceforge.ganttproject.GanttProject appli

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void itemStateChanged(java.awt.event.ItemEvent){}
	PUBLIC GanttLookAndFeelInfo getLookAndFeel(){}
	PUBLIC boolean getShowStatusBar(){}
	PUBLIC void initialize(){}
}]], PackageObject [name=net.sourceforge.ganttproject.shape, classes=[
Public class net.sourceforge.ganttproject.shape.ShapePaint {
	PROTECTED int width
	PROTECTED int height
	PROTECTED int[] array
	PROTECTED java.awt.Color foreground
	PROTECTED java.awt.Color background

	PRIVATE static BufferedImage createTexture(int,int,int[],java.awt.Color,java.awt.Color){}
	PUBLIC boolean equals(java.lang.Object){}
	PUBLIC String toString2(){}
	PUBLIC String toString(){}
	PUBLIC String getArray(){}
	PUBLIC int[] getarray(){}
}, 
Public class net.sourceforge.ganttproject.shape.ShapeConstants {
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint TRANSPARENT
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint DEFAULT
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint CROSS
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint VERT
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint HORZ
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint GRID
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint ROUND
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint NW_TRIANGLE
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint NE_TRIANGLE
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint SW_TRIANGLE
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint SE_TRIANGLE
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint DIAMOND
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint DOTS
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint DOT
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint SLASH
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint BACKSLASH
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint THICK_VERT
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint THICK_HORZ
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint THICK_GRID
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint THICK_SLASH
	PUBLIC static final net.sourceforge.ganttproject.shape.ShapePaint THICK_BACKSLASH
	PUBLIC static net.sourceforge.ganttproject.shape.ShapePaint[] PATTERN_LIST

}, 
Public class net.sourceforge.ganttproject.shape.JPaintCombo {
	PRIVATE java.awt.Paint[] myList

	PUBLIC Paint getSelectedPaint(){}
}, 
Public class net.sourceforge.ganttproject.shape.PaintCellRenderer {
	PROTECTED javax.swing.border.Border normalBorder
	PROTECTED javax.swing.border.Border selectBorder
	PROTECTED javax.swing.border.Border focusBorder
	PROTECTED java.awt.Paint paint

	PUBLIC void paintComponent(java.awt.Graphics){}
	PUBLIC Component getListCellRendererComponent(javax.swing.JList,java.lang.Object,int,boolean,boolean){}
}]], PackageObject [name=net.sourceforge.ganttproject.task.algorithm, classes=[
Public class net.sourceforge.ganttproject.task.algorithm.AlgorithmCollection {
	PRIVATE final net.sourceforge.ganttproject.task.algorithm.FindPossibleDependeesAlgorithm myFindPossibleDependeesAlgorithm
	PRIVATE final net.sourceforge.ganttproject.task.algorithm.RecalculateTaskScheduleAlgorithm myRecalculateTaskScheduleAlgorithm
	PRIVATE final net.sourceforge.ganttproject.task.algorithm.AdjustTaskBoundsAlgorithm myAdjustTaskBoundsAlgorithm
	PRIVATE final net.sourceforge.ganttproject.task.algorithm.RecalculateTaskCompletionPercentageAlgorithm myCompletionPercentageAlgorithm

	PUBLIC FindPossibleDependeesAlgorithm getFindPossibleDependeesAlgorithm(){}
	PUBLIC RecalculateTaskScheduleAlgorithm getRecalculateTaskScheduleAlgorithm(){}
	PUBLIC AdjustTaskBoundsAlgorithm getAdjustTaskBoundsAlgorithm(){}
	PUBLIC RecalculateTaskCompletionPercentageAlgorithm getRecalculateTaskCompletionPercentageAlgorithm(){}
}, 
Public class abstract net.sourceforge.ganttproject.task.algorithm.AdjustTaskBoundsAlgorithm extends net.sourceforge.ganttproject.task.algorithm.AlgorithmBase{
	PRIVATE java.util.Set myModifiedTasks

	PUBLIC void run(net.sourceforge.ganttproject.task.Task){}
	PUBLIC void run$2(net.sourceforge.ganttproject.task.Task[]){}
	PRIVATE void recalculateSupertaskScheduleBottomUp(java.util.Set,net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade){}
	PRIVATE void recalculateSupertaskSchedule(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade){}
	PRIVATE void modifyTaskStart(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.GanttCalendar){}
	PRIVATE void modifyTaskEnd(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.GanttCalendar){}
	PROTECTED abstract TaskContainmentHierarchyFacade createContainmentFacade(){}
}, 
Public class abstract net.sourceforge.ganttproject.task.algorithm.RecalculateTaskCompletionPercentageAlgorithm {

	PUBLIC void run(net.sourceforge.ganttproject.task.Task){}
	PRIVATE void recalculateSupertaskCompletionPercentageBottomUp(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade){}
	PRIVATE void recalculateSupertaskCompletionPercentage(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade){}
	PROTECTED abstract TaskContainmentHierarchyFacade createContainmentFacade(){}
}, 
Public class abstract net.sourceforge.ganttproject.task.algorithm.RecalculateTaskScheduleAlgorithm extends net.sourceforge.ganttproject.task.algorithm.AlgorithmBase{
	PRIVATE java.util.Set myMarkedTasks
	PRIVATE java.util.SortedMap myDistance2dependencyList
	PRIVATE java.util.Set myModifiedTasks
	PRIVATE final net.sourceforge.ganttproject.task.algorithm.AdjustTaskBoundsAlgorithm myAdjuster
	PRIVATE int myEntranceCounter
	PRIVATE boolean isRunning

	PUBLIC void run(net.sourceforge.ganttproject.task.Task){}
	PUBLIC void run$2(){}
	PUBLIC boolean isRunning(){}
	PRIVATE void traverse(net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade,net.sourceforge.ganttproject.task.Task,java.util.Set){}
	PRIVATE void fulfilDependencies(){}
	PRIVATE void fulfilConstraints(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PRIVATE void modifyTaskStart(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.GanttCalendar){}
	PRIVATE void modifyTaskEnd(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.GanttCalendar){}
	PRIVATE void buildDistanceGraph(net.sourceforge.ganttproject.task.Task){}
	PRIVATE void buildDistanceGraph$2(net.sourceforge.ganttproject.task.dependency.TaskDependency[],int){}
	PRIVATE void markTask(net.sourceforge.ganttproject.task.Task){}
	PRIVATE boolean isMarked(net.sourceforge.ganttproject.task.Task){}
	PROTECTED abstract TaskContainmentHierarchyFacade createContainmentFacade(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.algorithm.FindPossibleDependeesAlgorithm {

	PUBLIC abstract Task[] run(net.sourceforge.ganttproject.task.Task){}
}, 
Public class net.sourceforge.ganttproject.task.algorithm.AlgorithmBase {
	PROTECTED boolean isEnabled

	PUBLIC void setEnabled(boolean){}
	PROTECTED boolean isEnabled(){}
}, 
Public class abstract net.sourceforge.ganttproject.task.algorithm.FindPossibleDependeesAlgorithmImpl {
	PRIVATE net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade myContainmentFacade

	PUBLIC Task[] run(net.sourceforge.ganttproject.task.Task){}
	PROTECTED abstract TaskContainmentHierarchyFacade createContainmentFacade(){}
	PRIVATE void processTask(net.sourceforge.ganttproject.task.Task[],net.sourceforge.ganttproject.task.Task,java.util.ArrayList){}
}]], PackageObject [name=net.sourceforge.ganttproject.export, classes=[
Public class abstract net.sourceforge.ganttproject.export.ProjectExportProcessor {
	PRIVATE javax.xml.transform.sax.SAXTransformerFactory myTransformerFactory
	PRIVATE java.util.logging.Logger myLogger
	PRIVATE static java.lang.String LOGGER_NAME
	PRIVATE org.xml.sax.helpers.AttributesImpl myAttrs

	PUBLIC abstract void doExport(net.sourceforge.ganttproject.export.DeprecatedProjectExportData){}
	PROTECTED SAXTransformerFactory getTransformerFactory(){}
	PROTECTED boolean isInfoable(){}
	PROTECTED void info(java.lang.String){}
	PROTECTED Logger getLogger(){}
	PROTECTED AttributesImpl getCleanAttrs(){}
}, 
Public class net.sourceforge.ganttproject.export.DeprecatedProjectExportData {
	PUBLIC final java.lang.String myFilename
	PUBLIC final net.sourceforge.ganttproject.GanttProject myProject
	PUBLIC final net.sourceforge.ganttproject.GanttTree myTree
	PUBLIC final net.sourceforge.ganttproject.GanttGraphicArea myGanttChart
	PUBLIC final net.sourceforge.ganttproject.ResourceLoadGraphicArea myResourceChart
	PUBLIC final net.sourceforge.ganttproject.GanttExportSettings myExportOptions
	PUBLIC final java.lang.String myXslFoScript

}, 
Public class net.sourceforge.ganttproject.export.PDFExportProcessor extends net.sourceforge.ganttproject.export.ProjectExportProcessor{
	PRIVATE java.lang.String myStylesheetPath

	PUBLIC void doExport(net.sourceforge.ganttproject.export.DeprecatedProjectExportData){}
	PUBLIC void run(net.sourceforge.ganttproject.export.PDFExportData){}
	PROTECTED void exportProject(javax.xml.transform.sax.TransformerHandler){}
	PRIVATE Driver createDriver(net.sourceforge.ganttproject.export.PDFExportData){}
	PROTECTED File exportGanttChart(net.sourceforge.ganttproject.export.ProjectExportData){}
	PROTECTED File exportResourceChart(net.sourceforge.ganttproject.export.ProjectExportData){}
	PRIVATE Options createOptions(){}
	PRIVATE void createConfiguration(javax.xml.transform.sax.TransformerHandler,net.sourceforge.ganttproject.export.FontRecord[]){}
	PRIVATE void writeTriplets(javax.xml.transform.sax.TransformerHandler,net.sourceforge.ganttproject.export.FontTriplet[]){}
}, 
Public class net.sourceforge.ganttproject.export.ExportException {

}, 
Public class net.sourceforge.ganttproject.export.PDFExportData extends net.sourceforge.ganttproject.export.ProjectExportData{
	PUBLIC final java.lang.String myStylesheet

}, 
Public class net.sourceforge.ganttproject.export.ProjectExportData {
	PUBLIC final net.sourceforge.ganttproject.GanttExportSettings myExportSettings
	PUBLIC final java.io.File myOutputFile
	PUBLIC final java.lang.String myProjectName
	PUBLIC final java.lang.String myProjectDescription
	PUBLIC final java.lang.String myOrganization
	PUBLIC final net.sourceforge.ganttproject.resource.HumanResourceManager myResourceManager
	PUBLIC final net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade myTaskHierarchyFacade
	PUBLIC final net.sourceforge.ganttproject.export.GanttChartExportProcessor myGanttChartExportProcessor
	PUBLIC final net.sourceforge.ganttproject.export.ResourceChartExportProcessor myResourceChartExportProcessor
	PUBLIC final net.sourceforge.ganttproject.GanttTree myGanttTree

}, 
Public class net.sourceforge.ganttproject.export.FontRecord {
	PRIVATE java.net.URI myLocation
	PRIVATE java.net.URI myMetricsLocation
	PRIVATE java.util.ArrayList myTriplets
	PRIVATE net.sourceforge.ganttproject.export.TTFFileExt myTTFFile

	PUBLIC void addTriplet(net.sourceforge.ganttproject.export.FontTriplet){}
	PUBLIC URI getFontLocation(){}
	PUBLIC URI getMetricsLocation(){}
	PUBLIC FontTriplet[] getFontTriplets(){}
	PUBLIC TTFFileExt getTTFFile(){}
	PUBLIC String toString(){}
}, 
Public class net.sourceforge.ganttproject.export.FontTriplet {
	PRIVATE java.lang.String myName
	PRIVATE boolean isItalic
	PRIVATE boolean isBold

	PUBLIC String getName(){}
	PUBLIC boolean isItalic(){}
	PUBLIC boolean isBold(){}
}, 
Public class net.sourceforge.ganttproject.export.GanttChartExportData {
	PUBLIC final java.io.File myOutputFile
	PUBLIC final net.sourceforge.ganttproject.GanttExportSettings myExportSettings
	PUBLIC final java.lang.String myImageFormat

}, 
Public class net.sourceforge.ganttproject.export.ResourceChartExportProcessor {

	PUBLIC void run(net.sourceforge.ganttproject.export.ResourceChartExportData){}
}, 
Public class net.sourceforge.ganttproject.export.GanttChartExportProcessor {

	PUBLIC void run(net.sourceforge.ganttproject.export.GanttChartExportData){}
}, 
Public class net.sourceforge.ganttproject.export.ResourceChartExportData {
	PUBLIC final java.io.File myOutputFile
	PUBLIC final java.lang.String myImageFormat

}, 
Public class net.sourceforge.ganttproject.export.JDKFontLocator {
	PRIVATE net.sourceforge.ganttproject.export.FontMetricsStorage myFontMetricsStorage

	PUBLIC FontRecord[] getFontRecords(){}
	PRIVATE void populateWithTriplets(net.sourceforge.ganttproject.export.FontRecord){}
}, 
NA class net.sourceforge.ganttproject.export.TTFFileExt {
	PRIVATE final java.io.File myFile
	PRIVATE java.awt.Font myAwtFont

	PUBLIC boolean isItalic(){}
	PUBLIC boolean isBold(){}
	PUBLIC File getFile(){}
	PRIVATE Font getAwtFont(){}
}, 
Public class net.sourceforge.ganttproject.export.FontMetricsStorage {

	PUBLIC URI getFontMetricsURI(net.sourceforge.ganttproject.export.TTFFileExt){}
}]], PackageObject [name=net.sourceforge.ganttproject.gui.taskproperties, classes=[
Public class net.sourceforge.ganttproject.gui.taskproperties.TaskDependenciesPanel extends net.sourceforge.ganttproject.gui.taskproperties.CommonPanel{
	PROTECTED net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE java.awt.GridBagConstraints gbc
	PRIVATE javax.swing.JPanel predecessorsPanel
	PRIVATE javax.swing.JScrollPane predecessorsScrollPane
	PRIVATE javax.swing.JTable predecessorsTable
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager
	PRIVATE net.sourceforge.ganttproject.gui.taskproperties.DependencyTableModel myTableModel
	PRIVATE static net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint[] CONSTRAINTS

	PUBLIC JPanel getComponent(){}
	PUBLIC DependencyTableModel getTableModel(){}
	PROTECTED void constructPredecessorsPanel(){}
	PROTECTED void setUpPredecessorComboColumn(javax.swing.table.TableColumn,javax.swing.JTable){}
	PROTECTED void setUpTypeComboColumn(javax.swing.table.TableColumn){}
	PUBLIC JTable getTable(){}
	PUBLIC void durationChanged(int){}
	PUBLIC void nameChanged(java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.gui.taskproperties.TaskAllocationsPanel extends net.sourceforge.ganttproject.gui.taskproperties.CommonPanel{
	PRIVATE javax.swing.JPanel resourcesPanel
	PRIVATE net.sourceforge.ganttproject.gui.ResourcesTableModel myResourcesTableModel
	PRIVATE javax.swing.JTable resourcesTable
	PRIVATE final net.sourceforge.ganttproject.resource.HumanResourceManager myHRManager
	PRIVATE javax.swing.JScrollPane resourcesScrollPane

	PUBLIC JPanel getComponent(){}
	PRIVATE void constructResourcesPanel(net.sourceforge.ganttproject.task.ResourceAssignmentCollection){}
	PUBLIC ResourcesTableModel getTableModel(){}
	PRIVATE void setUpResourcesComboColumn(javax.swing.JTable){}
	PUBLIC void durationChanged(int){}
	PUBLIC void nameChanged(java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.gui.taskproperties.DependencyTableModel {
	PUBLIC final java.lang.String[] columnNames
	PRIVATE final java.util.List myDependencies
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependencyCollectionMutator myMutator
	PRIVATE final net.sourceforge.ganttproject.task.Task myTask

	PUBLIC void commit(){}
	PUBLIC int getColumnCount(){}
	PUBLIC int getRowCount(){}
	PUBLIC String getColumnName(int){}
	PUBLIC Object getValueAt(int,int){}
	PUBLIC boolean isCellEditable(int,int){}
	PUBLIC void setValueAt(java.lang.Object,int,int){}
	PRIVATE void updateDependency(java.lang.Object,int,int){}
	PRIVATE void createDependency(java.lang.Object){}
}, 
NA class abstract net.sourceforge.ganttproject.gui.taskproperties.CommonPanel {
	PRIVATE final net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE javax.swing.JLabel nameLabel3
	PRIVATE javax.swing.JLabel durationLabel3
	PRIVATE javax.swing.JTextField nameField3
	PRIVATE javax.swing.JTextField durationField3
	PRIVATE javax.swing.JPanel firstRowPanel3
	PRIVATE java.awt.FlowLayout flowL
	PRIVATE java.awt.GridBagConstraints gbc
	PRIVATE final net.sourceforge.ganttproject.task.Task myTask

	PROTECTED void addUsingGBL(java.awt.Container,java.awt.Component,java.awt.GridBagConstraints,int,int,int,int){}
	PROTECTED void setFirstRow(java.awt.Container,java.awt.GridBagConstraints,javax.swing.JLabel,javax.swing.JTextField,javax.swing.JLabel,javax.swing.JTextField){}
	PUBLIC void nameChanged(java.lang.String){}
	PUBLIC void durationChanged(int){}
	PROTECTED JPanel setupCommonFields(){}
	PROTECTED GanttLanguage getLanguage(){}
	PROTECTED Task getTask(){}
}, 
Public interface abstract net.sourceforge.ganttproject.gui.taskproperties.InternalStateListener {

	PUBLIC abstract void nameChanged(java.lang.String){}
	PUBLIC abstract void durationChanged(int){}
}]], PackageObject [name=net.sourceforge.ganttproject.resource, classes=[
Public interface abstract net.sourceforge.ganttproject.resource.ResourceView {

	PUBLIC abstract void resourceAdded(net.sourceforge.ganttproject.resource.ResourceEvent){}
	PUBLIC abstract void resourcesRemoved(net.sourceforge.ganttproject.resource.ResourceEvent){}
}, 
Public class abstract net.sourceforge.ganttproject.resource.ProjectResource {
	PROTECTED int id
	PROTECTED java.lang.String name
	PRIVATE double costsPerUnit
	PRIVATE int maximumUnitsPerDay
	PRIVATE java.lang.String unitMeasure
	PRIVATE java.lang.String description

	PUBLIC void setName(java.lang.String){}
	PUBLIC String getName(){}
	PUBLIC void setDescription(java.lang.String){}
	PUBLIC String getDescription(){}
	PUBLIC void setUnitMeasure(java.lang.String){}
	PUBLIC String getUnitMeasure(){}
	PUBLIC void setCostsPerUnit(double){}
	PUBLIC double getCostsPerUnit(){}
	PUBLIC void setMaximumUnitsPerDay(int){}
	PUBLIC int getMaximumUnitsPerDay(){}
	PUBLIC void setId(int){}
	PUBLIC int getId(){}
	PUBLIC String toString(){}
}, 
Public interface abstract net.sourceforge.ganttproject.resource.ResourceManager {

	PUBLIC abstract ProjectResource create(java.lang.String,int){}
	PUBLIC abstract void add(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC abstract ProjectResource getById(int){}
	PUBLIC abstract void remove(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC abstract void removeById(int){}
	PUBLIC abstract ArrayList getResources(){}
	PUBLIC abstract void save(java.io.OutputStream){}
	PUBLIC abstract void clear(){}
	PUBLIC abstract void addView(net.sourceforge.ganttproject.resource.ResourceView){}
}, 
Public class net.sourceforge.ganttproject.resource.ResourceEvent {
	PRIVATE net.sourceforge.ganttproject.resource.ProjectResource[] myResources
	PRIVATE net.sourceforge.ganttproject.resource.ResourceManager myManager
	PRIVATE net.sourceforge.ganttproject.resource.ProjectResource myResource

	PUBLIC ResourceManager getManager(){}
	PUBLIC ProjectResource getResource(){}
	PUBLIC ProjectResource[] getResources(){}
}, 
Public class net.sourceforge.ganttproject.resource.HumanResourceManager {
	PRIVATE java.util.List myViews
	PRIVATE java.util.ArrayList resources
	PRIVATE int nextFreeId
	PRIVATE final net.sourceforge.ganttproject.roles.Role myDefaultRole

	PUBLIC HumanResource newHumanResource(){}
	PUBLIC ProjectResource create(java.lang.String,int){}
	PUBLIC void add(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC ProjectResource getById(int){}
	PUBLIC ArrayList getResources(){}
	PUBLIC void remove(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC void removeById(int){}
	PUBLIC void save(java.io.OutputStream){}
	PUBLIC void clear(){}
	PUBLIC void addView(net.sourceforge.ganttproject.resource.ResourceView){}
	PRIVATE void fireResourceAdded(net.sourceforge.ganttproject.resource.ProjectResource){}
	PRIVATE void fireResourcesRemoved(net.sourceforge.ganttproject.resource.ProjectResource[]){}
	PRIVATE void fireCleanup(){}
	PUBLIC void up(int){}
	PUBLIC void down(int){}
}, 
Public interface abstract net.sourceforge.ganttproject.resource.ResourceContext {

	PUBLIC abstract ProjectResource[] getResources(){}
}, 
Public class net.sourceforge.ganttproject.resource.HumanResource extends net.sourceforge.ganttproject.resource.ProjectResource{
	PRIVATE java.lang.String phone
	PRIVATE java.lang.String email
	PRIVATE int function
	PRIVATE net.sourceforge.ganttproject.roles.Role myRole

	PUBLIC void setMail(java.lang.String){}
	PUBLIC String getMail(){}
	PUBLIC void setPhone(java.lang.String){}
	PUBLIC String getPhone(){}
	PUBLIC void setRole(net.sourceforge.ganttproject.roles.Role){}
	PUBLIC Role getRole(){}
}]], PackageObject [name=net.sourceforge.ganttproject.task.event, classes=[
Public interface abstract net.sourceforge.ganttproject.task.event.TaskListener {

	PUBLIC abstract void taskScheduleChanged(net.sourceforge.ganttproject.task.event.TaskScheduleEvent){}
	PUBLIC abstract void dependencyAdded(net.sourceforge.ganttproject.task.event.TaskDependencyEvent){}
	PUBLIC abstract void dependencyRemoved(net.sourceforge.ganttproject.task.event.TaskDependencyEvent){}
	PUBLIC abstract void taskAdded(net.sourceforge.ganttproject.task.event.TaskHierarchyEvent){}
	PUBLIC abstract void taskRemoved(net.sourceforge.ganttproject.task.event.TaskHierarchyEvent){}
	PUBLIC abstract void taskMoved(net.sourceforge.ganttproject.task.event.TaskHierarchyEvent){}
}, 
Public class net.sourceforge.ganttproject.task.event.TaskDependencyEvent {
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependency myDependency

	PUBLIC TaskDependency getDependency(){}
}, 
Public class net.sourceforge.ganttproject.task.event.TaskScheduleEvent {
	PRIVATE final net.sourceforge.ganttproject.GanttCalendar myOldStartDate
	PRIVATE final net.sourceforge.ganttproject.GanttCalendar myOldFinishDate
	PRIVATE final net.sourceforge.ganttproject.GanttCalendar myNewStartDate
	PRIVATE final net.sourceforge.ganttproject.GanttCalendar myNewFinishDate

	PUBLIC GanttCalendar getOldStartDate(){}
	PUBLIC GanttCalendar getOldFinishDate(){}
	PUBLIC GanttCalendar getNewStartDate(){}
	PUBLIC GanttCalendar getNewFinishDate(){}
}, 
Public class net.sourceforge.ganttproject.task.event.TaskHierarchyEvent {
	PRIVATE final net.sourceforge.ganttproject.task.Task myNewContainer
	PRIVATE final net.sourceforge.ganttproject.task.Task myTask
	PRIVATE final net.sourceforge.ganttproject.task.Task myOldContainer

	PUBLIC Task getTask(){}
	PUBLIC Task getOldContainer(){}
	PUBLIC Task getNewContainer(){}
}, 
Public class abstract net.sourceforge.ganttproject.task.event.TaskListenerAdapter {

	PUBLIC void taskScheduleChanged(net.sourceforge.ganttproject.task.event.TaskScheduleEvent){}
	PUBLIC void dependencyAdded(net.sourceforge.ganttproject.task.event.TaskDependencyEvent){}
	PUBLIC void dependencyRemoved(net.sourceforge.ganttproject.task.event.TaskDependencyEvent){}
	PUBLIC void taskAdded(net.sourceforge.ganttproject.task.event.TaskHierarchyEvent){}
	PUBLIC void taskRemoved(net.sourceforge.ganttproject.task.event.TaskHierarchyEvent){}
	PUBLIC void taskMoved(net.sourceforge.ganttproject.task.event.TaskHierarchyEvent){}
}]], PackageObject [name=net.sourceforge.ganttproject.document, classes=[
Public class net.sourceforge.ganttproject.document.DocumentsMRU {
	PRIVATE int maxSize
	PRIVATE java.util.List documents

	PUBLIC boolean add(net.sourceforge.ganttproject.document.Document){}
	PUBLIC boolean append(net.sourceforge.ganttproject.document.Document){}
	PUBLIC void clear(){}
	PUBLIC Iterator iterator(){}
}, 
Public interface abstract net.sourceforge.ganttproject.document.Document {

	PUBLIC abstract String getDescription(){}
	PUBLIC abstract boolean canRead(){}
	PUBLIC abstract boolean canWrite(){}
	PUBLIC abstract boolean isValidForMRU(){}
	PUBLIC abstract boolean acquireLock(){}
	PUBLIC abstract void releaseLock(){}
	PUBLIC abstract InputStream getInputStream(){}
	PUBLIC abstract OutputStream getOutputStream(){}
	PUBLIC abstract String getPath(){}
	PUBLIC abstract String getFilePath(){}
	PUBLIC abstract String getURLPath(){}
	PUBLIC abstract String getUsername(){}
	PUBLIC abstract String getPassword(){}
	PUBLIC abstract void setUserInfo(java.lang.String,java.lang.String){}
	PUBLIC abstract String getLastError(){}
}, 
Public class abstract net.sourceforge.ganttproject.document.AbstractURLDocument extends net.sourceforge.ganttproject.document.AbstractDocument{

	PUBLIC String getURLPath(){}
}, 
Public class net.sourceforge.ganttproject.document.DocumentCreator {

	PUBLIC static Document createDocument(java.lang.String){}
	PUBLIC static Document createDocument$2(java.lang.String,java.lang.String,java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.document.HttpDocument extends net.sourceforge.ganttproject.document.AbstractURLDocument{
	PRIVATE java.lang.String url
	PRIVATE java.lang.String lastError
	PRIVATE org.apache.commons.httpclient.HttpURL httpURL
	PRIVATE org.apache.webdav.lib.WebdavResource webdavResource
	PRIVATE boolean locked
	PRIVATE boolean malformedURL
	PRIVATE static int lockDAVMinutes

	PRIVATE WebdavResource getWebdavResource(){}
	PUBLIC String getDescription(){}
	PUBLIC boolean canRead(){}
	PUBLIC boolean canWrite(){}
	PUBLIC boolean isValidForMRU(){}
	PUBLIC boolean acquireLock(){}
	PUBLIC void releaseLock(){}
	PUBLIC InputStream getInputStream(){}
	PUBLIC OutputStream getOutputStream(){}
	PUBLIC String getPath(){}
	PUBLIC String getURLPath(){}
	PUBLIC String getUsername(){}
	PUBLIC String getPassword(){}
	PUBLIC void setUserInfo(java.lang.String,java.lang.String){}
	PUBLIC String getLastError(){}
	PUBLIC static void setLockDAVMinutes(int){}
}, 
Public class net.sourceforge.ganttproject.document.OpenDocumentAction {
	PRIVATE net.sourceforge.ganttproject.document.Document document
	PRIVATE java.awt.event.ActionListener listener
	PRIVATE static final int MENU_MASK
	PRIVATE static final int[] SHORTCUT_KEYS
	PRIVATE static final int[] MNEMONIC_KEYS

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
}, 
Public class net.sourceforge.ganttproject.document.FileDocument extends net.sourceforge.ganttproject.document.AbstractDocument{
	PRIVATE java.io.File file

	PUBLIC String getDescription(){}
	PUBLIC boolean canRead(){}
	PUBLIC boolean canWrite(){}
	PUBLIC boolean isValidForMRU(){}
	PUBLIC InputStream getInputStream(){}
	PUBLIC OutputStream getOutputStream(){}
	PUBLIC String getPath(){}
	PUBLIC String getFilePath(){}
}, 
Public class abstract net.sourceforge.ganttproject.document.AbstractDocument {

	PUBLIC boolean equals(java.lang.Object){}
	PUBLIC boolean acquireLock(){}
	PUBLIC void releaseLock(){}
	PUBLIC String getFilePath(){}
	PUBLIC String getURLPath(){}
	PUBLIC String getUsername(){}
	PUBLIC String getPassword(){}
	PUBLIC void setUserInfo(java.lang.String,java.lang.String){}
	PUBLIC String getLastError(){}
}, 
NA class net.sourceforge.ganttproject.document.HttpDocumentOutputStream {
	PRIVATE org.apache.webdav.lib.WebdavResource webdavResource

	PUBLIC void close(){}
}]], PackageObject [name=net.sourceforge.ganttproject.roles, classes=[
Public interface abstract net.sourceforge.ganttproject.roles.RoleManager {
	PUBLIC static final int DEFAULT_ROLES_NUMBER

	PUBLIC abstract RoleSet createRoleSet(java.lang.String){}
	PUBLIC abstract RoleSet[] getRoleSets(){}
	PUBLIC abstract void clear(){}
	PUBLIC abstract Role[] getProjectLevelRoles(){}
	PUBLIC abstract void add(int,java.lang.String){}
	PUBLIC abstract RoleSet getProjectRoleSet(){}
	PUBLIC abstract RoleSet getRoleSet(java.lang.String){}
	PUBLIC abstract Role[] getEnabledRoles(){}
	PUBLIC abstract Role getDefaultRole(){}
}, 
Public interface abstract net.sourceforge.ganttproject.roles.Role {

	PUBLIC abstract int getID(){}
	PUBLIC abstract String getName(){}
	PUBLIC abstract String getPersistentID(){}
}, 
Public interface abstract net.sourceforge.ganttproject.roles.RoleSet {
	PUBLIC static final java.lang.String SOFTWARE_DEVELOPMENT
	PUBLIC static final java.lang.String DEFAULT

	PUBLIC abstract String getName(){}
	PUBLIC abstract Role[] getRoles(){}
	PUBLIC abstract Role createRole(java.lang.String,int){}
	PUBLIC abstract void deleteRole(net.sourceforge.ganttproject.roles.Role){}
	PUBLIC abstract Role findRole(int){}
	PUBLIC abstract boolean isEnabled(){}
	PUBLIC abstract void setEnabled(boolean){}
	PUBLIC abstract boolean isEmpty(){}
	PUBLIC abstract void clear(){}
}, 
Public class net.sourceforge.ganttproject.roles.RolePersistentID {
	PRIVATE static final java.lang.String ROLESET_DELIMITER
	PRIVATE final java.lang.String myRoleSetID
	PRIVATE final int myRoleID

	PUBLIC String getRoleSetID(){}
	PUBLIC int getRoleID(){}
	PUBLIC String asString(){}
}, 
Public class net.sourceforge.ganttproject.roles.RoleManagerImpl {
	PUBLIC java.lang.String[] defaultRoles
	PRIVATE net.sourceforge.ganttproject.roles.RoleSetImpl myProjectRoleSet
	PRIVATE java.util.ArrayList myRoleSets
	PUBLIC static final net.sourceforge.ganttproject.roles.RoleSetImpl SOFTWARE_DEVELOPMENT_ROLE_SET
	PUBLIC static final net.sourceforge.ganttproject.roles.RoleSetImpl DEFAULT_ROLE_SET

	PUBLIC void clear(){}
	PUBLIC Role[] getProjectLevelRoles(){}
	PUBLIC void add(int,java.lang.String){}
	PUBLIC RoleSet[] getRoleSets(){}
	PUBLIC RoleSet createRoleSet(java.lang.String){}
	PUBLIC RoleSet getProjectRoleSet(){}
	PUBLIC RoleSet getRoleSet(java.lang.String){}
	PUBLIC Role[] getEnabledRoles(){}
	PUBLIC Role getDefaultRole(){}
}, 
Public class net.sourceforge.ganttproject.roles.RoleSetImpl {
	PRIVATE final java.lang.String myName
	PRIVATE final java.util.ArrayList myRoles
	PRIVATE boolean isEnabled

	PUBLIC String getName(){}
	PUBLIC Role[] getRoles(){}
	PUBLIC Role createRole(java.lang.String,int){}
	PUBLIC void deleteRole(net.sourceforge.ganttproject.roles.Role){}
	PUBLIC Role findRole(int){}
	PUBLIC boolean isEnabled(){}
	PUBLIC String toString(){}
	PUBLIC void setEnabled(boolean){}
	PUBLIC boolean isEmpty(){}
	PUBLIC void clear(){}
}, 
Public class net.sourceforge.ganttproject.roles.RoleImpl {
	PRIVATE final java.lang.String myName
	PRIVATE final int myID
	PRIVATE final net.sourceforge.ganttproject.roles.RoleSet myRoleSet

	PUBLIC int getID(){}
	PUBLIC String getName(){}
	PUBLIC String getPersistentID(){}
	PUBLIC String toString(){}
}]], PackageObject [name=net.sourceforge.ganttproject.time, classes=[
Public interface abstract net.sourceforge.ganttproject.time.TimeUnitManager {

	PUBLIC abstract TimeUnit getTimeUnit(int){}
}, 
Public interface abstract net.sourceforge.ganttproject.time.TimeUnit {
	PUBLIC static final int DAY

	PUBLIC abstract String getName(){}
	PUBLIC abstract boolean isConstructedFrom(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC abstract int getAtomCount(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC abstract TimeUnit getDirectAtomUnit(){}
	PUBLIC abstract void setTextFormatter(net.sourceforge.ganttproject.time.TextFormatter){}
	PUBLIC abstract String format(java.util.Date){}
}, 
Public interface abstract net.sourceforge.ganttproject.time.TimeUnitFunctionOfDate {

	PUBLIC abstract TimeUnit createTimeUnit(java.util.Date){}
}, 
Public class net.sourceforge.ganttproject.time.TimeUnitManagerImpl {
	PRIVATE net.sourceforge.ganttproject.time.TimeUnit[] myUnits
	PRIVATE net.sourceforge.ganttproject.time.TimeUnitGraph myTimeGraph

	PUBLIC TimeUnit getTimeUnit(int){}
}, 
Public interface abstract net.sourceforge.ganttproject.time.TextFormatter {

	PUBLIC abstract String format(net.sourceforge.ganttproject.time.TimeUnit,java.util.Date){}
}, 
Public interface abstract net.sourceforge.ganttproject.time.TimeFrame {

	PUBLIC abstract Date getStartDate(){}
	PUBLIC abstract Date getFinishDate(){}
	PUBLIC abstract TimeUnit getTopUnit(){}
	PUBLIC abstract TimeUnit getBottomUnit(){}
	PUBLIC abstract int getUnitCount(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC abstract String getUnitText(net.sourceforge.ganttproject.time.TimeUnit,int){}
	PUBLIC abstract Date getUnitStart(net.sourceforge.ganttproject.time.TimeUnit,int){}
}, 
Public class net.sourceforge.ganttproject.time.TimeUnitGraph {
	PRIVATE java.util.Map myUnit2compositions

	PUBLIC TimeUnit createAtomTimeUnit(java.lang.String){}
	PUBLIC TimeUnit createTimeUnit(java.lang.String,net.sourceforge.ganttproject.time.TimeUnit,int){}
	PUBLIC TimeUnit createDateFrameableTimeUnit(java.lang.String,net.sourceforge.ganttproject.time.TimeUnit,int,net.sourceforge.ganttproject.time.DateFrameable){}
	PUBLIC TimeUnitFunctionOfDate createTimeUnitFunctionOfDate(java.lang.String,net.sourceforge.ganttproject.time.TimeUnit,net.sourceforge.ganttproject.time.DateFrameable){}
	PRIVATE void registerTimeUnit(net.sourceforge.ganttproject.time.TimeUnit,int){}
	PUBLIC TimeUnitGraph$Composition getComposition(net.sourceforge.ganttproject.time.TimeUnitImpl,net.sourceforge.ganttproject.time.TimeUnit){}
}, 
Public interface abstract net.sourceforge.ganttproject.time.DateFrameable {

	PUBLIC abstract Date adjustRight(java.util.Date){}
	PUBLIC abstract Date adjustLeft(java.util.Date){}
	PUBLIC abstract Date jumpLeft(java.util.Date){}
}, 
Public class net.sourceforge.ganttproject.time.TimeUnitImpl {
	PRIVATE final java.lang.String myName
	PRIVATE final net.sourceforge.ganttproject.time.TimeUnitGraph myGraph
	PRIVATE final net.sourceforge.ganttproject.time.TimeUnit myDirectAtomUnit
	PRIVATE net.sourceforge.ganttproject.time.TextFormatter myTextFormatter

	PUBLIC String getName(){}
	PUBLIC boolean isConstructedFrom(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC int getAtomCount(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC TimeUnit getDirectAtomUnit(){}
	PUBLIC String toString(){}
	PUBLIC void setTextFormatter(net.sourceforge.ganttproject.time.TextFormatter){}
	PUBLIC String format(java.util.Date){}
	PROTECTED TextFormatter getTextFormatter(){}
}, 
Public class net.sourceforge.ganttproject.time.TimeUnitFunctionOfDateImpl extends net.sourceforge.ganttproject.time.TimeUnitDateFrameableImpl{
	PRIVATE final net.sourceforge.ganttproject.time.DateFrameable myDirectFrameable

	PUBLIC TimeUnit createTimeUnit(java.util.Date){}
	PUBLIC int getAtomCount(net.sourceforge.ganttproject.time.TimeUnit){}
}, 
Public class net.sourceforge.ganttproject.time.TimeUnitDateFrameableImpl extends net.sourceforge.ganttproject.time.TimeUnitImpl{
	PRIVATE final net.sourceforge.ganttproject.time.DateFrameable myFramer

	PUBLIC Date adjustRight(java.util.Date){}
	PUBLIC Date adjustLeft(java.util.Date){}
	PUBLIC Date jumpLeft(java.util.Date){}
}]], PackageObject [name=net.sourceforge.ganttproject.task.hierarchy, classes=[
Public class net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyManagerImpl {
	PRIVATE net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem myRootItem

	PUBLIC TaskHierarchyItem getRootItem(){}
	PUBLIC TaskHierarchyItem createItem(net.sourceforge.ganttproject.task.Task){}
}, 
Public class net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem {
	PRIVATE net.sourceforge.ganttproject.task.Task myTask
	PRIVATE net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem myContainerItem
	PRIVATE net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem myFirstNestedItem
	PRIVATE net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem myNextSiblingItem
	PRIVATE static final net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem[] EMPTY_ARRAY

	PUBLIC Task getTask(){}
	PUBLIC TaskHierarchyItem getContainerItem(){}
	PUBLIC TaskHierarchyItem[] getNestedItems(){}
	PUBLIC void addNestedItem(net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem){}
	PUBLIC void delete(){}
}]], PackageObject [name=net.sourceforge.ganttproject.task.dependency.constraint, classes=[
Public class net.sourceforge.ganttproject.task.dependency.constraint.FinishFinishConstraintImpl extends net.sourceforge.ganttproject.task.dependency.constraint.ConstraintImpl{

	PUBLIC TaskDependencyConstraint$Collision getCollision(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.constraint.StartStartConstraintImpl extends net.sourceforge.ganttproject.task.dependency.constraint.ConstraintImpl{

	PUBLIC TaskDependencyConstraint$Collision getCollision(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.constraint.FinishStartConstraintImpl extends net.sourceforge.ganttproject.task.dependency.constraint.ConstraintImpl{

	PUBLIC TaskDependencyConstraint$Collision getCollision(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.constraint.StartFinishConstraintImpl extends net.sourceforge.ganttproject.task.dependency.constraint.ConstraintImpl{

	PUBLIC TaskDependencyConstraint$Collision getCollision(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.constraint.ConstraintImpl {
	PRIVATE final int myID
	PRIVATE final java.lang.String myName
	PRIVATE net.sourceforge.ganttproject.task.dependency.TaskDependency myDependency

	PROTECTED TaskDependency getDependency(){}
	PUBLIC void setTaskDependency(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PUBLIC String getName(){}
	PUBLIC int getID(){}
	PUBLIC String toString(){}
}]], PackageObject [name=net.sourceforge.ganttproject.io, classes=[
Public class net.sourceforge.ganttproject.io.GanttXMLOpen {
	PUBLIC net.sourceforge.ganttproject.GanttTree treePanel
	PUBLIC net.sourceforge.ganttproject.GanttProject prj
	PUBLIC net.sourceforge.ganttproject.GanttResourcePanel peop
	PUBLIC int typeChar
	PUBLIC net.sourceforge.ganttproject.GanttGraphicArea area
	PUBLIC java.util.ArrayList lot
	PUBLIC java.util.ArrayList lod
	PUBLIC java.lang.String indent
	PUBLIC java.lang.String marge
	PUBLIC net.sourceforge.ganttproject.language.GanttLanguage language
	PUBLIC boolean bImport
	PUBLIC int maxID
	PRIVATE java.util.ArrayList myTagHandlers
	PRIVATE java.util.ArrayList myListeners
	PRIVATE net.sourceforge.ganttproject.parser.ParsingContext myContext
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager

	PUBLIC boolean load(java.lang.String){}
	PUBLIC boolean load$2(java.io.InputStream){}
	PUBLIC boolean load$3(java.io.File){}
	PUBLIC void addTagHandler(net.sourceforge.ganttproject.parser.TagHandler){}
	PUBLIC void addParsingListener(net.sourceforge.ganttproject.parser.ParsingListener){}
	PUBLIC ParsingContext getContext(){}
	PUBLIC TagHandler getDefaultTagHandler(){}
}, 
Public class net.sourceforge.ganttproject.io.CSVOptions {
	PUBLIC boolean bExportTaskID
	PUBLIC boolean bExportTaskName
	PUBLIC boolean bExportTaskStartDate
	PUBLIC boolean bExportTaskEndDate
	PUBLIC boolean bExportTaskPercent
	PUBLIC boolean bExportTaskDuration
	PUBLIC boolean bExportTaskWebLink
	PUBLIC boolean bExportTaskResources
	PUBLIC boolean bExportTaskNotes
	PUBLIC boolean bExportResourceID
	PUBLIC boolean bExportResourceName
	PUBLIC boolean bExportResourceMail
	PUBLIC boolean bExportResourcePhone
	PUBLIC boolean bExportResourceRole
	PUBLIC boolean bFixedSize
	PUBLIC java.lang.String sSeparatedChar
	PUBLIC java.lang.String sSeparatedTextChar

	PUBLIC String getXml(){}
	PUBLIC String correct(java.lang.String){}
	PUBLIC String[] getSeparatedTextChars(){}
}, 
Public class net.sourceforge.ganttproject.io.GanttCSVExport {
	PRIVATE net.sourceforge.ganttproject.GanttTree tree
	PRIVATE net.sourceforge.ganttproject.GanttResourcePanel peop
	PRIVATE net.sourceforge.ganttproject.PrjInfos prjInfos
	PRIVATE net.sourceforge.ganttproject.io.CSVOptions csvOptions
	PUBLIC java.util.ArrayList lot
	PUBLIC java.util.ArrayList resources
	PUBLIC int iMaxSize
	PUBLIC boolean bFixedSize

	PUBLIC void save(java.io.OutputStream){}
	PUBLIC void beginToSave(java.io.OutputStreamWriter){}
	PRIVATE void writeProjectInfos(java.io.OutputStreamWriter){}
	PRIVATE void writeTasks(java.io.OutputStreamWriter){}
	PRIVATE void writeResources(java.io.OutputStreamWriter){}
	PUBLIC void getMaxSize(){}
	PRIVATE String getName(javax.swing.tree.DefaultMutableTreeNode,net.sourceforge.ganttproject.GanttTask){}
	PRIVATE String getWebLink(net.sourceforge.ganttproject.GanttTask){}
	PRIVATE String getAssignments(net.sourceforge.ganttproject.GanttTask){}
	PRIVATE String correctField(java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.io.GanttTXTOpen {
	PUBLIC net.sourceforge.ganttproject.GanttTree treePanel
	PUBLIC net.sourceforge.ganttproject.GanttProject prj
	PUBLIC net.sourceforge.ganttproject.GanttGraphicArea area
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager

	PUBLIC boolean load(java.io.File){}
}, 
Public class net.sourceforge.ganttproject.io.GanttHTMLExport {
	PRIVATE static net.sourceforge.ganttproject.language.GanttLanguage language

	PRIVATE static String writeTasks(net.sourceforge.ganttproject.GanttTree){}
	PRIVATE static String writeResources(net.sourceforge.ganttproject.GanttProject){}
	PUBLIC static void save(java.io.File,net.sourceforge.ganttproject.PrjInfos,net.sourceforge.ganttproject.GanttProject,net.sourceforge.ganttproject.GanttTree,net.sourceforge.ganttproject.GanttGraphicArea,net.sourceforge.ganttproject.ResourceLoadGraphicArea,net.sourceforge.ganttproject.GanttExportSettings){}
	PUBLIC static String correct(java.lang.String){}
	PUBLIC static String getFileName(java.io.File){}
	PUBLIC static String getExtention(java.io.File){}
}, 
Public class net.sourceforge.ganttproject.io.GanttXFIGSaver {
	PRIVATE net.sourceforge.ganttproject.GanttTree tree
	PRIVATE net.sourceforge.ganttproject.GanttResourcePanel peop
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea area
	PRIVATE net.sourceforge.ganttproject.PrjInfos prjInfos
	PUBLIC java.util.ArrayList lot
	PUBLIC java.util.ArrayList lots
	PUBLIC java.util.ArrayList loc
	PUBLIC java.util.ArrayList atl
	PUBLIC java.util.ArrayList abl
	PUBLIC net.sourceforge.ganttproject.GanttCalendar dateShift
	PUBLIC float scale
	PUBLIC float chwidth
	PUBLIC float fTtextwidth
	PUBLIC boolean debug

	PUBLIC void save(java.io.OutputStream,java.lang.String){}
	PUBLIC void beginToSave(java.io.OutputStreamWriter,java.lang.String){}
	PUBLIC float getProjectTextWidth(){}
	PUBLIC float getTaskTextWidth(javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC void xfigheader(java.io.OutputStreamWriter){}
	PUBLIC void searchUserColor(){}
	PUBLIC void setProjectPlotTimes(float,float){}
	PUBLIC GanttXFIGSaver$TextObject task2text(net.sourceforge.ganttproject.GanttTask,int,int,int){}
	PUBLIC GanttXFIGSaver$BoxObject task2box(net.sourceforge.ganttproject.GanttTask,int,int,boolean){}
	PUBLIC void drawTasks(java.io.OutputStreamWriter){}
	PUBLIC void drawtext(java.io.OutputStreamWriter,net.sourceforge.ganttproject.io.GanttXFIGSaver$TextObject){}
	PUBLIC void drawbox(java.io.OutputStreamWriter,net.sourceforge.ganttproject.io.GanttXFIGSaver$BoxObject){}
	PUBLIC void labelAxes(java.io.OutputStreamWriter){}
	PRIVATE String getHexaColor(java.awt.Color){}
}, 
Public class net.sourceforge.ganttproject.io.GanttXMLSaver {
	PRIVATE final net.sourceforge.ganttproject.IGanttProject myProject
	PRIVATE net.sourceforge.ganttproject.GanttTree tree
	PRIVATE net.sourceforge.ganttproject.GanttResourcePanel peop
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea area
	PRIVATE java.util.HashMap usersId
	PUBLIC java.util.ArrayList number
	PUBLIC java.util.ArrayList lot
	PUBLIC java.util.ArrayList lots
	PUBLIC int cpt
	PUBLIC java.lang.String s

	PUBLIC String replaceAll(java.lang.String,java.lang.String,java.lang.String){}
	PUBLIC void writeTask(java.io.Writer,javax.swing.tree.DefaultMutableTreeNode,java.lang.String){}
	PUBLIC void writeResources(java.io.Writer){}
	PUBLIC void writeAllocations(java.io.Writer){}
	PUBLIC void writeRoles(java.io.Writer){}
	PUBLIC String correct(java.lang.String){}
	PUBLIC void save(java.io.OutputStream,java.lang.String){}
	PRIVATE void startElement(java.lang.String,org.xml.sax.helpers.AttributesImpl,javax.xml.transform.sax.TransformerHandler){}
	PRIVATE void endElement(java.lang.String,javax.xml.transform.sax.TransformerHandler){}
	PRIVATE void addAttribute(java.lang.String,java.lang.String,org.xml.sax.helpers.AttributesImpl){}
	PRIVATE void emptyElement(java.lang.String,org.xml.sax.helpers.AttributesImpl,javax.xml.transform.sax.TransformerHandler){}
	PRIVATE void emptyComment(javax.xml.transform.sax.TransformerHandler){}
	PUBLIC void save$2(java.io.OutputStreamWriter,java.lang.String,boolean){}
	PRIVATE void saveRoles(javax.xml.transform.sax.TransformerHandler){}
	PUBLIC void _save(java.io.OutputStreamWriter,java.lang.String,boolean){}
	PUBLIC IGanttProject getProject(){}
}, 
Public class net.sourceforge.ganttproject.io.GanttPDFExport {
	PRIVATE static net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE static javax.xml.transform.sax.SAXTransformerFactory ourTransformerFactory

	PRIVATE static String getHexaColor(java.awt.Color){}
	PRIVATE static String writeTasks(net.sourceforge.ganttproject.GanttTree){}
	PRIVATE static String writeResources(net.sourceforge.ganttproject.resource.HumanResourceManager){}
	PUBLIC static void save(java.io.File,net.sourceforge.ganttproject.IGanttProject,net.sourceforge.ganttproject.resource.HumanResourceManager,net.sourceforge.ganttproject.GanttTree,net.sourceforge.ganttproject.GanttGraphicArea,net.sourceforge.ganttproject.ResourceLoadGraphicArea,net.sourceforge.ganttproject.GanttExportSettings,java.lang.String){}
	PUBLIC static String correct(java.lang.String){}
	PUBLIC static void convert2PDF(java.lang.StringBuffer,java.io.File,java.lang.String){}
	PRIVATE static Options createOptions(){}
	PRIVATE static void createConfiguration(javax.xml.transform.sax.TransformerHandler,net.sourceforge.ganttproject.export.FontRecord[]){}
	PRIVATE static void writeTriplets(javax.xml.transform.sax.TransformerHandler,net.sourceforge.ganttproject.export.FontTriplet[]){}
	PRIVATE static SAXTransformerFactory getTransformerFactory(){}
}]], PackageObject [name=net.sourceforge.ganttproject.gui.projectwizard, classes=[
Public class net.sourceforge.ganttproject.gui.projectwizard.NewProjectWizard {
	PRIVATE final javax.swing.JFrame myMainFrame

	PUBLIC PrjInfos createNewProject(net.sourceforge.ganttproject.IGanttProject){}
}, 
Public class net.sourceforge.ganttproject.gui.projectwizard.NewProjectWizardWindow extends net.sourceforge.ganttproject.gui.projectwizard.WizardImpl{
	PRIVATE net.sourceforge.ganttproject.gui.projectwizard.I18N myI18n

	PUBLIC void addRoleSetPage(net.sourceforge.ganttproject.roles.RoleSet[]){}
	PUBLIC void addProjectNamePage(net.sourceforge.ganttproject.IGanttProject){}
	PUBLIC void nextPage(){}
	PUBLIC void show(){}
	PUBLIC void backPage(){}
}, 
Public class net.sourceforge.ganttproject.gui.projectwizard.I18N {

	PUBLIC String getNewProjectWizardWindowTitle(){}
	PUBLIC String getProjectDomainPageTitle(){}
	PUBLIC String getRolesetTooltipHeader(java.lang.String){}
	PUBLIC String getRolesetTooltipFooter(){}
	PUBLIC String formatRoleForTooltip(net.sourceforge.ganttproject.roles.Role){}
}, 
NA class abstract net.sourceforge.ganttproject.gui.projectwizard.WizardImpl {
	PRIVATE final java.util.ArrayList myPages
	PRIVATE int myCurrentPage
	PRIVATE javax.swing.JPanel myPagesContainer
	PRIVATE java.awt.CardLayout myCardLayout
	PRIVATE net.sourceforge.ganttproject.gui.projectwizard.WizardImpl$NextAction myNextAction
	PRIVATE net.sourceforge.ganttproject.gui.projectwizard.WizardImpl$BackAction myBackAction

	PUBLIC void nextPage(){}
	PUBLIC void backPage(){}
	PUBLIC void show(){}
	PRIVATE void adjustButtonState(){}
	PRIVATE JButton createNextButton(){}
	PRIVATE JButton createBackButton(){}
	PRIVATE JButton createOkButton(){}
	PROTECTED void addPage(net.sourceforge.ganttproject.gui.projectwizard.WizardPage){}
	PRIVATE WizardPage getCurrentPage(){}
}, 
Public class net.sourceforge.ganttproject.gui.projectwizard.RoleSetPage {
	PRIVATE final net.sourceforge.ganttproject.gui.projectwizard.I18N myI18N
	PRIVATE net.sourceforge.ganttproject.gui.projectwizard.RoleSetPage$RoleSetListModel myListModel

	PUBLIC String getTitle(){}
	PUBLIC Component getComponent(){}
	PUBLIC void setActive(boolean){}
}, 
Public class net.sourceforge.ganttproject.gui.projectwizard.ProjectNamePage {
	PRIVATE final net.sourceforge.ganttproject.gui.projectwizard.I18N myI18N
	PRIVATE net.sourceforge.ganttproject.gui.options.ProjectSettingsPanel myProjectSettingsPanel

	PUBLIC String getTitle(){}
	PUBLIC Component getComponent(){}
	PUBLIC void setActive(boolean){}
}, 
Public interface abstract net.sourceforge.ganttproject.gui.projectwizard.WizardPage {

	PUBLIC abstract String getTitle(){}
	PUBLIC abstract Component getComponent(){}
	PUBLIC abstract void setActive(boolean){}
}]], PackageObject [name=net.sourceforge.ganttproject.util, classes=[
Public class net.sourceforge.ganttproject.util.BrowserControl {
	PRIVATE static final java.lang.String URLTOKEN
	PRIVATE static final int WIN_ID
	PRIVATE static final java.lang.String WIN_PREFIX
	PRIVATE static final java.lang.String[] WIN_CMDLINE
	PRIVATE static final int MAC_ID
	PRIVATE static final java.lang.String MAC_PREFIX
	PRIVATE static final java.lang.String[] MAC_CMDLINE
	PRIVATE static final int OTHER_ID
	PRIVATE static final java.lang.String[][] OTHER_CMDLINES
	PRIVATE static final java.lang.String[][] OTHER_FALLBACKS

	PUBLIC static boolean displayURL(java.lang.String){}
	PRIVATE static int getPlatform(){}
	PRIVATE static String connectStringArray(java.lang.String[]){}
	PRIVATE static String[] replaceToken(java.lang.String[],java.lang.String,java.lang.String){}
	PRIVATE static boolean runCmdLine(java.lang.String[]){}
	PRIVATE static boolean runCmdLine$2(java.lang.String[],java.lang.String[]){}
}, 
Public class net.sourceforge.ganttproject.util.ColorConvertion {

	PUBLIC static String getColor(java.awt.Color){}
	PUBLIC static Color determineColor(java.lang.String){}
}]], PackageObject [name=net.sourceforge.ganttproject.gui.about, classes=[
Public class net.sourceforge.ganttproject.gui.about.AboutDialog extends net.sourceforge.ganttproject.gui.GeneralDialog{

	PUBLIC void valueChanged(javax.swing.event.TreeSelectionEvent){}
	PUBLIC void constructSections(){}
}, 
Public class net.sourceforge.ganttproject.gui.about.AboutLibraryPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.about.AboutPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.about.AboutJavaInfosPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.about.AboutLicensePanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}, 
Public class net.sourceforge.ganttproject.gui.about.AboutAuthorPanel extends net.sourceforge.ganttproject.gui.options.GeneralOptionPanel{

	PUBLIC boolean applyChanges(boolean){}
	PUBLIC void initialize(){}
}]], PackageObject [name=net.sourceforge.ganttproject.task.dependency, classes=[
Public interface abstract net.sourceforge.ganttproject.task.dependency.TaskDependencyCollection {

	PUBLIC abstract TaskDependency[] getDependencies(net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract TaskDependency[] getDependenciesAsDependant(net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract TaskDependency[] getDependenciesAsDependee(net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract TaskDependencyCollectionMutator createMutator(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.TaskDependencyException {

}, 
Public interface abstract net.sourceforge.ganttproject.task.dependency.TaskDependency {

	PUBLIC abstract Task getDependant(){}
	PUBLIC abstract Task getDependee(){}
	PUBLIC abstract void setConstraint(net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint){}
	PUBLIC abstract TaskDependencyConstraint getConstraint(){}
	PUBLIC abstract void delete(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.dependency.TaskDependencySlice {

	PUBLIC abstract TaskDependency[] toArray(){}
	PUBLIC abstract void clear(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint {

	PUBLIC abstract void setTaskDependency(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PUBLIC abstract TaskDependencyConstraint$Collision getCollision(){}
	PUBLIC abstract String getName(){}
	PUBLIC abstract int getID(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.dependency.MutableTaskDependencyCollection {

	PUBLIC abstract void clear(){}
	PUBLIC abstract TaskDependency createDependency(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract void deleteDependency(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.dependency.TaskDependencyCollectionMutator {

	PUBLIC abstract void commit(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.TaskDependencyCollectionImpl {
	PRIVATE java.util.Set myDependencies
	PRIVATE java.util.SortedMap mySearchKey2dependency
	PRIVATE final net.sourceforge.ganttproject.task.dependency.EventDispatcher myEventDispatcher

	PUBLIC TaskDependency[] getDependencies(){}
	PUBLIC TaskDependency[] getDependencies$2(net.sourceforge.ganttproject.task.Task){}
	PUBLIC TaskDependency[] getDependenciesAsDependant(net.sourceforge.ganttproject.task.Task){}
	PUBLIC TaskDependency[] getDependenciesAsDependee(net.sourceforge.ganttproject.task.Task){}
	PUBLIC TaskDependency createDependency(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.Task){}
	PUBLIC void deleteDependency(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PUBLIC void clear(){}
	PUBLIC TaskDependencyCollectionMutator createMutator(){}
	PRIVATE TaskDependency auxCreateDependency(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.Task){}
	PUBLIC void addDependency(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PUBLIC void delete(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PUBLIC void doClear(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.dependency.EventDispatcher {

	PUBLIC abstract void fireDependencyAdded(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PUBLIC abstract void fireDependencyRemoved(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.TaskDependencySliceAsDependant extends net.sourceforge.ganttproject.task.dependency.TaskDependencySliceImpl{

	PUBLIC TaskDependency[] toArray(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.TaskDependencySliceImpl {
	PRIVATE final net.sourceforge.ganttproject.task.Task myTask
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependencyCollection myDependencyCollection

	PUBLIC TaskDependency[] toArray(){}
	PUBLIC void clear(){}
	PROTECTED Task getTask(){}
	PROTECTED TaskDependencyCollection getDependencyCollection(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.TaskDependencySliceAsDependee extends net.sourceforge.ganttproject.task.dependency.TaskDependencySliceImpl{

	PUBLIC TaskDependency[] toArray(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.SearchKey {
	PUBLIC static final int DEPENDANT
	PUBLIC static final int DEPENDEE
	PUBLIC final int myFirstTaskID
	PUBLIC final int myType
	PUBLIC final int mySecondTaskID

	PUBLIC int compareTo(java.lang.Object){}
	PUBLIC boolean equals(java.lang.Object){}
	PUBLIC int hashCode(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.TaskDependencyImpl {
	PRIVATE net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint myConstraint
	PRIVATE final net.sourceforge.ganttproject.task.Task myDependant
	PRIVATE final net.sourceforge.ganttproject.task.Task myDependee
	PRIVATE net.sourceforge.ganttproject.task.dependency.TaskDependencyCollectionImpl myCollection

	PUBLIC Task getDependant(){}
	PUBLIC Task getDependee(){}
	PUBLIC void setConstraint(net.sourceforge.ganttproject.task.dependency.TaskDependencyConstraint){}
	PUBLIC TaskDependencyConstraint getConstraint(){}
	PUBLIC void delete(){}
	PUBLIC boolean equals(java.lang.Object){}
	PUBLIC int hashCode(){}
}, 
Public class net.sourceforge.ganttproject.task.dependency.RangeSearchToKey extends net.sourceforge.ganttproject.task.dependency.SearchKey{

}, 
NA class net.sourceforge.ganttproject.task.dependency.RangeSearchFromKey extends net.sourceforge.ganttproject.task.dependency.SearchKey{

}]], PackageObject [name=net.sourceforge.ganttproject.task, classes=[
Public interface abstract net.sourceforge.ganttproject.task.TaskManager {

	PUBLIC abstract Task getRootTask(){}
	PUBLIC abstract void clear(){}
	PUBLIC abstract GanttTask getTask(int){}
	PUBLIC abstract void registerTask(net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract GanttTask createTask(){}
	PUBLIC abstract GanttTask createTask$2(int){}
	PUBLIC abstract TimeUnitManager getTimeUnitManager(){}
	PUBLIC abstract TaskLength createLength(net.sourceforge.ganttproject.time.TimeUnit,long){}
	PUBLIC abstract TaskDependencyCollection getDependencyCollection(){}
	PUBLIC abstract AlgorithmCollection getAlgorithmCollection(){}
	PUBLIC abstract TaskDependencyConstraint createConstraint(int){}
	PUBLIC abstract void addTaskListener(net.sourceforge.ganttproject.task.event.TaskListener){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.Task {
	PUBLIC static final java.awt.Color DEFAULT_COLOR

	PUBLIC abstract TaskMutator createMutator(){}
	PUBLIC abstract int getTaskID(){}
	PUBLIC abstract String getName(){}
	PUBLIC abstract boolean isMilestone(){}
	PUBLIC abstract int getPriority(){}
	PUBLIC abstract GanttCalendar getStart(){}
	PUBLIC abstract GanttCalendar getEnd(){}
	PUBLIC abstract TaskLength getDuration(){}
	PUBLIC abstract int getCompletionPercentage(){}
	PUBLIC abstract boolean isStartFixed(){}
	PUBLIC abstract ShapePaint getShape(){}
	PUBLIC abstract Color getColor(){}
	PUBLIC abstract String getNotes(){}
	PUBLIC abstract boolean getExpand(){}
	PUBLIC abstract GanttTaskRelationship[] getPredecessors(){}
	PUBLIC abstract GanttTaskRelationship[] getSuccessors(){}
	PUBLIC abstract ResourceAssignment[] getAssignments(){}
	PUBLIC abstract TaskDependencySlice getDependencies(){}
	PUBLIC abstract TaskDependencySlice getDependenciesAsDependant(){}
	PUBLIC abstract TaskDependencySlice getDependenciesAsDependee(){}
	PUBLIC abstract ResourceAssignmentCollection getAssignmentCollection(){}
	PUBLIC abstract Task getSupertask(){}
	PUBLIC abstract Task[] getNestedTasks(){}
	PUBLIC abstract void move(net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract TaskManager getManager(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.TaskManagerConfig {

	PUBLIC abstract Color getDefaultColor(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.TaskContainmentHierarchyFacade {

	PUBLIC abstract Task[] getNestedTasks(net.sourceforge.ganttproject.task.Task){}
	PUBLIC abstract Task getRoot(){}
	PUBLIC abstract Task getContainer(net.sourceforge.ganttproject.task.Task){}
}, 
Public class net.sourceforge.ganttproject.task.TaskManagerImpl {
	PRIVATE final net.sourceforge.ganttproject.time.TimeUnitManager myTimeUnitManager
	PRIVATE final net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyManagerImpl myHierarchyManager
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependencyCollectionImpl myDependencyCollection
	PRIVATE final net.sourceforge.ganttproject.task.algorithm.AlgorithmCollection myAlgorithmCollection
	PRIVATE final java.util.List myListeners
	PRIVATE int myMaxID
	PRIVATE java.util.Map myId2task
	PRIVATE net.sourceforge.ganttproject.task.Task myRoot
	PRIVATE final net.sourceforge.ganttproject.task.TaskManagerConfig myConfig

	PUBLIC GanttTask getTask(int){}
	PUBLIC Task getRootTask(){}
	PUBLIC void clear(){}
	PUBLIC GanttTask createTask(){}
	PUBLIC GanttTask createTask$2(int){}
	PUBLIC void registerTask(net.sourceforge.ganttproject.task.Task){}
	PUBLIC void setTask(net.sourceforge.ganttproject.task.Task){}
	PUBLIC TimeUnitManager getTimeUnitManager(){}
	PUBLIC TaskLength createLength(net.sourceforge.ganttproject.time.TimeUnit,long){}
	PUBLIC TaskDependencyCollection getDependencyCollection(){}
	PUBLIC AlgorithmCollection getAlgorithmCollection(){}
	PUBLIC TaskHierarchyManagerImpl getHierarchyManager(){}
	PUBLIC TaskDependencyConstraint createConstraint(int){}
	PUBLIC int getMaxID(){}
	PRIVATE void setMaxID(int){}
	PUBLIC void increaseMaxID(){}
	PUBLIC void addTaskListener(net.sourceforge.ganttproject.task.event.TaskListener){}
	PUBLIC void fireTaskScheduleChanged(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.GanttCalendar,net.sourceforge.ganttproject.GanttCalendar){}
	PRIVATE void fireDependencyAdded(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PRIVATE void fireDependencyRemoved(net.sourceforge.ganttproject.task.dependency.TaskDependency){}
	PRIVATE void fireTaskAdded(net.sourceforge.ganttproject.task.Task){}
	PRIVATE void fireTaskRemoved(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.task.Task){}
	PUBLIC TaskManagerConfig getConfig(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.MutableTask {

	PUBLIC abstract void setName(java.lang.String){}
	PUBLIC abstract void setMilestone(boolean){}
	PUBLIC abstract void setPriority(int){}
	PUBLIC abstract void setStart(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC abstract void setEnd(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC abstract void setDuration(net.sourceforge.ganttproject.task.TaskLength){}
	PUBLIC abstract void setCompletionPercentage(int){}
	PUBLIC abstract void setStartFixed(boolean){}
	PUBLIC abstract void setShape(net.sourceforge.ganttproject.shape.ShapePaint){}
	PUBLIC abstract void setColor(java.awt.Color){}
	PUBLIC abstract void setNotes(java.lang.String){}
	PUBLIC abstract void addNotes(java.lang.String){}
	PUBLIC abstract void setExpand(boolean){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.TaskMutator {

	PUBLIC abstract void commit(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.TaskLength {

	PUBLIC abstract long getLength(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC abstract long getLength$2(){}
	PUBLIC abstract TimeUnit getTimeUnit(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.ResourceAssignment {

	PUBLIC abstract Task getTask(){}
	PUBLIC abstract ProjectResource getResource(){}
	PUBLIC abstract float getLoad(){}
	PUBLIC abstract void setLoad(float){}
	PUBLIC abstract void delete(){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.ResourceAssignmentCollection {

	PUBLIC abstract ResourceAssignment[] getAssignments(){}
	PUBLIC abstract ResourceAssignment getAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC abstract ResourceAssignmentMutator createMutator(){}
}, 
Public class net.sourceforge.ganttproject.task.TaskImpl {
	PRIVATE int myID
	PRIVATE final net.sourceforge.ganttproject.task.TaskManagerImpl myManager
	PRIVATE java.lang.String myName
	PRIVATE java.lang.String myWebLink
	PRIVATE boolean isMilestone
	PRIVATE int myPriority
	PRIVATE net.sourceforge.ganttproject.GanttCalendar myStart
	PRIVATE net.sourceforge.ganttproject.GanttCalendar myEnd
	PRIVATE int myCompletionPercentage
	PRIVATE net.sourceforge.ganttproject.task.TaskLength myLength
	PRIVATE boolean isStartFixed
	PRIVATE boolean bExpand
	PRIVATE net.sourceforge.ganttproject.time.TimeUnitManager myTimeUnitManager
	PRIVATE final net.sourceforge.ganttproject.task.ResourceAssignmentCollectionImpl myAssignments
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependencySlice myDependencySlice
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependencySlice myDependencySliceAsDependant
	PRIVATE final net.sourceforge.ganttproject.task.dependency.TaskDependencySlice myDependencySliceAsDependee
	PRIVATE boolean myEventsEnabled
	PRIVATE final net.sourceforge.ganttproject.task.hierarchy.TaskHierarchyItem myTaskHierarchyItem
	PRIVATE net.sourceforge.ganttproject.shape.ShapePaint myShape
	PRIVATE java.awt.Color myColor
	PRIVATE java.lang.String myNotes

	PUBLIC TaskMutator createMutator(){}
	PUBLIC int getTaskID(){}
	PUBLIC String getName(){}
	PUBLIC String getWebLink(){}
	PUBLIC boolean isMilestone(){}
	PUBLIC int getPriority(){}
	PUBLIC GanttCalendar getStart(){}
	PUBLIC GanttCalendar getEnd(){}
	PUBLIC TaskLength getDuration(){}
	PUBLIC int getCompletionPercentage(){}
	PUBLIC boolean isStartFixed(){}
	PUBLIC boolean getExpand(){}
	PUBLIC ShapePaint getShape(){}
	PUBLIC Color getColor(){}
	PUBLIC String getNotes(){}
	PUBLIC GanttTaskRelationship[] getPredecessors(){}
	PUBLIC GanttTaskRelationship[] getSuccessors(){}
	PUBLIC ResourceAssignment[] getAssignments(){}
	PUBLIC ResourceAssignmentCollection getAssignmentCollection(){}
	PUBLIC Task getSupertask(){}
	PUBLIC Task[] getNestedTasks(){}
	PUBLIC void move(net.sourceforge.ganttproject.task.Task){}
	PUBLIC TaskDependencySlice getDependencies(){}
	PUBLIC TaskDependencySlice getDependenciesAsDependant(){}
	PUBLIC TaskDependencySlice getDependenciesAsDependee(){}
	PUBLIC TaskManager getManager(){}
	PROTECTED void setTaskIDHack(int){}
	PROTECTED TimeUnitManager getTimeUnitManager(){}
	PUBLIC void setName(java.lang.String){}
	PUBLIC void setWebLink(java.lang.String){}
	PUBLIC void setMilestone(boolean){}
	PUBLIC void setPriority(int){}
	PUBLIC void setStart(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC void setEnd(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC void setDuration(net.sourceforge.ganttproject.task.TaskLength){}
	PUBLIC void setCompletionPercentage(int){}
	PUBLIC void setStartFixed(boolean){}
	PUBLIC void setShape(net.sourceforge.ganttproject.shape.ShapePaint){}
	PUBLIC void setColor(java.awt.Color){}
	PUBLIC void setNotes(java.lang.String){}
	PUBLIC void setExpand(boolean){}
	PUBLIC void addNotes(java.lang.String){}
	PROTECTED void enableEvents(boolean){}
	PROTECTED boolean areEventsEnabled(){}
	PUBLIC boolean shapeDefined(){}
	PUBLIC boolean colorDefined(){}
}, 
Public class net.sourceforge.ganttproject.task.TaskLengthImpl {
	PRIVATE final net.sourceforge.ganttproject.time.TimeUnit myUnit
	PRIVATE long myCount

	PUBLIC long getLength(){}
	PUBLIC TimeUnit getTimeUnit(){}
	PUBLIC void setLength(net.sourceforge.ganttproject.time.TimeUnit,long){}
	PUBLIC long getLength$2(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC String toString(){}
}, 
NA interface abstract net.sourceforge.ganttproject.task.MutableResourceAssignmentCollection {

	PUBLIC abstract ResourceAssignment addAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC abstract void deleteAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
}, 
Public interface abstract net.sourceforge.ganttproject.task.ResourceAssignmentMutator {

	PUBLIC abstract void commit(){}
}, 
NA class net.sourceforge.ganttproject.task.ResourceAssignmentCollectionImpl {
	PRIVATE final java.util.Map myAssignments
	PRIVATE final net.sourceforge.ganttproject.task.TaskImpl myTask

	PUBLIC ResourceAssignment[] getAssignments(){}
	PUBLIC ResourceAssignment getAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC ResourceAssignmentMutator createMutator(){}
	PUBLIC ResourceAssignmentCollectionImpl copy(){}
	PUBLIC ResourceAssignment addAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
	PUBLIC void deleteAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
	PRIVATE ResourceAssignment auxAddAssignment(net.sourceforge.ganttproject.resource.ProjectResource){}
	PRIVATE void addAssignment$2(net.sourceforge.ganttproject.task.ResourceAssignment){}
	PRIVATE Task getTask(){}
}]], PackageObject [name=net.sourceforge.ganttproject.gui, classes=[
Public class net.sourceforge.ganttproject.gui.TestGanttRolloverButton {
	PROTECTED javax.swing.Icon _iconOn
	PROTECTED javax.swing.Icon _iconOff
	PRIVATE static java.awt.AlphaComposite c

	PUBLIC void setIcon(javax.swing.Icon,javax.swing.Icon){}
	PUBLIC void setDefaultIcon(javax.swing.Icon){}
	PUBLIC boolean isOpaque(){}
	PUBLIC void setEnabled(boolean){}
	PUBLIC void paint(java.awt.Graphics){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttLookAndFeelInfo {
	PROTECTED java.lang.String toString

	PUBLIC String toString(){}
}, 
Public class net.sourceforge.ganttproject.gui.UIConfiguration {
	PRIVATE final java.awt.Font myMenuFont
	PRIVATE final java.awt.Font myChartMainFont
	PRIVATE java.awt.Color myTaskColor
	PRIVATE java.awt.Color myResColor
	PRIVATE java.awt.Color myResOverColor
	PRIVATE boolean isRedlineOn

	PUBLIC Font getMenuFont(){}
	PUBLIC Font getChartMainFont(){}
	PUBLIC Color getTaskColor(){}
	PUBLIC void setTaskColor(java.awt.Color){}
	PUBLIC Color getResourceColor(){}
	PUBLIC void setResourceColor(java.awt.Color){}
	PUBLIC Color getResourceOverloadColor(){}
	PUBLIC void setResourceOverloadColor(java.awt.Color){}
	PUBLIC boolean isRedlineOn(){}
	PUBLIC void setRedlineOn(boolean){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttStatusBar {
	PROTECTED net.sourceforge.ganttproject.gui.GanttStatusBar$ProgressBarPanel pbp
	PROTECTED net.sourceforge.ganttproject.gui.GanttStatusBar$MessagePanel message0
	PROTECTED net.sourceforge.ganttproject.gui.GanttStatusBar$MessagePanel message1
	PROTECTED net.sourceforge.ganttproject.gui.GanttStatusBar$MessagePanel message2
	PRIVATE static final int NO_MESSAGE
	PRIVATE static final int MESSAGE_1
	PRIVATE static final int MESSAGE_2
	PRIVATE static final int PROGRESS_FINISH
	PUBLIC int mode
	PUBLIC boolean bRunning

	PUBLIC void setProgressValue(int){}
	PUBLIC void setFirstText(java.lang.String){}
	PUBLIC void setSecondText(java.lang.String){}
	PUBLIC void setFirstText$2(java.lang.String,int){}
	PUBLIC void setSecondText$2(java.lang.String,int){}
	PUBLIC Dimension getPreferredSize(){}
	PUBLIC void run(){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttPreviewPrint {
	PUBLIC int[] zoomX
	PUBLIC int[] zoomY
	PUBLIC int index
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PUBLIC net.sourceforge.ganttproject.gui.GanttPreviewPrint$PreviewPanel panel
	PUBLIC javax.swing.JButton bPrint
	PUBLIC javax.swing.JButton bPortrait
	PUBLIC javax.swing.JButton bLandscape
	PUBLIC javax.swing.JButton bClose
	PUBLIC javax.swing.JButton bZoomP
	PUBLIC javax.swing.JButton bZoomM
	PUBLIC javax.swing.JSpinner factorSpinner
	PUBLIC javax.swing.SpinnerNumberModel spinnerModel

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PUBLIC void stateChanged(javax.swing.event.ChangeEvent){}
}, 
Public class net.sourceforge.ganttproject.gui.TipsDialog {
	PUBLIC javax.swing.JPanel pnPanel0
	PUBLIC javax.swing.JTextPane taArea0
	PUBLIC javax.swing.JButton btBut0
	PUBLIC javax.swing.JButton btBut1
	PUBLIC javax.swing.JButton btBut2
	PUBLIC javax.swing.JCheckBox cbBox0
	PUBLIC javax.swing.JLabel lbImg
	PUBLIC net.sourceforge.ganttproject.GanttProject appli
	PUBLIC java.lang.String[] tipsText
	PUBLIC int index

	PUBLIC void changeText(java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttURLChooser {
	PRIVATE javax.swing.JTextField urlField
	PRIVATE javax.swing.JTextField userNameField
	PRIVATE javax.swing.JPasswordField passwordField
	PUBLIC net.sourceforge.ganttproject.language.GanttLanguage language
	PUBLIC java.lang.String fileurl
	PUBLIC java.lang.String userName
	PUBLIC java.lang.String password
	PUBLIC boolean change

}, 
Public class net.sourceforge.ganttproject.gui.GanttDialogInfo {
	PUBLIC static int ERROR
	PUBLIC static int WARNING
	PUBLIC static int INFO
	PUBLIC static int QUESTION
	PUBLIC static int YES
	PUBLIC static int NO
	PUBLIC static int CANCEL
	PUBLIC int res
	PUBLIC static int YES_OPTION
	PUBLIC static int YES_NO_OPTION
	PUBLIC static int YES_NO_CANCEL_OPTION

}, 
Public class net.sourceforge.ganttproject.gui.GanttDialogProperties {
	PRIVATE javax.swing.JTextField jtfname
	PRIVATE javax.swing.JComboBox jcbfather
	PRIVATE javax.swing.JTextField jtfbegin
	PRIVATE javax.swing.JTextField jtflength
	PRIVATE javax.swing.JSlider spercent
	PRIVATE javax.swing.JLabel advancementLabel
	PRIVATE javax.swing.JCheckBox jcbbilan
	PUBLIC boolean haschild
	PRIVATE net.sourceforge.ganttproject.GanttTree ttree
	PRIVATE net.sourceforge.ganttproject.task.Task ttask
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea aarea
	PRIVATE javax.swing.JButton button
	PUBLIC int percentValue
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage lang
	PUBLIC javax.swing.JFrame prj
	PUBLIC int saveDuration
	PUBLIC java.awt.Color saveColor
	PUBLIC boolean change
	PROTECTED static javax.swing.JColorChooser colorChooser
	PRIVATE net.sourceforge.ganttproject.gui.GanttTaskPropertiesBean taskPropertiesBean
	PRIVATE net.sourceforge.ganttproject.task.Task task
	PRIVATE net.sourceforge.ganttproject.GanttTree tree
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea area
	PRIVATE javax.swing.JFrame parent

	PRIVATE int findTheIndex(java.lang.Object,java.lang.String[]){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PRIVATE TaskManager getTaskManager(){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttDialogCalendar {
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE javax.swing.JList listCalendar
	PRIVATE net.sourceforge.ganttproject.gui.GanttPanelDate panelDate
	PRIVATE javax.swing.JButton bNew
	PRIVATE javax.swing.JButton bDelete
	PRIVATE javax.swing.JButton bImport
	PRIVATE javax.swing.JButton bSelectDays
	PRIVATE javax.swing.JRadioButton bWork
	PRIVATE javax.swing.JRadioButton bNonWork

	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttLookAndFeels {
	PROTECTED java.util.Map infoByClass
	PROTECTED java.util.Map infoByName
	PROTECTED static net.sourceforge.ganttproject.gui.GanttLookAndFeels singleton

	PROTECTED void addLookAndFeel(net.sourceforge.ganttproject.gui.GanttLookAndFeelInfo){}
	PUBLIC GanttLookAndFeelInfo getInfoByClass(java.lang.String){}
	PUBLIC GanttLookAndFeelInfo getInfoByName(java.lang.String){}
	PUBLIC GanttLookAndFeelInfo getDefaultInfo(){}
	PUBLIC GanttLookAndFeelInfo[] getInstalledLookAndFeels(){}
	PUBLIC static GanttLookAndFeels getGanttLookAndFeels(){}
}, 
Public class net.sourceforge.ganttproject.gui.DialogAligner {

	PUBLIC static void center(javax.swing.JDialog,java.awt.Container){}
}, 
Public class abstract net.sourceforge.ganttproject.gui.GeneralDialog {
	PROTECTED net.sourceforge.ganttproject.language.GanttLanguage language
	PROTECTED net.sourceforge.ganttproject.GanttProject appli
	PROTECTED javax.swing.JTree treeSections
	PROTECTED javax.swing.tree.DefaultMutableTreeNode rootNode
	PROTECTED javax.swing.tree.DefaultTreeModel treeModel
	PROTECTED javax.swing.JButton okButton
	PROTECTED javax.swing.JButton cancelButton
	PROTECTED javax.swing.JButton applyButton
	PROTECTED net.sourceforge.ganttproject.gui.options.GeneralOptionPanel settingPanel
	PROTECTED javax.swing.JPanel mainPanel2
	PROTECTED javax.swing.JPanel southPanel

	PUBLIC abstract void constructSections(){}
	PUBLIC DefaultMutableTreeNode addObject(java.lang.Object,javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PROTECTED IGanttProject getProject(){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttDialogPerson {
	PRIVATE boolean change
	PRIVATE net.sourceforge.ganttproject.resource.HumanResource person
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE javax.swing.JButton cancelButton
	PRIVATE javax.swing.JLabel efficency
	PRIVATE javax.swing.JLabel email
	PRIVATE javax.swing.JTextField emailField
	PRIVATE javax.swing.JTextField emailInput1
	PRIVATE javax.swing.JLabel function
	PRIVATE javax.swing.JComboBox functionList
	PRIVATE javax.swing.JPanel jPanel1
	PRIVATE javax.swing.JPanel jPanel2
	PRIVATE javax.swing.JPanel jPanel3
	PRIVATE javax.swing.JPanel jPanel4
	PRIVATE javax.swing.JLabel name
	PRIVATE javax.swing.JTextField nameField
	PRIVATE javax.swing.JButton okButton
	PRIVATE javax.swing.JLabel persTitle
	PRIVATE javax.swing.JLabel phone
	PRIVATE javax.swing.JTextField phoneField
	PRIVATE javax.swing.JTextField phoneField1
	PRIVATE javax.swing.JLabel resourceData
	PRIVATE javax.swing.JLabel unitsPerDay

	PUBLIC boolean result(){}
	PRIVATE void initComponents(){}
	PRIVATE void okButtonActionPerformed(java.awt.event.ActionEvent){}
	PRIVATE void cancelButtonActionPerformed(java.awt.event.ActionEvent){}
	PRIVATE void nameFieldActionPerformed(java.awt.event.ActionEvent){}
	PRIVATE void emailFieldActionPerformed(java.awt.event.ActionEvent){}
	PRIVATE void closeDialog(java.awt.event.WindowEvent){}
}, 
Public class net.sourceforge.ganttproject.gui.OpenFileDialog {
	PRIVATE java.io.File myStartDirectory
	PRIVATE net.sourceforge.ganttproject.GanttProject myproject

	PUBLIC File show(){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttTaskPropertiesBean {
	PROTECTED net.sourceforge.ganttproject.GanttTask selectedTask
	PROTECTED java.util.Vector savePredecessors
	PRIVATE net.sourceforge.ganttproject.GanttTree tree
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE javax.swing.JDialog parent
	PRIVATE int length
	PRIVATE int percentComplete
	PRIVATE int priority
	PRIVATE net.sourceforge.ganttproject.GanttCalendar start
	PRIVATE net.sourceforge.ganttproject.GanttCalendar end
	PRIVATE boolean bilan
	PRIVATE java.lang.String notes
	PUBLIC java.awt.GridBagConstraints gbc
	PUBLIC java.awt.FlowLayout flowL
	PUBLIC javax.swing.JTabbedPane tabbedPane
	PUBLIC javax.swing.JPanel generalPanel
	PUBLIC javax.swing.JPanel predecessorsPanel
	PUBLIC javax.swing.JPanel resourcesPanel
	PUBLIC javax.swing.JPanel notesPanel
	PUBLIC javax.swing.JPanel firstRowPanel1
	PUBLIC javax.swing.JTextField nameField1
	PUBLIC javax.swing.JTextField durationField1
	PUBLIC javax.swing.JLabel nameLabel1
	PUBLIC javax.swing.JLabel durationLabel1
	PUBLIC javax.swing.JLabel lblWebLink
	PUBLIC javax.swing.JTextField tfWebLink
	PUBLIC javax.swing.JButton bWebLink
	PUBLIC javax.swing.JPanel secondRowPanel1
	PUBLIC javax.swing.JSpinner percentCompleteSlider
	PUBLIC javax.swing.JLabel percentCompleteLabel1
	PUBLIC javax.swing.JLabel priorityLabel1
	PUBLIC javax.swing.JComboBox priorityComboBox
	PUBLIC javax.swing.JPanel thirdRowPanel1
	PUBLIC javax.swing.JTextField startDateField1
	PUBLIC javax.swing.JTextField finishDateField1
	PUBLIC javax.swing.JLabel startDateLabel1
	PUBLIC javax.swing.JLabel finishDateLabel1
	PUBLIC javax.swing.JButton startDateButton1
	PUBLIC javax.swing.JButton finishDateButton1
	PUBLIC javax.swing.JPanel lastRowPanel1
	PUBLIC javax.swing.JPanel webLinkPanel
	PUBLIC javax.swing.JLabel mileStoneLabel1
	PUBLIC javax.swing.JCheckBox mileStoneCheckBox1
	PUBLIC javax.swing.JButton colorButton
	PUBLIC javax.swing.JButton colorSpace
	PUBLIC javax.swing.JPanel colorPanel
	PUBLIC net.sourceforge.ganttproject.shape.JPaintCombo shapeComboBox
	PUBLIC javax.swing.JLabel nameLabel2
	PUBLIC javax.swing.JLabel durationLabel2
	PUBLIC javax.swing.JTextField nameField2
	PUBLIC javax.swing.JPanel firstRowPanel2
	PUBLIC javax.swing.JScrollPane predecessorsScrollPane
	PUBLIC javax.swing.JLabel nameLabelNotes
	PUBLIC javax.swing.JLabel durationLabelNotes
	PUBLIC javax.swing.JTextField nameFieldNotes
	PUBLIC javax.swing.JTextField durationFieldNotes
	PUBLIC javax.swing.JScrollPane scrollPaneNotes
	PUBLIC javax.swing.JTextArea noteAreaNotes
	PUBLIC javax.swing.JPanel firstRowPanelNotes
	PUBLIC javax.swing.JPanel secondRowPanelNotes
	PUBLIC javax.swing.JButton okButton
	PUBLIC javax.swing.JButton cancelButton
	PUBLIC javax.swing.JPanel southPanel
	PRIVATE net.sourceforge.ganttproject.gui.taskproperties.TaskDependenciesPanel myDependenciesPanel
	PRIVATE net.sourceforge.ganttproject.gui.taskproperties.TaskAllocationsPanel myAllocationsPanel
	PRIVATE boolean isStartFixed
	PRIVATE final net.sourceforge.ganttproject.resource.HumanResourceManager myHumanResourceManager

	PRIVATE void addUsingGBL(java.awt.Container,java.awt.Component,java.awt.GridBagConstraints,int,int,int,int){}
	PRIVATE void setFirstRow(java.awt.Container,java.awt.GridBagConstraints,javax.swing.JLabel,javax.swing.JTextField,javax.swing.JLabel,javax.swing.JTextField){}
	PRIVATE void constructGeneralPanel(){}
	PUBLIC void addActionListener(java.awt.event.ActionListener){}
	PUBLIC void changeNameOfTask(){}
	PRIVATE void constructPredecessorsPanel(){}
	PRIVATE void constructResourcesPanel(){}
	PRIVATE void constructNotesPanel(){}
	PRIVATE void constructSouthPanel(){}
	PUBLIC void init(){}
	PUBLIC Task getReturnTask(){}
	PUBLIC void setSelectedTask(net.sourceforge.ganttproject.GanttTask){}
	PUBLIC void setTree(net.sourceforge.ganttproject.GanttTree){}
	PUBLIC boolean isBilan(){}
	PUBLIC GanttCalendar getEnd(){}
	PUBLIC int getLength(){}
	PUBLIC void fireDurationChanged(){}
	PUBLIC void changeLength(int){}
	PUBLIC String getNotes(){}
	PUBLIC String getTaskName(){}
	PUBLIC String getWebLink(){}
	PUBLIC int getPercentComplete(){}
	PUBLIC int getPriority(){}
	PUBLIC void setStartFixed(boolean){}
	PUBLIC GanttCalendar getStart(){}
	PUBLIC void setStart(net.sourceforge.ganttproject.GanttCalendar,boolean){}
	PUBLIC void setEnd(net.sourceforge.ganttproject.GanttCalendar,boolean){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttDialogDate {
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE net.sourceforge.ganttproject.gui.GanttPanelDate panel
	PRIVATE javax.swing.JButton jbPrevMonth
	PRIVATE javax.swing.JButton jbNextMonth
	PRIVATE javax.swing.JFormattedTextField jtDate
	PRIVATE javax.swing.JButton jbPrevYear
	PRIVATE javax.swing.JButton jbNextYear
	PRIVATE boolean myFixedDate

	PUBLIC GanttCalendar getDate(){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttPanelDate {
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE net.sourceforge.ganttproject.gui.GanttPanelDate$GanttDialogDateDay ddd
	PRIVATE net.sourceforge.ganttproject.GanttCalendar save
	PRIVATE javax.swing.JButton jbPrevMonth
	PRIVATE javax.swing.JButton jbNextMonth
	PRIVATE javax.swing.JFormattedTextField jtDate
	PRIVATE javax.swing.JButton jbPrevYear
	PRIVATE javax.swing.JButton jbNextYear

	PUBLIC void cancel(){}
	PRIVATE void changeDate(net.sourceforge.ganttproject.time.gregorian.GregorianCalendar){}
	PRIVATE void rollDate(int,int){}
	PUBLIC GanttCalendar getDate(){}
}, 
Public class net.sourceforge.ganttproject.gui.RolesTableModel {
	PUBLIC final java.lang.String[] columnNames
	PUBLIC final java.lang.Object[][] data
	PRIVATE net.sourceforge.ganttproject.roles.RoleManager myRoleManager

	PUBLIC int getColumnCount(){}
	PUBLIC int getRowCount(){}
	PUBLIC String getColumnName(int){}
	PUBLIC Object getValueAt(int,int){}
	PUBLIC boolean isCellEditable(int,int){}
	PUBLIC void setValueAt(java.lang.Object,int,int){}
	PUBLIC RoleManager getRoleManager(){}
	PUBLIC boolean hasChanges(){}
	PUBLIC void applyChanges(){}
}, 
Public class net.sourceforge.ganttproject.gui.GanttCellListRenderer {

	PUBLIC Component getListCellRendererComponent(javax.swing.JList,java.lang.Object,int,boolean,boolean){}
}, 
Public class net.sourceforge.ganttproject.gui.ResourcesTableModel {
	PUBLIC final java.lang.String[] columnNames
	PRIVATE final net.sourceforge.ganttproject.task.ResourceAssignmentCollection myAssignmentCollection
	PRIVATE final java.util.List myAssignments
	PRIVATE static final int MAX_ROW_COUNT
	PRIVATE final net.sourceforge.ganttproject.task.ResourceAssignmentMutator myMutator

	PUBLIC int getColumnCount(){}
	PUBLIC int getRowCount(){}
	PUBLIC String getColumnName(int){}
	PUBLIC Object getValueAt(int,int){}
	PUBLIC Class getColumnClass(int){}
	PUBLIC boolean isCellEditable(int,int){}
	PUBLIC void setValueAt(java.lang.Object,int,int){}
	PRIVATE void updateAssignment(java.lang.Object,int,int){}
	PRIVATE void createAssignment(java.lang.Object){}
	PUBLIC void commit(){}
}]], PackageObject [name=net.sourceforge.ganttproject.filter, classes=[
Public class net.sourceforge.ganttproject.filter.GanttHTMLFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttPNGFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttCSVFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
	PUBLIC static String getExtension(java.io.File){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttTXTFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
	PUBLIC static String getExtension(java.io.File){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttJPGFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttPDFFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttXMLFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
	PUBLIC static String getExtension(java.io.File){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttXFIGFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
}, 
Public class net.sourceforge.ganttproject.filter.GanttXSLFileFilter {

	PUBLIC boolean accept(java.io.File){}
	PUBLIC String getDescription(){}
}]], PackageObject [name=net.sourceforge.ganttproject.time.gregorian, classes=[
Public class net.sourceforge.ganttproject.time.gregorian.GregorianCalendar {

	PUBLIC void add(int,int){}
}, 
Public class net.sourceforge.ganttproject.time.gregorian.GregorianTimeUnitStack {
	PRIVATE static net.sourceforge.ganttproject.time.TimeUnitGraph ourGraph
	PRIVATE static final net.sourceforge.ganttproject.time.DateFrameable DAY_FRAMER
	PRIVATE static final net.sourceforge.ganttproject.time.DateFrameable MONTH_FRAMER
	PRIVATE static final net.sourceforge.ganttproject.time.DateFrameable HOUR_FRAMER
	PRIVATE static final net.sourceforge.ganttproject.time.DateFrameable MINUTE_FRAMER
	PRIVATE static final net.sourceforge.ganttproject.time.DateFrameable SECOND_FRAMER
	PUBLIC static final net.sourceforge.ganttproject.time.TimeUnit SECOND
	PUBLIC static final net.sourceforge.ganttproject.time.TimeUnit MINUTE
	PUBLIC static final net.sourceforge.ganttproject.time.TimeUnit HOUR
	PUBLIC static final net.sourceforge.ganttproject.time.TimeUnit DAY
	PUBLIC static final net.sourceforge.ganttproject.time.TimeUnitFunctionOfDate MONTH
	PRIVATE static final net.sourceforge.ganttproject.time.TimeUnit ATOM_UNIT
	PRIVATE static final java.util.HashMap ourUnit2field

	PUBLIC TimeFrame createTimeFrame(java.util.Date,net.sourceforge.ganttproject.time.TimeUnit,net.sourceforge.ganttproject.time.TimeUnit){}
}, 
NA class net.sourceforge.ganttproject.time.gregorian.FramerImpl {
	PRIVATE final int myCalendarField

	PUBLIC Date adjustRight(java.util.Date){}
	PRIVATE void clearFields(java.util.Calendar){}
	PUBLIC Date adjustLeft(java.util.Date){}
	PUBLIC Date jumpLeft(java.util.Date){}
}, 
Public class net.sourceforge.ganttproject.time.gregorian.DayTextFormatter {

	PUBLIC String format(net.sourceforge.ganttproject.time.TimeUnit,java.util.Date){}
}, 
Public class net.sourceforge.ganttproject.time.gregorian.MonthTextFormatter {
	PRIVATE java.text.SimpleDateFormat myFormat

	PUBLIC String format(net.sourceforge.ganttproject.time.TimeUnit,java.util.Date){}
}]], PackageObject [name=net.sourceforge.ganttproject.chart, classes=[
Public interface abstract net.sourceforge.ganttproject.chart.ChartHeader {

	PUBLIC abstract void paint(java.awt.Graphics){}
}, 
Public class net.sourceforge.ganttproject.chart.ChartModelImpl {
	PRIVATE final net.sourceforge.ganttproject.chart.ChartHeader myChartHeader
	PRIVATE final net.sourceforge.ganttproject.chart.ChartUIConfiguration myChartUIConfiguration
	PRIVATE java.awt.Dimension myBounds
	PRIVATE java.util.Date myStartDate
	PRIVATE int myAtomUnitPixels
	PRIVATE net.sourceforge.ganttproject.time.TimeFrame[] myTimeFrames
	PRIVATE final net.sourceforge.ganttproject.time.gregorian.GregorianTimeUnitStack myTimeUnitStack
	PRIVATE net.sourceforge.ganttproject.time.TimeUnit myTopUnit
	PRIVATE net.sourceforge.ganttproject.time.TimeUnit myBottomUnit

	PUBLIC ChartHeader getChartHeader(){}
	PUBLIC ChartUIConfiguration getChartUIConfiguration(){}
	PUBLIC void setBounds(java.awt.Dimension){}
	PUBLIC void setStartDate(java.util.Date){}
	PUBLIC void setBottomUnitWidth(int){}
	PUBLIC void setTopTimeUnit(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC void setBottomTimeUnit(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC Dimension getBounds(){}
	PUBLIC TimeFrame[] getTimeFrames(){}
	PUBLIC int getBottomUnitWidth(){}
	PRIVATE TimeFrame[] calculateTimeFrames(){}
}, 
Public interface abstract net.sourceforge.ganttproject.chart.ChartModel {

	PUBLIC abstract ChartHeader getChartHeader(){}
	PUBLIC abstract void setBounds(java.awt.Dimension){}
	PUBLIC abstract void setStartDate(java.util.Date){}
	PUBLIC abstract void setBottomUnitWidth(int){}
	PUBLIC abstract void setTopTimeUnit(net.sourceforge.ganttproject.time.TimeUnit){}
	PUBLIC abstract void setBottomTimeUnit(net.sourceforge.ganttproject.time.TimeUnit){}
}, 
NA class net.sourceforge.ganttproject.chart.ChartUIConfiguration {
	PRIVATE final java.awt.Font mySpanningRowTextFont
	PRIVATE final java.awt.Color mySpanningHeaderBackgroundColor
	PRIVATE final java.awt.Color myHeaderBorderColor
	PRIVATE final java.awt.Color myHorizontalGutterColor1
	PRIVATE final java.awt.Color myHorizontalGutterColor2
	PRIVATE final java.awt.Color myBottomUnitGridColor
	PRIVATE final java.awt.Font myBottomUnitFont

	PUBLIC Font getSpanningHeaderFont(){}
	PUBLIC Font getBottomUnitFont(){}
	PUBLIC int getSpanningHeaderHeight(){}
	PUBLIC Color getSpanningHeaderBackgroundColor(){}
	PUBLIC Color getHeaderBorderColor(){}
	PUBLIC Color getHorizontalGutterColor1(){}
	PUBLIC Color getHorizontalGutterColor2(){}
	PUBLIC Color getBottomUnitGridColor(){}
}, 
Public class net.sourceforge.ganttproject.chart.ChartHeaderImpl {
	PRIVATE final net.sourceforge.ganttproject.chart.ChartModelImpl myChartModel
	PRIVATE final net.sourceforge.ganttproject.chart.GraphicPrimitiveContainer myPrimitiveContainer

	PUBLIC void paint(java.awt.Graphics){}
	PRIVATE void preparePrimitives(){}
	PRIVATE void createGreyRectangleWithNiceBorders(){}
	PRIVATE void createFrames(){}
	PRIVATE int getHeight(){}
	PRIVATE int getWidth(){}
}, 
NA class net.sourceforge.ganttproject.chart.GraphicPrimitiveContainer {
	PRIVATE java.util.ArrayList myRectangles
	PRIVATE java.util.ArrayList myLines
	PRIVATE java.util.ArrayList myTexts

	PUBLIC GraphicPrimitiveContainer$Rectangle createRectangle(int,int,int,int){}
	PUBLIC GraphicPrimitiveContainer$Line createLine(int,int,int,int){}
	PUBLIC GraphicPrimitiveContainer$Text createText(int,int,java.lang.String){}
	PUBLIC void paint(java.awt.Graphics){}
}]], PackageObject [name=net.sourceforge.ganttproject.language, classes=[
Public class net.sourceforge.ganttproject.language.GanttLanguage {
	PRIVATE static net.sourceforge.ganttproject.language.GanttLanguage ganttLanguage
	PRIVATE java.util.ArrayList myListeners
	PUBLIC java.util.Locale currentLocale
	PUBLIC java.util.ResourceBundle i18n
	PUBLIC java.text.DateFormat currentDateFormat
	PUBLIC java.text.DateFormat currentTimeFormat

	PUBLIC static GanttLanguage getInstance(){}
	PUBLIC void setLocale(java.util.Locale){}
	PUBLIC String formatDate(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC String formatTime(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC GanttCalendar parseDate(java.lang.String){}
	PUBLIC String getMonth(int){}
	PUBLIC String getDay(int){}
	PUBLIC String getText(java.lang.String){}
	PUBLIC ComponentOrientation getComponentOrientation(){}
	PUBLIC void addListener(net.sourceforge.ganttproject.language.GanttLanguage$Listener){}
	PUBLIC void removeListener(net.sourceforge.ganttproject.language.GanttLanguage$Listener){}
	PRIVATE void fireLanguageChanged(){}
}]], PackageObject [name=net.sourceforge.ganttproject.parser, classes=[
Public class net.sourceforge.ganttproject.parser.RoleTagHandler {
	PRIVATE net.sourceforge.ganttproject.roles.RoleSet myRoleSet
	PRIVATE final net.sourceforge.ganttproject.roles.RoleManager myRoleManager

	PUBLIC void endElement(java.lang.String,java.lang.String,java.lang.String){}
	PRIVATE void clearRoleSet(){}
	PUBLIC void startElement(java.lang.String,java.lang.String,java.lang.String,org.xml.sax.Attributes){}
	PRIVATE void findRoleSet(java.lang.String){}
	PRIVATE void loadRoles(org.xml.sax.Attributes){}
	PRIVATE RoleManager getRoleManager(){}
}, 
Public class net.sourceforge.ganttproject.parser.DependencyTagHandler {
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager
	PRIVATE java.util.List myDependencies
	PRIVATE boolean myDependenciesSectionStarted
	PRIVATE net.sourceforge.ganttproject.parser.ParsingContext myContext

	PUBLIC void endElement(java.lang.String,java.lang.String,java.lang.String){}
	PUBLIC void startElement(java.lang.String,java.lang.String,java.lang.String,org.xml.sax.Attributes){}
	PUBLIC void parsingStarted(){}
	PUBLIC void parsingFinished(){}
	PROTECTED void loadDependency(org.xml.sax.Attributes){}
	PROTECTED int getDependencyAddressee(org.xml.sax.Attributes){}
	PROTECTED int getDependencyAddresser(org.xml.sax.Attributes){}
	PRIVATE List getDependencies(){}
	PRIVATE ParsingContext getContext(){}
}, 
Public interface abstract net.sourceforge.ganttproject.parser.ParsingListener {

	PUBLIC abstract void parsingStarted(){}
	PUBLIC abstract void parsingFinished(){}
}, 
Public class net.sourceforge.ganttproject.parser.ParsingContext {
	PRIVATE int myTaskID

	PUBLIC int getTaskID(){}
	PUBLIC void setTaskID(int){}
}, 
Public class net.sourceforge.ganttproject.parser.AllocationTagHandler {
	PRIVATE net.sourceforge.ganttproject.resource.ResourceManager myResourceManager
	PRIVATE net.sourceforge.ganttproject.task.TaskManager myTaskManager

	PUBLIC void startElement(java.lang.String,java.lang.String,java.lang.String,org.xml.sax.Attributes){}
	PUBLIC void endElement(java.lang.String,java.lang.String,java.lang.String){}
	PRIVATE void loadAllocation(org.xml.sax.Attributes){}
	PRIVATE ResourceManager getResourceManager(){}
	PRIVATE TaskManager getTaskManager(){}
}, 
Public interface abstract net.sourceforge.ganttproject.parser.TagHandler {

	PUBLIC abstract void startElement(java.lang.String,java.lang.String,java.lang.String,org.xml.sax.Attributes){}
	PUBLIC abstract void endElement(java.lang.String,java.lang.String,java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.parser.ResourceTagHandler {
	PRIVATE final java.util.HashMap myLateResource2roleBinding
	PRIVATE final net.sourceforge.ganttproject.resource.ResourceManager myResourceManager
	PRIVATE final net.sourceforge.ganttproject.roles.RoleManager myRoleManager

	PUBLIC void endElement(java.lang.String,java.lang.String,java.lang.String){}
	PUBLIC void startElement(java.lang.String,java.lang.String,java.lang.String,org.xml.sax.Attributes){}
	PRIVATE void loadResource(org.xml.sax.Attributes){}
	PRIVATE ResourceManager getResourceManager(){}
	PRIVATE Role findRole(java.lang.String){}
	PUBLIC void parsingStarted(){}
	PUBLIC void parsingFinished(){}
}, 
Public class net.sourceforge.ganttproject.parser.FileFormatException {

}]], PackageObject [name=net.sourceforge.ganttproject, classes=[
Public class net.sourceforge.ganttproject.GanttProject {
	PUBLIC static final java.lang.String version
	PRIVATE java.util.ArrayList listOfCalendar
	PUBLIC static boolean byCommandLine
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE net.sourceforge.ganttproject.GanttTree tree
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea area
	PRIVATE net.sourceforge.ganttproject.GanttResourcePanel resp
	PUBLIC javax.swing.JMenu mProject
	PUBLIC javax.swing.JMenu mMRU
	PUBLIC javax.swing.JMenu mEdit
	PUBLIC javax.swing.JMenu mTask
	PUBLIC javax.swing.JMenu mHuman
	PUBLIC javax.swing.JMenu mHelp
	PUBLIC javax.swing.JMenu mServer
	PUBLIC javax.swing.JMenu mCalendar
	PUBLIC javax.swing.JMenuItem miNew
	PUBLIC javax.swing.JMenuItem miOpen
	PUBLIC javax.swing.JMenuItem miOpenURL
	PUBLIC javax.swing.JMenuItem miSave
	PUBLIC javax.swing.JMenuItem miSaveAs
	PUBLIC javax.swing.JMenuItem miSaveAsURL
	PUBLIC javax.swing.JMenuItem miExport
	PUBLIC javax.swing.JMenuItem miImport
	PUBLIC javax.swing.JMenuItem miPrint
	PUBLIC javax.swing.JMenuItem miPreview
	PUBLIC javax.swing.JMenuItem miQuit
	PUBLIC javax.swing.JMenuItem miCut
	PUBLIC javax.swing.JMenuItem miCopy
	PUBLIC javax.swing.JMenuItem miPaste
	PUBLIC javax.swing.JMenuItem miOptions
	PUBLIC javax.swing.JMenuItem miDeleteTask
	PUBLIC javax.swing.JMenuItem miPropertiesTask
	PUBLIC javax.swing.JMenuItem miUp
	PUBLIC javax.swing.JMenuItem miDown
	PUBLIC javax.swing.JMenuItem miDelHuman
	PUBLIC javax.swing.JMenuItem miPropHuman
	PUBLIC javax.swing.JMenuItem miSendMailHuman
	PUBLIC javax.swing.JMenuItem miEditCalendar
	PUBLIC javax.swing.JMenuItem miPrjCal
	PUBLIC javax.swing.JMenuItem miWebPage
	PUBLIC javax.swing.JMenuItem miTips
	PUBLIC javax.swing.JMenuItem miAbout
	PUBLIC javax.swing.JMenuItem miManual
	PRIVATE static final int maxSizeMRU
	PRIVATE net.sourceforge.ganttproject.document.DocumentsMRU documentsMRU
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bNew
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bOpen
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bSave
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bSaveAs
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bExport
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bImport
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bPrint
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bNewTask
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bDelete
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bProperties
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bUnlink
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bLink
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bInd
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bUnind
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bUp
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bDown
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bPrev
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bNext
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bZoomIn
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bZoomOut
	PUBLIC net.sourceforge.ganttproject.gui.TestGanttRolloverButton bZoomFit
	PUBLIC net.sourceforge.ganttproject.document.Document projectDocument
	PUBLIC javax.swing.JTabbedPane tabpane
	PUBLIC net.sourceforge.ganttproject.PrjInfos prjInfos
	PUBLIC boolean askForSave
	PUBLIC net.sourceforge.ganttproject.gui.GanttLookAndFeelInfo lookAndFeel
	PRIVATE java.util.Hashtable managerHash
	PRIVATE net.sourceforge.ganttproject.action.ResourceActionSet myResourceActions
	PRIVATE boolean isApplet
	PRIVATE javax.swing.JFrame helpFrame
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager
	PRIVATE net.sourceforge.ganttproject.GanttProject$FacadeInvalidator myFacadeInvalidator
	PRIVATE net.sourceforge.ganttproject.gui.UIConfiguration myUIConfiguration
	PRIVATE final net.sourceforge.ganttproject.GanttOptions options
	PRIVATE javax.swing.JToolBar toolBar
	PRIVATE net.sourceforge.ganttproject.gui.GanttStatusBar statusBar
	PRIVATE net.sourceforge.ganttproject.action.NewTaskAction myNewTaskAction
	PRIVATE net.sourceforge.ganttproject.action.NewHumanAction myNewHumanAction
	PRIVATE net.sourceforge.ganttproject.action.NewArtefactAction myNewArtefactAction
	PRIVATE static final java.lang.String HUMAN_RESOURCE_MANAGER_ID

	PRIVATE void updateMenuMRU(){}
	PUBLIC String getXslDir(){}
	PUBLIC GanttOptions getOptions(){}
	PUBLIC void restoreOptions(){}
	PUBLIC GanttStatusBar getStatusBar(){}
	PUBLIC String getXslFo(){}
	PUBLIC void setMemonic(){}
	PUBLIC JMenuItem createNewItemText(java.lang.String){}
	PUBLIC JMenuItem createNewItem(java.lang.String){}
	PUBLIC JMenuItem createNewItem$2(java.lang.String,java.lang.String){}
	PUBLIC void changeLanguage(){}
	PUBLIC void changeLookAndFeel(net.sourceforge.ganttproject.gui.GanttLookAndFeelInfo){}
	PUBLIC static String correctLabel(java.lang.String){}
	PUBLIC JMenu changeMenuLabel(javax.swing.JMenu,java.lang.String){}
	PUBLIC JMenuItem changeMenuLabel$2(javax.swing.JMenuItem,java.lang.String){}
	PRIVATE void changeLanguageOfMenu(){}
	PUBLIC static String getToolTip(java.lang.String){}
	PUBLIC void setButtonText(){}
	PUBLIC void applyButtonOptions(){}
	PUBLIC void addButtons(javax.swing.JToolBar){}
	PRIVATE String getDisplayName(net.sourceforge.ganttproject.resource.ProjectResource[]){}
	PRIVATE void upDatas(){}
	PRIVATE void downDatas(){}
	PRIVATE void exitForm(java.awt.event.WindowEvent){}
	PRIVATE boolean checkCurrentProject(){}
	PUBLIC void actionPerformed(java.awt.event.ActionEvent){}
	PUBLIC void launchOptionsDialog(){}
	PUBLIC Task newTask(){}
	PUBLIC void deleteTasks(){}
	PUBLIC void propertiesTask(){}
	PUBLIC void propertiesTask$2(javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC void unlinkRelationships(){}
	PUBLIC void linkRelationships(){}
	PUBLIC void export(){}
	PRIVATE static String getExceptionReport(java.lang.Exception){}
	PRIVATE void doExport(net.sourceforge.ganttproject.GanttProject$ExportFileInfo){}
	PUBLIC void refreshProjectInfos(){}
	PRIVATE GanttProject$ExportFileInfo selectExportFile(javax.swing.filechooser.FileFilter){}
	PUBLIC void importcbk(){}
	PUBLIC void printProject(){}
	PUBLIC void previewPrint(){}
	PUBLIC void newProject(){}
	PRIVATE void showNewProjectWizard(){}
	PRIVATE void closeProject(){}
	PUBLIC void setResourcePanelToGraphicArea(){}
	PUBLIC void openFile(){}
	PUBLIC void openURL(){}
	PUBLIC void openURL$2(net.sourceforge.ganttproject.document.Document){}
	PRIVATE void openDocument(net.sourceforge.ganttproject.document.Document){}
	PUBLIC void openStartupDocument(java.lang.String){}
	PUBLIC void openStartupDocument$2(net.sourceforge.ganttproject.document.Document){}
	PUBLIC void openXMLStream(java.io.InputStream,java.lang.String){}
	PUBLIC boolean saveAsProject(){}
	PUBLIC boolean saveAsURLProject(){}
	PUBLIC boolean saveAsURLProject$2(net.sourceforge.ganttproject.document.Document){}
	PUBLIC void saveProject(){}
	PUBLIC void saveProject$2(net.sourceforge.ganttproject.document.Document){}
	PUBLIC void changeWorkingDirectory(java.lang.String){}
	PUBLIC UIConfiguration getUIConfiguration(){}
	PUBLIC void quitApplication(){}
	PUBLIC void openWebPage(){}
	PUBLIC void setAskForSave(boolean){}
	PRIVATE static void usage(){}
	PUBLIC GanttResourcePanel getResourcePanel(){}
	PRIVATE GanttLanguage getLanguage(){}
	PUBLIC GanttGraphicArea getArea(){}
	PRIVATE GanttTree getTree(){}
	PRIVATE ResourceActionSet getResourceActions(){}
	PUBLIC static void main(java.lang.String[]){}
	PUBLIC static boolean checkProjectFile(java.lang.String[]){}
	PRIVATE static void exportProject(java.lang.String[]){}
	PRIVATE static void exportPDF(java.lang.String[]){}
	PRIVATE static void exportPNG(java.lang.String[]){}
	PRIVATE static void exportXFIG(java.lang.String[]){}
	PRIVATE static void exportCSV(java.lang.String[]){}
	PRIVATE static void exportJPG(java.lang.String[]){}
	PUBLIC String getProjectName(){}
	PUBLIC void setProjectName(java.lang.String){}
	PUBLIC String getDescription(){}
	PUBLIC void setDescription(java.lang.String){}
	PUBLIC String getOrganization(){}
	PUBLIC void setOrganization(java.lang.String){}
	PUBLIC String getWebLink(){}
	PUBLIC void setWebLink(java.lang.String){}
	PUBLIC ResourceManager getHumanResourceManager(){}
	PUBLIC TaskManager getTaskManager(){}
	PUBLIC RoleManager getRoleManager(){}
	PUBLIC GanttLanguage getI18n(){}
	PUBLIC void resourceAdded(net.sourceforge.ganttproject.resource.ResourceEvent){}
	PUBLIC void resourcesRemoved(net.sourceforge.ganttproject.resource.ResourceEvent){}
}, 
Public interface abstract net.sourceforge.ganttproject.IGanttProject {

	PUBLIC abstract String getProjectName(){}
	PUBLIC abstract void setProjectName(java.lang.String){}
	PUBLIC abstract String getDescription(){}
	PUBLIC abstract void setDescription(java.lang.String){}
	PUBLIC abstract String getOrganization(){}
	PUBLIC abstract void setOrganization(java.lang.String){}
	PUBLIC abstract String getWebLink(){}
	PUBLIC abstract void setWebLink(java.lang.String){}
	PUBLIC abstract Task newTask(){}
	PUBLIC abstract GanttLanguage getI18n(){}
	PUBLIC abstract UIConfiguration getUIConfiguration(){}
	PUBLIC abstract ResourceManager getHumanResourceManager(){}
	PUBLIC abstract RoleManager getRoleManager(){}
	PUBLIC abstract TaskManager getTaskManager(){}
}, 
Public class net.sourceforge.ganttproject.GanttTree {
	PRIVATE javax.swing.tree.DefaultMutableTreeNode rootNode
	PRIVATE javax.swing.tree.DefaultTreeModel treeModel
	PRIVATE javax.swing.JTree tree
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea area
	PRIVATE net.sourceforge.ganttproject.GanttProject appli
	PRIVATE static final int AUTOSCROLL_MARGIN
	PUBLIC javax.swing.JScrollBar vbar
	PUBLIC net.sourceforge.ganttproject.language.GanttLanguage language
	PUBLIC int nbTasks
	PRIVATE javax.swing.tree.TreePath dragPath
	PRIVATE java.awt.image.BufferedImage ghostImage
	PRIVATE java.awt.Point offsetPoint
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager
	PRIVATE javax.swing.tree.DefaultMutableTreeNode cpNode

	PRIVATE void initRootNode(){}
	PUBLIC void createPopupMenu(int,int,boolean){}
	PUBLIC void setGraphicArea(net.sourceforge.ganttproject.GanttGraphicArea){}
	PUBLIC DefaultMutableTreeNode addObjectWithExpand(java.lang.Object,javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC DefaultMutableTreeNode addObject(java.lang.Object,javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC GanttTask getSelectedTask(){}
	PUBLIC DefaultMutableTreeNode getSelectedNode(){}
	PUBLIC DefaultMutableTreeNode[] getSelectedNodes(){}
	PUBLIC Task getTask(java.lang.String){}
	PUBLIC DefaultMutableTreeNode getNode(int){}
	PUBLIC boolean hasTasks(){}
	PUBLIC ArrayList getAllTasks(){}
	PUBLIC ArrayList getAllChildTask(net.sourceforge.ganttproject.task.Task){}
	PUBLIC ArrayList getAllChildTask$2(javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC void computePercentComplete(javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC DefaultMutableTreeNode getLastNode(){}
	PUBLIC Object[] getAllTaskArray(){}
	PUBLIC String[] getAllTaskString(java.lang.String){}
	PUBLIC ArrayList getArryListTaskString(java.lang.String){}
	PUBLIC void removeCurrentNode(){}
	PUBLIC void clearTree(){}
	PUBLIC void selectTreeRow(int){}
	PUBLIC DefaultMutableTreeNode getFatherNode(net.sourceforge.ganttproject.task.Task){}
	PUBLIC DefaultMutableTreeNode getFatherNode$2(javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC JTree getJTree(){}
	PUBLIC DefaultMutableTreeNode getRoot(){}
	PUBLIC boolean checkDepend(net.sourceforge.ganttproject.task.Task,net.sourceforge.ganttproject.GanttTask){}
	PUBLIC void upCurrentNodes(){}
	PUBLIC void downCurrentNodes(){}
	PUBLIC void indentCurrentNodes(){}
	PUBLIC void dedentCurrentNodes(){}
	PUBLIC void expandRefresh(javax.swing.tree.DefaultMutableTreeNode){}
	PUBLIC void refreshAllChild(java.lang.String){}
	PUBLIC void refreshAllFather(java.lang.String){}
	PUBLIC void cutSelectedNode(){}
	PUBLIC void copySelectedNode(){}
	PUBLIC void pasteNode(){}
	PRIVATE void insertClonedNode(javax.swing.tree.DefaultMutableTreeNode,javax.swing.tree.DefaultMutableTreeNode,int,boolean){}
	PUBLIC void forwardScheduling(){}
	PUBLIC ArrayList getAllGanttTasks(){}
	PRIVATE void setAllTasksUnchecked(){}
	PRIVATE TaskManager getTaskManager(){}
	PUBLIC void dragEnter(java.awt.dnd.DragSourceDragEvent){}
	PUBLIC void dragOver(java.awt.dnd.DragSourceDragEvent){}
	PUBLIC void dropActionChanged(java.awt.dnd.DragSourceDragEvent){}
	PUBLIC void dragDropEnd(java.awt.dnd.DragSourceDropEvent){}
	PUBLIC void dragExit(java.awt.dnd.DragSourceEvent){}
	PUBLIC void dragGestureRecognized(java.awt.dnd.DragGestureEvent){}
}, 
Public class net.sourceforge.ganttproject.GanttGraphicArea {
	PUBLIC net.sourceforge.ganttproject.GanttCalendar date
	PUBLIC net.sourceforge.ganttproject.GanttCalendar olddate
	PUBLIC net.sourceforge.ganttproject.GanttTree tree
	PUBLIC static final int ONE_WEEK
	PUBLIC static final int TWO_WEEK
	PUBLIC static final int ONE_MONTH
	PUBLIC static final int TWO_MONTH
	PUBLIC static final int THREE_MONTH
	PUBLIC static final int FOUR_MONTH
	PUBLIC static final int SIX_MONTH
	PUBLIC static final int ONE_YEAR
	PUBLIC static final int TWO_YEAR
	PUBLIC static final int THREE_YEAR
	PUBLIC static java.awt.Color taskDefaultColor
	PRIVATE int zoomValue
	PRIVATE java.util.ArrayList listOfParam
	PRIVATE int margY
	PRIVATE java.awt.Point upperLeft
	PRIVATE boolean printRendering
	PRIVATE boolean drawdepend
	PRIVATE boolean drawPercent
	PRIVATE boolean drawName
	PRIVATE boolean draw3dBorders
	PRIVATE boolean drawVersion
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE net.sourceforge.ganttproject.GanttProject appli
	PRIVATE int oldX
	PRIVATE int oldY
	PRIVATE boolean moveView
	PRIVATE int moveTask
	PUBLIC boolean curs
	PUBLIC int drag
	PUBLIC int typeSeletion
	PRIVATE int storeTaskLength
	PRIVATE float addTaskLength
	PRIVATE net.sourceforge.ganttproject.GanttCalendar storeTaskStart
	PRIVATE int[] storeX
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea$Arrow arrow
	PRIVATE net.sourceforge.ganttproject.GanttGraphicArea$Notes notes
	PRIVATE net.sourceforge.ganttproject.GanttTask taskToMove
	PRIVATE java.awt.Color[] arrayColor
	PRIVATE java.util.ArrayList listOfTask
	PUBLIC net.sourceforge.ganttproject.GanttCalendar beg
	PUBLIC net.sourceforge.ganttproject.GanttCalendar end
	PUBLIC int mouseButton
	PRIVATE final net.sourceforge.ganttproject.gui.UIConfiguration myUIConfiguration
	PRIVATE java.awt.Color myProjectLevelTaskColor
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager
	PRIVATE int rowCount

	PRIVATE TaskManager getTaskManager(){}
	PUBLIC Color getTaskColor(){}
	PUBLIC void setProjectLevelTaskColor(java.awt.Color){}
	PUBLIC int detectPosition(int,int,boolean){}
	PUBLIC void setTaskToMove(int){}
	PUBLIC Dimension getPreferredSize(){}
	PUBLIC void changeDate(boolean){}
	PUBLIC void changeDate2(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC void zoomToBegin(){}
	PUBLIC void paintComponent(java.awt.Graphics){}
	PUBLIC void drawGPVersion(java.awt.Graphics){}
	PUBLIC boolean isVisible(net.sourceforge.ganttproject.task.Task){}
	PUBLIC int indexOf(java.util.ArrayList,int){}
	PUBLIC void setScrollBar(int){}
	PUBLIC int getScrollBar(){}
	PUBLIC void setZoom(int){}
	PUBLIC void zoomMore(){}
	PUBLIC void zoomLess(){}
	PUBLIC int getZoom(){}
	PUBLIC void setDate(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC GanttCalendar getDate(){}
	PUBLIC int getGranit(boolean){}
	PUBLIC int getFoot(){}
	PUBLIC void calcProjectBegAndEnd(){}
	PUBLIC void paintCalendar1(java.awt.Graphics){}
	PUBLIC void paintCalendar2New(java.awt.Graphics){}
	PUBLIC void paintCalendar2(java.awt.Graphics){}
	PUBLIC void paintCalendar2Old(java.awt.Graphics){}
	PUBLIC void paintTasks(java.awt.Graphics){}
	PUBLIC void paintATaskFather(java.awt.Graphics,int,int,int,net.sourceforge.ganttproject.task.Task){}
	PUBLIC void paintATaskChild(java.awt.Graphics,int,int,int,net.sourceforge.ganttproject.task.Task){}
	PUBLIC void paintATaskBilan(java.awt.Graphics,int,int,net.sourceforge.ganttproject.task.Task){}
	PUBLIC void paintResources(int,int,net.sourceforge.ganttproject.task.Task,java.awt.Graphics){}
	PUBLIC void paintDepend(java.awt.Graphics){}
	PUBLIC int paintAdvancement(java.awt.Graphics,int,int,int,int,net.sourceforge.ganttproject.shape.ShapePaint,java.awt.Color,boolean){}
	PUBLIC void drawVerticalLinedash(java.awt.Graphics,int,int,int,int){}
	PUBLIC BufferedImage getChart(net.sourceforge.ganttproject.GanttExportSettings){}
	PRIVATE void printTasks(java.awt.Graphics){}
	PRIVATE int printTask(java.awt.Graphics,int,int,java.util.ArrayList){}
	PUBLIC void printProject(net.sourceforge.ganttproject.GanttExportSettings){}
	PUBLIC void export(java.io.File,net.sourceforge.ganttproject.GanttExportSettings,java.lang.String){}
	PRIVATE GanttTree getTree(){}
	PUBLIC void fitWholeProject(boolean){}
}, 
Public class net.sourceforge.ganttproject.GanttResourcePanel {
	PUBLIC int nbobj
	PUBLIC javax.swing.JTable table
	PUBLIC net.sourceforge.ganttproject.GanttTree tree
	PUBLIC net.sourceforge.ganttproject.GanttResourcePanel$GanttTableModel model
	PUBLIC net.sourceforge.ganttproject.language.GanttLanguage lang
	PUBLIC net.sourceforge.ganttproject.GanttProject appli
	PUBLIC int cx
	PUBLIC int cy
	PUBLIC javax.swing.JScrollBar vbar
	PRIVATE net.sourceforge.ganttproject.action.ResourceActionSet myResourceActionSet
	PUBLIC net.sourceforge.ganttproject.ResourceLoadGraphicArea area
	PUBLIC javax.swing.JScrollPane scrollpane
	PUBLIC javax.swing.JPanel left
	PRIVATE net.sourceforge.ganttproject.resource.ProjectResource[] EMPTY_CONTEXT
	PRIVATE final net.sourceforge.ganttproject.resource.ResourceContext myContext

	PUBLIC void createPopupMenu(int,int){}
	PUBLIC void refresh(net.sourceforge.ganttproject.language.GanttLanguage){}
	PUBLIC String getNameByNumber(int){}
	PUBLIC HumanResource getUserByNumber(int){}
	PUBLIC void newHuman(net.sourceforge.ganttproject.resource.HumanResource){}
	PUBLIC void addHumans(java.util.ArrayList){}
	PUBLIC void deleteHuman(net.sourceforge.ganttproject.GanttProject){}
	PUBLIC void propertiesHuman(net.sourceforge.ganttproject.GanttProject){}
	PUBLIC void sendMail(net.sourceforge.ganttproject.GanttProject){}
	PUBLIC void upResource(){}
	PUBLIC void downResource(){}
	PUBLIC ArrayList getPeople(){}
	PUBLIC int nbPeople(){}
	PUBLIC void setPeople(java.util.ArrayList){}
	PUBLIC void reset(){}
	PUBLIC void setResourceActions(net.sourceforge.ganttproject.action.ResourceActionSet){}
	PUBLIC void setTree(net.sourceforge.ganttproject.GanttTree){}
	PUBLIC void resourceAdded(net.sourceforge.ganttproject.resource.ResourceEvent){}
	PUBLIC void resourcesRemoved(net.sourceforge.ganttproject.resource.ResourceEvent){}
	PUBLIC ProjectResource[] getResources(){}
	PUBLIC ResourceContext getContext(){}
}, 
Public class net.sourceforge.ganttproject.PrjInfos {
	PUBLIC java.lang.String _sProjectName
	PUBLIC java.lang.String _sDescription
	PUBLIC java.lang.String _sOrganization
	PUBLIC java.lang.String _sWebLink

	PUBLIC String getName(){}
	PUBLIC String getDescription(){}
	PUBLIC String getOrganization(){}
	PUBLIC String getWebLink(){}
}, 
Public class net.sourceforge.ganttproject.GanttOptions {
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE int x
	PRIVATE int y
	PRIVATE int width
	PRIVATE int height
	PRIVATE java.lang.String styleClass
	PRIVATE java.lang.String styleName
	PRIVATE net.sourceforge.ganttproject.gui.GanttLookAndFeelInfo lookAndFeel
	PRIVATE boolean isloaded
	PRIVATE boolean automatic
	PRIVATE boolean dragTime
	PRIVATE boolean openTips
	PRIVATE boolean redline
	PRIVATE int lockDAVMinutes
	PRIVATE java.lang.String xslDir
	PRIVATE java.lang.String xslFo
	PRIVATE java.lang.String workingDir
	PRIVATE final net.sourceforge.ganttproject.roles.RoleManager myRoleManager
	PRIVATE net.sourceforge.ganttproject.document.DocumentsMRU documentsMRU
	PRIVATE net.sourceforge.ganttproject.gui.UIConfiguration myUIConfig
	PRIVATE java.awt.Font myChartMainFont
	PRIVATE java.lang.String sTaskNamePrefix
	PRIVATE int toolBarPosition
	PRIVATE boolean bShowStatusBar
	PRIVATE java.lang.String iconSize
	PUBLIC static final int ICONS
	PUBLIC static final int ICONS_TEXT
	PUBLIC static final int TEXT
	PRIVATE int buttonsshow
	PRIVATE boolean bExportName
	PRIVATE boolean bExportComplete
	PRIVATE boolean bExportRelations
	PRIVATE boolean bExport3DBorders
	PRIVATE net.sourceforge.ganttproject.io.CSVOptions csvOptions
	PRIVATE java.awt.Font myMenuFont

	PUBLIC void initByDefault(){}
	PRIVATE void startElement(java.lang.String,org.xml.sax.Attributes,javax.xml.transform.sax.TransformerHandler){}
	PRIVATE void endElement(java.lang.String,javax.xml.transform.sax.TransformerHandler){}
	PRIVATE void addAttribute(java.lang.String,java.lang.String,org.xml.sax.helpers.AttributesImpl){}
	PRIVATE void emptyElement(java.lang.String,org.xml.sax.helpers.AttributesImpl,javax.xml.transform.sax.TransformerHandler){}
	PUBLIC void save(){}
	PRIVATE String getFontSpec(java.awt.Font){}
	PRIVATE String getFontStyle(java.awt.Font){}
	PUBLIC String correct(java.lang.String){}
	PUBLIC boolean load(){}
	PRIVATE void loadRoleSets(java.io.File){}
	PRIVATE void saveRoleSets(javax.xml.transform.sax.TransformerHandler){}
	PRIVATE void saveRoles(net.sourceforge.ganttproject.roles.RoleSet,javax.xml.transform.sax.TransformerHandler){}
	PUBLIC UIConfiguration getUIConfiguration(){}
	PRIVATE RoleManager getRoleManager(){}
	PUBLIC GanttLanguage getLanguage(){}
	PUBLIC Color getDefaultColor(){}
	PUBLIC Color getResourceColor(){}
	PUBLIC Color getResourceOverloadColor(){}
	PUBLIC int getLockDAVMinutes(){}
	PUBLIC String getWorkingDir(){}
	PUBLIC String getXslDir(){}
	PUBLIC String getXslFo(){}
	PUBLIC boolean getOpenTips(){}
	PUBLIC boolean getDragTime(){}
	PUBLIC boolean getAutomatic(){}
	PUBLIC GanttLookAndFeelInfo getLnfInfos(){}
	PUBLIC boolean isLoaded(){}
	PUBLIC boolean getShowStatusBar(){}
	PUBLIC void setShowStatusBar(boolean){}
	PUBLIC int getX(){}
	PUBLIC int getY(){}
	PUBLIC int getWidth(){}
	PUBLIC int getHeight(){}
	PUBLIC CSVOptions getCSVOptions(){}
	PUBLIC String getTaskNamePrefix(){}
	PUBLIC String getTrueTaskNamePrefix(){}
	PUBLIC int getToolBarPosition(){}
	PUBLIC String getIconSize(){}
	PUBLIC boolean getExportName(){}
	PUBLIC boolean getExportComplete(){}
	PUBLIC boolean getExportRelations(){}
	PUBLIC boolean getExport3dBorders(){}
	PUBLIC GanttExportSettings getExportSettings(){}
	PUBLIC void setExportName(boolean){}
	PUBLIC void setExportComplete(boolean){}
	PUBLIC void setExportRelations(boolean){}
	PUBLIC void setExport3dBorders(boolean){}
	PUBLIC int getButtonShow(){}
	PUBLIC void setButtonShow(int){}
	PUBLIC void setIconSize(java.lang.String){}
	PUBLIC void setToolBarPosition(int){}
	PUBLIC void setWindowPosition(int,int){}
	PUBLIC void setWindowSize(int,int){}
	PUBLIC void setWorkingDirectory(java.lang.String){}
	PUBLIC void setLockDAVMinutes(int){}
	PUBLIC void setDefaultTaskColor(java.awt.Color){}
	PUBLIC void setResourceColor(java.awt.Color){}
	PUBLIC void setResourceOverloadColor(java.awt.Color){}
	PUBLIC void setXslDir(java.lang.String){}
	PUBLIC void setXslFo(java.lang.String){}
	PUBLIC void setDocumentsMRU(net.sourceforge.ganttproject.document.DocumentsMRU){}
	PUBLIC void setUIConfiguration(net.sourceforge.ganttproject.gui.UIConfiguration){}
	PUBLIC void setOpenTips(boolean){}
	PUBLIC void setAutomatic(boolean){}
	PUBLIC void setDragTime(boolean){}
	PUBLIC void setLookAndFeel(net.sourceforge.ganttproject.gui.GanttLookAndFeelInfo){}
	PUBLIC void setTaskNamePrefix(java.lang.String){}
}, 
Public class net.sourceforge.ganttproject.GanttExportSettings {
	PUBLIC boolean name
	PUBLIC boolean percent
	PUBLIC boolean depend
	PUBLIC boolean border3d
	PUBLIC boolean ok

}, 
Public class net.sourceforge.ganttproject.ResourceLoadGraphicArea {
	PUBLIC net.sourceforge.ganttproject.GanttCalendar date
	PUBLIC net.sourceforge.ganttproject.GanttCalendar olddate
	PUBLIC javax.swing.JTable table
	PUBLIC static final int ONE_WEEK
	PUBLIC static final int TWO_WEEK
	PUBLIC static final int ONE_MONTH
	PUBLIC static final int TWO_MONTH
	PUBLIC static final int THREE_MONTH
	PUBLIC static final int FOUR_MONTH
	PUBLIC static final int SIX_MONTH
	PUBLIC static final int ONE_YEAR
	PUBLIC static final int TWO_YEAR
	PUBLIC static final int THREE_YEAR
	PUBLIC static java.awt.Color taskDefaultColor
	PRIVATE int zoomValue
	PRIVATE java.util.ArrayList listOfParam
	PRIVATE int margY
	PRIVATE java.awt.Point upperLeft
	PRIVATE boolean printRendering
	PRIVATE boolean drawdepend
	PRIVATE boolean drawPercent
	PRIVATE boolean drawName
	PRIVATE boolean drawVersion
	PRIVATE boolean draw3dBorders
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE net.sourceforge.ganttproject.GanttProject appli
	PRIVATE int oldX
	PRIVATE int oldY
	PRIVATE boolean moveView
	PRIVATE int moveTask
	PUBLIC boolean curs
	PUBLIC int typeSeletion
	PRIVATE int storeTaskLength
	PRIVATE float addTaskLength
	PRIVATE net.sourceforge.ganttproject.GanttCalendar storeTaskStart
	PRIVATE int[] storeX
	PRIVATE java.awt.Color[] arrayColor
	PRIVATE java.util.ArrayList listOfTask
	PUBLIC net.sourceforge.ganttproject.GanttCalendar beg
	PUBLIC net.sourceforge.ganttproject.GanttCalendar end
	PRIVATE java.util.ArrayList loads
	PUBLIC net.sourceforge.ganttproject.GanttTree tree
	PUBLIC int mouseButton
	PRIVATE int rowCount

	PUBLIC Dimension getPreferredSize(){}
	PUBLIC void changeDate(boolean){}
	PUBLIC void changeDate2(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC void zoomToBegin(){}
	PUBLIC void paintComponent(java.awt.Graphics){}
	PUBLIC void drawGPVersion(java.awt.Graphics){}
	PUBLIC int indexOf(java.util.ArrayList,java.lang.String){}
	PUBLIC void setScrollBar(int){}
	PUBLIC int getScrollBar(){}
	PUBLIC void setZoom(int){}
	PUBLIC void zoomMore(){}
	PUBLIC void zoomLess(){}
	PUBLIC int getZoom(){}
	PUBLIC void setDate(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC GanttCalendar getDate(){}
	PUBLIC void setLanguage(net.sourceforge.ganttproject.language.GanttLanguage){}
	PUBLIC int getGranit(boolean){}
	PUBLIC int getFoot(){}
	PUBLIC void paintCalendar1(java.awt.Graphics){}
	PUBLIC void drawVerticalLinedash(java.awt.Graphics,int,int,int,int){}
	PUBLIC void paintCalendar2(java.awt.Graphics){}
	PUBLIC void paintLoads(java.awt.Graphics){}
	PUBLIC void paintEndBorder(java.awt.Graphics,int,int){}
	PUBLIC void paintAResourceLoad(java.awt.Graphics,int,int,int,java.lang.String,java.awt.Color,boolean,boolean){}
	PRIVATE void calculateLoad(java.util.ArrayList){}
	PUBLIC BufferedImage getChart(net.sourceforge.ganttproject.GanttExportSettings){}
	PRIVATE void printResources(java.awt.Graphics){}
	PUBLIC void export(java.io.File,java.lang.String,net.sourceforge.ganttproject.GanttExportSettings){}
	PUBLIC void printProject(net.sourceforge.ganttproject.GanttExportSettings){}
}, 
NA class net.sourceforge.ganttproject.GanttSplash {

	PUBLIC void close(){}
}, 
Public class net.sourceforge.ganttproject.GanttTask extends net.sourceforge.ganttproject.task.TaskImpl{
	PUBLIC static int LOW
	PUBLIC static int NORMAL
	PUBLIC static int HIGHT
	PRIVATE boolean checked

	PUBLIC GanttTask Clone(){}
	PUBLIC String toString(){}
	PUBLIC int getLength(){}
	PUBLIC void setLength(int){}
	PUBLIC Vector getPredecessorsOld(){}
	PUBLIC Vector getSuccessorsOld(){}
	PUBLIC void unlink(){}
	PUBLIC boolean isChecked(){}
	PUBLIC void setChecked(boolean){}
	PUBLIC void setTaskID(int){}
}, 
Public class net.sourceforge.ganttproject.GanttImagePanel {
	PUBLIC int x
	PUBLIC int y
	PUBLIC javax.swing.ImageIcon image
	PUBLIC javax.swing.ImageIcon image2

	PUBLIC void paintComponent(java.awt.Graphics){}
	PUBLIC Dimension getPreferredSize(){}
}, 
Public class net.sourceforge.ganttproject.GanttCalendar extends net.sourceforge.ganttproject.time.gregorian.GregorianCalendar{
	PRIVATE net.sourceforge.ganttproject.language.GanttLanguage language
	PRIVATE boolean isFixed

	PUBLIC boolean isFixed(){}
	PUBLIC void setFixed(boolean){}
	PUBLIC static GanttCalendar parseXMLDate(java.lang.String){}
	PUBLIC GanttCalendar Clone(){}
	PUBLIC String toString(){}
	PUBLIC String toXMLString(){}
	PUBLIC int getYear(){}
	PUBLIC int getMonth(){}
	PUBLIC int getDate(){}
	PUBLIC int getDay(){}
	PUBLIC int getDayWeek(){}
	PUBLIC int getWeek(){}
	PUBLIC String[] getDayMonthLanguage(){}
	PUBLIC String[] getDayWeekLanguage(){}
	PUBLIC void setYear(int){}
	PUBLIC void setMonth(int){}
	PUBLIC void setDay(int){}
	PUBLIC void add(int){}
	PUBLIC GanttCalendar newAdd(int){}
	PUBLIC int diff(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC String getdayMonth(){}
	PUBLIC String getdayWeek(){}
	PUBLIC int getNumberOfDay(){}
	PRIVATE int module(int){}
	PUBLIC int compareTo(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC boolean equals(net.sourceforge.ganttproject.GanttCalendar){}
	PUBLIC void goNextMonth(){}
	PUBLIC void goPrevMonth(){}
	PUBLIC void go(int,int){}
	PUBLIC static String getDateAndTime(){}
}, 
Public class net.sourceforge.ganttproject.GanttPrintable {
	PRIVATE java.awt.image.BufferedImage image
	PRIVATE int x
	PRIVATE int y

	PUBLIC int print(java.awt.Graphics,java.awt.print.PageFormat,int){}
}, 
Public class net.sourceforge.ganttproject.GanttTaskRelationship {
	PUBLIC static final int SS
	PUBLIC static final int FS
	PUBLIC static final int FF
	PUBLIC static final int SF
	PRIVATE int predecessorTaskID
	PRIVATE int successorTaskID
	PRIVATE int relationshipType
	PRIVATE final net.sourceforge.ganttproject.task.TaskManager myTaskManager

	PUBLIC GanttTask getPredecessorTask(){}
	PUBLIC int getPredecessorTaskID(){}
	PUBLIC void setPredecessorTask(net.sourceforge.ganttproject.task.Task){}
	PUBLIC void setPredecessorTask$2(int){}
	PUBLIC Task getSuccessorTask(){}
	PUBLIC int getSuccessorTaskID(){}
	PUBLIC void setSuccessorTask(net.sourceforge.ganttproject.task.Task){}
	PUBLIC void setSuccessorTask$2(int){}
	PUBLIC int getRelationshipType(){}
	PUBLIC void setRelationshipType(int){}
	PUBLIC boolean equals(net.sourceforge.ganttproject.GanttTaskRelationship){}
	PUBLIC Object clone(){}
	PUBLIC String toString(){}
	PRIVATE TaskManager getManager(){}
}]]]],1.5326397158618872,2.0160426948492485,3.1027324258431017,53.8114242920979,111.28909112841495,-75.91260942214083,7.077981651376147,10.582568807339449,1.8984724399367376,0.20642201834862386,5.18348623853211,0.32150212833872416,0.7320306208035992,218.0,1.5321100917431192,1.3669724770642202,0.11924135300124956,16.0,6.114678899082569,5.238532110091743,10.582568807339449,7.077981651376147,1.8984724399367376
