package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public VisualizedHeap(Label label) {
        super(label);
        this.heap = new PriorityQueue<Integer>();
    }

    /**
     * Reset the heap with random numbers
     * @param size The size of the heap
     * @param range The range of random numbers
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
        TextField textField1 = new TextField();
        EventHandler<ActionEvent> eventHandler1 = event -> {
            if (this.isInt(textField1.getText())) {
                heap.offer(Integer.parseInt(textField1.getText()));
                outputLabel.setText(textField1.getText());
                System.out.println(outputLabel);
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
        TextField textField2 = new TextField();
        textField2.setDisable(true);
        EventHandler<ActionEvent> eventHandler2 = event -> {
            if (this.heap.isEmpty()) {
                outputLabel.setText("Empty");
                return;
            }
            outputLabel.setText(Integer.toString(heap.poll()));
        };
        ControlWrapper controlWrapper2 = new ControlWrapper(textField2, button2, eventHandler2);
        list.add(controlWrapper2);

        // add the peek operation
        Button button3 = new Button("Peek");
        TextField textField3 = new TextField();
        textField3.setDisable(true);
        EventHandler<ActionEvent> eventHandler3 = event -> {
            if (this.heap.isEmpty()) {
                outputLabel.setText("Empty");
                return;
            }
            outputLabel.setText(Integer.toString(heap.peek()));
        };
        ControlWrapper controlWrapper3 = new ControlWrapper(textField3, button3, eventHandler3);
        list.add(controlWrapper3);
        return list;
    }

    /**
     * Helper method to check if a string represents an integer
     * @param s the string to be checked
     * @return true if it represents an integer, false if not
     */
    private boolean isInt(String s) {
        return s.matches("[-+]?\\d+");
    }

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

    @Override
    public VisualizedHeap deserialize(String stringRepresentation) {
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
        return null;
    }

    public static void main(String[] args) {
        //Testing serialize and deserialize
        /*
        Object[] arrayForm;
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        arrayForm = heap.toArray();
        String stringArray = Arrays.toString(arrayForm);
        String result = stringArray.substring(1,stringArray.length()-1);
        System.out.println("The result is: " + result);

        String[] items = result.replaceAll(" ", "").split(",");
        System.out.println("The first item is: "+ items[0] + ". The second item is: " + items[1] + ". The third item is: " + items[2]);

        Integer[] intArray = new Integer[items.length];
        Integer item;
        for (int i = 0; i < items.length; i++) {
            try {
                item = new Integer(items[i]);
                intArray[i] = item;
            } catch (NumberFormatException nfe) {};
        }
        System.out.println("The first int is: "+ intArray[0] + ". The second int is: " + intArray[1] + ". The third int is: " + intArray[2]);

        PriorityQueue<Integer> newHeap = new PriorityQueue<Integer>();
        for (int j = 0; j < intArray.length; j++) {
            newHeap.add(intArray[j]);
        }
        Object[] newArrayForm;
        newArrayForm = newHeap.toArray();
        System.out.println(stringArray + " should be the same as " + Arrays.toString(newArrayForm));
        */

    }
}
