package Structure;
import java.util.HashMap;
import java.util.Map;

import Calculation.*;

public class Tree <T> {
	
	//defining root and Tree constructor
	private Node<T> root;
	
	public Node<T> getRoot() {
		return root;
	}
	
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
    /*public Tree () {
		
		root = null;
		//constructor?
	}*/
	
	
	//Hashmap
	
	public Map<Node<T>, Map<Node<T>>> treeMap = new HashMap<Node<T>, HashMap<Node<T>>>();
	
	
	//creating the tree object and its nodes
	
	public Main main = new Main();
	public Tree tree = new Tree();
	
	public String[] list = main.getEnterCalculation();
	
	

	
	/*public void addNode(Node<T> node){
		
		tree.
		
	}*/
	
	// methods
	
	
	public void addChild(Node<T> parentNode, Node<T> childNode) {
		
	}
	
	
	
	
}
