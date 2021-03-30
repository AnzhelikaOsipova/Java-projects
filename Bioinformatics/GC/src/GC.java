import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class GC {
	
	//function reads DNA strings in FASTA format from a file
	public static List<String> readInputFile(String fileName)
	{
		Path fin = Path.of(fileName);
		List<String> data = null;
		try 
		{
			data = Files.readAllLines(fin);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		return data;
	}
	
	//function counts maximum GC-content from all given DNA and returns it with DNA`s id
	public static String[] countGCCont(List<String> data)
	{
		String[] GCres = new String[2];
		String idMaxGC = "", idCurrGC = "";
		double maxGC = 0, currGC = 0;
		double cntGC = 0, allLen = 1;
		for(String str: data)
		{
			if(str.charAt(0) == '>')
			{
				currGC = cntGC / allLen * 100;
				if(maxGC < currGC)
				{
					maxGC = currGC;
					idMaxGC = idCurrGC;
				}
				idCurrGC = str.substring(1);
				currGC = 0;
				cntGC = 0;
				allLen = 0;
			}
			else
			{
				cntGC += str.chars().filter(ch -> (ch =='G' || ch == 'C')).count();
				allLen += str.length();
			}
		}
		currGC = cntGC / allLen * 100;
		if(maxGC < currGC)
		{
			maxGC = currGC;
			idMaxGC = idCurrGC;
		}
		GCres[0] = idMaxGC;
		GCres[1] = Double.toString(maxGC);
		return GCres;
	}
	
	//function writes maximum GC-content from given DNA and it`s id
	public static void writeInFile(String fileName, String[] GCres)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, GCres[0] + "\n" + GCres[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args)
	{
		//reading of input data
		List<String> data = readInputFile("data.txt");
		
		//count maximum GC-content from input data
		String[] GCres = countGCCont(data);
		
		//write maximum GC-content and it`s id in a file
		writeInFile("out.txt", GCres);
	}
}
