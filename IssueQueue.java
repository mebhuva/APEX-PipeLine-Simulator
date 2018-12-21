package Apex;



public class IssueQueue{ /// Need to think about Load Store Queue if we use ISQ to hold its entry 
	private boolean status;
	private boolean src1Available;
	private boolean src2Available;
	InstructionInformation inst;
	
	IssueQueue(InstructionInformation instruction){
	  status = src1Available = src2Available = false;  
	  inst = instruction;
	}
	
	public void setSrc1(String src){
		inst.setSource1(src);
		src1Available = true;
		if(src2Available)
			status = true;
	}
	
	public void setSrc2(String src){
		inst.setSource2(src);
		src2Available = true;
		if(src1Available)
			status = true;
	}
	
	public void setSrc1Available(boolean flag){
		src1Available = flag;
	}
	
	public void setSrc2Available(boolean flag){
		src2Available = flag;
	}
	
	public void setStatus(boolean flag){
		status = flag;
	}
	
	boolean getStatus(){
		return status;
	}
	
	InstructionInformation getInstruction(){
		return inst;
	}
	
	void display(){
		//System.out.println("*************");
		//inst.displayInst();
		System.out.println("SRC1 "+src1Available+" SRC2 "+src2Available+" STATUS "+status);
	}
}