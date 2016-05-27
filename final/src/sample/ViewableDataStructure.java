package sample;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Parent class of all the data structures to be visualized
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public interface ViewableDataStructure {
    /**
     * This is a wrapper for the components for one type of operations
     */
    class ControlWrapper {
        public TextField textField;
        public Button button;
        public EventHandler<ActionEvent> handler;
        public ControlWrapper(TextField textField, Button button, EventHandler<ActionEvent> handler) {
            this.textField = textField;
            this.button = button;
            this.handler = handler;
        }
    }

    /**
     * This method returns all the data as Nodes as a list
     * @return a list of Nodes to be shown
     */
    List<Node> getNodes();

    /**
     * This method returns all control options of the data structure as a list of wrappers
     * @return a list of ControlWrapper that has all the control components
     */
    List<ControlWrapper> getControls();
}
