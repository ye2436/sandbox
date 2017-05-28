package interview.li.other;

public class MidStack<T> {

    int size;
    Node<T> head;
    Node<T> tail;
    Node<T> mid;

    public MidStack() {

    }

	public T pop() {
		if (tail == null) {
			return null;
		}
		Node<T> temp = tail;
		if (tail == head) {
			tail = null;
			head = null;
			mid = null;
		} else {
			tail.pre.next = null;
			if (size % 2 != 0) {
				mid = mid.pre;
			}
			tail = tail.pre;
		}
		size--;
		return temp.val;
	}
	
	public void push(T val) {
		Node<T> newNode = new Node<>(val);
		if (head == null) {
			head = newNode;
			tail = newNode;
			mid = newNode;
		} else {
			tail.next = newNode;
			newNode.pre = tail;
			tail = newNode;
			if (size % 2 == 0) {
				mid = mid.next;
			}
		}
		size++;
	}
	
	public T peekMid() {
		if (mid == null) {
			return null;
		}
		return mid.val;
	}
	
	public T popMid() {
		if (mid == head) {
			T temp = mid.val;
			mid = head = tail = null;
			return temp;
		}
		mid.pre.next = mid.next;
		mid.next.pre = mid.pre;
		if (size % 2 == 0) {
			mid = mid.next;
		} else {
			mid = mid.pre;
		}
		size--;
		return mid.val;
	}
	
	public String toString() {
		if (head == null) {
			return "";
		}
		Node<T> cur = head;
		StringBuilder sb = new StringBuilder();
		while (cur != null) {
			sb.append(cur.val).append(',');
			cur = cur.next;
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		MidStack<Integer> ms = new MidStack<>();
		ms.push(1);
		ms.push(2);
		ms.push(3);
		ms.pop();
		ms.pop();
		System.out.println("stack: " + ms);
		System.out.println("mid: " + ms.peekMid());
		ms.popMid();
		System.out.println("stack: " + ms);
		System.out.println("mid: " + ms.peekMid());
	}

    static class Node<T>{
        T val;
        Node<T> next;
        Node<T> pre;

        public Node(T val){
            this.val = val;
        }
    }

}
