package Apex;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	public static SharedDataResource objSharedDataResource = new SharedDataResource();
	public static PipelineFunctions objPipelineFunctions = new PipelineFunctions();

	public static void main(String[] args) {

		try {

			objSharedDataResource.instructions = new ArrayList<>();

			objSharedDataResource.ARCregstate = new ArrayList<>();

			objSharedDataResource.PipelineInformation.put("Fetch", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Decode", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("ISQ", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("LSQ", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : ALU", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : MUL1", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : MUL2", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : DIV1", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : DIV2", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : DIV3", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Execute : DIV4", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Memory", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("rob", objSharedDataResource.getEmpty());

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter 1: Initialize");
			System.out.println("Enter 2: Simulate");
			System.out.println("Enter 3: Display");
			while (true) {

				String command = scanner.next();
				switch (command) {
				case "1":
					initializer();
					System.out.println("Simulator Initialized");

					break;
				case "2":
					simulate();
					System.out.println("Simulation Completed.");
					break;
				case "3":
					printArchState();
					break;
				}
				if (command.equals("Close")) {
					break;
				}
			}
			scanner.close();

		} catch (Exception e) {
			System.err.println("Error in Pipeline: " + e.getMessage());
		}

	}

	private static void printArchState() {
		for (int i = 0; i < 16; i++) {
			System.out.print("R" + i + "=" + objSharedDataResource.ARCregstate.get(i).getValue() + ",  ");

		}

		for (int i = 0; i < 20; i++) {
			System.out.print("Mem " + objSharedDataResource.memory.get(i).getBaseAdd() + "="
					+ objSharedDataResource.memory.get(i).getValue() + ",  ");

		}

		System.out.print("\n");

	}

	private static void simulate() throws IOException, CloneNotSupportedException {
		BufferedReader br = new BufferedReader((new FileReader("src/testproj.txt")));
		String strLine;

		InstructionParser objInstructionParser = new InstructionParser();
		ApexPrinter objApexPrinter = new ApexPrinter();
		PipelineFunctions objPipelineFunctions = new PipelineFunctions();

		while ((strLine = br.readLine()) != null) {
			objInstructionParser.parseString(strLine, objSharedDataResource);
		}
		do {
			objPipelineFunctions.RAT(objSharedDataResource);
			objPipelineFunctions.PushStages(objSharedDataResource);
			objPipelineFunctions.ReorderBuffer(objSharedDataResource);
			objPipelineFunctions.fetch(objSharedDataResource);
		//	objPipelineFunctions.writeBack(objSharedDataResource);
			objPipelineFunctions.memory(objSharedDataResource);
			objPipelineFunctions.execute(objSharedDataResource);
			objPipelineFunctions.decode(objSharedDataResource);
			if (objSharedDataResource.StagesFinished()) {
				break;
			}
			objApexPrinter.printstages(objSharedDataResource);
			
			printArchState();
		} while (true);

		br.close();
	}

	public static void initializer() {
		objSharedDataResource.PC = 4000;
		int i = 0;
		for (i = 0; i < 16; i++) {
			objSharedDataResource.ARCregstate.add(new ARCRegister("R" + i, 0, false, i));
		}
		int c = 0;
		objSharedDataResource.memory.add(new Memory(4000, 0));

		for (int j = 4; c < 4000; i++) {
			if (j % 4 == 0) {
				objSharedDataResource.memory.add(new Memory(4000 + j, 0));
				c++;
				j=j+4;
			}
		}
		
		objSharedDataResource.renameTable.initialize();
		objSharedDataResource.prf.init();
		objSharedDataResource.rob.initialize();
	}
	
	void forwarding(String register, String value){
		

		/*Iterator lsqiterator = objSharedDataResource.LSQ.iterator();
		while(iterator.hasNext()){
			IssueQueue isq =  (IssueQueue) iterator.next();
			Instruction inst = isq.getInstruction();
			if((inst.getsrc1() != null) && inst.getsrc1().equals(register)){
				inst.setSrc1(value);
			}
			if((inst.getsrc2() != null) && (inst.getsrc2().equals(register))){
				inst.setSrc2(value);
			}
		}*/
	}
}
