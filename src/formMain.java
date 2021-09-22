import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class formMain extends JFrame{
    private JPanel panelMain;
    private JTextArea textResult;
    private JFormattedTextField textDecimal;
    private JFormattedTextField textASCII;
    private JButton buttonDecimal;
    private JButton buttonASCII;

    public static String AsciiToBinary(String asciiString){

        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();
    }


    public formMain() {
        super("Binary Converter");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        buttonDecimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String decimal = textDecimal.getText();
                textResult.setText(Integer.toBinaryString(Integer.parseInt(String.valueOf(decimal))));
            }
        });

        buttonASCII.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textASCII.getText();
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                    result.append(AsciiToBinary(String.valueOf(text.charAt(i))));
                }
                textResult.setText(String.valueOf(result));
            }
        });
    }


    public static void main(String[] args) {
        formMain screen = new formMain();
        screen.setVisible(true);
    }
}
