package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
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
    public VisualizedStack() {
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
        return list;
    }

    /**
     * Get and returns control components
     * @return control components
     */
    @Override
    public List<ControlWrapper> getControls() {
        List<ControlWrapper> list = new ArrayList<ControlWrapper>();
        Button button = new Button("Push");
        TextField textField = new TextField();
        EventHandler<ActionEvent> eventHandler = event -> {
            if (this.isNumeric(textField.getText())) {
                operationResult = Integer.toString(stack.push(Integer.parseInt(textField.getText())));
                System.out.println(operationResult);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Non-numeric input");
                alert.setContentText("\"" + textField.getText() + "\" is not a number");
                alert.showAndWait();
            }
        };
        ControlWrapper controlWrapper = new ControlWrapper(textField, button, eventHandler);
        list.add(controlWrapper);
        return list;
    }

    private boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
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