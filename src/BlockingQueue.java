import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
public class BlockingQueue extends Pipe {
    Queue<String> _inData = new LinkedList<String>();

    public boolean isEmpty(){
        return _inData.isEmpty();
    }
    public synchronized void dataIn (String temp){
        _inData.add(temp);
        notify();
    }
    public synchronized String dataOut (){
        if(_inData.isEmpty())
            try {
                wait();
            } catch (InterruptedException e) {

                e.printStackTrace();

            }
        return _inData.poll();
    }
}
