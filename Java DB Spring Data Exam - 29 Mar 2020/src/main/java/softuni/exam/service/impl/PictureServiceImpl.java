package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURE_FILE_PATH = "src/main/resources/files/json/pictures.json";

    private PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        return null;
    }
}
