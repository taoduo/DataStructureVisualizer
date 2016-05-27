package sample;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by duotao on 5/26/16.
 */
public class ViewableQueue implements ViewableDataStructure {
    private Queue<Integer> queue;

    public ViewableQueue() {
        this.queue = new ArrayDeque<>();
    }

    public ViewableQueue(String initString) {

    }

    @Override
    public List<Node> getNodes() {
        return null;
    }

    @Override
    public List<ControlWrapper> getControls() {
        List<ControlWrapper> list = new ArrayList<ControlWrapper>();
        TextField textField = new TextField();
        Button button = new Button("Enqueue");
        EventHandler<ActionEvent> handler = (event) -> {
            this.queue.add(Integer.parseInt(textField.getText()));
        };
        ControlWrapper wrapper = new ControlWrapper(textField, button, handler);
        list.add(wrapper);
        // Other operations go here...
        return list;
    }
}
