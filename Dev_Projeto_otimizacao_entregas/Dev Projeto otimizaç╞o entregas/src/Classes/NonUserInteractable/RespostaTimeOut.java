package Classes.NonUserInteractable;

import java.util.Collection;
import java.util.concurrent.Callable;

public class RespostaTimeOut implements Callable {

    private boolean output = false;
    private int timer;

    public RespostaTimeOut(int timer) throws InterruptedException {
        this.timer = timer;
    }
    @Override
    public Boolean call() throws InterruptedException {

        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.getSuppressed();

        }

        return false;
    }



    public void setOutput(boolean output) {
        this.output = output;
    }

    public boolean isOutput() {
        return output;
    }




}