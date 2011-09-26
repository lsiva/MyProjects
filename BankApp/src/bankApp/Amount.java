package bankApp;

public class Amount implements Comparable<Object> {
	private int value;
	
	public Amount(int val){
		this.value = val;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void add(int addValue) {
		this.value = value + addValue;
	}

	public void subtract(int addValue) {
		this.value = value - addValue;
	}

	
	@Override
	public int compareTo(Object obj) {
		int result = -1;
		if(this.equals(obj))
			result =  0;
		else if(this.getValue() > ((Amount)obj).getValue()){
			result = 1;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;

		Amount amount = (Amount) obj;
		return value == amount.value;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + value;
		return hash;
	}

}
