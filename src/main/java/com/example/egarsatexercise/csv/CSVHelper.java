package com.example.egarsatexercise.csv;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

	public static Integer cont = 0;
	//public static String numCont;
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Nombre", "Coste", "Plataforma", "Tipo", "Disponibilidad" };

	public static boolean hasCSVFormat(MultipartFile file) {
		if (TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {
			return true;

		}

		return false;
	}

	public static List<Videogame> csvToVideogames(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Videogame> videogamesList = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Videogame videogames = new Videogame(csvRecord.get("Nombre"),
						Long.parseLong(csvRecord.get("Coste")), csvRecord.get("Plataforma"), csvRecord.get("Tipo"),
						csvRecord.get("Disponibilidad"));

				videogamesList.add(videogames);
				cont++;
			}

			//numCont = cont + "";

			return videogamesList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream videogamesToCSV(List<Videogame> videogamesList) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (Videogame videogames : videogamesList) {
				List<Object> data = Arrays.asList(videogames.getNombre(),
						String.valueOf(videogames.getCoste()), videogames.getPlataforma(),
						videogames.getTipo(), videogames.getDisponibilidad());

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

}
