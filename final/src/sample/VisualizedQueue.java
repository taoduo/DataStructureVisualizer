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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * The model class of Queue in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class VisualizedQueue extends VisualizedDataStructure {
    private Queue<Integer> queue;
    private static final int X_ADJUSTMENT = 25;
    private static final int Y_ADJUSTMENT = 15;
    private static final int LINE_WIDTH = 5;
    private static final int RADIUS = 25;

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
     * @param min The min of random numbers
     * @param max The max of random numbers
     */
    @Override
    public void randomize(int size, int min, int max) {
        this.queue.clear();
        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * (max - min + 1) + min);
            this.queue.offer(rand);
        }
    }

    /**
     * Returns visual representation of nodes of the data structure
     * @return a list of Nodes with one Node for each entry
     */
    @Override
    public List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        int count = 0;
        if (this.queue.isEmpty()) {
            return list;
        } else {

            for (Iterator<Integer> itr = this.queue.iterator(); itr.hasNext();) {

                Circle circle = new Circle(RADIUS);
                circle.setCenterY(controller.displayBoard.getHeight() / 2);
                circle.setCenterX(RADIUS + 2 * count * RADIUS + (LINE_WIDTH * count));
                circle.setFill(Color.LIGHTGREY);
                if (count == 0) {
                    circle.setFill(Color.LIGHTBLUE);
                } else if (count == this.queue.size() - 1 ) {
                    circle.setFill(Color.LIGHTCYAN);
                }
                circle.setStroke(Color.BLACK);
                Line line = new Line();
                line.setStartX(circle.getCenterX() + RADIUS);
                line.setStartY(controller.displayBoard.getHeight() / 2);
                line.setEndX(line.getStartX() + LINE_WIDTH);
                line.setEndY(controller.displayBoard.getHeight() / 2);
                Label label;
                if (count == 0) {
                    label = new Label(itr.next().toString() + " \nfront");
                } else if (count == this.queue.size()-1){
                    label = new Label(itr.next().toString() + " \nend");
                } else {
                    label = new Label(itr.next().toString());

                }

                label.setLayoutY(controller.displayBoard.getHeight() / 2 - Y_ADJUSTMENT);
                label.setLayoutX(circle.getCenterX() + RADIUS/2-X_ADJUSTMENT);


                list.add(circle);
                list.add(label);
                if (count != this.queue.size() -1) list.add(line);

                count++;
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
                textField1.requestFocus();
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
     * Serialize the queue into a string
     * @return the serialization output as a string
     */
    @Override
    public String serialize() {
        String result = "";
        for (Integer i : this.queue) {
            result = result + " " + i;
        }
        return result;
    }

    /**
     * Deserialize the string to initialize the queue
     * Returns null if the input is misformatted
     * Format: numbers splitted by one or more spaces, tabs or new lines
     * Number on the right is on the top of the stack
     * @param stringRepresentation the input string
     * @return if the input is viable
     */
    @Override
    public boolean deserialize(String stringRepresentation) {
        if (stringRepresentation.isEmpty()) {
            this.queue.clear();
            return true;
        }
        String[] arr = stringRepresentation.split("\\s+");
        Queue<Integer> tempQueue = new ArrayDeque<Integer>();
        for (String str : arr) {
            if (this.isInt(str)) {
                tempQueue.offer(Integer.parseInt(str));
            } else {
                return false;
            }
        }
        this.queue = tempQueue;
        return true;
    }
}
