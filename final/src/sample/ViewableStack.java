package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The model class of Stack in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class ViewableStack implements ViewableDataStructure {

    private Stack<Integer> stack;

    /**
     * Empty stack constructor
     */
    public ViewableStack() {
        this.stack = new Stack<Integer>();
    }

    /**
     * Initialize stack with a string
     * @param initString Data structure serialized
     */
    public ViewableStack(String initString) {

    }

    /**
     * Returns visual representation of nodes of the data structure
     * @return
     */
    @Override
    public List<Node> getNodes() {
        return null;
    }

    /**
     * Get and returns control components
     * @return control components
     */
    @Override
    public List<ControlWrapper> getControls() {
        List<ControlWrapper> list = new ArrayList<ControlWrapper>();
        TextField textField = new TextField();
        Button button = new Button("Push");
        EventHandler<ActionEvent> handler = (event) -> {
            this.stack.add(Integer.parseInt(textField.getText()));
        };
        ControlWrapper wrapper = new ControlWrapper(textField, button, handler);
        list.add(wrapper);
        // Other operations go here...
        return list;
    }
}