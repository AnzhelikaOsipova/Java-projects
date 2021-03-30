import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class REVC {
	
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
	
	//function build reverse complement of DNA string
	public static String revCompDNA(String data)
	{
		StringBuilder data2 = new StringBuilder(data);
		for(int i = 0; i < data.length(); i++)
		{
			switch(data.charAt(i))
			{
				case 'A':
					data2.setCharAt(data.length() - 1 - i, 'T');
					break;
				case 'T':
					data2.setCharAt(data.length() - 1 - i, 'A');
					break;
				case 'C':
					data2.setCharAt(data.length() - 1 - i, 'G');
					break;
				case 'G':
					data2.setCharAt(data.length() - 1 - i, 'C');
					break;
			}
		}
		return data2.toString();
	}
	
	//function writes result string in a file
	public static void writeInFile(String fileName, String res)
	{
			Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, res);
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
		
		//build reverse complement of DNA
		String revComp = revCompDNA(data);
		
		//write reverse complement of DNA in a file
		writeInFile("out.txt", revComp);
	}
}
