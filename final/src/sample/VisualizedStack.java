package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * The model class of Stack in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class VisualizedStack extends VisualizedDataStructure {
    private Stack<Integer> stack;
    /**
     * Empty stack constructor
     */
    public VisualizedStack(Label label) {
        super(label);
        this.stack = new Stack<Integer>();
    }

    /**
     * Initialize the stack with random numbers
     * @param size the size of the stack
     * @param range the range of the random numbers
     */
    @Override
    public void randomize(int size, int range) {

    }

    /**
     * Returns visual representation of nodes of the data structure
     * @return the viewable nodes of the data structure
     */
    @Override
    public List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        Queue<Integer> queue;

        return list;
    }

    /**
     * Get and returns control components
     * @return control components
     */
    @Override
    public List<ControlWrapper> getControls() {
        List<ControlWrapper> list = new ArrayList<ControlWrapper>();
        // add the push operation
        Button button1 = new Button("Push");
        TextField textField1 = new TextField();
        EventHandler<ActionEvent> eventHandler1 = event -> {
            if (this.isInt(textField1.getText())) {
                outputLabel.setText(Integer.toString(stack.push(Integer.parseInt(textField1.getText()))));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Non-numeric input");
                alert.setContentText("\"" + textField1.getText() + "\" is not a Integer");
                alert.showAndWait();
            }
        };
        ControlWrapper controlWrapper1 = new ControlWrapper(textField1, button1, eventHandler1);
        list.add(controlWrapper1);

        // add the pop operation
        Button button2 = new Button("Pop");
        TextField textField2 = new TextField();
        textField2.setDisable(true);
        EventHandler<ActionEvent> eventHandler2 = event -> {
            if (this.stack.isEmpty()) {
                outputLabel.setText("Empty");
                return;
            }
            outputLabel.setText(Integer.toString(stack.pop()));
        };
        ControlWrapper controlWrapper2 = new ControlWrapper(textField2, button2, eventHandler2);
        list.add(controlWrapper2);

        // add the peek operation
        Button button3 = new Button("Peek");
        TextField textField3 = new TextField();
        textField3.setDisable(true);
        EventHandler<ActionEvent> eventHandler3 = event -> {
            if (this.stack.isEmpty()) {
                outputLabel.setText("Empty");
                return;
            }
            outputLabel.setText(Integer.toString(stack.peek()));
        };
        ControlWrapper controlWrapper3 = new ControlWrapper(textField3, button3, eventHandler3);
        list.add(controlWrapper3);
        return list;
    }

    /**
     * Helper method to check if a string represents an integer
     * @param s the string to be checked
     * @return true if it represents an integer, false if not
     */
    private boolean isInt(String s) {
        return s.matches("[-+]?\\d+");
    }
    /**
     * Serialize the stack into a string
     * @return the serialization output as a string
     */
    public String serialize() {
        String result = "";
        return result;
    }

    /**
     * Deserialize the string and get the stack back
     * @param stringRepresentation the input string
     * @return the ViewableDataStructure object
     */
    @Override
    public VisualizedStack deserialize(String stringRepresentation) {
        return null;
    }
}