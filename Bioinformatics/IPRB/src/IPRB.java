import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IPRB {
	
	//function reads input parameters from file and split it
	public static String[] readInputFile(String fileName)
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
		return data.split(" ");
	}
	
	//function counts the probability that two randomly selected mating organisms will produce a dominant allele
	public static double countProbDom(String[] data)
	{
		double prob= 0;			
		double countDom = Double.parseDouble(data[0]), countHet = Double.parseDouble(data[1]), countRec = Double.parseDouble(data[2]);
		double allOrg = countDom + countHet + countRec;
		//dom + dom
		prob += countDom / allOrg * (countDom - 1) / (allOrg - 1);
		//dom + het
		prob += countDom / allOrg * countHet / (allOrg - 1);
		//dom + rec
		prob += countDom / allOrg * countRec / (allOrg - 1);
		//het + dom
		prob += countHet / allOrg * countDom / (allOrg - 1);
		//het + het
		prob += countHet / allOrg * (countHet - 1) / (allOrg - 1) * 3 / 4;
		//het + rec
		prob += countHet / allOrg * countRec / (allOrg - 1) * 1 / 2;
		//rec + dom
		prob += countRec / allOrg * countDom / (allOrg - 1);
		//rec + het
		prob += countRec / allOrg * countHet / (allOrg - 1) * 1 / 2;
		//rec + rec = 0
		return prob;
	}
	
	//function writes result probability in a file
	public static void writeInFile(String fileName, double prob)
	{
		Path fout = Path.of(fileName);
		try 
		{
			Files.writeString(fout, Double.toString(prob));
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
		
		//count probability dominant allele will produce
		double prob = countProbDom(data);
		
		//write result probability in a file
		writeInFile("out.txt", prob);
	}
}
