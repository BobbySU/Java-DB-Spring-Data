package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.Ticket;
import softuni.exam.models.dto.xml.TicketSeedRootDTO;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TicketServiceImpl implements TicketService {

    private static final String TICKETS_FILE_PATH = "src/main/resources/files/xml/tickets.xml";

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final TownService townService;
    private final PassengerService passengerService;
    private final PlaneService planeService;


    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper,
                             ValidationUtil validationUtil, XmlParser xmlParser,
                             TownService townService, PassengerService passengerService,
                             PlaneService planeService) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
        this.passengerService = passengerService;
        this.planeService = planeService;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(TICKETS_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        TicketSeedRootDTO dto = xmlParser.fromFile(TICKETS_FILE_PATH, TicketSeedRootDTO.class);
        xmlParser.fromFile(TICKETS_FILE_PATH, TicketSeedRootDTO.class)
                .getTickets()
                .stream()
                .filter(ticketSeedDTO -> {
                    boolean isValid = validationUtil.isValid(ticketSeedDTO);
                    if (ticketRepository.findTicketBySerialNumber(ticketSeedDTO.getSerialNumber()) != null){
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Ticket %s - %s",
                                    ticketSeedDTO.getFromTown().getName(), ticketSeedDTO.getToTown().getName())
                                    : "Invalid Ticket")
                            .append(System.lineSeparator());
                    return isValid;
                })
                .map(ticketSeedDTO -> {
                    Ticket ticket = modelMapper.map(ticketSeedDTO, Ticket.class);
                        ticket.setFromTown(townService.FindTownByName(ticketSeedDTO.getFromTown().getName()));
                        ticket.setToTown(townService.FindTownByName(ticketSeedDTO.getToTown().getName()));
                        ticket.setPassenger(passengerService.FindPassengerByEmail(ticketSeedDTO.getPassenger().getEmail()));
                        ticket.setPlane(planeService.FindPlaneByRegisterNumber(ticketSeedDTO.getPlane().getRegisterNumber()));
                    return ticket;
                })
                .forEach(ticketRepository::save);
        return sb.toString();
    }
}
