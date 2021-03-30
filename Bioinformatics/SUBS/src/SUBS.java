import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SUBS {
	
	//function reads 2 DNA strings from a file
	public static String[] readInputFile(String fileName)
	{
		Path fin = Path.of(fileName);
		List<String> data = null;
		try {
			data = Files.readAllLines(fin);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] res = {data.get(0), data.get(1)};
		return res;
	}
	
	//function finds all substrings in a string
	public static List<Integer> findSubstr(String str, String substr)
	{
		List<Integer> countSubstr = new ArrayList<Integer>();
		for(int i = 0; i < str.length() - substr.length(); i++)
		{
			if(str.substring(i).startsWith(substr))
			{
				countSubstr.add(i + 1);
			}
		}	
		return countSubstr;
	}
	
	//function writes all locations of the substring in a file
	public static void writeInFile(String fileName, List<Integer> countSubStr)
	{
		Path fout = Path.of(fileName);		
		try 
		{
			Files.writeString(fout, "");
			for(int i = 0; i < countSubStr.size(); i++)
			{
				Files.writeString(fout, countSubStr.get(i) + " ", StandardOpenOption.APPEND);	
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args)
	{
		//reading of input data
		String[] data = readInputFile("data.txt");
		
		//find all substring locations
		List<Integer> countSubstr = findSubstr(data[0], data[1]);
		
		//write substring locations in a file
		writeInFile("out.txt", countSubstr);
	}
}
