public class EventPriorityQueue {

    class Node {
        Event event;
        int priority;

        Node(Event event, int priority) {
            this.event = event;
            this.priority = priority;
        }
    }

    private int size;
    public int indexCounter;
    public int parent;
    private Node[] array;

    public EventPriorityQueue() {
        this.array = new Node[10];


    }

   // public int arraysize = 10;


    public int size() {
        return size; //done i think
    }


    public void insert(Event event, int priority) {
        Node node = new Node(event, priority);
       if (size() == (array.length) ){
           Node[] temp = new Node [array.length * 2];
           for (int i = 0; i < array.length; i++){
               temp[i] = array[i];
           }
          // arraysize = arraysize*2;
           array = temp;
        }
        if (array[0] == null) {
                size++;
                array[0] = node;
                return;
       }
        if ( (array[0] != null)) {
            array[size] = node;
            reorder(array, size);
            size++;
        }

    }

    public Node[] getArray() {
        return array;
    }


    public Node[] reorder(Node[] array, int currentIndex) {
        parent = (int) Math.floor((currentIndex-1) /2);
        if (array[parent] == null ) {
            return array;
        }
        else if (array[currentIndex].priority >= array[parent].priority){
            return array;
        }
            else if (array[currentIndex].priority < array[parent].priority){
            Node temp = array[parent];
            array[parent] = array[currentIndex];
            array[currentIndex] = temp;
            reorder(array, parent);
            return array;
        }
        return array;
    }

    public Event peek() {
        Node result = array[0];
        return result.event;
    }

    public static boolean checkHeapProperty(int[] priorities) {
        for (int i = 0; i < priorities.length; i++) {
            if (2 * i + 1 < priorities.length && priorities[i] > priorities[2 * i + 1]) {
                return false;
            }
            if (2 * i + 2 < priorities.length && priorities[i] > priorities[2 * i + 2]) {
                return false;
            }
        }
        return true;
    }

    public Event poll() {
        if (size == 0){
            return null;
        }
            Node result = array[0];
            // int currentIndex = 0;
            int lastElementIndex = size - 1;
            array[0] = array[lastElementIndex];
            array[lastElementIndex] = null;
            //array[currentIndex] = null;
            size = size - 1;
            bubbleDown(array, 0);
            return result.event;

    }

    public void bubbleDown(Node[] array, int currentIndex) {
       Node  child1 = array[currentIndex*2+1];
       Node  child2 = array[(currentIndex*2) +2];
       Node currentNode = array[currentIndex];
       Node lowestChild = null;
       int location = 0;
        if ((child1 == null)){
            return;
        }
        if (child2 == null){
            lowestChild = child1;
        }
        else if (child1.priority<child2.priority){
            lowestChild = child1;
            location = (currentIndex*2 +1);
        }
        else if (child1.priority>child2.priority) {
            lowestChild = child2;
            location = (currentIndex*2) +2;
        }
        else {
            lowestChild = child1;
            location = (currentIndex*2 +1);
        }
         if (currentNode.priority > lowestChild.priority){
            Node temp = array[currentIndex];
            array[currentIndex] = lowestChild;
            array[location] = temp;
             bubbleDown(array, location);
        }

    }


    public int[] toPrioritiesArray() {
        // DO NOT CHANGE THIS FUNCTION
        int[] result = new int[this.size()];
        for (int i = 0; i < this.size(); i++) {
            result[i] = this.array[i].priority;
        }
        return result;
    }




    public static void main(String[] args) {
        // initialize the queue
        EventPriorityQueue queue = new EventPriorityQueue();

        // add some numbers to the queue
        int NUM_EVENTS = 7;
        for (int i = NUM_EVENTS; i > 0; i--) {
            queue.insert(new Event(i), i);
            System.out.println("inserted " + i);
        }
        queue.poll();

        int[] x = queue.toPrioritiesArray();
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);

        }
        System.out.println("new line");

        queue.poll();

        int[] z = queue.toPrioritiesArray();
        for (int i = 0; i < z.length; i++) {
            System.out.println(z[i]);

        }

       // if (x = )

        //System.out.println(x.length);




        // poll everything
     //   for (int i = 1; i <= NUM_EVENTS; i++) {
       //     int next = queue.poll().getTime();
          //  System.out.println("polled " + next);
        //}



        System.out.println(checkHeapProperty(x));





    }

}

