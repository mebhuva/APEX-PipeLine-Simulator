package Apex;

import java.util.ArrayList;

public class ReOrder{
	private int head, tail;
	private ArrayList<InstructionInformation>instuctionsROB;
	private String []rob;
	private boolean []status;
	boolean reuse;

	ReOrder(){
		head = tail = 0;
		rob = new String[32];
		instuctionsROB = new ArrayList<>();
		status = new boolean[32];
		reuse = false;
	}

	void initialize(){
		for(int i = 0; i < 32 ;i++){
			rob[i] = "P"+i;
			status[i] = true;
		}
	}

	boolean isAvailable(){
		if((reuse && (tail >= head)) || (!reuse && (tail <= head)))
			return true;
		else
			return false;
	}

	boolean canRemove(){
		if(Math.abs(head - tail) != 0){
			return true;	
		}
		return false;
	}

	void insert(String regNo, InstructionInformation inst){
		if(head == 32 && tail != 0){
			head = 0;
			reuse = true;
		}
		instuctionsROB.add(inst);
		rob[head] = regNo;
		status[head] = false;
		head++;
	}

	void remove(){
		//if(tail == 32){
			//tail = 0;
			//reuse = false;
		//} 
		instuctionsROB.remove(tail);
		//status[tail] = true;
		//tail++;
		
	}

	boolean check(String regNo){
		for(int i = 0; i < 32; i++ ){
			if(rob[i].equals(regNo))
				return true;
		}
		return false;
	}

	String getFirst(){
		String element = null;
		if(canRemove())
			element = rob[tail];
		return element;
	}

	InstructionInformation getInstruction(){
		return instuctionsROB.get(tail);
	}
	
	ArrayList<InstructionInformation> getAllInstruction()
	{
		return instuctionsROB;
		
	}

	
	ArrayList<InstructionInformation> getrobInstruction()
	{
		
		ArrayList<InstructionInformation> robinst = new ArrayList<>();
		int index = instuctionsROB.indexOf(instuctionsROB.get(tail));
		if(instuctionsROB.size()>1)
		{
		robinst.add(instuctionsROB.get(tail));
		robinst.add(instuctionsROB.get(index+1));
		}else 
		{
			robinst.add(instuctionsROB.get(tail));
		}
		return robinst;
		
	}
	
	String getRegister(){
		return rob[tail];
	} 

	void setStatus(String regNo, boolean flag){
		for(int i = 0; i < 32; i++ ){
			if(rob[i].equals(regNo))
				status[i] = flag;
		}
	}

	boolean getStatus(String regNo){
		boolean state = false;
		for(int i = 0; i < 32; i++ ){
			if(rob[i].equals(regNo))
				state = status[i];
		}
		return state;
	}

	void display(){
		System.out.println("ROB DISPLAY");
		for(int i = 0; i < 32 ; i++){
			System.out.print("rob "+rob[i]);
			System.out.println(": status "+status[i]);
		}
	}
}