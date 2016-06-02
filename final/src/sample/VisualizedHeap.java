package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * The model class of Heap in MVC
 * @author Claire Tagoe, Duo Tao and Yijun Wang
 */
public class VisualizedHeap extends VisualizedDataStructure {
    private PriorityQueue<Integer> heap;

    /**
     * Empty heap constructor
     */
    public VisualizedHeap(Controller controller) {
        super(controller);
        this.heap = new PriorityQueue<Integer>();
    }

    /**
     * Reset the heap with random numbers
     * @param size The size of the heap
     * @param range The range of random numbers
     */
    @Override
    public void randomize(int size, int range) {
        this.heap.clear();
        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * range + 1);
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
        if (this.heap.isEmpty()) {
            return list;
        } else {
            for (Iterator<Integer> itr = this.heap.iterator(); itr.hasNext(); itr.next()){
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
        //add the offer operation
        Button button1 = new Button("add");
        button1.setPrefWidth(1000);
        TextField textField1 = new TextField();
        EventHandler<ActionEvent> eventHandler1 = event -> {
            if (this.isInt(textField1.getText())) {
                heap.offer(Integer.parseInt(textField1.getText()));
                this.controller.refreshOutput(textField1.getText());
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
        /*
        String result;
        Object[] arrayForm;
        arrayForm = this.heap.toArray();
        result = Arrays.toString(arrayForm);
        System.out.println(result);
        return result;
        */
        return "";
    }

    /**
     * Parse a string to get the heap
     * @param stringRepresentation the input string
     * @return true if the input is valid, false if not
     */
    @Override
    public boolean deserialize(String stringRepresentation) {
        /*
        String[] stringArray = stringRepresentation.replaceAll(" ", "").split(",");
        Integer[] intArray = new Integer[stringArray.length];
        Integer item;
        for (int i = 0; i < stringArray.length; i++) {
            try {
                item = new Integer(stringArray[i]);
                intArray[i] = item;
            } catch (NumberFormatException nfe) {};
        }

        PriorityQueue<Integer> newHeap = new PriorityQueue<Integer>();
        for (int j = 0; j < intArray.length; j++) {
            newHeap.add(intArray[j]);
        }
        */
        return false;
    }
}
