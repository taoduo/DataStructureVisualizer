package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.lang.Math;

/**
 * The model class of Heap in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class VisualizedHeap extends VisualizedDataStructure {
    private PriorityQueue<Integer> heap;
    private static final int RECTANGLE_WIDTH = 30;
    private static final int RECTANGLE_HEIGHT = 30;
    private static final int X_ADJUSTMENT = 10;
    private static final int Y_ADJUSTMENT = 6;
    private static final int CEILING_GAP = 5;
    private static final int GAP_BETWEEN_ROW = 10;

    /**
     * Empty heap constructor
     */
    public VisualizedHeap(Controller controller) {
        super(controller);
        this.heap = new PriorityQueue<>();
    }

    /**
     * Reset the heap with random numbers
     * @param size The size of the heap
     * @param min The min of random numbers
     * @param max The max of random numbers
     */
    @Override
    public void randomize(int size, int min, int max) {
        this.heap.clear();
        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * (max - min + 1) + min);
            this.heap.offer(rand);
        }
    }


    /**
     * Returns visual representation of nodes of the data structure
     * @return the viewable nodes of the data structure
     */
    @Override
    public List<Node> getNodes() {
        List<Node> list = new ArrayList<>();
        Object[] arrayOfInt;
        arrayOfInt = this.heap.toArray();
        int count = 1;
        double logOfCount;
        int intLogOfCount;
        int countInRow = 0;
        double x;
        double y;
        double lineEndX = 0;

        for (Object item : arrayOfInt) {
            Rectangle rectangle = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
            logOfCount = Math.log(count)/Math.log(2);
            if (logOfCount%1 == 0) {
                countInRow = 0;
            }
            intLogOfCount = (int) logOfCount;
            x = (controller.displayBoard.getWidth() - rectangle.getWidth()) / 2 - rectangle.getWidth()/2  * Math.pow(2, intLogOfCount) + countInRow * rectangle.getWidth();
            y = CEILING_GAP + intLogOfCount * (rectangle.getHeight() + GAP_BETWEEN_ROW);
            rectangle.setX(x);
            rectangle.setY(y);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            Label label = new Label(item.toString());
            label.setLayoutX(x + X_ADJUSTMENT);
            label.setLayoutY(y + Y_ADJUSTMENT);
            if (count > 1) {
                Line line = new Line();
                line.setStartX(rectangle.getX() + rectangle.getWidth()/2);
                line.setStartY(rectangle.getY());
                lineEndX = (controller.displayBoard.getWidth() - rectangle.getWidth()) / 2 - rectangle.getWidth()/2  * Math.pow(2, (intLogOfCount-1)) + ((int) ((countInRow)/2) + 1) * rectangle.getWidth();
                line.setEndX(lineEndX - rectangle.getWidth()/2);
                line.setEndY(line.getStartY()-GAP_BETWEEN_ROW);
                list.add(line);
            }
            list.add(rectangle);
            list.add(label);
            count++;
            countInRow++;
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
        //add the offer operation
        Button button1 = new Button("add");
        button1.setPrefWidth(1000);
        TextField textField1 = new TextField();
        textField1.setOnMouseClicked(event -> {
            textField1.selectAll();
        });
        EventHandler<ActionEvent> eventHandler1 = event -> {
            if (this.isInt(textField1.getText())) {
                heap.offer(Integer.parseInt(textField1.getText()));
                this.controller.refreshOutput(textField1.getText());
                textField1.requestFocus();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Non-numeric input");
                alert.setContentText("\"" + textField1.getText() + "\" is not a number");
                alert.showAndWait();
            }
        };
        ControlWrapper controlWrapper1 = new ControlWrapper(textField1, button1, eventHandler1);
        list.add(controlWrapper1);

        // add the poll operation
        Button button2 = new Button("Poll");
        button2.setPrefWidth(1000);
        TextField textField2 = new TextField();
        textField2.setVisible(false);
        EventHandler<ActionEvent> eventHandler2 = event -> {
            if (this.heap.isEmpty()) {
                this.controller.outputLabel.setText("Empty");
                return;
            }
            this.controller.refreshOutput(Integer.toString(heap.poll()));
        };
        ControlWrapper controlWrapper2 = new ControlWrapper(textField2, button2, eventHandler2);
        list.add(controlWrapper2);

        // add the peek operation
        Button button3 = new Button("Peek");
        button3.setPrefWidth(1000);
        TextField textField3 = new TextField();
        textField3.setVisible(false);
        EventHandler<ActionEvent> eventHandler3 = event -> {
            if (this.heap.isEmpty()) {
                this.controller.outputLabel.setText("Empty");
                return;
            }
            this.controller.outputLabel.setText(Integer.toString(heap.peek()));
        };
        ControlWrapper controlWrapper3 = new ControlWrapper(textField3, button3, eventHandler3);
        list.add(controlWrapper3);
        return list;
    }

    /**
     * Tells if the heap is empty
     * @return true if it is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    /**
     * Turns the heap into the string
     * @return the string result of serialization
     */
    @Override
    public String serialize() {
        String result = "";
        for (Integer i : this.heap) {
            result = result + " " + i;
        }
        return result;
    }

    /**
     * Parse a string to get the heap
     * @param stringRepresentation the input string
     * @return true if the input is valid, false if not
     */
    @Override
    public boolean deserialize(String stringRepresentation) {
        if (stringRepresentation.isEmpty()) {
            this.heap.clear();
            return true;
        }
        String[] arr = stringRepresentation.split("\\s+");
        PriorityQueue<Integer> tempHeap = new PriorityQueue<Integer>();
        for (String str : arr) {
            if (this.isInt(str)) {
                tempHeap.offer(Integer.parseInt(str));
            } else {
                return false;
            }
        }
        this.heap = tempHeap;
        return true;
    }
    private class maxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y)
        {
            if (x < y)
            {
                return 1;
            }
            if (x > y)
            {
                return -1;
            }
            return 0;
        }
    }
    /**
     * Extra control for the heap, switch back and forth from max and min heap
     * @return the list of extra control components
     */
    @Override
    public List<Node> extraControls() {
        List<Node> extraControls = new ArrayList<>();
        ToggleGroup group = new ToggleGroup();
        RadioButton maxHeap = new RadioButton("Max Heap");
        RadioButton minHeap = new RadioButton("Min Heap");
        minHeap.setSelected(true);
        maxHeap.setToggleGroup(group);
        minHeap.setToggleGroup(group);
        HBox groupBox = new HBox();
        groupBox.getChildren().add(minHeap);
        groupBox.getChildren().add(maxHeap);
        groupBox.setSpacing(5);
        extraControls.add(groupBox);
        maxHeap.setOnAction(event -> {
            PriorityQueue<Integer> newHeap = new PriorityQueue<>(this.heap.size(), new maxHeapComparator());
            while (!this.heap.isEmpty()) {
                newHeap.add(this.heap.poll());
            }
            this.heap = newHeap;
            this.controller.refreshOutput("Output");
        });
        minHeap.setOnAction(event -> {
            PriorityQueue<Integer> newHeap = new PriorityQueue<>();
            while (!this.heap.isEmpty()) {
                newHeap.add(this.heap.poll());
            }
            this.heap = newHeap;
            this.controller.refreshOutput("Output");
        });
        return extraControls;
    }
}
