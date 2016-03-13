/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg141_mp1;

import java.io.*;
import java.util.*;

class MP1{
        public static void main(String[] args) throws FileNotFoundException, IOException {
            FileInputStream read_file = new FileInputStream("C:\\mp1.in");
            BufferedReader br = new BufferedReader(new InputStreamReader(read_file));
            
            String line;
			
            //get the first line of input containing the initial state
            String line1 = br.readLine();
            String[] str = line1.split("\\s+");
            ArrayList <Integer> currentState = new ArrayList();
      
            //store the values of the first state in an arraylist
            for (int i = 0; i < str.length; i++ ) {
                currentState.add(Integer.parseInt(str[i]));
            }
       		
            //store the next lines to an arrayList
            ArrayList <String> tmpArray = new ArrayList<>();
            while((line = br.readLine()) != null) {
                tmpArray.add(line);
            }
            
            //make clean instructions
            for (int i = 0, ctr = 1; i <= tmpArray.size()-1; i++, ctr++) {
            	str = tmpArray.get(i).split(" ");
            	makeInstructions(tmpArray.get(i).substring(0,1), tmpArray.get(i).substring(1,tmpArray.get(i).length()));
            }

            //call the main execution function
	    compute(currentState,instructionsArr);
	}
	
    //store instructions to an arrayList
    static ArrayList <Instruction> instructionsArr = new ArrayList<>();
    static void makeInstructions(String comm, String to_do){ 
    	Instruction ins = new Instruction(comm, to_do);
    	instructionsArr.add(ins);
    }
    
    //remove the first space character
    static void split(ArrayList <Instruction> instructionList){ 
    	Instruction ins;
    	String[] s;
    	String newArg;
    	for (int i = 0; i <= instructionList.size() - 1; i++) {
    		ins = instructionList.get(i);
    		s = (ins.getArgs()).split(" ");
    		newArg = (ins.getArgs()).substring(1,(ins.getArgs().length()));
    		ins.setArgs(newArg);
    	}
    }
    
    //compute and write output
    static void compute(ArrayList <Integer> currentState, ArrayList <Instruction> instructions) throws IOException{
     	FileWriter fw = new FileWriter("MP1.out");
        split(instructions);
        
    	for (int i = 0; i < instructions.size() ;) {
    		Instruction ins = instructions.get(i);
    		int index, index2, number;
    		
    		if("S".equals(ins.getOperation())){
    			index = Integer.parseInt(ins.getArgs());
    			currentState.set(index, currentState.get(index) + 1);
    			i++; 
    		}
    		else if("Z".equals(ins.getOperation())){
    			index = Integer.parseInt(ins.getArgs());
    			currentState.set(index, 0);
    			i++;
    		}
    		else if("C".equals(ins.getOperation())){
    			String[] str = (ins.getArgs()).split(" ");
    			index = Integer.parseInt(str[0]);
    			index2 = Integer.parseInt(str[1]);
    			currentState.set(index2, currentState.get(index));
    			i++;
    		}
    		else if("J".equals(ins.getOperation())){    			
    			String[] str = (ins.getArgs()).split(" ");
    			index = Integer.parseInt(str[0]);
    			index2 = Integer.parseInt(str[1]);
    			number = Integer.parseInt(str[2]);
    			if( Objects.equals(currentState.get(index), currentState.get(index2))){
                            i = number - 1;}
    			else
                            i++;
    		}
    		System.out.println(currentState);
                for (Integer current_state : currentState) {
                     fw.write(String.valueOf(current_state) + " ");
                 }
    		fw.write("\n");
    	}
        fw.close();
    }
}

class Instruction{
	private String args;
	private String operation;
	public Instruction(){
		args = null;
		operation = null;
	}
	public Instruction (String operation, String args){
		this.operation = operation;
		this.args = args;
	}
        
        void setArgs(String a){
            this.args = a;
        }
        
        void setOperation(String o){
            this.operation = o;
        }
        
        String getArgs(){
            return args;
        }
        String getOperation(){
            return operation;
        }
    }
