package Apex;

public class Memory {
	private int BaseAddress;
	private int Data;

	public Memory(int BaseAddress, int Data) {
		this.BaseAddress = BaseAddress;
		this.Data = Data;

	}

	public int getValue() {
		return this.Data;
	}

	public void setValue(int value) {
		this.Data = value;
	}

	public void setBaseAdd(int baseAddress) {
		this.BaseAddress = baseAddress;
	}

	public int getBaseAdd() {
		return this.BaseAddress;
	}

}
