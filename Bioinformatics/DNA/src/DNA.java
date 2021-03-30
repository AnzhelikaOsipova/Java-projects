import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.stream.IntStream;

public class DNA 
{
	//function reads a DNA string from a file
	public static String readInputFile(String fileName)
	{
		Path fin = Path.of(fileName);
		String data = null;
		try 
		{
			data = Files.readString(fin);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return data;
	}
	
	//function counts nucleotides in a DNA string
	public static int[] countNucleotides(String data)
	{
		int[] countNuc = {0, 0, 0, 0};
		HashMap<String, Integer> NuclsMapComp = new HashMap<>();
		NuclsMapComp.put("A", 0);
		NuclsMapComp.put("C", 1);
		NuclsMapComp.put("G", 2);
		NuclsMapComp.put("T", 3);
		IntStream.range(0, data.length()).forEach(i->countNuc[NuclsMapComp.get(String.valueOf(data.charAt(i)))]++);
		return countNuc;
	}
	
	//function writes number of nucleotides in a string into a file
	public static void writeInFile(String fileName, int[] countNuc)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, String.join(" ", Integer.toString(countNuc[0]), Integer.toString(countNuc[1]), 
					Integer.toString(countNuc[2]), Integer.toString(countNuc[3])));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		//reading of input data
		String data = readInputFile("data.txt");
		
		//count nucleotides in a given string
		int[] countNuc = countNucleotides(data);
		
		//write results in a file
		writeInFile("out.txt", countNuc);
	}

}
