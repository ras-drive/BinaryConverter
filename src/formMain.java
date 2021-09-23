import javax.swing.*;
import java.io.*;
import java.util.HashMap;


public class formMain extends JFrame{
    private JPanel panelMain;
    private JTextArea textResult;
    private JFormattedTextField textDecimal;
    private JFormattedTextField textASCII;
    private JButton buttonDecimal;
    private JButton buttonASCII;
    private JButton buttonSave;
    private JFormattedTextField textHex;
    private JButton buttonHex;

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

    String hexToBinary(String hex)
    {

        // variable to store the converted
        // Binary Sequence
        String binary = "";

        // converting the accepted Hexadecimal
        // string to upper case
        hex = hex.toUpperCase();

        // initializing the HashMap class
        HashMap<Character, String> hashMap
                = new HashMap<>();

        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < hex.length(); i++) {
            // extracting each character
            ch = hex.charAt(i);

            // checking if the character is
            // present in the keys
            if (hashMap.containsKey(ch))

                // adding to the Binary Sequence
                // the corresponding value of
                // the key
                binary += hashMap.get(ch);

                // returning Invalid Hexadecimal
                // String if the character is
                // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary
        return binary;
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
            long decimal = 0;
            boolean overflow = false;
            try {
                if (textDecimal.getText().length() > 19) {
                    overflow = true;
                }
                decimal = Long.parseLong(textDecimal.getText());
                System.out.println(String.valueOf(decimal).length());
                textResult.setText(Long.toBinaryString(decimal));
            } catch (Exception E) {
                if (overflow) {
                    System.out.println("Number too large");
                    textResult.setText("Number too large");
                } else {
                System.out.println("Error, you tried putting a non decimal character into the decimal section. use the ASCII form for text");
                textResult.setText("Invalid Decimal String");
                }
            }
        });

        buttonASCII.addActionListener(e -> {
            String text = textASCII.getText();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                result.append(AsciiToBinary(String.valueOf(text.charAt(i))));
            }
            textResult.setText(String.valueOf(result));
        });

        buttonHex.addActionListener(e -> textResult.setText(hexToBinary(textHex.getText())));

        buttonSave.addActionListener(e -> {
            WriteToFile(textResult.getText());
            System.out.println("Successfully written to file.");
        });

    }

    //TODO: Add "Read From File" functionality
    //TODO: Make a CLI version with file I/O

    public static void main(String[] args) {
        formMain screen = new formMain();
        screen.setVisible(true);
    }
}
