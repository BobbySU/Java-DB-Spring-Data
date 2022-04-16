package softuni.exam.service;

//ToDo - Before start App implement this Service and set areImported to return false

import softuni.exam.models.Town;

import java.io.IOException;

public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws IOException;

    Town FindTownByName(String name);
}
