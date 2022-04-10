package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.User;
import softuni.exam.instagraphlite.models.dto.UserSeedDTO;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String USERS_FILE_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final PictureService pictureService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, PictureService pictureService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readFromFileContent(), UserSeedDTO[].class))
                .filter(userSeedDTO -> {
                    boolean isValid = validationUtil.isValid(userSeedDTO);
                    if (pictureService.FindPictureByPath(userSeedDTO.getProfilePicture()) == null) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported User: %s",
                                    userSeedDTO.getUsername())
                                    : "Invalid User")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(userSeedDTO -> {
                    User user = modelMapper.map(userSeedDTO, User.class);
                    user.setProfilePicture(pictureService.FindPictureByPath(userSeedDTO.getProfilePicture()));
                    return user;
                })
                .forEach(userRepository::save);
        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();
        userRepository.findAllByPostsOrderByPosts()
                .forEach(user -> {
                    sb.append(String.format("User: %s\n" + 
                                    "Post count: %d\n",
                            user.getUsername(), user.getPosts().size()));
                    List<Post> postList = new ArrayList<>(user.getPosts());
                    postList.sort(Comparator.comparing(post -> post.getPicture().getSize()));
                    postList.forEach(post ->
                            sb.append(String.format("==Post Details:\n" +
                                            "----Caption: %s\n" +
                                            "----Picture Size: %.2f\n",
                                    post.getCaption(), post.getPicture().getSize())));
                    sb.append(System.lineSeparator());
                });
        return sb.toString();
    }

    @Override
    public User FindUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }
}
