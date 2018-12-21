package Apex;

import java.util.ArrayList;
import java.util.Iterator;

public class PipelineFunctions {

	public SharedDataResource PushStages(SharedDataResource objSharedDataResource) {

		if (objSharedDataResource.PipelineInformation.get("Memory") == null) {

			Iterator iterator = objSharedDataResource.LSQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				if (isq.getStatus()) {
					InstructionInformation objInstructionInformation = isq.getInstruction();
					String Source_One = objInstructionInformation.getSource1Arch();
					if (objInstructionInformation.getOperation().equals("STORE")) {
						Source_One = objInstructionInformation.getdstArch();
					}
					if (Source_One != null) {
						if (!objSharedDataResource.getArchReg(Source_One, objSharedDataResource).isBusy()) {

							String Source_Two = objInstructionInformation.getSource2Arch();
							if (Source_Two != null) {
								if (!objSharedDataResource.getArchReg(Source_Two, objSharedDataResource).isBusy()) {

									objInstructionInformation.setInstructionStage("ready");
								} else {

									objInstructionInformation.setInstructionStage("stalled");
								}

							} else {
								objInstructionInformation.setInstructionStage("ready");
							}
						} else {
							objInstructionInformation.setInstructionStage("stalled");
						}

					} else {
						objInstructionInformation.setInstructionStage("ready");
					}
					if (objInstructionInformation.getOperation().equals("STORE")
							|| objInstructionInformation.getOperation().equals("LOAD")) {

						if (objInstructionInformation != null) {

							if ("ready" == objInstructionInformation.getInstructionStage()) {
								// objSharedDataResource.PipelineInformation.put("Decode", null);
								objInstructionInformation.setInstructionStage("LSQ");
								objSharedDataResource.PipelineInformation.put("Memory", objInstructionInformation);
								objSharedDataResource.LSQ.remove(isq);
							}

						}
					}
				}
			}
		}

		if (objSharedDataResource.PipelineInformation.get("Execute : MUL2") == null) {

			InstructionInformation objInstructionInformationMUL1 = objSharedDataResource.PipelineInformation
					.get("Execute : MUL1");
			if (objInstructionInformationMUL1 != null) {

				objSharedDataResource.PipelineInformation.put("Execute : MUL1", null);
				objSharedDataResource.PipelineInformation.put("Execute : MUL2", objInstructionInformationMUL1);
			}
		}

		if (objSharedDataResource.PipelineInformation.get("Execute : DIV4") == null) {
			InstructionInformation objInstructionInfoDIV3 = objSharedDataResource.PipelineInformation
					.get("Execute : DIV3");
			InstructionInformation objInstructionInfoDIV2 = objSharedDataResource.PipelineInformation
					.get("Execute : DIV2");
			InstructionInformation objInstructionInfoDIV1 = objSharedDataResource.PipelineInformation
					.get("Execute : DIV1");
			if (objInstructionInfoDIV3 != null) {

				objSharedDataResource.PipelineInformation.put("Execute : DIV3", null);
				objSharedDataResource.PipelineInformation.put("Execute : DIV4", objInstructionInfoDIV3);
			}

			if (objInstructionInfoDIV2 != null) {

				objSharedDataResource.PipelineInformation.put("Execute : DIV2", null);
				objSharedDataResource.PipelineInformation.put("Execute : DIV3", objInstructionInfoDIV2);
			}

			if (objInstructionInfoDIV1 != null) {

				objSharedDataResource.PipelineInformation.put("Execute : DIV1", null);
				objSharedDataResource.PipelineInformation.put("Execute : DIV2", objInstructionInfoDIV1);
			}

		}

