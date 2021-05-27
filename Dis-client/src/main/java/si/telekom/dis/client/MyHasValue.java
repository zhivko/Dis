package si.telekom.dis.client;

public interface MyHasValue<T> {
	public T getValue();
	public void setValue(String val);
	public void disable();
}
