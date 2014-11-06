import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String in, out, pattern;
		if(args.length < 3){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Insert the path of input file: ");
			in = scanner.next();
			System.out.println("Insert the path of output file: ");
			out = scanner.next();
			System.out.println("Insert the pattern: ");
			pattern = scanner.next();
			scanner.close();
		}else{
			in = args[1];
			out = args[2];
			pattern = args[3];
		}
		Scanner scanner = new Scanner(new File(in));
		PrintStream printStream = new PrintStream(new File(out));
		String line;
		while(scanner.hasNext()){
			line = scanner.nextLine();
			if(line.contains(pattern))
				printStream.println(line);
		}
		scanner.close();
		printStream.close();
		System.out.println("Done!");
	}

}
