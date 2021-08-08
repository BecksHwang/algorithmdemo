package com.becks.algorithmdemo.tencenttest;

/**
 * @ClassName ReverseLinkedList
 * @Description 反转单链表
 * @Author Becks
 * @Date 2021/7/27
 **/
public class ReverseLinkedList {

     public static class Node{
         public Node(int index){
             this.index = index;
             this.next = null;
         }

         public Node(){
             this.index = -1;
             this.next = null;
         }


        public int index;
        public Node next;
    }

    public Node reverseLinkedListRecursion(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node temp = head.next;
        Node newHead = reverseLinkedListRecursion(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    public Node reverseLinkedList(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node pre = null;
        Node cur = null;
        while (head != null){
            cur = head.next;
            head.next = pre;
            pre = head;
            head = cur;
        }
        return pre;
    }

    public void nodePrint(Node head){
         if(head == null){
             return;
         }

         while(head.next != null){
             System.out.print(head.index + " -> ");
             head = head.next;
         }
         System.out.println(head.index);
    }

    public static void main(String[] args){
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        a.next = b;
        b.next = c;
        c.next = d;
        new ReverseLinkedList().nodePrint(a);
        Node newHead = new ReverseLinkedList().reverseLinkedList(a);
        //Node newHead = new ReverseLinkedList().reverseLinkedListRecursion(a);
        new ReverseLinkedList().nodePrint(newHead);
    }

}
