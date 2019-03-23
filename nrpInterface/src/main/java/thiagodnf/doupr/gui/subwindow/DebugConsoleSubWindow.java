package thiagodnf.doupr.gui.subwindow;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugConsoleSubWindow extends SubWindow {

    private static final long serialVersionUID = -6786161270072119947L;
    protected JTextArea textArea;

    public DebugConsoleSubWindow() {
        super("Debug Console");

        addComponents();
        initLayout();
    }

    private void addComponents() {
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);

        Logger.getRootLogger().addAppender(new LogMessageAppender(textArea));
    }

    private void initLayout() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }

    public class LogMessageAppender extends AppenderSkeleton {

        protected JTextArea textArea;

        protected SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public LogMessageAppender(JTextArea textArea) {
            this.textArea = textArea;
        }

        protected void append(LoggingEvent event) {
            textArea.append(dt.format(new Date(event.timeStamp)));
            textArea.append(" ");
            textArea.append(event.getLevel().toString());
            textArea.append(" ");
            textArea.append(FilenameUtils.getBaseName(event.getLocationInformation().getFileName()));
            textArea.append(":");
            textArea.append(event.getLocationInformation().getLineNumber());
            textArea.append(" - ");
            textArea.append(event.getRenderedMessage().toString());
            textArea.append("\n");
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }

        public void close() {

        }

        public boolean requiresLayout() {
            return true;
        }
    }
}
