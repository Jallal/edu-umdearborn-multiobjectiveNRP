package thiagodnf.doupr.gui.component;

import javax.swing.*;
import java.awt.event.ItemListener;

public class JSortedComboBox<T> extends JComboBox<T> {

    private static final long serialVersionUID = 7566882704500335678L;

    public JSortedComboBox() {
        setModel(new SortedComboBoxModel<T>());
    }

    public JSortedComboBox(ItemListener listener) {
        this();
        addItemListener(listener);
    }

    public void addItem(T item, boolean isSelected) {
        super.addItem(item);

        if (isSelected) {
            setSelectedItem(item);
        }
    }

    public class SortedComboBoxModel<E> extends DefaultComboBoxModel<T> {

        private static final long serialVersionUID = 8825317571670940052L;

        public SortedComboBoxModel(T[] items) {
            super(items);
        }

        public SortedComboBoxModel() {
            super();
        }

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
