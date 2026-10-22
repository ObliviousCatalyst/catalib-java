import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public class DynamicArray <T> {
	private T[] internal;
	public final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public DynamicArray (int size) {
		ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
		type = (Class<T>) superClass.getActualTypeArguments()[0];
		internal = (T[]) Array.newInstance(type,size);
	}
	
	public boolean checkBounds (int index) {
		if (index < 0) {
			return false;
		}
		return index < internal.length;
	}
	
	public T get (int index) {
		if (checkBounds(index)) {
			return internal[index];
		}
		throw new ArrayIndexOutOfBoundsException("cannot get item from index '%d' because it is out of bounds".formatted(index));
	}
	
	public void set (int index, T val) {
		if (checkBounds(index)) {
			internal[index] = val;
			return;
		}
		throw new ArrayIndexOutOfBoundsException("cannot set item to index '%d' because it is out of bounds".formatted(index));
	}
	
	public int length () {
		return internal.length;
	}
	
	public int sysLength () {
		return internal.length - 1;
	}
	
	public int lastIndex () {
		int ix = 0;
		for (int pt = 0; pt < internal.length; pt++) {
			if (internal[pt] != null) {
				ix = pt;
			}
		}
		return ix;
	}
	
	public void push (T val) {
		int last = lastIndex();
		if (last == sysLength()) {
			internal = Arrays.copyOf(internal, internal.length + 1);
		}
		last++;
		set(last, val);
	}
	
	public T pop () {
		int last = lastIndex();
		T item = get(last);
		set(last, null);
		return item;
	}
	
	public void volatileShift (int index) {
		int syslen = sysLength();
		for (int pt = index +1; pt <= syslen; pt++) {
			internal[pt - 1] = internal[pt];
		}
		internal[syslen] = null;
	}
	
	public void volatileShift () {
		volatileShift(0);
	}
	
	public T shift (int index) {
		T temp = get(index);
		volatileShift(index);
		return temp;
	}
	
	public T shift () {
		return shift(0);
	}
	
	public void volatileUnshift () {
	
	}
	
	public void unshift (T val) {
	
	}
}
