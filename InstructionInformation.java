package Apex;

import java.util.Iterator;

public class InstructionInformation implements Cloneable {
	
	
	
	public int getDstArchValue() {
		return dstArchValue;
	}

	public void setDstArchValue(int dstArchValue) {
		this.dstArchValue = dstArchValue;
	}


	private String opcode;
	private String source2;
	private String source2Arch;
	private int source2ArchValue;
	private Integer literalvalue;
	private int instructionNumber;
	private boolean takeValue;
	private int result;
	private String StageState;
	private String desination;
	private String CurrentInstruction;
	private String source1;
	private String source1Arch = null;
	private int source1ArchValue;
	private String dstArch;
	private int dstArchValue;
	private String dstPhy;
	private int instructionAddress;
   
	

	public String getSource2Arch() {
		return source2Arch;
	}

	public void setSource2Arch(String source2Arch) {
		this.source2Arch = source2Arch;
	}

	public int getSource2ArchValue() {
		return source2ArchValue;
	}

	public void setSource2ArchValue(int source2ArchValue) {
		this.source2ArchValue = source2ArchValue;
	}

	public String getSource1Arch() {
		return source1Arch;
	}

	public void setSource1Arch(String source1Arch) {
		this.source1Arch = source1Arch;
	}

	public int getSource1ArchValue() {
		return source1ArchValue;
	}

	public void setSource1ArchValue(int source1ArchValue) {
		this.source1ArchValue = source1ArchValue;
	}


		public InstructionInformation() {

		this.CurrentInstruction = "";
	}

	public void setInstructionAdd(int instructionAddress) {
		this.instructionAddress = instructionAddress;
	}

	public int getInstructionAdd() {
		return this.instructionAddress;
	}

	public boolean isTakeValue() {
		return takeValue;
	}

	public void setTakeValue(boolean takeValue) {
		this.takeValue = takeValue;
	}

	public int getInstructionNumber() {
		return this.instructionNumber;
	}


	public void setOperation(String opcode) {
		this.opcode = opcode;
		this.CurrentInstruction += " " + this.opcode;
	}
	
	
	public String getOperation() {
		return this.opcode;
	}

	

	public void setInstructionNumber(int instrNumber) {
		this.instructionNumber = instrNumber;
		this.CurrentInstruction += "" + this.instructionNumber + "" + ":";
	}

	public void setDestination(String destinationReg) {
		this.desination = destinationReg.replace(",", "");
		this.CurrentInstruction += " " + this.desination;
	}

	public String getDestination() {
		return this.desination;
	}
	
	public void setSource1(String sourceReg1) {
		this.source1 = sourceReg1.replace(",", "");
		if (!opcode.equals("BNZ") && !opcode.equals("BZ") && !opcode.equals("JUMP") && !opcode.equals("BAL")) {
			this.CurrentInstruction += " " + this.source1;
		}
	}


	public String getSource1() {
		return this.source1;
	}


	public void setSource2(String sourceReg2) {
		this.source2 = sourceReg2;
		this.CurrentInstruction += " " + this.source2;
	}

	public String getSource2() {
		return this.source2;
	}

	public void setLiteral(int literal) {
		this.literalvalue = literal;
		this.CurrentInstruction += " " + this.literalvalue;
	}
	
	public int getLiteral() {
		return this.literalvalue;
	}

	public void setCurrentInstruction(String CurrentInstruction) {
		this.CurrentInstruction = CurrentInstruction;
	}

	public String getCurrentInstruction() {
		return this.CurrentInstruction;
	}

	public void setResult(int tempResult) {
		this.result = tempResult;
	}

	public int getInstructionResult() {
		return this.result;
	}

	public void setInstructionStage(String stageState) {
		this.StageState = stageState;
	}

	public String getInstructionStage() {
		return this.StageState;
	}
	
	public void setdstArch(String src){
		this.dstArch = src;
	}

	public void setdstPhy(String src){
		this.dstPhy = src;
	}
	

	public String getdstPhy(){
		return this.dstPhy;
	}
	
	public String getdstArch(){
		return this.dstArch;
	}
	

	public String getdst(){
		return this.desination;
	}
	

	public void setdst(String src){
		this.desination = src;
	}
	
	public void setrenamedCurrentinstruction()
	{
		this.CurrentInstruction = "";
		this.CurrentInstruction += " " + this.opcode;
		if(this.desination != null)
		{
			this.CurrentInstruction += " " + this.desination;
		}
		
	    if(this.source2 != null)
		{
			this.CurrentInstruction += " " +this.source2;
		}
	    
	
	   if(this.source1 != null&&!this.opcode.equals("BNZ") && !this.opcode.equals("BZ") && !this.opcode.equals("JUMP"))
		{
			this.CurrentInstruction += " " +this.source1;
		}
	   
	   if(this.literalvalue != null)
			{
				this.CurrentInstruction += " " +this.literalvalue;
			}
				
	}
	
	public Object getClone() throws CloneNotSupportedException
	{
		return this.clone();
	}
	

	@Override
	public String toString() {

		return this.CurrentInstruction;
	}

}
