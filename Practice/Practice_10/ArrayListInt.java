public class ArrayListInt {
	private static final int INITIAL_CAPACITY = 2;
	private static final int CAPACITY_MULTIPLIER = 2;
	
	private int[] data;
	private int size;
	
	public ArrayListInt() {
		data = new int[INITIAL_CAPACITY];
		size = 0;
	}

	public int size() {
		return size;
	}
	
	public int get(int i) {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}

		return data[i];
	}
	
	public void set(int i, int value) {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}

		data[i] = value;
	}
	
	public void add(int value) {
		if (size + 1 > data.length) {
			int[] temp = new int[data.length * CAPACITY_MULTIPLIER];
			for (int i = 0; i < size; ++i) {
				temp[i] = data[i];
			}
			temp[size] = value;
			data = temp;
		} else {
			data[size] = value;
		}
		
		++size;
	}
	
	public void add(int i, int value) {
		int[] temp = new int[data.length + 1];

		for (int j = 0; j < i; ++j) {
			temp[j] = data[j];
		}
		temp[i] = value;
		for (int j = i; j < data.length; ++j) {
			temp[j + 1] = data[j];
		}
		
		data = temp;
	}
	
	public int pop() {
		int result = data[data.length - 1];
		
		int[] temp = new int[data.length - 1];
		for (int i = 0; i < temp.length; ++i) {
			temp[i] = data[i];
		}
		data = temp;
		
		return result;
	}
	
	public int remove(int i) {
		int result = data[i];
		
		int[] temp = new int[data.length - 1];
		for (int j = 0; j < i; ++j) {
			temp[j] = data[j];
		}
		for (int j = i; j < temp.length; ++j) {
			temp[j] = data[j + 1];
		}
		
		data = temp;
		
		return result;
	}
	
}
