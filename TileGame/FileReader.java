package TileGame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class FileReader extends FileInputStream
{
    FileInputStream fileIn = null;
   
    public FileReader(String fileName) throws FileNotFoundException
    {
      
      super(fileName);
      
      
    } 
    public int readTileNumber(String fileName) throws FileNotFoundException //returns the number of tiles
    {
      File file = new File(fileName);     
      byte[] tileSize = new byte[4];
      try
      {
        fileIn = new FileInputStream(file);
        fileIn.read(tileSize);
      }
      catch(FileNotFoundException fnf)
      {
        System.out.println("Found an exception: " + fnf);
      } catch (IOException e) {
        
        System.out.println("Found an exception: " + e);
      }
      finally
      {
        try
        {
          if(fileIn != null)
          {
            fileIn.close();
          }
        }
        catch (IOException ioe) 
        {
          System.out.println("Error while closing stream: " + ioe);
        }
          
      }
      
     return java.nio.ByteBuffer.wrap(tileSize).getInt();
    }
    public  void tileSpecs(String fileName) throws FileNotFoundException
    {
      int numTile = readTileNumber(fileName);
      
      File file = new File(fileName);     
      byte[] tileNumber = new byte[4];
      byte[] numberLines = new byte[4];
      byte[] coordVal = new byte[4];
      Vector<Float> xyVals = new Vector<Float>(1,2);
      try
      {
        fileIn = new FileInputStream(file);
        fileIn.read(tileNumber);
        fileIn.read(numberLines);
        int numLines = java.nio.ByteBuffer.wrap(numberLines).getInt();
        for(int i = 0; i <= numLines*4; i++)
        {
          fileIn.read(coordVal);
          float value = java.nio.ByteBuffer.wrap(numberLines).getFloat();
          xyVals.add(value);
        }
        
      }
      catch(FileNotFoundException fnf)
      {
        System.out.println("Found an exception: " + fnf);
      } catch (IOException e) {
        
        System.out.println("Found an exception: " + e);
      }
      finally
      {
        try
        {
          if(fileIn != null)
          {
            fileIn.close();
          }
        }
        catch (IOException ioe) 
        {
          System.out.println("Error while closing stream: " + ioe);
        }
          
      }
      
    }    
    
  
}
