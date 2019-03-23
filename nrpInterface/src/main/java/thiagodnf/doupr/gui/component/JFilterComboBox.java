package thiagodnf.doupr.gui.component;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class JFilterComboBox<T> extends JSortedComboBox<T> {

    private static final long serialVersionUID = 7194096984846468373L;

    /**
     * Entries to the combobox list.
     */
    private List<T> entries;

    public JFilterComboBox() {

        this.setEditable(true);
        this.entries = new ArrayList<>();

        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();

        /**
         * Listen for key presses.
         */
        textfield.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent ke) {

                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        /**
                         * On key press filter the list. If there is "text" entered in text field of
                         * this combo use that "text" for filtering.
                         */
                        comboFilter(textfield.getText());
                    }
                });
            }
        });
    }

    /**
     * Build a list of entries that match the given filter.
     */
    @SuppressWarnings("unchecked")
    public void comboFilter(String enteredText) {

        List<T> entriesFiltered = new ArrayList<>();

        for (T entry : entries) {

            String entryStr = (String) entry;

            if (entryStr.toLowerCase().contains(enteredText.toLowerCase())) {
                entriesFiltered.add(entry);
            }
        }

        if (entriesFiltered.size() > 0) {
            this.setModel(new SortedComboBoxModel<String>((T[]) entriesFiltered.toArray(new String[entriesFiltered.size()])));
            this.setSelectedItem(enteredText);
            this.showPopup();
        } else {
            this.hidePopup();
        }
    }

    @Override
    public void addItem(T object) {
        super.addItem(object);
        this.entries.add(object);
    }

    @Override
    public void removeAllItems() {
        super.removeAllItems();
        this.entries.clear();
    }
}
