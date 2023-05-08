import java.lang.Runnable;
public abstract class Filter implements Runnable{
    Pipe _dataInPipe;
    Pipe _dataOutPipe;

    public String getData(){
        return _dataInPipe.dataOut();
    }

    public void sendData(String tempData){
        _dataOutPipe.dataIn(tempData);
    }

}