import java.util.Scanner;

class StackNode {
    StackNode(double data, StackNode underneath) {
        this.data = data;
        this.underneath = underneath;
    }

    StackNode underneath;
    double data;
}


class RPN {
    private void into(double new_data) {
        top = new StackNode (new_data, top);
    }

    private double outOf() {
        double top_data = top.data;
        top = top.underneath;
        return top_data;
    }

    private RPN(String command) {
        top = null;
        this.command = command;
    }

    private double get() {
        double a, b;
        int j;

        for (int i = 0; i < command.length (); i++) {
// if it's a digit
            if (Character.isDigit (command.charAt (i))) {

// get a string of the number
                StringBuilder temporary = new StringBuilder ();
                for (j = 0; (j < 100) && (Character.isDigit (command.charAt (i)) || (command.charAt (i) == '.')); j++, i++) {
                    temporary.append (String.valueOf (command.charAt (i)));
                }

// convert to double and add to the stack
                double number = Double.parseDouble (temporary.toString ());
                into (number);
            } else if (command.charAt (i) == '+') {
                b = outOf ();
                a = outOf ();
                into (a + b);
            } else if (command.charAt (i) == '-') {
                b = outOf ();
                a = outOf ();
                into (a - b);
            } else if (command.charAt (i) == '*') {
                b = outOf ();
                a = outOf ();
                into (a * b);
            } else if (command.charAt (i) == '/') {
                b = outOf ();
                a = outOf ();
                into (a / b);
            } else if (command.charAt (i) == '^') {
                b = outOf ();
                a = outOf ();
                into (Math.pow (a, b));
            } else if (command.charAt (i) != ' ') {
                throw new IllegalArgumentException ();
            }
        }

        double val = outOf ();

        if (top != null) {
            throw new IllegalArgumentException ();
        }

        return val;
    }

    protected String command;
    private StackNode top;

    /* main method */
    public static void main(String args[]) {
        while (true) {
            Scanner in = new Scanner (System.in);
            System.out.println ("Enter RPN expression or \"quit\".");
            String line = in.nextLine ();
            if (line.equals ("quit")) {
                break;
            } else {
                RPN calc = new RPN (line);
                System.out.printf ("Answer is %f\n", calc.get ());
            }
        }
    }
}