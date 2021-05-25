package com.becks.algorithmdemo.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LRUCacheDemo
 * @Description 自定义数据结构实现LRU算法
 * @Author Becks
 * @Date 2021/4/21
 */
public class LRUCache2 {

    //自定义双向链表的数据载体Node
    class Node<K, V> {
        K key;
        V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        public Node() {
            prev = next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            prev = next = null;
        }
    }

    //自定义双向链表
    class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        // 链表头部增加节点
        public void addHead(Node<K, V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        // 删除一个节点
        public void removeNode(Node<K, V> node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = node.next = null;
        }

        // 获取尾节点
        public Node<K, V> getLastNode(){
            return tail.prev;
        }
    }

    // 容量
    private int capacity;
    // map提高查询效率
    private Map<Integer, Node<Integer, Integer>> map;
    // 双向链表提高插入效率
    private DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCache2(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.doubleLinkedList = new DoubleLinkedList<>();
    }

    //获取节点
    public Integer get(Integer key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        // 移除已经存在的节点
        doubleLinkedList.removeNode(node);
        // 将该节点移动到链表头部
        doubleLinkedList.addHead(node);
        return node.value;
    }

    // 添加节点
    public void put(Integer key, Integer value){
        if(map.containsKey(key)){
            // 更新已经存在的节点
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);
            // 移除已经存在的节点
            doubleLinkedList.removeNode(node);
            // 将该节点移动到链表头部
            doubleLinkedList.addHead(node);
        }else {
            if(map.size() == capacity){
                // 容量已满，删除尾节点再新增
                Node<Integer, Integer> nodeOld = doubleLinkedList.getLastNode();
                doubleLinkedList.removeNode(nodeOld);
                map.remove(nodeOld.key);
            }
            //新增节点
            Node<Integer, Integer> nodeNew = new Node<>(key, value);
            map.put(key, nodeNew);
            doubleLinkedList.addHead(nodeNew);
        }

    }

    public static void systemOut(LRUCache2 lruCache2){
        systemOutValue(lruCache2.doubleLinkedList.head.next);
        System.out.println("==========================");
    }

    public static void systemOutValue(Node node){
        if(node.next != null){
            System.out.println(node.value);
            systemOutValue(node.next);
        }

    }

    public static void main(String[] args) {
        LRUCache2 lruCache2 = new LRUCache2(3);
        lruCache2.put(1,1);
        lruCache2.put(2,2);
        lruCache2.put(3,3);
        System.out.println("初始化完毕：");
        systemOut(lruCache2);
        System.out.println("get(2)后：");
        lruCache2.get(2);
        systemOut(lruCache2);
        System.out.println("put(4,4)后：");
        lruCache2.put(4,4);
        systemOut(lruCache2);
        System.out.println("put(5,5)后：");
        lruCache2.put(5,5);
        systemOut(lruCache2);
    }


}
