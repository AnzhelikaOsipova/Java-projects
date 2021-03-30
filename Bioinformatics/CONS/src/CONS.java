import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.*;

public class CONS {
	
	//function reads DNA strings in FASTA format from a file
	public static List<String> readInputFile(String fileName)
	{
		Path fin = Path.of(fileName);
		List<String> data = null;
		try {
			data = Files.readAllLines(fin);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return data;
	}
	
	//function counts the length of given DNA strings
	public static int findLengthOfDNAString(List<String> data)
	{
		int len = 0;
		for(int i = 1; i < data.size(); i++)
		{
			if(data.get(i).charAt(0) != '>')
			{
				len += data.get(i).length();
			}
			else
			{
				break;
			}
		}
		return len;
	}
	
	//function builds a profile matrix for given DNA strings
	public static int[][] buildProfileMatr(List<String> data, int colProf, HashMap<String, Integer> NuclsMapComp)
	{
		int[][] profile = new int[4][];
		for(int i = 0; i < 4; i++)
		{
			profile[i] = new int[colProf];
		}
		
		int currSymb = 0;
		for(String str: data)
		{
			if(str.charAt(0) == '>')
			{
				currSymb = 0;
			}
			else
			{
				final int currLen = currSymb;
				IntStream.range(0, str.length()).forEach(i->profile[NuclsMapComp.get(String.valueOf(str.charAt(i)))][i + currLen]++);
				currSymb += str.length();
			}
		}
		return profile;
	}
	
	//function finds a consensus string from the given profile matrix
	public static String findConsensusString(int[][] profile, int colProf, HashMap<Integer, String> NuclsMap)
	{
		StringBuilder consStr = new StringBuilder();

		for(int i = 0; i < colProf; i++)
		{
			int max = profile[0][i], jmax = 0;
			for(int j = 1; j < 4; j++)
			{
				if(profile[j][i] > max)
				{
					max = profile[j][i];
					jmax = j;
				}
			}
			consStr.append(NuclsMap.get(jmax));
		}
		return consStr.toString();
	}
	
	//function writes an answer to a file: the consensus string and the profile matrix
	public static void writeInFile(String fileName, String consStr, int[][] profile, int colProf, HashMap<Integer, String> NuclsMap)
	{
		Path fout = Path.of(fileName);
		try 
		{			
			Files.writeString(fout, "");	
			Files.writeString(fout, consStr, StandardOpenOption.APPEND);
			for(int i = 0; i < 4; i++)
			{
				Files.writeString(fout, "\n" + NuclsMap.get(i) + ":", StandardOpenOption.APPEND);
				for(int j = 0; j < colProf; j++)
				{
					Files.writeString(fout, " " + profile[i][j], StandardOpenOption.APPEND);
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		HashMap<Integer, String> NuclsMap = new HashMap<>();
		NuclsMap.put(0, "A");
		NuclsMap.put(1, "C");
		NuclsMap.put(2, "G");
		NuclsMap.put(3, "T");
		HashMap<String, Integer> NuclsMapComp = new HashMap<>();
		NuclsMapComp.put("A", 0);
		NuclsMapComp.put("C", 1);
		NuclsMapComp.put("G", 2);
		NuclsMapComp.put("T", 3);
		
		//reading of input data
		List<String> data;
		data = readInputFile("data.txt");
		
		//find length of DNA strings
		int len = findLengthOfDNAString(data);
		
		//build a profile matrix
		int[][] profile = buildProfileMatr(data, len, NuclsMapComp);
		
		//find a consensus string
		String consStr = findConsensusString(profile, len, NuclsMap);
		
		//write results into a file
		writeInFile("out.txt", consStr, profile, len, NuclsMap);
		
	}
}
