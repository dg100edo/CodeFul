package pt.greatcinema.sellapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FilePersistence{
    
    private Context context;
    
    public FilePersistence(Context context){
        this.context = context;
    }
    
    public void storePrivateData(byte[] data, String filename){
        try{
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(data);
            fos.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public byte[] readFileData(String filename){
        try{
            return this.readData(filename);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean removeFile(String filename){
        return context.deleteFile(filename);
    }
    
    public boolean haveFile(String filename){
        try{
            readData(filename);
        }catch(FileNotFoundException e){
            return false;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return true;
    }
    
    private byte[] readData(String filename) throws IOException{
        FileInputStream fis = context.openFileInput(filename);
        int numBytes = fis.available();
        byte[] data = new byte[numBytes];
        fis.read(data, 0, numBytes);
        fis.close();
        return data;
    }
}