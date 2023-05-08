import javax.swing.*;

public class TraceGui {

    private JFrame frame;
    private boolean isShown = false;
    private JPanel p;
    public boolean getIsShown() {
        return isShown;
    }
    public void switchIsShown() {
        this.isShown = !(this.isShown);
    }

    public TraceGui(){
        //  trace frame
        this.frame = new JFrame("Trace");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(false  );
        frame.setLocationRelativeTo(null);

        this.p = new JPanel();

        p.setBounds(0,0,400,400);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        this.frame.add(p);

        frame.setSize(400, 400);
    }

    public void showFrame(){
        frame.setVisible(true);
    }

    public void hideFrame(){
        frame.setVisible(false);
    }

    public void toggleFrame(){
        if(isShown) showFrame();
        else hideFrame();
        switchIsShown();
    }

    public void addDataToDisplay(String line){
        System.out.println(line + "  line");
        JLabel l = new JLabel("<html>" + line + "<br></html>");
        this.p.add(l);
        this.frame.add(p);
        this.frame.repaint();
        this.frame.revalidate();
    }
}
