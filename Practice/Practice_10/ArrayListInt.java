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
		if (size + 1 > data.length) {
			int[] temp = new int[data.length * CAPACITY_MULTIPLIER];

			for (int j = 0; j < i; ++j) {
				temp[j] = data[j];
			}
			temp[i] = value;
			for (int j = i; j < size; ++j) {
				temp[j + 1] = data[j];
			}

			data = temp;
		} else {
			for (int j = size - 1; j >= i; --j) {
				data[j + 1] = data[j];
			}
			
			data[i] = value;
		}
		
		++size;
	}
	
	public int pop() {
		int result = data[size - 1];
		--size;
		
		return data[size - 1];
	}
	
	public int remove(int i) {
		int result = data[i];

		for (int j = i; j < size; ++j) {
			data[j] = data[j + 1];
		}

		--size;

		return result;
	}
	
}
