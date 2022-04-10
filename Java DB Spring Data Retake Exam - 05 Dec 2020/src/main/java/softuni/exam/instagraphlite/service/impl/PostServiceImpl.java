package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.dto.PostSeedRootDTO;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {
    private static final String POSTS_FILE_PATH = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final UserService userService;
    private final PictureService pictureService;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper,
                           ValidationUtil validationUtil, XmlParser xmlParser,
                           UserService userService, PictureService pictureService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.userService = userService;
        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POSTS_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(POSTS_FILE_PATH, PostSeedRootDTO.class)
                .getPosts()
                .stream()
                .filter(postSeedDTO -> {
                    boolean isValid = validationUtil.isValid(postSeedDTO);
                    if (pictureService.FindPictureByPath(postSeedDTO.getPicture().getPath()) == null ||
                    userService.FindUserByUserName(postSeedDTO.getUser().getUsername()) == null){
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Post, made by %s",
                                    postSeedDTO.getUser().getUsername())
                                    : "Invalid Post")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(postSeedDTO -> {
                    Post post = modelMapper.map(postSeedDTO, Post.class);
                    post.setPicture(pictureService.FindPictureByPath(postSeedDTO.getPicture().getPath()));
                    post.setUser(userService.FindUserByUserName(postSeedDTO.getUser().getUsername()));
                    return post;
                })
                .forEach(postRepository::save);
        return sb.toString();
    }
}
