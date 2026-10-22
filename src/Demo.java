public class Demo {
	static void main (String[] args) {
		DynamicArray<Integer> arr = new DynamicArray<>(Integer.class,3);
		arr.push(5);
		System.out.println(arr.pop().toString());
	}
}
