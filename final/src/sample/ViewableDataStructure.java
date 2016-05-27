package sample;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by duotao on 5/26/16.
 */
public interface ViewableDataStructure {
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
    List<Node> getNodes();
    List<ControlWrapper> getControls();
}
