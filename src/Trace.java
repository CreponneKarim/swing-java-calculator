import java.io.*;
import java.nio.Buffer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Trace extends Filter{

    public Trace(Pipe pGui,Pipe pCalc){
        this._dataInPipe = pCalc;
        this._dataOutPipe = pGui;

        //  read file and write its totality to the pipe
        try{
            FileReader fileReader =new FileReader("Trace.txt");

            BufferedReader reader = new BufferedReader(fileReader);

            //  write all the previously read values to the trace

            String line = reader.readLine();
            while (line != null && (!line.isEmpty())) {
                this._dataOutPipe.dataIn(line);
                line = reader.readLine();
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLine(String tmp){

        try{
            FileWriter fileWriter =new FileWriter("Trace.txt",true);

            BufferedWriter writer = new BufferedWriter(fileWriter);

            //  write all the previously read values to the trace

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
            try{
                FileReader fileReader =new FileReader("Trace.txt");

                BufferedReader reader = new BufferedReader(fileReader);
                //  write all the previously read values to the trace

                String line = reader.readLine();
                if(line==null)
                    System.out.println("ksksksksks");
                while (line != null) {

                    System.out.println("stakcccc");
                    line = reader.readLine();
                    System.out.println(line + "==>from file");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

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
