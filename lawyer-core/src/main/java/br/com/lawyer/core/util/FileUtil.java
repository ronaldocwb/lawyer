package br.com.lawyer.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	
	/**
	 * Recupera conteudo do arquivo informado
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getConteudoArquivo(File file) throws IOException{
		StringBuffer retorno = new StringBuffer();
		

		FileReader reader = new FileReader(file);

		BufferedReader br =  new BufferedReader(reader);
		while(br.ready()){
			retorno.append(br.readLine());
		}

		//Fecha os arquivos para liberar recursos do SO
		reader.close();
		br.close();
		
		return retorno.toString();
	}
	
	public static void gravaConteudoArquivo(File arquivo, String conteudo) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
		writer.write(conteudo);
		writer.close();
	}
	
}
