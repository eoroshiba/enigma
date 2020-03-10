import java.util.*;
class Main {
  public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PlugBoard plugboard = new PlugBoard();
		int r1 = 1;
		int r2 = 2;
		int r3 = 3;
		int ref = 1;
		while(true) {
			try {
				//Main menu
				System.out.println("\nWelcome to the Enigma machine\n\nPlease enter the number of the action you want to complete\n1. Set up plugboard\n2. Select Rotors\n3. Select Reflector\n4. Encode/Decode");
				int in = 0;
				in = scan.nextInt();
				//Plugboard setup
				if(in == 1)
					plugboard.setPlugBoard(scan);
				//Rotor setup
				if(in == 2) {
					System.out.println("Which rotor would you like in the farthest right slot? (1-5)");
					r1 = scan.nextInt();
					System.out.println("Which rotor would you like in the middle slot? (1-5)");
					r2 = scan.nextInt();
					System.out.println("Which rotor would you like in the farthest left slot? (1-5)");
					r3 = scan.nextInt();
				}
				//Reflector Selection
				if(in == 3) {
					System.out.println("Please enter the number of which reflector you would like to use:\n1. Reflector A\n2. Reflector B\n3. Reflector C");
					ref = scan.nextInt();}
				//Encoding or decoding
				if(in == 4) {
					System.out.println("\nEnter the String you would like to encode/decode\n(Please make sure every character is a letter and there are no spaces)");
					String input = scan.next().toUpperCase();
					int[] toCode = new int[input.length()];
					for(int i = 0; i < input.length(); i++) {
						toCode[i] = (int)input.charAt(i);
					}
					System.out.println("What is the starting position for the rightmost rotor?");
					int startPos = scan.nextInt();
					Rotor rotor1;
					switch(r1) {
						case 1: {
							rotor1 = new Rotor1(startPos);
							break;
						}
						case 2: {
							rotor1 = new Rotor2(startPos);
							break;
						}
						case 3: {
							rotor1 = new Rotor3(startPos);
							break;
						}
						case 4: {
							rotor1 = new Rotor4(startPos);
							break;
						}
						case 5: {
							rotor1 = new Rotor5(startPos);
							break;
						}
						default: {
							rotor1 = new Rotor1(startPos);
							break;
						}
					}
					System.out.println("What is the starting position for the middle rotor");
					startPos = scan.nextInt();
					Rotor rotor2;
					switch(r2) {
						case 1: {
							rotor2 = new Rotor1(startPos);
							break;
						}
						case 2: {
							rotor2 = new Rotor2(startPos);
							break;
						}
						case 3: {
							rotor2 = new Rotor3(startPos);
							break;
						}
						case 4: {
							rotor2 = new Rotor4(startPos);
							break;
						}
						case 5: {
							rotor2 = new Rotor5(startPos);
							break;
						}
						default: {
							rotor2 = new Rotor2(startPos);
							break;
						}
					}
					System.out.println("What is the starting position for the leftmost rotor?");
					startPos = scan.nextInt();
					Rotor rotor3;
					switch(r3) {
						case 1: {
							rotor3 = new Rotor1(startPos);
							break;
						}
						case 2: {
							rotor3 = new Rotor2(startPos);
							break;
						}
						case 3: {
							rotor3 = new Rotor3(startPos);
							break;
						}
						case 4: {
							rotor3 = new Rotor4(startPos);
							break;
						}
						case 5: {
							rotor3 = new Rotor5(startPos);
							break;
						}
						default: {
							rotor3 = new Rotor3(startPos);
							break;
						}
					}
					Reflector reflector;
					switch(ref) {
						case 1: {
							reflector = new ReflectorA();
							break;
						}
						case 2: {
							reflector = new ReflectorB();
							break;
						}
						case 3: {
							reflector = new ReflectorC();
							break;
						}
						default: {
							reflector = new ReflectorA();
							break;
						}
					}

					int[] doneCode = new int[toCode.length];
					for(int i = 0; i < toCode.length; i++) {
						int temp = toCode[i];
						temp = plugboard.in(temp);
						temp = rotor1.encode(temp);
						temp = rotor2.encode(temp);
						temp = rotor3.encode(temp);
						temp = reflector.encode(temp);
						temp = rotor3.reverse(temp);
						temp = rotor2.reverse(temp);
						temp = rotor1.reverse(temp);
						temp = plugboard.out(temp);
						doneCode[i] = temp;
						rotor1.inc();
						if(rotor1.getPos() == rotor1.getTurnPoint())
							rotor2.inc();
						if(rotor2.getPos() == rotor2.getTurnPoint())
							rotor3.inc();
					}
					String out = "";
					for(int x: doneCode) {
						out+= (char)x;
					}
					System.out.println("\nThe finished code is:\n" + out);
				}
			} catch(Exception e) {System.out.println("Error");break;}
		}
	}
}