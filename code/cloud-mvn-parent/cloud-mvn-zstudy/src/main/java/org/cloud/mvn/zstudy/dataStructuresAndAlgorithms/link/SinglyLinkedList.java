package org.cloud.mvn.zstudy.dataStructuresAndAlgorithms.link;

/**
 * @ClassName: SinglyLinkedList 
 * @Description: 单向链表
 */
public class SinglyLinkedList<E> {
	Node<E> head = null; // 头节点

    /**
     * @author LiuTao @date 2020年1月17日 下午3:06:22
     * @ClassName: Node<E> 
     * @Description: 链表中的节点,data代表节点的值,next是指向下一个节点的引用
     */
    private static class Node<E> {
    	E data;// 节点的对象,即内容数据
        Node<E> next = null;// 节点的引用,指向下一个节点

        public Node(E data) {
            this.data = data;
        }
    }

    /**
     * 向链表中插入数据
     * @param d
     */
    public void addNode(E d) {
        Node<E> newNode = new Node<E>(d);// 实例化一个节点
        if (head == null) {
            head = newNode;
            return;
        }
        Node<E> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    /**
     * @param index:删除第index个节点
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {//超出链表的下标的index
            return false;
        }
        if (index == 1) {
            head = head.next;
            return true;
        }
        int i = 1;
        Node<E> preNode = head;
        Node<E> curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }

    /**
     * @return 返回节点长度
     */
    public int length() {
        int length = 0;
        Node<E> tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * 在不知道头指针的情况下删除指定节点
     * @param n
     * @return
     */
    public boolean deleteNode11(Node<E> n) {
        if (n == null || n.next == null)
            return false;
        E tmp = n.data;
        n.data = n.next.data;
        n.next.data = tmp;
        n.next = n.next.next;
        System.out.println("删除成功！");
        return true;
    }
    /**
     * 链表反转
     * @param head
     * @return
     */
    public Node<E> ReverseIteratively(Node<E> head) {
        Node<E> pReversedHead = head;
        Node<E> pNode = head;
        Node<E> pPrev = null;
        while (pNode != null) {
            Node<E> pNext = pNode.next;
            if (pNext == null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        this.head = pReversedHead;
        return this.head;
    }
    /**
     * 查找单链表的中间节点
     * @param head
     * @return
     */
    public Node<E> SearchMid(Node<E> head) {
        Node<E> p = this.head, q = this.head;
        while (p != null && p.next != null && p.next.next != null) {
            p = p.next.next;
            q = q.next;
        }
        System.out.println("Mid:" + q.data);
        return q;
    }
    /**
     * 查找倒数 第k个元素
     * 
     * @param head
     * @param k
     * @return
     */
    public Node<E> findElem(Node<E> head, int k) {
        if (k < 1 || k > this.length()) {
            return null;
        }
        Node<E> p1 = head;
        Node<E> p2 = head;
        for (int i = 0; i < k; i++)// 前移k步
            p1 = p1.next;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }
    /**
     * 排序
     * @return
     */
    public Node<E> orderList() {
        Node<E> nextNode = null;
        E tmp = null;
        Node<E> curNode = head;
        while (curNode.next != null) {
            nextNode = curNode.next;
            while (nextNode != null) {
                if (curNode.data.hashCode() > nextNode.data.hashCode()) {
                    tmp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = tmp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }
    /**
     * 删除重复节点
     */
    public void deleteDuplecate(Node<E> head) {
        Node<E> p = head;
        while (p != null) {
            Node<E> q = p;
            while (q.next != null) {
                if (p.data == q.next.data) {
                    q.next = q.next.next;
                } else
                    q = q.next;
            }
            p = p.next;
        }
    }
    /**
     * 从尾到头输出单链表,采用递归方式实现
     * @param pListHead
     */
    public void printListReversely(Node<E> pListHead) {
        if (pListHead != null) {
            printListReversely(pListHead.next);
            System.out.println("printListReversely:" + pListHead.data);
        }
    }
    
    /**
     * @Title: printList 
     * @Description: 打印链表
     */
    public void printList() {
        Node<E> tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }
    /**
     * @Title: main 
     * @param args
     */
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(2);
        list.addNode(55);
        list.addNode(36);
        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.printList();
        list.deleteNode(4);
        System.out.println("After deleteNode(4):");
        list.printList();
    }
}
