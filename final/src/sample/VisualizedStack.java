package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    public VisualizedStack(Controller controller) {
        super(controller);
        this.stack = new Stack<>();
    }

    /**
     * Initialize the stack with random numbers
     * @param size the size of the stack
     * @param range the range of the random numbers
     */
    @Override
    public void randomize(int size, int range) {
        this.stack.clear();
        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * range + 1);
            this.stack.push(rand);
        }
    }

    /**
     * Returns visual representation of nodes of the data structure
     * @return the viewable nodes of the data structure
     */
    @Override
    public List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        Circle circle = new Circle();
        circle.setCenterX(50);
        circle.setCenterY(50);
        circle.setRadius(20);
        circle.setFill(Color.WHITE);
        list.add(circle);
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
        button1.setPrefWidth(1000);
        TextField textField1 = new TextField();
        EventHandler<ActionEvent> eventHandler1 = event -> {
            if (this.isInt(textField1.getText())) {
                this.controller.refreshOutput(Integer.toString(
                        stack.push(Integer.parseInt(textField1.getText()))));
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
        button2.setPrefWidth(1000);
        TextField textField2 = new TextField();
        textField2.setVisible(false);
        EventHandler<ActionEvent> eventHandler2 = event -> {
            if (this.stack.isEmpty()) {
                this.controller.outputLabel.setText("Empty");
                return;
            }
            this.controller.refreshOutput(Integer.toString(stack.pop()));
        };
        ControlWrapper controlWrapper2 = new ControlWrapper(textField2, button2, eventHandler2);
        list.add(controlWrapper2);

        // add the peek operation
        Button button3 = new Button("Peek");
        button3.setPrefWidth(1000);
        TextField textField3 = new TextField();
        textField3.setVisible(false);
        EventHandler<ActionEvent> eventHandler3 = event -> {
            if (this.stack.isEmpty()) {
                this.controller.outputLabel.setText("Empty");
                return;
            }
            this.controller.outputLabel.setText(Integer.toString(stack.peek()));
        };
        ControlWrapper controlWrapper3 = new ControlWrapper(textField3, button3, eventHandler3);
        list.add(controlWrapper3);
        return list;
    }

    /**
     * Tells if the stack is empty
     * @return true if it is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Serialize the stack into a string
     * @return the serialization output as a string
     */
    @Override
    public String serialize() {
        String result = "";
        for (Integer i : this.stack) {
            result = result + " " + i;
        }
        return result;
    }

    /**
     * Deserialize the string to initialize the stack
     * Returns null if the input is misformatted
     * Format: numbers splitted by one or more spaces, tabs or new lines
     * Number on the right is on the top of the stack
     * Example: 5 10    15
     * 15 will be on the top of the stack
     * @param stringRepresentation the input string
     * @return if the input is viable
     */
    @Override
    public boolean deserialize(String stringRepresentation) {
        if (stringRepresentation.isEmpty()) {
            this.stack.clear();
            return true;
        }
        String[] arr = stringRepresentation.split("\\s+");
        Stack<Integer> tempStack = new Stack<>();
        for (String str : arr) {
            if (this.isInt(str)) {
                tempStack.push(Integer.parseInt(str));
            } else {
                return false;
            }
        }
        this.stack = tempStack;
        return true;
    }
}