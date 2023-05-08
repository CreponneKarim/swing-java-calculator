public class EvaluatorWrapper extends Filter{
    private PostFixe postFixe;

    public EvaluatorWrapper(Pipe pTrace,Pipe pGui){
        this._dataInPipe = pGui;
        this._dataOutPipe = pTrace;
        this.postFixe = new PostFixe();
    }
    @Override
    public void run() {
        while(true){
           String expression = this._dataInPipe.dataOut();
           int result = new Evaluator(expression).interpret(new Context());
           this._dataInPipe.dataIn(Integer.toString(result));   //  pipe bidirectionel
           this._dataOutPipe.dataIn(expression + " = " + result);


            try {
                Thread.sleep(2);
            }catch (InterruptedException exc){
                exc.printStackTrace();
            }
        }
    }
}
