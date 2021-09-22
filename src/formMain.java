import javax.swing.*;
import java.io.*;


public class formMain extends JFrame{
    private JPanel panelMain;
    private JTextArea textResult;
    private JFormattedTextField textDecimal;
    private JFormattedTextField textASCII;
    private JButton buttonDecimal;
    private JButton buttonASCII;
    private JButton buttonSave;
    private JButton buttonUpload;

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

    public static void WriteToFile(String text) {
        try {
            FileWriter myWriter = new FileWriter("BinaryConverterOutput.txt");
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }




    public formMain() {
        super("Binary Converter");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        buttonDecimal.addActionListener(e -> {
            String decimal = textDecimal.getText();
            textResult.setText(Integer.toBinaryString(Integer.parseInt(String.valueOf(decimal))));
        });

        buttonASCII.addActionListener(e -> {
            String text = textASCII.getText();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                result.append(AsciiToBinary(String.valueOf(text.charAt(i))));
            }
            textResult.setText(String.valueOf(result));
        });

        buttonSave.addActionListener(e -> {
            WriteToFile(textResult.getText());
            System.out.println("Successfully written to file.");
        });

    }
    
    //TODO: Possibly add Hex functionality
    //TODO: Add "Read From File" functionality
    //TODO: Make a CLI version with file I/O

    public static void main(String[] args) {
        formMain screen = new formMain();
        screen.setVisible(true);
    }
}
