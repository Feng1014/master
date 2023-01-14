package src.class02;

import java.util.HashMap;

/**
 * 假设此时到来的元素是A，则打印A；如果先到来C，则需要等待B到来，再等待A到来，此刻才能打印出A，B，C
 */
public class ReceiveAndPrintOrderLine {

    public static class Node {
        public String info;
        public Node next;

        public Node(String in) {
            info = in;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node node = new Node(info);
            headMap.put(num, node);
            tailMap.put(num, node);
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = node;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if (headMap.containsKey(num + 1)) {
                node.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }
            if (num == waitPoint) {
                print();
            }
        }

        public void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MessageBox messageBox = new MessageBox();
        messageBox.receive(2, "e");
        messageBox.receive(1, "h");
        messageBox.receive(5, "o");
        messageBox.receive(4, "l");
        messageBox.receive(3, "l");
    }

}
