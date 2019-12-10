package br.com.danilopaixao.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public static void main1(String[] args) {
		String name = "name";
		String time = "time";
		String t = String.format("%1$s -  %2$s ", name, time).toString();
		System.out.println(t.toString());
	}
	
	public static void main(String[] args) {
		List<Candidato> listaCandidatos = new ArrayList<Candidato>();
		
		String fileName = "d://concurso//aprovados.txt";

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(l ->{
				String[] line = l.split(" ");
				String nome = null;
				try {
					nome = line[11] + " " + line[12] + " " + line[13] + " " + line[14];					
				}catch (Exception e) {
					nome = line[11] + " " + line[12];
				}
				String rg = line[10];
				boolean aprovadoNegro = isAprovadoNegro(rg);
				String objetiva = getPontuacaoObjetiva(rg);
				String discursiva = getPontuacaoDiscursiva(rg);
				String titulos = getPontuacaoTitulos(rg);
				
				Integer objetivaInt = Integer.valueOf(objetiva.isEmpty() ? "0":objetiva);
				Integer discursivaInt = Integer.valueOf(discursiva.isEmpty()?"0":discursiva);
				Integer titulosInt = Integer.valueOf(titulos.isEmpty()?"0":titulos);
				listaCandidatos.add(new Candidato(nome, objetivaInt, discursivaInt, titulosInt, aprovadoNegro));
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Candidato> sortedList = listaCandidatos.stream().sorted(Comparator.comparing(Candidato::getNota).reversed()).collect(Collectors.toList());
		sortedList.stream().forEach(l->{
//			if(!l.isAprovadoNegro()) {
//				System.out.println(l.getNome() + " - " + l.getNota());
//			}
			String texto = String.format("%1$s - %2$s ( %3s + %4s + %5s)", 
					l.getNome() , l.getNota(), l.getNotaObjetiva(), l.getNotaDiscursiva(), l.getNotaTitulos() );
			System.out.println(texto);
		});

	}
	
	public static String getPontuacaoObjetiva(String rg) {
		String fileName = "d://concurso//objetiva.txt";

		//read file into stream, try-with-resources
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				String id = l[0];
				if(id.equalsIgnoreCase(rg)) {
					String[] temp = line.split("/");
					String nota = temp[2];
					return nota.split(" ")[7];					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "0";

	}
	
	public static String getPontuacaoDiscursiva(String rg) {
		String fileName = "d://concurso//discursiva.txt";

		//read file into stream, try-with-resources
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				String id = l[0];
				if(id.equalsIgnoreCase(rg)) {
					String[] temp = line.split("/");
					String nota = temp[2];
					return nota.split(" ")[3];					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public static String getPontuacaoTitulos(String rg) {
		//D:\concurso\avaliacao de titulos.txt
		//927024917 Adriana Araújo Santana 0 0 0 0
		String fileName = "d://concurso//avaliacao de titulos.txt";

		//read file into stream, try-with-resources
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				String id = l[0];
				int lastIndex = l.length-1;
				if(id.equalsIgnoreCase(rg)) {
					String nota = l[lastIndex];
					return nota;					
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

	}
	
	public static boolean isAprovadoNegro(String rg) {
		String fileName = "d://concurso//discursiva.txt";

		//read file into stream, try-with-resources
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				String id = l[0];
				if(id.equalsIgnoreCase(rg)) 
					return l[l.length-1].equalsIgnoreCase("Negro");
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}
	


}
