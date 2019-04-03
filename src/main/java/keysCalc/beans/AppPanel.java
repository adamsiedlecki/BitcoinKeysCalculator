package keysCalc.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Component
public class AppPanel extends JPanel {

    @Autowired
    Calc calc;
    private JLabel name;
    private LayoutManager layout;
    private JTextField hexPrivateKey;
    private JTextField WIFPrivateKey;
    private JTextField publicKey;
    private JLabel first;
    private JLabel second;
    private JLabel third;
    private ArrayList<JLabel> labels;
    private ArrayList<JTextField> fields;

    @Autowired
    public AppPanel(BufferedImage logo) {
        setBackground(Color.BLACK);
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        // setLayout(layout);
        setBtcLogo(logo);
        setNameLabel();
        setLabels();

        hexPrivateKey.addActionListener(new FirstListener());
        WIFPrivateKey.addActionListener(new SecondListener());
        publicKey.addActionListener(new ThirdListener());
    }


    private void setNameLabel() {
        name = new JLabel("Bitcoin Key Calculator");
        name.setFont(new Font("Ubuntu Bold Italic", Font.PLAIN, 35));
        name.setForeground(Color.WHITE);
        name.setVisible(true);
        name.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.add(name);
    }


    private void setBtcLogo(BufferedImage logo) {
        JLabel btcLogo = new JLabel(new ImageIcon(logo));
        btcLogo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        this.add(btcLogo);
    }

    private void setLabels() {
        add(first = new JLabel("Crude, simple private key (64 characters long hexadecimal)"));
        add(hexPrivateKey = new JTextField());
        add(second = new JLabel("Private key in Wallet Import Format (WIF)"));
        add(WIFPrivateKey = new JTextField());
        add(third = new JLabel("                             Public key"));
        add(publicKey = new JTextField());

        labels = new ArrayList<>();
        labels.add(first);
        labels.add(second);
        labels.add(third);

        for (JLabel label : labels) {
            label.setFont(new Font("Ubuntu Bold Italic", Font.PLAIN, 20));
            label.setForeground(Color.WHITE);
        }
        fields = new ArrayList<>();
        fields.add(hexPrivateKey);
        fields.add(WIFPrivateKey);
        fields.add(publicKey);
        for (JTextField field : fields) {
            field.setFont(new Font("Ubuntu Bold Italic", Font.PLAIN, 14));
            field.setPreferredSize(new Dimension(580, 40));
        }
    }


    private class FirstListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (hexPrivateKey.getText().length() == 64) {
                WIFPrivateKey.setText(calc.calcWIFFromSimple(hexPrivateKey.getText()));
                publicKey.setText(calc.calcPublicFromWIF(WIFPrivateKey.getText()));
            } else {
                WIFPrivateKey.setText("Simple, raw key should be 64 character hex");
                publicKey.setText("Simple, raw key should be 64 character hex");
            }
        }
    }

    private class SecondListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (WIFPrivateKey.getText().length() == 51) {
                hexPrivateKey.setText(calc.calcSimpleFromWIF(WIFPrivateKey.getText()));
                publicKey.setText(calc.calcPublicFromWIF(WIFPrivateKey.getText()));
            } else {
                hexPrivateKey.setText("WIF key should be 51 characters long.");
                publicKey.setText("WIF key should be 51 characters long.");
            }
        }
    }

    private class ThirdListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            hexPrivateKey.setText("It is not possible to calculate a private key by public.");
            WIFPrivateKey.setText("It is not possible to calculate a private key by public.");
        }
    }

}
