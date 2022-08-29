import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Calculator implements ActionListener{
    JFrame frame;
    ImageIcon bg;
    JLabel myLabel;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton,subButton,mulButton,divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    Font myFont = new Font("Helvetica",Font.BOLD,25);
    Font myFont2 = new Font("Helvetica",Font.PLAIN,30);
    double num1=0,num2=0,result=0;
    char operator;
    int mouseX;
    int mouseY;

    Calculator(){

        bg = new ImageIcon(this.getClass().getResource("/bg.jpg"));
        myLabel = new JLabel(bg);
        myLabel.setSize(2000, 2000);


        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //centers frame when program is run
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, 400, 520, 20, 20 ));

        frame.addMouseMotionListener(new MouseMotionAdapter() { //this allows the entire frame to be dragged around the screen
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
            }
        });
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });



        textfield = new JTextField();
        textfield.setBounds(50, 45, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBackground(new Color(217,191,217));
        textfield.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 255),1));



        ImageIcon closeIcon = new ImageIcon(this.getClass().getResource("/close.png"));

        Image image = closeIcon.getImage();
        Image newimg = image.getScaledInstance(18, 18,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        closeIcon = new ImageIcon(newimg);


        ImageIcon minIcon = new ImageIcon(this.getClass().getResource("/hide.png"));

        Image image1 = minIcon.getImage();
        Image newimg1 = image1.getScaledInstance(18, 18,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        minIcon = new ImageIcon(newimg1);


        JButton minimize = new JButton();
        minimize.setFocusPainted(false);
        minimize.setContentAreaFilled(false);
        minimize.setBorderPainted(false);
        minimize.setOpaque(false);
        minimize.setBounds(345,10,20,20);
        minimize.setIcon(minIcon);
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setState(JFrame.ICONIFIED);
            }
        };
        minimize.addActionListener(al);


        JButton exit = new JButton();
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setOpaque(false);
        exit.setBounds(370,10,20,20);
        exit.setIcon(closeIcon);
        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        exit.addActionListener(al1);
        addButton = new JButton("+");

        subButton = new JButton("-");

        mulButton = new JButton("*");

        divButton = new JButton("/");

        decButton = new JButton(".");

        equButton = new JButton("=");

        delButton = new JButton("⌫");

        clrButton = new JButton("C");

        negButton = new JButton("+/-");



        functionButtons[0] = addButton;

        functionButtons[1] = subButton;

        functionButtons[2] = mulButton;

        functionButtons[3] = divButton;

        functionButtons[4] = decButton;

        functionButtons[5] = equButton;

        functionButtons[6] = delButton;

        functionButtons[7] = clrButton;

        functionButtons[8] = negButton;



        for(int i =0;i<9;i++) {

            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont2);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setFocusPainted(false);
            functionButtons[i].setOpaque(true);
            functionButtons[i].setBorderPainted(true);
            functionButtons[i].setBackground(new Color(227, 181, 227));
            functionButtons[i].setBorder(BorderFactory.createLineBorder(new Color(153, 153, 255),1));
        }


        for(int i =0;i<10;i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].setOpaque(true);
            numberButtons[i].setBorderPainted(true);
            numberButtons[i].setBackground(new Color(227, 181, 227));
            numberButtons[i].setBorder(BorderFactory.createLineBorder(new Color(153, 153, 255),1));



        }

        negButton.setBounds(50,110,90,50);

        delButton.setBounds(260,110,90,50);

        clrButton.setBounds(155,110,90,50);


        panel = new JPanel();

        panel.setBounds(50, 175, 300, 300);

        panel.setLayout(new GridLayout(4,4,10,10));

        panel.setBackground(new Color(0,0,0,0));



        panel.add(numberButtons[1]);

        panel.add(numberButtons[2]);

        panel.add(numberButtons[3]);

        panel.add(addButton);

        panel.add(numberButtons[4]);

        panel.add(numberButtons[5]);

        panel.add(numberButtons[6]);

        panel.add(subButton);

        panel.add(numberButtons[7]);

        panel.add(numberButtons[8]);

        panel.add(numberButtons[9]);

        panel.add(mulButton);

        panel.add(decButton);

        panel.add(numberButtons[0]);

        panel.add(equButton);

        panel.add(divButton);






        frame.add(panel);

        frame.add(negButton);

        frame.add(delButton);

        frame.add(clrButton);

        frame.add(textfield);

        frame.add(exit);
        frame.add(minimize);

        frame.add(myLabel);

        frame.setVisible(true);

    }

    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }


    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0; i < 10; i++) {
            if(e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }

        if(e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='+';
            textfield.setText("");
        }

        if(e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='-';
            textfield.setText("");
        }

        if(e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='*';
            textfield.setText("");
        }

        if(e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator ='/';
            textfield.setText("");
        }

        if(e.getSource() == equButton) {
            num2=Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }
            textfield.setText(String.valueOf(result));
            num1=result;
        }

        if(e.getSource() == clrButton) {
            textfield.setText("");
        }

        if(e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");

            for(int i=0;i<string.length()-1;i++) {
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }

        if(e.getSource()==negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp*=-1;
            textfield.setText(String.valueOf(temp));
        }
    }
}