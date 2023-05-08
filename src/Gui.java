import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.lang.Runnable;

public class Gui extends Filter{

    private JFrame frame;
    private TraceGui traceGui;

    private JTextField tfNb1;
    private JTextField tfNb2;
    private JTextField tfRes;
    private PostFixe postFixer;
    private JButton quit;
    private JButton trace;
    private JButton sum;
    private JButton mult;
    private JButton fact;

    private String currentOperation;

    private void onOperationCallback(String operation){
        this.currentOperation = operation;
        this._dataOutPipe.dataIn(this.getExpression());
        //  on récupére le résultat et on l'écrit dans res
        //  lah ghaleb, vraiment j'ai pas trouvé un autre mécanisme pour faire réussir ça
        //  on pouvais utiliser un autre pipe mais ça violerait le fait qu'on utilise une
        //  class abstraite filtre
        try {
            Thread.sleep(2);
        }catch (InterruptedException exc){
            exc.printStackTrace();
        }
        this.tfRes.setText(
                this._dataOutPipe.dataOut()
        );
        this.traceGui.addDataToDisplay(
                this._dataInPipe.dataOut()
        );
    }

    public Gui(Pipe pTrace, Pipe pCalc){

        this.traceGui = new TraceGui();

        postFixer = new PostFixe();
        this._dataInPipe = pTrace;
        this._dataOutPipe = pCalc;
        //Creating the Frame
        this.frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the panels at
        JPanel titlePanel = new JPanel();
        JLabel titre = new JLabel("LES FORMES ET LES VUES ");
        titlePanel.add(titre); // Components Added using the Flow Layout

        JPanel operationPanel = new JPanel();
        JLabel nb1 = new JLabel("Nombre 1 ");
        this.tfNb1 = new JTextField(10); // accepts upto 10 characters
        JLabel nb2 = new JLabel("Nombre 2 ");
        this.tfNb2 = new JTextField(10); // accepts upto 10 characters

        this.sum = new JButton("SOMME");
        this.mult = new JButton("PRODUIT");
        this.fact = new JButton("FACTORIEL");

        operationPanel.add(nb1); // Components Added using Flow Layout
        operationPanel.add(tfNb1);
        operationPanel.add(nb2);
        operationPanel.add(tfNb2);
        operationPanel.add(Box.createVerticalStrut(100));

        operationPanel.add(sum); // Components Added using Flow Layout
        operationPanel.add(mult);
        operationPanel.add(fact);


        JPanel resultPanel = new JPanel();
        JLabel res = new JLabel("Résultat = ");
        this.tfRes = new JTextField(10);
        operationPanel.add(Box.createVerticalStrut(100));
        this.quit = new JButton("Quiter");
        this.trace = new JButton("Trace");

        resultPanel.add(res);
        resultPanel.add(tfRes);
        resultPanel.add(quit);
        resultPanel.add(trace);


        //Adding Components to the frame.

        frame.getContentPane().add(BorderLayout.NORTH,titlePanel);

        frame.getContentPane().add(BorderLayout.CENTER, operationPanel);

        frame.getContentPane().add(BorderLayout.SOUTH, resultPanel);

        frame.setVisible(true);

        Gui saveRef = this;

        this.sum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOperationCallback("+");
            }
        });
        this.fact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOperationCallback("!");
            }
        });
        this.mult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOperationCallback("*");
            }
        });

        this.trace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRef.traceGui.toggleFrame();
            }
        });

        this.quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void showFrame(){
        this.frame.setVisible(true);
    }
    public void hideFrame(){
        this.frame.setVisible(false);
    }

    public String getExpression(){
        String v1 = this.tfNb1.getText();
        String v2 = this.tfNb2.getText();
        String v3 = this.currentOperation;
        return  this.postFixer.postFixe(v1,v2,v3);
    }

    @Override
    public void run() {
        this.showFrame();
    }
}
