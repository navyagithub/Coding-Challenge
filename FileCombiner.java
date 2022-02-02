
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileCombiner {

	public static void main(String[] args) throws Exception {
		
		FileWriter fileWriter = null;
        BufferedWriter bufferedFileWriter = null;
        
		try {
        String filePath = args[0];
        int is_Header_added = 0;
        
        String filePathWithOutName = filePath.substring(0, filePath.lastIndexOf("\\") + 1);

        String outputFilePath = filePathWithOutName + "combined.csv";
        
        
        
        File outputFile = new File(outputFilePath);
        fileWriter = new FileWriter(outputFile,false);  
        bufferedFileWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i< args.length; i++) {
            File readFile = new File(args[i]);
            FileReader fileReader = new FileReader(readFile);
            BufferedReader bufferedFileReader = new BufferedReader(fileReader);
            String fileName = args[i].substring(filePath.lastIndexOf("\\") + 1);
            String line = bufferedFileReader.readLine();
            if(is_Header_added == 0 && line != null && !(line.equals(""))) {
            	bufferedFileWriter.write(line+",fileName"); 
            	bufferedFileWriter.newLine();
            	is_Header_added = 1;
            }
            while ((line = bufferedFileReader.readLine()) != null) {
            	bufferedFileWriter.write(line + "," + fileName); 
            	bufferedFileWriter.newLine();
            }
            
            bufferedFileReader.close();
            fileReader.close();
        }
        }
        catch(FileNotFoundException e) {
        	System.out.print("File is not found to process further");
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println(e.getMessage());
        }
        finally {

            bufferedFileWriter.close();
            fileWriter.close();
        }
    }

}
