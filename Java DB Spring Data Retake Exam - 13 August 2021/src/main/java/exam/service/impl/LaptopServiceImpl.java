package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Laptop;
import exam.model.dto.LaptopSeedDTO;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class LaptopServiceImpl implements LaptopService {
    private static final String LAPTOPS_FILE_PATH = "src/main/resources/files/json/laptops.json";

    private final LaptopRepository laptopRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final ShopService shopService;

    public LaptopServiceImpl(LaptopRepository laptopRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, ShopService shopService) {
        this.laptopRepository = laptopRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.shopService = shopService;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOPS_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(readLaptopsFileContent(), LaptopSeedDTO[].class))
                .filter(laptopSeedDTO -> {
                    boolean isValid = validationUtil.isValid(laptopSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                                    laptopSeedDTO.getMacAddress(), laptopSeedDTO.getCpuSpeed(),
                                    laptopSeedDTO.getRam(), laptopSeedDTO.getStorage())
                                    : "Invalid Laptop")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(laptopSeedDTO -> {
                    Laptop laptop = modelMapper.map(laptopSeedDTO, Laptop.class);
                    laptop.setShop(shopService.FindShopByName(laptopSeedDTO.getShop().getName()));
                    return laptop;
                })
                .forEach(laptopRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();
        laptopRepository.findAllOrderByCpuSpeedDescRamDescStorageDescMacAddress()
                .forEach(laptop -> {
                    sb.append(String.format("Laptop - %s\n" +
                                            "*Cpu speed - %.2f\n" +
                                            "**Ram - %d\n" +
                                            "***Storage - %d\n" +
                                            "****Price - %.2f\n" +
                                            "#Shop name - %s\n" +
                                            "##Town - %s\n",
                                    laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(),
                                    laptop.getStorage(), laptop.getPrice(),
                                    laptop.getShop().getName(), laptop.getShop().getTown().getName()))
                            .append(System.lineSeparator());
                });
        return sb.toString();
    }
}
