package Operators;

import Structure.Node;

public interface Action<T> {
	
	public Node<T> operate(Node<T> node_1, Node<T> node_2);

}
