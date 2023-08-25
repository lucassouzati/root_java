package com.lucassouzati.rootjava.infrasctructure.files;

import com.lucassouzati.rootjava.application.files.IFileReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FileCsvReader implements IFileReader {

    private final String filePath = "resources\\alunos.csv";
    private Logger logger = Logger.getLogger(FileCsvReader.class.getName());



    @Override
    public ArrayList<String[]> read(){
        var retorno = new ArrayList<String[]>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                retorno.add(values);
            }
            return retorno;
        }
        catch(IOException e){
            logger.info("Erro ao abrir arquivo! " + e.getMessage());
            e.printStackTrace();
        }

        return retorno;
    }
    
}