		if (objSharedDataResource.PipelineInformation.get("ISQ") != null) {

			Iterator<IssueQueue> iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				if (isq.getStatus()) {
					InstructionInformation objInstructionInformation = isq.getInstruction();
					String Source_One = objInstructionInformation.getSource1Arch();
					if (objInstructionInformation.getOperation().equals("STORE")) {
						Source_One = objInstructionInformation.getdstArch();
					}
					if (Source_One != null) {
						if (!objSharedDataResource.getArchReg(Source_One, objSharedDataResource).isBusy()) {

							String Source_Two = objInstructionInformation.getSource2Arch();
							if (Source_Two != null) {
								if (!objSharedDataResource.getArchReg(Source_Two, objSharedDataResource).isBusy()) {

									objInstructionInformation.setInstructionStage("ready");
								} else {

									objInstructionInformation.setInstructionStage("stalled");
								}

							} else {
								objInstructionInformation.setInstructionStage("ready");
							}
						} else {
							objInstructionInformation.setInstructionStage("stalled");
						}

					} else {
						objInstructionInformation.setInstructionStage("ready");
					}
					if (!objInstructionInformation.getOperation().equals("MUL")
							&& !objInstructionInformation.getOperation().equals("DIV")) {
						if (objSharedDataResource.PipelineInformation.get("Execute : ALU") == null) {
							if (objInstructionInformation != null) {
								if ("ready" == objInstructionInformation.getInstructionStage()) {
									// objSharedDataResource.PipelineInformation.put("Decode", null);
									objInstructionInformation.setInstructionStage("Execute : ALU");
									objSharedDataResource.PipelineInformation.put("Execute : ALU",
											objInstructionInformation);
									// objSharedDataResource.ISQ.remove(isq);
									iterator.remove();
								}

							}

						}
					}

					if (objInstructionInformation.getOperation().equals("MUL")) {
						if (objSharedDataResource.PipelineInformation.get("Execute : MUL1") == null) {
							if (objInstructionInformation != null) {
								if (objInstructionInformation.getOperation().equals("MUL")) {
									if ("ready" == objInstructionInformation.getInstructionStage()) {
										// objSharedDataResource.PipelineInformation.put("Decode", null);
										objInstructionInformation.setInstructionStage("Execute : MUL1");
										objSharedDataResource.PipelineInformation.put("Execute : MUL1",
												objInstructionInformation);
										// objSharedDataResource.ISQ.remove(isq);
										iterator.remove();
									}

								}
							}
						}

						/*
						 * else if (objSharedDataResource.PipelineInformation.get("Execute : MUL2") ==
						 * null) {
						 * 
						 * InstructionInformation objInstructionInformationMUL1 =
						 * objSharedDataResource.PipelineInformation .get("Execute : MUL1"); if
						 * (objInstructionInformation != null) {
						 * 
						 * objSharedDataResource.PipelineInformation.put("Execute : MUL1", null);
						 * objSharedDataResource.PipelineInformation.put("Execute : MUL2",
						 * objInstructionInformationMUL1); if
						 * (objSharedDataResource.PipelineInformation.get("Execute : MUL1") == null) {
						 * if (objInstructionInformation != null) { if
						 * (objInstructionInformation.getOperation().equals("MUL")) { //
						 * objSharedDataResource.PipelineInformation.put("Decode", null);
						 * objInstructionInformation.setInstructionStage("Execute : MUL1");
						 * objSharedDataResource.PipelineInformation.put("Execute : MUL1",
						 * objInstructionInformation); objSharedDataResource.ISQ.remove(isq);
						 * 
						 * } } } } }
						 */
					}
					if (objInstructionInformation.getOperation().equals("DIV")) {

						if (objSharedDataResource.PipelineInformation.get("Execute : DIV1") == null) {
							if (objInstructionInformation != null) {
								if (objInstructionInformation.getOperation().equals("DIV")) {
									if ("ready" == objInstructionInformation.getInstructionStage()) {
										// objSharedDataResource.PipelineInformation.put("Decode", null);
										objInstructionInformation.setInstructionStage("Execute : DIV1");
										objSharedDataResource.PipelineInformation.put("Execute : DIV1",
												objInstructionInformation);
										// objSharedDataResource.ISQ.remove(isq);
										iterator.remove();
									}

								}
							}
						}

						/*
						 * else if (objSharedDataResource.PipelineInformation.get("Execute : DIV2") ==
						 * null) {
						 * 
						 * InstructionInformation objInstructionInformationDIV1 =
						 * objSharedDataResource.PipelineInformation .get("Execute : DIV1"); if
						 * (objInstructionInformation != null) {
						 * 
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV1", null);
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2",
						 * objInstructionInformationDIV1); if
						 * (objSharedDataResource.PipelineInformation.get("Execute : DIV1") == null) {
						 * if (objInstructionInformation != null) { if
						 * (objInstructionInformation.getOperation().equals("DIV")) { //
						 * objSharedDataResource.PipelineInformation.put("Decode", null);
						 * objInstructionInformation.setInstructionStage("Execute : DIV1");
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV1",
						 * objInstructionInformation); objSharedDataResource.ISQ.remove(isq);
						 * 
						 * } } } } }
						 * 
						 * else if (objSharedDataResource.PipelineInformation.get("Execute : DIV3") ==
						 * null) {
						 * 
						 * InstructionInformation objInstructionInformationDIV2 =
						 * objSharedDataResource.PipelineInformation .get("Execute : DIV2");
						 * InstructionInformation objInstructionInformationDIV1 =
						 * objSharedDataResource.PipelineInformation .get("Execute : DIV1"); if
						 * (objInstructionInformationDIV2 != null) {
						 * 
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2", null);
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2",
						 * objInstructionInformationDIV2); } if (objInstructionInformationDIV1 != null)
						 * { objSharedDataResource.PipelineInformation.put("Execute : DIV1", null);
						 * objInstructionInformationDIV1.setInstructionStage("Execute : DIV2");
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2",
						 * objInstructionInformationDIV1);
						 * 
						 * if (objSharedDataResource.PipelineInformation.get("Execute : DIV1") == null)
						 * { if (objInstructionInformation != null) { if
						 * (objInstructionInformation.getOperation().equals("DIV")) {
						 * 
						 * // objSharedDataResource.PipelineInformation.put("Decode", null);
						 * objInstructionInformation.setInstructionStage("Execute : DIV1");
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV1",
						 * objInstructionInformation); objSharedDataResource.ISQ.remove(isq);
						 * 
						 * } } } } }
						 * 
						 * else if (objSharedDataResource.PipelineInformation.get("Execute : DIV4") ==
						 * null) { InstructionInformation objInstructionInfoDIV3 =
						 * objSharedDataResource.PipelineInformation .get("Execute : DIV3");
						 * 
						 * InstructionInformation objInstructionInformationDIV2 =
						 * objSharedDataResource.PipelineInformation .get("Execute : DIV2");
						 * InstructionInformation objInstructionInformationDIV1 =
						 * objSharedDataResource.PipelineInformation .get("Execute : DIV1");
						 * 
						 * if (objInstructionInfoDIV3 != null) {
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV3", null);
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV4",
						 * objInstructionInfoDIV3);
						 * 
						 * } if (objInstructionInformationDIV2 != null) {
						 * 
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2", null);
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2",
						 * objInstructionInformationDIV2); } if (objInstructionInformationDIV1 != null)
						 * { objSharedDataResource.PipelineInformation.put("Execute : DIV1", null);
						 * objInstructionInformationDIV1.setInstructionStage("Execute : DIV2");
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV2",
						 * objInstructionInformationDIV1);
						 * 
						 * if (objSharedDataResource.PipelineInformation.get("Execute : DIV1") == null)
						 * { if (objInstructionInformation != null) { if
						 * (objInstructionInformation.getOperation().equals("DIV")) {
						 * 
						 * // objSharedDataResource.PipelineInformation.put("Decode", null);
						 * objInstructionInformation.setInstructionStage("Execute : DIV1");
						 * objSharedDataResource.PipelineInformation.put("Execute : DIV1",
						 * objInstructionInformation); objSharedDataResource.ISQ.remove(isq);
						 * 
						 * } } } } }
						 */

					}

				}

			}
		}

		if (objSharedDataResource.PipelineInformation.get("Decode") != null) {
			if (objSharedDataResource.rob.isAvailable()) {
				InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation
						.get("Decode");
				objSharedDataResource.rob.insert(objInstructionInformation.getdst(), objInstructionInformation);
				objSharedDataResource.PipelineInformation.put("rob", objInstructionInformation);
				objSharedDataResource.robcounter++;
			}
		}
		if (objSharedDataResource.PipelineInformation.get("Decode") != null) {
			InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation.get("Decode");
			if (objInstructionInformation.getOperation().equals("STORE")
					|| objInstructionInformation.getOperation().equals("LOAD")) {
				objSharedDataResource.PipelineInformation.put("LSQ", objInstructionInformation);
				objInstructionInformation.setInstructionStage("LSQ");
				IssueQueue lsq = new IssueQueue(objInstructionInformation);
				lsq.setSrc1Available(objSharedDataResource.srcOneFlag);
				lsq.setSrc2Available(objSharedDataResource.srcTwoFlag);
				if (objSharedDataResource.srcOneFlag && objSharedDataResource.srcTwoFlag)
					lsq.setStatus(true);
				else
					lsq.setStatus(false);
				objSharedDataResource.LSQ.add(lsq);
				// objSharedDataResource.ISQ.remove(isq);
				// iterator.remove();

			}
			if (objSharedDataResource.PipelineInformation.get("Decode") != null) {
				if (objInstructionInformation != null) {
					objSharedDataResource.PipelineInformation.put("Decode", null);
					objSharedDataResource.PipelineInformation.put("ISQ", objInstructionInformation);
					objInstructionInformation.setInstructionStage("ISQ");
					IssueQueue isq = new IssueQueue(objInstructionInformation);
					isq.setSrc1Available(objSharedDataResource.srcOneFlag);
					isq.setSrc2Available(objSharedDataResource.srcTwoFlag);
					if (objSharedDataResource.srcOneFlag && objSharedDataResource.srcTwoFlag)
						isq.setStatus(true);
					else
						isq.setStatus(false);
					objSharedDataResource.ISQ.add(isq);
				}
			}
		}

		if (objSharedDataResource.PipelineInformation.get("Decode") == null) {

			InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation.get("Fetch");
			if (objInstructionInformation != null) {
				objSharedDataResource.PipelineInformation.put("Fetch", null);
				objInstructionInformation.setInstructionStage("Decode");
				objSharedDataResource.PipelineInformation.put("Decode", objInstructionInformation);

			}
		}

		return objSharedDataResource;
	}

	public SharedDataResource ReorderBuffer(SharedDataResource objSharedDataResource){
		if(objSharedDataResource.rob.getFirst() != null)
		{
			int count = 0;
			ArrayList<InstructionInformation> objArrayInstruction  = new ArrayList<>();
			objArrayInstruction = objSharedDataResource.rob.getrobInstruction();
			try {
				
				for(int i=0; i<objArrayInstruction.size(); i++){
                InstructionInformation robInst  = objArrayInstruction.get(i);
				if(robInst != null) {
					//String robRegister = objSharedDataResource.rob.getFirst(); // This holds the first inst's physical register not archi reg
					InstructionInformation objInstructionInformation =objSharedDataResource.instructions.get(robInst.getInstructionNumber()-1);
					//if(objInstructionInformation.getOperation().equals("STORE"))
						//objSharedDataResource.rob.remove();
					if((objInstructionInformation.getOperation().equals("BZ") )|| (objInstructionInformation.getOperation().equals("BNZ"))||(objInstructionInformation.getOperation().equals("HALT")))
							{
						//objSharedDataResource.ARCregstate.put(objInstructionInformation.getInstructionResult(), registerState.getRegName()); // For this reason we need the arch reg
						objSharedDataResource.rob.remove();
	objSharedDataResource.robcounter--;
	count++;
							}
					if(robInst.getInstructionNumber() == objInstructionInformation.getInstructionNumber() && objInstructionInformation.isTakeValue()
					&& objInstructionInformation.getdstArch().contains("R")&&objInstructionInformation.getOperation() != null) {
						if(count == 0 && objArrayInstruction.indexOf(robInst)==1)
							break;
						//objInstructionInformation.setInstructionStage("rob");
						String Dest_Reg =robInst.getdstArch();
						ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
						registerState.setValue(robInst.getInstructionResult());
						//objSharedDataResource.ARCregstate.put(objInstructionInformation.getInstructionResult(), registerState.getRegName()); // For this reason we need the arch reg
						objSharedDataResource.rob.remove();
						//objArrayInstruction.remove(robInst);
	objSharedDataResource.renameTable.setLocation(Dest_Reg, "ARF");
	objSharedDataResource.robcounter--;
	objInstructionInformation.setTakeValue(false);
	count++;
	if(objInstructionInformation.getdstArch()!=null)
	{
	objInstructionInformation.setDestination(objInstructionInformation.getdstArch());
	
	}
	if(objInstructionInformation.getSource1Arch() != null)
	{
	objInstructionInformation.setSource1(objInstructionInformation.getSource1Arch());
	}
	if(objInstructionInformation.getSource2Arch()!=null)
	{
	objInstructionInformation.setSource2(objInstructionInformation.getSource2Arch());
	}
	objInstructionInformation.setrenamedCurrentinstruction();
	//objSharedDataResource.PipelineInformation.put("rob",null );
	
					}
				}
			}
			}
				catch(Exception e)
				{
				System.out.println(e);
				}
					
			
			}
		
				
				
				// objSharedDataResource.rob.setAllInstruction(objArrayInstruction);
			
			
		
		return objSharedDataResource;
	}

	public SharedDataResource memory(SharedDataResource objSharedDataResource) {
		if (objSharedDataResource.PipelineInformation.get("Memory") != null) {
			InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation.get("Memory");
			if (objInstructionInformation.getDestination() != null) {
				// int destination = objInstructionInformation.getDestination();
				// ARCRegister destination =
				// objSharedDataResource.getArchReg(objInstructionInformation.getDestination(),
				// objSharedDataResource);
				// destination.setBusy(true);
				Integer calculation = 0;

				ARCRegister destination = objSharedDataResource.getArchReg(objInstructionInformation.getdstArch(),
						objSharedDataResource);

				if (objInstructionInformation.getOperation().equals("LOAD")) {
					int src1 = destination.AddRegister(objInstructionInformation.getSource1Arch());
					int dest = destination.AddRegister(objInstructionInformation.getdstArch());
					int lit = objInstructionInformation.getLiteral();
					int address = src1 + lit;
					Memory src_add = objSharedDataResource.memory.get(address);
					ARCRegister dest_add = objSharedDataResource.ARCregstate.get(dest);
					objInstructionInformation.setDstArchValue(src_add.getValue());
					dest_add.setValue(src_add.getValue());
				} else if (objInstructionInformation.getOperation().equals("STORE")) {
					int src1 = destination.AddRegister(objInstructionInformation.getdstArch());
					int dest1 = destination.AddRegister(objInstructionInformation.getSource1Arch());
					int lit = objInstructionInformation.getLiteral();
					int address = dest1 + lit;
					ARCRegister src_add = objSharedDataResource.ARCregstate.get(src1);
					Memory dest_add = objSharedDataResource.memory.get(address);
					dest_add.setValue(src_add.getValue());
					System.out.println(dest_add.getValue());
				}

				String Dest_Reg = objInstructionInformation.getdstArch();
				ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
				if (registerState != null) {
					registerState.setBusy(false);
				}

				objInstructionInformation.setTakeValue(true);
				objSharedDataResource.prf.setValid(objInstructionInformation.getDestination());
				objSharedDataResource.prf.setValid(objInstructionInformation.getSource1());
				objSharedDataResource.prf.setValid(objInstructionInformation.getSource2());
				Iterator iterator = objSharedDataResource.ISQ.iterator();
				while (iterator.hasNext()) {
					IssueQueue isq = (IssueQueue) iterator.next();
					InstructionInformation inst = isq.getInstruction();
					if ((inst.getSource1() != null)
							&& (inst.getSource1Arch().equals(objInstructionInformation.getdstArch()))) {
						inst.setSource1ArchValue(calculation);
					}
					if ((inst.getSource2() != null)
							&& (inst.getSource2Arch().equals(objInstructionInformation.getdstArch()))) {
						inst.setSource2ArchValue(calculation);
					}
				}

				objSharedDataResource.prf.deAllocate(objInstructionInformation.getDestination());
				objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource1());
				objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource2());

			}

		}

		return objSharedDataResource;

	}

	public SharedDataResource execute(SharedDataResource objSharedDataResource) {
		if (objSharedDataResource.PipelineInformation.get("Execute : ALU") != null) {
			InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation
					.get("Execute : ALU");
			if (!objInstructionInformation.getOperation().equals("STORE")) {

				/*
				 * if (objInstructionInformation.getDestination() != null) { ARCRegister
				 * destination = objSharedDataResource
				 * .getArchReg(objInstructionInformation.getDestination(),
				 * objSharedDataResource); destination.setBusy(true); }
				 */

				if (!(objInstructionInformation.getOperation() == "MUL")
						&& !(objInstructionInformation.getOperation() == "DIV")) {
					objSharedDataResource = ALU(objSharedDataResource, objInstructionInformation);
				}

			}

		}

		InstructionInformation objInstructionInformationMUL1 = objSharedDataResource.PipelineInformation
				.get("Execute : MUL1");
		InstructionInformation objInstructionInformationMUL2 = objSharedDataResource.PipelineInformation
				.get("Execute : MUL2");

		if (objSharedDataResource.PipelineInformation.get("Execute : MUL1") != null) {
			objSharedDataResource = MUL1(objSharedDataResource, objInstructionInformationMUL1);

		}

		if (objSharedDataResource.PipelineInformation.get("Execute : MUL2") != null) {
			objSharedDataResource = MUL2(objSharedDataResource, objInstructionInformationMUL2);

		}
		InstructionInformation objInstructionInfoDIV4 = objSharedDataResource.PipelineInformation.get("Execute : DIV4");

		InstructionInformation objInstructionInfoDIV3 = objSharedDataResource.PipelineInformation.get("Execute : DIV3");
		InstructionInformation objInstructionInfoDIV2 = objSharedDataResource.PipelineInformation.get("Execute : DIV2");
		InstructionInformation objInstructionInfoDIV1 = objSharedDataResource.PipelineInformation.get("Execute : DIV1");
		if (objSharedDataResource.PipelineInformation.get("Execute : DIV1") != null) {
			objSharedDataResource = DIV1(objSharedDataResource, objInstructionInfoDIV1);

		}
		if (objSharedDataResource.PipelineInformation.get("Execute : DIV2") != null) {
			objSharedDataResource = DIV2(objSharedDataResource, objInstructionInfoDIV2);

		}
		if (objSharedDataResource.PipelineInformation.get("Execute : DIV3") != null) {
			objSharedDataResource = DIV3(objSharedDataResource, objInstructionInfoDIV3);

		}
		if (objSharedDataResource.PipelineInformation.get("Execute : DIV4") != null) {
			objSharedDataResource = DIV4(objSharedDataResource, objInstructionInfoDIV4);

		}
		return objSharedDataResource;
	}

	public SharedDataResource RAT(SharedDataResource objSharedDataResource) {

		// for register renaming

		if (objSharedDataResource.PipelineInformation.get("Decode") != null) {
			InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation.get("Decode");

			if (objSharedDataResource.prf.canAllocate()) {
				String physicalRegister = "";
				if (!objInstructionInformation.getOperation().equals("BNZ")
						&& !objInstructionInformation.getOperation().equals("BZ")) {
					physicalRegister = objSharedDataResource.prf.allocate();
				}
				String dstReg = objInstructionInformation.getDestination();
				if (dstReg != null)
					if (dstReg.contains("R")) {
						objInstructionInformation.setdstArch(dstReg);
					}
				if ((objInstructionInformation.getSource1() != null)
						&& (objInstructionInformation.getSource1().contains("R"))) {
					objInstructionInformation.setSource1Arch(objInstructionInformation.getSource1());
					objInstructionInformation.setSource1(objSharedDataResource.prf.allocate());
				}

				if ((objInstructionInformation.getSource2() != null)
						&& (objInstructionInformation.getSource2().contains("R"))) {
					objInstructionInformation.setSource2Arch(objInstructionInformation.getSource2());
					objInstructionInformation.setSource2(objSharedDataResource.prf.allocate());
				}

				objInstructionInformation.setdst(physicalRegister);
				objInstructionInformation.setdstPhy(physicalRegister);

				objSharedDataResource.renameTable.setLocation(dstReg, "P"); // Look at the PRF
				objSharedDataResource.renameTable.setPhyReg(dstReg, physicalRegister);
				objInstructionInformation.setrenamedCurrentinstruction();

				// dependencyFlag = false;
			}

		}
		objSharedDataResource.PipelineInformation.put("Execute : ALU", null);
		objSharedDataResource.PipelineInformation.put("Execute : MUL2", null);
		objSharedDataResource.PipelineInformation.put("Execute : DIV4", null);
		objSharedDataResource.PipelineInformation.put("Memory", null);

		return objSharedDataResource;
	}

	public SharedDataResource decode(SharedDataResource objSharedDataResource) {
		if (objSharedDataResource.PipelineInformation.get("Decode") != null) {

			InstructionInformation objInstructionInformation = objSharedDataResource.PipelineInformation.get("Decode");

			String src = null;

			if ((objInstructionInformation.getSource1() != null)
					&& (objInstructionInformation.getSource1().contains("P"))) {
				src = objSharedDataResource.renameTable.getLocation(objInstructionInformation.getSource1());
				if (src.equals("P") && (objSharedDataResource.prf.getValid(objInstructionInformation.getSource1()))) {

					objInstructionInformation
							.setSource1(objSharedDataResource.prf.getValue(objInstructionInformation.getSource1()));
					objSharedDataResource.srcOneFlag = true;
				} else
					objSharedDataResource.srcOneFlag = false;
			} else {
				objSharedDataResource.srcOneFlag = true;
			}
			if ((objInstructionInformation.getSource2() != null)
					&& (objInstructionInformation.getSource2().contains("P"))) {
				src = objSharedDataResource.renameTable.getLocation(objInstructionInformation.getSource2());
				if (src.equals("P") && (objSharedDataResource.prf.getValid(objInstructionInformation.getSource2()))) {

					objInstructionInformation
							.setSource2(objSharedDataResource.prf.getValue(objInstructionInformation.getSource2()));
					objSharedDataResource.srcTwoFlag = true;
				} else
					objSharedDataResource.srcTwoFlag = false;
			} else {
				objSharedDataResource.srcTwoFlag = true;
			}

		}
		return objSharedDataResource;

	}

	public SharedDataResource fetch(SharedDataResource objSharedDataResource) {
		objSharedDataResource.CycleCount++;
		if (objSharedDataResource.PipelineInformation.get("Fetch") == null && objSharedDataResource.HaltFlag == 0) {

			if (objSharedDataResource.instructionCounter < objSharedDataResource.instructions.size()) {

				InstructionInformation objinst1 = objSharedDataResource.instructions
						.get(objSharedDataResource.instructionCounter);
				objSharedDataResource.PipelineInformation.put("Fetch", objinst1);

				objinst1.setInstructionStage("Fetch");

				if (objinst1.getOperation().equals("BZ")) {
					
					while(true)
					{

					InstructionInformation objinst2 = objSharedDataResource.instructions
							.get(objSharedDataResource.instructionCounter - 1);
					objinst1.setSource1Arch(objinst2.getDestination());
					if(objinst1.getSource1Arch() != null)break;
					}

				}
				if (objinst1.getOperation().equals("JUMP")) {
					while(true)
					{
					objinst1.setSource1Arch(objinst1.getDestination());
					if(objinst1.getSource1Arch() != null)break;
					}
				}
				if (objinst1.getOperation().equals("BNZ")) {
					while(true)
					{
					InstructionInformation objinst2 = objSharedDataResource.instructions
							.get(objSharedDataResource.instructionCounter - 1);
					objinst1.setSource1Arch(objinst2.getDestination());
					if(objinst1.getSource1Arch() != null)break;
					}

				}
				objSharedDataResource.instructionCounter++;
				if (!(objSharedDataResource.instructionCounter == 1))
					objSharedDataResource.PC = objSharedDataResource.PC + 4;
			}

		}
		return objSharedDataResource;

	}

	public SharedDataResource ALU(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {
		Integer calculation = 0;
		if (objInstructionInformation.getOperation().equals("ADD")) {
			// int source1 = objInstructionInformation.getSource1ArchValue();
			// int source2 = objInstructionInformation.getSource2ArchValue();

			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
					objSharedDataResource);
			calculation = source1.getValue() + source2.getValue();
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);
			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("SUB")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
					objSharedDataResource);
			calculation = source1.getValue() - source2.getValue();
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);

			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("MOVC")) {
			calculation = objInstructionInformation.getLiteral();
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);
			// objSharedDataResource.prf.setValue(objInstructionInformation.getDestination(),
			// Integer.toString(calculation));
			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("MOV")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			calculation = source1.getValue();
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);
			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("AND")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
					objSharedDataResource);
			calculation = (source1.getValue()) & (source2.getValue());
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);
			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("OR")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
					objSharedDataResource);
			calculation = (source1.getValue()) | (source2.getValue());
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);
			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("EX-OR")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
					objSharedDataResource);
			calculation = source1.getValue() ^ source2.getValue();
			objInstructionInformation.setDstArchValue(calculation);
			objInstructionInformation.setResult(calculation);
			objInstructionInformation.setTakeValue(true);
		} else if (objInstructionInformation.getOperation().equals("BNZ")) {
			int a = objInstructionInformation.getLiteral();
			ARCRegister dest = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			if (dest.getValue() != 0) {
				objSharedDataResource.PC = objSharedDataResource.PC + a - 8;
				for (int i = 0; i < 4000; i++) {
					if (objSharedDataResource.memory.get(i).getBaseAdd() == objSharedDataResource.PC)
						objSharedDataResource.instructionCounter = i;
				}
				objSharedDataResource.PipelineInformation.put("Decode", objSharedDataResource.getEmpty());
				objSharedDataResource.PipelineInformation.put("Fetch", objSharedDataResource.getEmpty());
				// objSharedDataResource.PipelineInformation.put("Execute : ALU",
				// objSharedDataResource.getEmpty());
				Iterator<IssueQueue> iterator = objSharedDataResource.ISQ.iterator();
				while (iterator.hasNext()) {
					IssueQueue isq = (IssueQueue) iterator.next();
					iterator.remove();
				}

			}
		} else if (objInstructionInformation.getOperation().equals("BZ")) {
			int a = objInstructionInformation.getLiteral();
			ARCRegister dest = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			if (dest.getValue() == 0) {
				objSharedDataResource.PC = objSharedDataResource.PC + a - 8;
				for (int i = 0; i < 4000; i++) {
					if (objSharedDataResource.memory.get(i).getBaseAdd() == objSharedDataResource.PC) {
						objSharedDataResource.instructionCounter = i;
						break;
					}
				}
				
				}
				objSharedDataResource.PipelineInformation.put("Decode", objSharedDataResource.getEmpty());
				objSharedDataResource.PipelineInformation.put("Fetch", objSharedDataResource.getEmpty());
				// objSharedDataResource.PipelineInformation.put("Execute : ALU",
				// objSharedDataResource.getEmpty());
				Iterator<IssueQueue> iterator = objSharedDataResource.ISQ.iterator();
				while (iterator.hasNext()) {
					IssueQueue isq = (IssueQueue) iterator.next();
					iterator.remove();
				
			}
		} else if (objInstructionInformation.getOperation().equals("JAL")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			int lit = objInstructionInformation.getLiteral();
			objInstructionInformation.setResult(
					objSharedDataResource.memory.get(objInstructionInformation.getInstructionNumber()).getBaseAdd());
			objSharedDataResource.PC = (source1.getValue() + lit);

			for (int i = 0; i < 4000; i++) {
				if (objSharedDataResource.memory.get(i).getBaseAdd() == objSharedDataResource.PC)
					objSharedDataResource.instructionCounter = i;
			}

			objInstructionInformation.setTakeValue(true);
			objSharedDataResource.PipelineInformation.put("Decode", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Fetch", objSharedDataResource.getEmpty());
			Iterator<IssueQueue> iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				iterator.remove();
			}

		} else if (objInstructionInformation.getOperation().equals("JUMP")) {
			ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
					objSharedDataResource);
			int lit = objInstructionInformation.getLiteral();
			objSharedDataResource.PC = (source1.getValue() + lit);
			for (int i = 0; i < 4000; i++) {
				if (objSharedDataResource.memory.get(i).getBaseAdd() == objSharedDataResource.PC)
					objSharedDataResource.instructionCounter = i;
			}
			objSharedDataResource.PipelineInformation.put("Decode", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Fetch", objSharedDataResource.getEmpty());
			Iterator<IssueQueue> iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				iterator.remove();
			}

		} else if (objInstructionInformation.getOperation().equals("HALT")) {
			objSharedDataResource.HaltFlag = 1;
			objSharedDataResource.PipelineInformation.put("Decode", objSharedDataResource.getEmpty());
			objSharedDataResource.PipelineInformation.put("Fetch", objSharedDataResource.getEmpty());
			Iterator<IssueQueue> iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				iterator.remove();
			}

		}
		if (calculation != null) {
			String register = objInstructionInformation.getdst();
			objInstructionInformation.setdst(Integer.toString(calculation));
			objSharedDataResource.prf.setValid(register);
			objSharedDataResource.prf.setValid(objInstructionInformation.getSource1());
			objSharedDataResource.prf.setValid(objInstructionInformation.getSource2());
			Iterator iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				InstructionInformation inst = isq.getInstruction();
				if ((inst.getSource1() != null)
						&& (inst.getSource1Arch().equals(objInstructionInformation.getdstArch()))) {
					inst.setSource1ArchValue(calculation);
				}
				if ((inst.getSource2() != null)
						&& (inst.getSource2Arch().equals(objInstructionInformation.getdstArch()))) {
					inst.setSource2ArchValue(calculation);
				}
			}

			objSharedDataResource.prf.deAllocate(register);
			objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource1());
			objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource2());
		}
		String Dest_Reg = objInstructionInformation.getdstArch();
		ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
		if (registerState != null) {
			registerState.setBusy(false);
		}
		// forwarding

		return objSharedDataResource;
	}

	public SharedDataResource MUL1(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {
		Integer calculation = 0;
		ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
				objSharedDataResource);
		ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
				objSharedDataResource);
		calculation = source1.getValue() * source2.getValue();
		objInstructionInformation.setDstArchValue(calculation);
		objInstructionInformation.setResult(calculation);
		objInstructionInformation.setTakeValue(true);
		String Dest_Reg = objInstructionInformation.getdstArch();
		ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
		if (registerState != null) {
			registerState.setBusy(true);
		}

		return objSharedDataResource;
	}

	public SharedDataResource MUL2(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {
		Integer calculation = 0;
		ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
				objSharedDataResource);
		ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
				objSharedDataResource);
		calculation = source1.getValue() * source2.getValue();
		objInstructionInformation.setDstArchValue(calculation);
		objInstructionInformation.setResult(calculation);

		// objInstructionInformation.setTakeValue(true);
		// String Dest_Reg = objInstructionInformation.getDestination();
		// ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg,
		// objSharedDataResource);
		// if (objInstructionInformation.isTakeValue()) {
		// registerState.setValue(objInstructionInformation.getInstructionResult());
		// }

		if (calculation != null) {
			String register = objInstructionInformation.getdst();
			objInstructionInformation.setdst(Integer.toString(calculation));
			objSharedDataResource.prf.setValid(register);
			objSharedDataResource.prf.setValid(objInstructionInformation.getSource1());
			objSharedDataResource.prf.setValid(objInstructionInformation.getSource2());
			Iterator iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				InstructionInformation inst = isq.getInstruction();
				if ((inst.getSource1() != null)
						&& (inst.getSource1Arch().equals(objInstructionInformation.getdstArch()))) {
					inst.setSource1ArchValue(calculation);
				}
				if ((inst.getSource2() != null)
						&& (inst.getSource2Arch().equals(objInstructionInformation.getdstArch()))) {
					inst.setSource2ArchValue(calculation);
				}
			}

			objSharedDataResource.prf.deAllocate(register);
			objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource1());
			objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource2());
			String Dest_Reg = objInstructionInformation.getdstArch();
			ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
			if (registerState != null) {
				registerState.setBusy(false);
			}
		}

		return objSharedDataResource;
	}

	public SharedDataResource DIV1(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {
		ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
				objSharedDataResource);
		ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
				objSharedDataResource);
		objInstructionInformation.setResult(source1.getValue() / source2.getValue());
		// objInstructionInformation.setTakeValue(true);
		String Dest_Reg = objInstructionInformation.getdstArch();
		ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
		if (registerState != null) {
			registerState.setBusy(true);
		}
		return objSharedDataResource;
	}

	public SharedDataResource DIV2(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {
		ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
				objSharedDataResource);
		ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
				objSharedDataResource);
		objInstructionInformation.setResult(source1.getValue() / source2.getValue());
		// objInstructionInformation.setTakeValue(true);
		return objSharedDataResource;
	}

	public SharedDataResource DIV3(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {

		ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
				objSharedDataResource);
		ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
				objSharedDataResource);
		objInstructionInformation.setResult(source1.getValue() / source2.getValue());
		// objInstructionInformation.setTakeValue(true);
		return objSharedDataResource;
	}

	public SharedDataResource DIV4(SharedDataResource objSharedDataResource,
			InstructionInformation objInstructionInformation) {
		Integer calculation = 0;
		ARCRegister source1 = objSharedDataResource.getArchReg(objInstructionInformation.getSource1Arch(),
				objSharedDataResource);
		ARCRegister source2 = objSharedDataResource.getArchReg(objInstructionInformation.getSource2Arch(),
				objSharedDataResource);
		objInstructionInformation.setResult(source1.getValue() / source2.getValue());
		objInstructionInformation.setTakeValue(true);
		objInstructionInformation.setDstArchValue(source1.getValue() / source2.getValue());
		// objInstructionInformation.setTakeValue(true);
		// String Dest_Reg = objInstructionInformation.getDestination();
		// ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg,
		// objSharedDataResource);
		// if (objInstructionInformation.isTakeValue()) {
		// registerState.setValue(objInstructionInformation.getInstructionResult());
		// }
		// forwarding
		if (calculation != null) {
			String register = objInstructionInformation.getdst();
			objInstructionInformation.setdst(Integer.toString(calculation));
			objSharedDataResource.prf.setValid(register);
			objSharedDataResource.prf.setValid(objInstructionInformation.getSource1());
			objSharedDataResource.prf.setValid(objInstructionInformation.getSource2());
			Iterator iterator = objSharedDataResource.ISQ.iterator();
			while (iterator.hasNext()) {
				IssueQueue isq = (IssueQueue) iterator.next();
				InstructionInformation inst = isq.getInstruction();
				if ((inst.getSource1() != null)
						&& (inst.getSource1Arch().equals(objInstructionInformation.getdstArch()))) {
					inst.setSource1ArchValue(calculation);
				}
				if ((inst.getSource2() != null)
						&& (inst.getSource2Arch().equals(objInstructionInformation.getdstArch()))) {
					inst.setSource2ArchValue(calculation);
				}
			}

			objSharedDataResource.prf.deAllocate(register);
			objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource1());
			objSharedDataResource.prf.deAllocate(objInstructionInformation.getSource2());
			String Dest_Reg = objInstructionInformation.getdstArch();
			ARCRegister registerState = objSharedDataResource.getArchReg(Dest_Reg, objSharedDataResource);
			if (registerState != null) {
				registerState.setBusy(false);
			}
		}

		return objSharedDataResource;
	}
}
