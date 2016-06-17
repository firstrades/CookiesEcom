package ecom.model;

import java.io.Serializable;

public class TwoObjects<T,V> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private T obj1;
	private V obj2;
	
	
	public T getObj1() {
		return obj1;
	}
	public void setObj1(T obj1) {
		this.obj1 = obj1;
	}
	public V getObj2() {
		return obj2;
	}
	public void setObj2(V obj2) {
		this.obj2 = obj2;
	}
	
	
}
