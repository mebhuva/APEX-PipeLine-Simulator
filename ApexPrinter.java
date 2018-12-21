package Apex;

import java.util.ArrayList;
import java.util.Iterator;

public class ApexPrinter {
	public void printstages(SharedDataResource objSharedDataResource) {
		// SharedDataResource objSharedDataResource = new SharedDataResource();
		// if(!objSharedDataResource.StagesFinished())
		// {
		System.out.println("Cycle No: " + objSharedDataResource.CycleCount);
		InstructionInformation instrInFetch = objSharedDataResource.PipelineInformation.get("Fetch");
		System.out.println("Fetch-Stage:           "
				+ (instrInFetch == null ? "No Operation" : instrInFetch.getInstructionStage() + "-------")
				+ (instrInFetch == null ? "Empty" : instrInFetch));

		InstructionInformation instrInDecode = objSharedDataResource.PipelineInformation.get("Decode");
		System.out.println("Decode-Stage:          "
				+ (instrInDecode == null ? "No Operation" : instrInDecode.getInstructionStage() + "-------")
				+ (instrInDecode == null ? "Empty" : instrInDecode));
		
		
		/*System.out.println("ISQ-Stage:          "
				+ (instrInISQ == null ? "No Operation" : instrInISQ.getInstructionStage() + "-------")
				+ (instrInISQ == null ? "Empty" : instrInISQ));*/
		
		
		if(objSharedDataResource.PipelineInformation.get("ISQ") != null){
			Iterator iter = objSharedDataResource.ISQ.iterator();
			while(iter.hasNext()){
				IssueQueue isq = (IssueQueue)iter.next();
				InstructionInformation instrInISQ = isq.getInstruction();
				System.out.println("ISQ - stage:      "+"-------"+instrInISQ.getCurrentInstruction());
			}
		}
		
		if(objSharedDataResource.rob != null)
		{
			ArrayList<InstructionInformation> objArrayInstruction = objSharedDataResource.rob.getAllInstruction();
			for(InstructionInformation objInstructionInformation : objArrayInstruction)
			{
				System.out.println("ROB - stage:      "+"-------"+objInstructionInformation.getCurrentInstruction());
			}
			
		}
		
		if(objSharedDataResource.PipelineInformation.get("LSQ") != null){
			Iterator iter = objSharedDataResource.LSQ.iterator();
			while(iter.hasNext()){
				IssueQueue lsq = (IssueQueue)iter.next();
				InstructionInformation instrInLSQ = lsq.getInstruction();
				System.out.println("LSQ - stage:      "+"-------"+instrInLSQ.getCurrentInstruction());
			}
		}

		InstructionInformation instrInExecuteALU = objSharedDataResource.PipelineInformation.get("Execute : ALU");
		System.out.println("Execution-Stage: ALU      "
				+ (instrInExecuteALU == null ? "No Operation" : instrInExecuteALU.getInstructionStage() + "-------")
				+ (instrInExecuteALU == null ? "Empty" : instrInExecuteALU));

		InstructionInformation instrInExecuteMUL1 = objSharedDataResource.PipelineInformation.get("Execute : MUL1");
		System.out.println("Execution-Stage: MUL1      "
				+ (instrInExecuteMUL1 == null ? "No Operation" : instrInExecuteMUL1.getInstructionStage() + "-------")
				+ (instrInExecuteMUL1 == null ? "Empty" : instrInExecuteMUL1));

		InstructionInformation instrInExecuteMUL2 = objSharedDataResource.PipelineInformation.get("Execute : MUL2");
		System.out.println("Execution-Stage: MUL2      "
				+ (instrInExecuteMUL2 == null ? "No Operation" : instrInExecuteMUL2.getInstructionStage() + "-------")
				+ (instrInExecuteMUL2 == null ? "Empty" : instrInExecuteMUL2));
		
		InstructionInformation instrInExecuteDIV1 = objSharedDataResource.PipelineInformation.get("Execute : DIV1");
		System.out.println("Execution-Stage: DIV1      "
				+ (instrInExecuteDIV1 == null ? "No Operation" : instrInExecuteDIV1.getInstructionStage() + "-------")
				+ (instrInExecuteDIV1 == null ? "Empty" : instrInExecuteDIV1));
		
		InstructionInformation instrInExecuteDIV2 = objSharedDataResource.PipelineInformation.get("Execute : DIV2");
		System.out.println("Execution-Stage: DIV2      "
				+ (instrInExecuteDIV2 == null ? "No Operation" : instrInExecuteDIV2.getInstructionStage() + "-------")
				+ (instrInExecuteDIV2 == null ? "Empty" : instrInExecuteDIV2));

		InstructionInformation instrInExecuteDIV3 = objSharedDataResource.PipelineInformation.get("Execute : DIV3");
		System.out.println("Execution-Stage: DIV3      "
				+ (instrInExecuteDIV3 == null ? "No Operation" : instrInExecuteDIV3.getInstructionStage() + "-------")
				+ (instrInExecuteDIV3== null ? "Empty" : instrInExecuteDIV3));

		InstructionInformation instrInExecuteDIV4 = objSharedDataResource.PipelineInformation.get("Execute : DIV4");
		System.out.println("Execution-Stage: DIV4      "
				+ (instrInExecuteDIV4 == null ? "No Operation" : instrInExecuteDIV4.getInstructionStage() + "-------")
				+ (instrInExecuteDIV4 == null ? "Empty" : instrInExecuteDIV4));

		InstructionInformation instrInMemory = objSharedDataResource.PipelineInformation.get("Memory");
		System.out.println("Memory-Stage:          "
				+ (instrInMemory == null ? "No Operation" : instrInMemory.getInstructionStage() + "-------")
				+ (instrInMemory == null ? "Empty" : instrInMemory));
		System.out.println();
		// }

	}
}
