package com.example.egarsatexercise.csv;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
	@Autowired
	VideogameRepository repository;

	public void save(MultipartFile file) {
		try {
			List<Videogame> videogames = CSVHelper.csvToVideogames(file.getInputStream());
			repository.saveAll(videogames);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public ByteArrayInputStream load() {
		List<Videogame> videogames = repository.findAll();

		ByteArrayInputStream in = CSVHelper.videogamesToCSV(videogames);
		return in;
	}

	public List<Videogame> getAllVideogames() {
		return repository.findAll();
	}
}