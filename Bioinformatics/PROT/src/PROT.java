import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PROT {
	
	//function reads a RNA string from a file
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
	
	//function builds dictionary for translating
	public static Map<String,String> buildDict(String dictFileName)
	{
		Path dicttxt = Path.of("dict.txt");
		String[] data = null;
		try 
		{
			data = Files.readString(dicttxt).split("\n");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Map<String,String> dict = new HashMap<String,String>();
		for(int i = 0; i < data.length; i++)
		{
			String[] data2 = data[i].split(" ");
			dict.put(data2[0], data2[1].substring(0,1)); //\n?
		}
		return dict;
	}
	
	//function encodes RNA string
	public static String encodeRNA(String data, Map<String,String> dict)
	{
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < data.length() / 3 - 1; i++)
		{
			res.append(dict.get(data.substring(i * 3, i * 3 + 3)));
		}
		return res.toString();
	}
	
	//function writes encoded RNA in a file
	public static void writeInFile(String fileName, String encRNA)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, encRNA);
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
		
		//build dictionary
		Map<String,String> dict = buildDict("dict.txt");
		
		//encode RNA
		String encRNA = encodeRNA(data, dict);
		
		//write encoded RNA in a file
		writeInFile("out.txt", encRNA);
	}
}
