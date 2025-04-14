package com.tablemaster_api.service;

import com.tablemaster_api.abstraction.repository.ReservationRepository;
import com.tablemaster_api.abstraction.repository.RestaurantRepository;
import com.tablemaster_api.abstraction.repository.TablesRepository;
import com.tablemaster_api.abstraction.repository.UserRepository;
import com.tablemaster_api.abstraction.service.IReservationService;
import com.tablemaster_api.dto.ContactInfoDto;
import com.tablemaster_api.dto.RestaurantDto;
import com.tablemaster_api.dto.TimeIntervalDto;
import com.tablemaster_api.entity.Reservation;
import com.tablemaster_api.entity.Restaurant;
import com.tablemaster_api.entity.Tables;
import com.tablemaster_api.entity.User;
import com.tablemaster_api.mapper.RestaurantDtoMapper;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final TablesRepository tablesRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RestaurantDtoMapper restaurantDtoMapper;

    public ReservationService(ReservationRepository reservationRepository, TablesRepository tablesRepository, RestaurantRepository restaurantRepository, UserRepository userRepository, EmailService emailService, RestaurantDtoMapper restaurantDtoMapper) {
        this.reservationRepository = reservationRepository;
        this.tablesRepository = tablesRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.restaurantDtoMapper = restaurantDtoMapper;
    }

    public List<Tables> getFreeTables(long restaurantId, TimeIntervalDto timeIntervalDto) {
        return reservationRepository.getFreeTables(restaurantId, timeIntervalDto.timeStart(), timeIntervalDto.timeEnd());
    }


    public void sendReservingMessageToEmail(User user, RestaurantDto restaurant,
                                            ContactInfoDto contactInfo, Reservation reservation, Tables table) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String subject = "TableMaster API reservation";
        String htmlMessage = "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\" />\n" +
                "  <title>Подтверждение бронирования</title>\n" +
                "  <style>\n" +
                "    body {\n" +
                "      font-family: Arial, sans-serif;\n" +
                "      background-color: #f9f9f9;\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    .email-container {\n" +
                "      max-width: 600px;\n" +
                "      margin: 40px auto;\n" +
                "      background: #ffffff;\n" +
                "      border-radius: 8px;\n" +
                "      box-shadow: 0 2px 6px rgba(0,0,0,0.1);\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "    .header {\n" +
                "      background-color: #2d89ef;\n" +
                "      color: #ffffff;\n" +
                "      padding: 20px;\n" +
                "      text-align: center;\n" +
                "    }\n" +
                "    .header h1 {\n" +
                "      margin: 0;\n" +
                "      font-size: 24px;\n" +
                "    }\n" +
                "    .content {\n" +
                "      padding: 30px 20px;\n" +
                "      color: #333333;\n" +
                "    }\n" +
                "    .content h2 {\n" +
                "      margin-top: 0;\n" +
                "      color: #2d89ef;\n" +
                "    }\n" +
                "    .details {\n" +
                "      margin: 20px 0;\n" +
                "      padding: 15px;\n" +
                "      background-color: #f1f7ff;\n" +
                "      border-left: 4px solid #2d89ef;\n" +
                "    }\n" +
                "    .footer {\n" +
                "      padding: 20px;\n" +
                "      text-align: center;\n" +
                "      font-size: 12px;\n" +
                "      color: #888888;\n" +
                "      background-color: #f0f0f0;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <div class=\"email-container\">\n" +
                "    <div class=\"header\">\n" +
                "      <h1>Спасибо за ваше бронирование!</h1>\n" +
                "    </div>\n" +
                "    <div class=\"content\">\n" +
                "      <h2>Уважаемый(ая) " + user.getUsername() + " ,</h2>\n" +
                "      <p>Ваш столик успешно забронирован. Мы с нетерпением ждём вас!</p>\n" +
                "\n" +
                "      <div class=\"details\">\n" +
                "        <p><strong>Дата:</strong>"+ reservation.getReservationTimeStart().format(dateFormatter) + "</p>\n" +
                "        <p><strong>Время:</strong>"+ reservation.getReservationTimeStart().format(timeFormatter) + "</p>\n" +
                "        <p><strong>Количество гостей:</strong>" + table.getCapacity() + "</p>\n" +
                "        <p><strong>Место:</strong>" + restaurant.name() + "/" + restaurant.address() + "</p>\n" +
                "      </div>\n" +
                "\n" +
                "      <p>Если у вас возникнут вопросы или вы хотите изменить бронирование — просто ответьте на это письмо или позвоните нам.</p>\n" +
                "      <p> " + contactInfo + "</p>\n" +
                "    </div>\n" +
                "    <div class=\"footer\">\n" +
                "      © 2025 " + restaurant.name() + ". Все права защищены.\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
        try {
            emailService.sendEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String reserveTable(long restaurantId, long tableId, TimeIntervalDto timeIntervalDto, Principal principal) {

        Tables table = tablesRepository.findById(tableId)
                .orElseThrow(() -> new EntityNotFoundException("Table not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!getFreeTables(restaurantId, timeIntervalDto).contains(table)) {
            return "This table is reserved at this time.";
        }
        Reservation reservation = new Reservation();
        reservation.setTables(table);
        reservation.setRestaurant(restaurant);
        reservation.setUser(user);
        reservation.setReservationTimeStart(timeIntervalDto.timeStart());
        reservation.setReservationTimeEnd(timeIntervalDto.timeEnd());
        ContactInfoDto contacts = new ContactInfoDto(restaurant.getPhone(), restaurant.getEmail());
        sendReservingMessageToEmail(user, restaurantDtoMapper.fromEntity(restaurant), contacts, reservation, table);
        reservationRepository.save(reservation);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String reservedTime = timeIntervalDto.timeStart().format(timeFormatter) + "-"
                + timeIntervalDto.timeEnd().format(timeFormatter);
        String reservedDay = timeIntervalDto.timeStart().format(dateFormatter);

        return "Table reserved successfully on time: " + reservedTime + ",\n On date: " + reservedDay;
    }
}
