package thiagodnf.doupr.gui.asynctask;

import thiagodnf.doupr.gui.component.JProgressBox;
import thiagodnf.doupr.gui.listener.AsyncTaskListener;
import thiagodnf.doupr.gui.util.MessageBox;

import javax.swing.*;
import java.util.List;

public abstract class AsyncTask<T> extends SwingWorker<T, Integer> {

    protected AsyncTaskListener listener;

    protected int progress;

    public AsyncTask(String title, int maxValue) {
        this.progress = 0;
        JProgressBox.start(title, maxValue);
    }

    public AsyncTaskListener getListener() {
        return listener;
    }

    public void addListener(AsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void process(List<Integer> chunks) {
        for (int number : chunks) {
            JProgressBox.setValue(number);
        }
    }

    @Override
    public void done() {

        JProgressBox.close();

        if (listener != null) {
            try {
                listener.done(get());
            } catch (Exception ex) {
                MessageBox.error(ex);
            }
        }
    }
}
