package exam.service.impl;

import exam.model.Town;
import exam.model.dto.TownSeedRootDTO;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/xml/towns.xml";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(TOWNS_FILE_PATH, TownSeedRootDTO.class)
                .getTowns()
                .stream()
                .filter(townSeedDTO -> {
                    boolean isValid = validationUtil.isValid(townSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Town %s",
                                    townSeedDTO.getName())
                                    : "Invalid Town")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(townSeedDTO ->  modelMapper.map(townSeedDTO, Town.class))
                .forEach(townRepository::save);
        return sb.toString();
    }

    @Override
    public Town FindTownByName(String name) {
        return townRepository.findTownByName(name);
    }
}
