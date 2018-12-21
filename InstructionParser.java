package Apex;

import java.util.ArrayList;

public class InstructionParser {

	public SharedDataResource parseString(String strLine,SharedDataResource objSharedDataResource) throws CloneNotSupportedException {
		String[] InstructionString = strLine.split(",");

		InstructionInformation objInstInfo = new InstructionInformation();
		
		objSharedDataResource.instrNum++;
		
		objInstInfo.setInstructionNumber(objSharedDataResource.instrNum);
		objInstInfo.setInstructionAdd(objSharedDataResource.PC);
		objSharedDataResource.instructions.add(objInstInfo);
		
		objInstInfo.setOperation(InstructionString[0]);
		objInstInfo.setCurrentInstruction(InstructionString[0]);
		objInstInfo.setInstructionStage("Read");
		if (InstructionString.length == 2) {
			try {
				Integer.parseInt(InstructionString[1].replace("#", ""));
				objInstInfo.setLiteral(Integer.parseInt(InstructionString[1].replace("#", "")));
			} catch (NumberFormatException e) {
				objInstInfo.setDestination(InstructionString[1].replace("#", ""));
			} catch (NullPointerException e) {
				objInstInfo.setDestination(InstructionString[1].replace("#", ""));
			}

		} else if (InstructionString.length == 3) {
			try {
				Integer.parseInt(InstructionString[1].replace("#", ""));
				objInstInfo.setLiteral(Integer.parseInt(InstructionString[1].replace("#", "")));
			} catch (NumberFormatException e) {
				objInstInfo.setDestination(InstructionString[1].replace("#", ""));
			} catch (NullPointerException e) {
				objInstInfo.setDestination(InstructionString[1].replace("#", ""));
			}
			try {
				Integer.parseInt(InstructionString[2].replace("#", ""));
				objInstInfo.setLiteral(Integer.parseInt(InstructionString[2].replace("#", "")));
			} catch (NumberFormatException e) {
				objInstInfo.setSource1(InstructionString[2].replace("#", ""));
			} catch (NullPointerException e) {
				objInstInfo.setSource1(InstructionString[2].replace("#", ""));
			}
		} else if (InstructionString.length == 4) {
			try {
				Integer.parseInt(InstructionString[1].replace("#", ""));
				objInstInfo.setLiteral(Integer.parseInt(InstructionString[1].replace("#", "")));
			} catch (NumberFormatException e) {
				objInstInfo.setDestination(InstructionString[1].replace("#", ""));
			} catch (NullPointerException e) {
				objInstInfo.setDestination(InstructionString[1].replace("#", ""));
			}
			try {
				Integer.parseInt(InstructionString[2].replace("#", ""));
				objInstInfo.setLiteral(Integer.parseInt(InstructionString[2].replace("#", "")));
			} catch (NumberFormatException e) {
				objInstInfo.setSource1(InstructionString[2].replace("#", ""));
			} catch (NullPointerException e) {
				objInstInfo.setSource1(InstructionString[2].replace("#", ""));
			}
			try {
				Integer.parseInt(InstructionString[3].replace("#", ""));
				objInstInfo.setLiteral(Integer.parseInt(InstructionString[3].replace("#", "")));
			} catch (NumberFormatException e) {
				objInstInfo.setSource2(InstructionString[3].replace("#", ""));
			} catch (NullPointerException e) {
				objInstInfo.setSource2(InstructionString[3].replace("#", ""));
			}
			
		
		}
		//objSharedDataResource.instructionsROB.add(objInstInfo);
		return objSharedDataResource;
	}
	

}
