package Apex;

import java.util.ArrayList;
import java.util.List;

public class PRF{
	private String []registers;
	private String []value;
	private boolean []valid;

	PRF(){
		registers = new String[32];
		valid = new boolean[32];
		value = new String[32];
	}

	void init(){	
		for(int i = 0; i < 32;i++){
			registers[i] = "P"+i;
			valid[i] = true;
			value[i] = "0";
		}
	}

	boolean canAllocate(){
		for(int i = 0; i < 32 ; i++){
			if(valid[i])
				return true;
		}
		System.out.println("NO PRF");
		return false;
	}

	String allocate(){
		String register = null;
		for(int i = 0; i < 32 ; i++){
			if(valid[i]) {
				valid[i] = false;
				register = registers[i];
				break;
			}
		} 
		return register;
	}

	void deAllocate(String reg){
		for(int i = 0; i < 32 ; i++){
			if(registers[i].equals(reg)) {
				valid[i] = true;
				value[i] = "0";
				break;
			}
		} 
	}
	
	boolean getValid(String reg){
		boolean validity = false;
		for(int i = 0; i < 32 ; i++){
			if(registers[i].equals(reg)) {
				validity = valid[i];
				break;
			}
		} 
		return validity;
	}
	
	void setValid(String reg){
		for(int i = 0; i < 32 ; i++){
			if(registers[i].equals(reg)) {
				valid[i] = true;
				break;
			}
		} 
	}

	String getValue(String reg){
		String content = null;
		for(int i = 0; i < 16 ; i++){
			if(registers[i].equals(reg)) {
				content = value[i];
				break;
			}
		}
		return content;
	}
	
	void setValue(String reg, String val){
		for(int i = 0; i < 32 ; i++){
			if(registers[i].equals(reg)) {
				value[i] = val;
				break;
			}
		}
	}
	
	List<String> getAllocatedRegisters(){
		List<String> regList = new ArrayList<String>();
		for(int i = 0; i < 32; i++){
			if(!valid[i])
				regList.add(registers[i]);
		}
		return regList;
	}
	
	void display(){
		System.out.println("PRF DISPLAY");
		for(int i = 0; i < 32 ; i++){
			System.out.print(registers[i]);
			System.out.print(" : "+value[i]);
			System.out.println(": Valid bit "+valid[i]);
			}
		}
}
