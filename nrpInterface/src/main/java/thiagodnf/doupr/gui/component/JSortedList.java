package thiagodnf.doupr.gui.component;

import javax.swing.*;

public class JSortedList<T> extends JList<T> {

    private static final long serialVersionUID = 283134616641347959L;

    public JSortedList(DefaultListModel<T> model) {
        super(model);
    }

    public JSortedList() {
        setModel(new SortedListModel<T>());
    }

    public class SortedListModel<E> extends DefaultListModel<T> {

        private static final long serialVersionUID = 5098985288820054021L;

        @Override
        public void addElement(T element) {
            insertElementAt(element, 0);
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Override
        public void insertElementAt(T element, int index) {

            for (index = 0; index < getSize(); index++) {
                if (((Comparable) getElementAt(index)).compareTo(element) > 0) {
                    break;
                }
            }

            super.insertElementAt(element, index);
        }
    }
}
