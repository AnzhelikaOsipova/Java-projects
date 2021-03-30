import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HAMM {
	
	//function reads DNA strings from a file
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
		
	//function counts hamming distance between given DNA
	public static int countHammDist(List<String> data)
	{
		String str1 = data.get(0);
		String str2 = data.get(1);
		int hammDist = 0;
		for(int i = 0; i < str1.length(); i++)
		{
			if(str1.charAt(i) != str2.charAt(i))
			{
				hammDist++;
			}
		}
		return hammDist;
	}
		
	//function writes hamming distance in a file
	public static void writeInFile(String fileName, int hamDist)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, Integer.toString(hamDist));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args)
	{
		//reading of input data
		List<String> data = readInputFile("data.txt");
		
		//find hamming distance between given DNA
		int hammDist = countHammDist(data);
		
		//write hamming distance in a file
		writeInFile("out.txt", hammDist);
	}
}
