package calculator;

public class Node <T> {
	public T data;
	public Node<T> left;//new NullNode<T>();
	public Node<T> right;//new NullNode<T>();
	public Node(T data){
		this.data = data;
	}
	public Node(){}
}
