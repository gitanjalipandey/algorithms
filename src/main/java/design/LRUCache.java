package design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    DLLNode head , tail;
    int capacity;
    Map<Integer,DLLNode> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLLNode(0,0);
        tail = new DLLNode(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int value = -1;
        if(map.containsKey(key)){
            DLLNode node = map.get(key);
            value = node.value;
            remove(node);
            movetohead(node);
        }
        return value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            DLLNode node = map.get(key);
            node.value = value;
            map.put(key,node);
            remove(node);
            movetohead(node);
        }else{
            if(capacity == map.size()){
                DLLNode currtail = poptail();
                map.remove(currtail.key);

            }
            DLLNode node = new DLLNode(key,value);
            map.put(key,node);
            movetohead(node);
        }
    }

    private DLLNode poptail(){
        DLLNode currtail = tail.prev;
        tail.prev = currtail.prev;
        currtail.prev.next = tail;
        return currtail;

    }

    private void remove(DLLNode node){
        DLLNode prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
    }

    private void movetohead(DLLNode node){
        DLLNode currtop = head.next;
        head.next = node;
        node.prev = head;
        node.next = currtop;
        currtop.prev = node;
    }
}

class DLLNode {
    int key;
    int value;
    DLLNode prev;
    DLLNode next;

    public DLLNode(int key , int value){
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */