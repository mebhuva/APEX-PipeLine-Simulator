package Apex;

public class ARCRegister {

	private int value;
	private boolean inOperation;
	private String registerName;
	private int registerNumber;
	private boolean validFlag = false;

	public ARCRegister(String regName, int value, boolean underOperation, int registerNumber) {
		this.registerName = regName;
		this.inOperation = underOperation;
		this.value = value;
		this.registerNumber = registerNumber;

	}

	public int getregisterNumber() {
		return this.registerNumber;
	}

	public void setregisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getRegName() {
		return this.registerName;
	}

	public int getValue() {
		return this.value;
	}

	public void setRegName(String regName) {
		this.registerName = regName;
	}

	public boolean isBusy() {
		return this.inOperation;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setBusy(boolean underOperation) {
		this.inOperation = underOperation;
	}


	public boolean getValidFlag() {
		return validFlag;
	}
	
	public void setValidFlag(boolean validFlag) {
		this.validFlag = validFlag;
	}
	public int AddRegister(String source) {
		String[] string = source.split("(?<=\\D)(?=\\d)");
		return Integer.parseInt(string[1]);
	}
}
