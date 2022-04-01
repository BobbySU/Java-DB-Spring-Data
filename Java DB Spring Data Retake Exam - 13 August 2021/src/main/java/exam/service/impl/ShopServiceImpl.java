package exam.service.impl;

import exam.model.Shop;
import exam.model.dto.ShopSeedRootDTO;
import exam.repository.ShopRepository;
import exam.service.ShopService;
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
public class ShopServiceImpl implements ShopService {
    private static final String SHOPS_FILE_PATH = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final TownService townService;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownService townService) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOPS_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(SHOPS_FILE_PATH, ShopSeedRootDTO.class)
                .getShops()
                .stream()
                .filter(shopSeedDTO -> {
                    boolean isValid = validationUtil.isValid(shopSeedDTO);
                    sb.append(isValid ? String.format("Successfully imported Shop %s - %.0f",
                                    shopSeedDTO.getName(), shopSeedDTO.getIncome())
                                    : "Invalid Shop")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(shopSeedDTO ->  {
                    Shop shop = modelMapper.map(shopSeedDTO, Shop.class);
                    shop.setTown(townService.FindTownByName(shopSeedDTO.getTown().getName()));
                    return shop;
                })
                .forEach(shopRepository::save);
        return sb.toString();
    }

    @Override
    public Shop FindShopByName(String name) {
        return shopRepository.findShopByName(name);
    }
}
