package Apex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SharedDataResource {
	public Map<String, InstructionInformation> PipelineInformation= new HashMap<String, InstructionInformation>();
	public ArrayList<ARCRegister> ARCregstate = new ArrayList<ARCRegister>();
	public ArrayList<InstructionInformation> instructions; 
	public ArrayList<InstructionInformation> instructionsROB = new ArrayList<>();
	public ArrayList<Memory> memory =  new ArrayList<Memory>();
	public Queue<IssueQueue> ISQ = new LinkedList<IssueQueue>(); 
	public Queue<IssueQueue> LSQ = new LinkedList<IssueQueue>();
	RAT renameTable = new RAT();
    PRF prf = new PRF();
    ReOrder  rob = new ReOrder();
	int HaltFlag=0;
	int i = 0;
	public int PC;
    public int CycleCount = 0;
    int instrNum = 0;
    int instructionCounter = 0;
    public boolean srcOneFlag, srcTwoFlag;
    int robcounter = 0;
 	
public InstructionInformation getEmpty(){
		return null;
	}

public boolean StagesFinished() {
	if((PipelineInformation.get("Fetch")==null)&&(PipelineInformation.get("Decode")==null)&&
			(PipelineInformation.get("Execute : ALU")==null)&&
			(PipelineInformation.get("Execute : MUL1") == null)&&(PipelineInformation.get("Execute : MUL2") == null)&&
			(PipelineInformation.get("Execute : DIV1") == null)&&(PipelineInformation.get("Execute : DIV2") == null)
			&&(PipelineInformation.get("Execute : DIV3") == null)&&(PipelineInformation.get("Execute : DIV4") == null)
			&&(PipelineInformation.get("Memory")==null)&&(PipelineInformation.get("WriteBack")==null)&&(ISQ.isEmpty())
			&&(LSQ.isEmpty())&&robcounter == 0){
		return true;
	}
	else
	{
	return false;
	}
}
public ARCRegister getArchReg(String destRegister, SharedDataResource objSharedDataResource) {

	for (int i = 0; i < objSharedDataResource.ARCregstate.size(); i++) {
		if (objSharedDataResource.ARCregstate.get(i).getRegName().equals(destRegister)) {
			return objSharedDataResource.ARCregstate.get(i);
		}
	}
	return null;
}



}
