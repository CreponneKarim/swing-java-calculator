import java.io.*;
import java.nio.Buffer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Trace extends Filter{

    public Trace(Pipe pGui,Pipe pCalc){
        this._dataInPipe = pCalc;
        this._dataOutPipe = pGui;

        //  read file and write its totality to the pipe
        try(
                BufferedReader reader = new BufferedReader(new FileReader("Trace.txt"))
        ){

            //  write all the previously read values to the trace
            String line = reader.readLine();
            while (line != null && (!line.isEmpty())) {

                System.out.println("=========>" + line);
                this._dataOutPipe.dataIn(line);
                line = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLine(String tmp){

        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter("Trace.txt",true))
        ){
            //  write all the previously read values to the trace
            System.out.println(tmp + " in writter");
            writer.write(tmp);
            writer.write('\n');
            System.out.println("written");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){

            String s = this._dataInPipe.dataOut();
            //  write it out to the file here
            this.addLine(s);
            this._dataOutPipe.dataIn(s);

            try {
                Thread.sleep(2);
            }catch (InterruptedException exc){
                exc.printStackTrace();
            }

            //  read file and write its totality to the pipe

        }
    }
}
