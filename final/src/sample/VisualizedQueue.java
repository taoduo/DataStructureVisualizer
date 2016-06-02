package sample;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The model class of Queue in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class VisualizedQueue extends VisualizedDataStructure {
    private Queue<Integer> queue;
    /**
     * Empty queue constructor
     */
    public VisualizedQueue(Controller controller) {
        super(controller);
        this.queue = new ArrayDeque<>();
    }

    /**
     * Reset the queue with random numbers
     * @param size The size of the queue
     * @param range The range of random numbers
     */
    @Override
    public void randomize(int size, int range) {

    }

    /**
     * Returns visual representation of nodes of the data structure
     * @return a list of Nodes with one Node for each entry
     */
    @Override
    public List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        if (this.queue.isEmpty()) {
            return list;
        } else {
            for (Iterator<Integer> itr = this.queue.iterator(); itr.hasNext(); itr.next()){
            //Should do something with value in queue later but this loop prints it for now
                System.out.println(itr);
            }
        }
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
        Button button1 = new Button("Enqueue");
        button1.setPrefWidth(1000);
        TextField textField1 = new TextField();
        EventHandler<ActionEvent> eventHandler1 = event -> {
            if (this.isInt(textField1.getText())) {
                this.queue.offer(Integer.parseInt(textField1.getText()));
                this.controller.refreshOutput(textField1.getText());
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
        Button button2 = new Button("Dequeue");
        button2.setPrefWidth(1000);
        TextField textField2 = new TextField();
        textField2.setVisible(false);
        EventHandler<ActionEvent> eventHandler2 = event -> {
            if (this.queue.isEmpty()) {
                this.controller.outputLabel.setText("Empty");
                return;
            }
            this.controller.refreshOutput(Integer.toString(this.queue.poll()));
        };
        ControlWrapper controlWrapper2 = new ControlWrapper(textField2, button2, eventHandler2);
        list.add(controlWrapper2);

        // add the pop operation
        Button button3 = new Button("Peek");
        button3.setPrefWidth(1000);
        TextField textField3 = new TextField();
        textField3.setVisible(false);
        EventHandler<ActionEvent> eventHandler3 = event -> {
            if (this.queue.isEmpty()) {
                this.controller.outputLabel.setText("Empty");
                return;
            }
            this.controller.outputLabel.setText(Integer.toString(this.queue.peek()));
        };
        ControlWrapper controlWrapper3 = new ControlWrapper(textField3, button3, eventHandler3);
        list.add(controlWrapper3);
        return list;
    }

    /**
     * Tells if the queue is empty
     * @return true if it is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Serialize the stack into a string
     * @return the serialization output as a string
     */
    @Override
    public String serialize() {
        String result = "";
        return result;
    }

    /**
     * Deserialize the string and get the queue back
     * @param stringRepresentation the input string
     * @return the ViewableDataStructure object
     */
    @Override
    public boolean deserialize(String stringRepresentation) {
        return false;
    }
}
