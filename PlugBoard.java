import java.util.*;
public class PlugBoard {
	int[] plugs;
	public PlugBoard() {
		plugs = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
	}
	public void setPlugBoard(Scanner scan) {
		System.out.println("\nYou are now in the plugboard setup menu.\nFor each letter, type the letter you would like to link it\nwith. If you would not like to link a letter, type \"none\" or \ntype the same letter that is shown.\n\n");
		int i = 0;
		while(i < 26) {
		boolean go = true;
			if(i>0) {
				for(int j = i-1; j >= 0; j--) {
					if(plugs[j] == i) {
						plugs[i] = j;
						go = false;
					}
				}
			}
			if(go) {
				System.out.print((char)(i+65) + " - ");
				String in = scan.next().toUpperCase();
				if(in.equals("NONE"))
					plugs[i] = i;
				else {
					plugs[i] = ((int)in.charAt(0)) - 65;
					if(plugs[i]<i)
						plugs[plugs[i]] = i;
				}
			}
			i++;
		}
	}
	
	public int in(int n) {
		return plugs[n-65]+65;
	}

	public int out(int n) {
		int output = -66;
		for(int i = 0; i < 26; i++) {
			if(plugs[i] == n-65)
				output = i+65;
		}
		return output;
	}
}