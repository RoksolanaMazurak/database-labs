package com.mazurak.lab3_uklon.view;

import com.mazurak.lab3_uklon.controller.*;
import com.mazurak.lab3_uklon.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.Integer.valueOf;

@Component
public class MyView {

    @Autowired
    private CarController carController;

    @Autowired
    private CarModelController carModelController;

    @Autowired
    private ClientController clientController;
    @Autowired
    private ClientCardController clientCardController;
    @Autowired
    private DriverController driverController;
    @Autowired
    private PaymentController paymentController;
    @Autowired
    private RatingController ratingController;

    @Autowired
    private ReservationController reservationController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Car nullCar = new Car(null, null, null);
    private final CarModel nullCarModel = new CarModel(null, null, null, null, null);
    private final Client nullClient = new Client(null, null, null, null, null,null, null, null);
    private final ClientCard nullCard = new ClientCard(null, null);
    private final Driver nullDriver = new Driver(null, null, null, null, null, null);
    private final Payment nullPayment = new Payment(null, null, null, null, null);
    private final Rating nullRating = new Rating(null, null, null);
    private final Reservation nullReservation = new Reservation(null, null, null, null, null, null, null);



    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "  1 - Table: Car");
        menu.put("11", " 11 - Create Car");
        menu.put("12", " 12 - Update Car");
        menu.put("13", " 13 - Delete from Car");
        menu.put("14", " 14 - Find all Cars");
        menu.put("15", " 15 - Find Car by ID");
        menu.put("16", " 16 - Find Car by car_number");

        menu.put("2", "  2 - Table: CarModel");
        menu.put("21", " 21 - Create Car Model");
        menu.put("22", " 22 - Update Car Model");
        menu.put("23", " 23 - Delete from Car Model");
        menu.put("24", " 24 - Find all Car Models");
        menu.put("25", " 25 - Find Car Model by ID");

        menu.put("3", "  3 - Table: Client");
        menu.put("31", " 31 - Create Client");
        menu.put("32", " 32 - Update Client");
        menu.put("33", " 33 - Delete from Client");
        menu.put("34", " 34 - Find all Clients");
        menu.put("35", " 35 - Find Client by ID");

        menu.put("4", "  4 - Table: ClientCard");
        menu.put("41", " 41 - Create Client Card");
        menu.put("42", " 42 - Update Client Card");
        menu.put("43", " 43 - Delete from Client Card");
        menu.put("44", " 44 - Find all Client Cards");
        menu.put("45", " 45 - Find Client Card by ID");

        menu.put("5", "  5 - Table: Driver");
        menu.put("51", " 51 - Create Driver");
        menu.put("52", " 52 - Update Driver");
        menu.put("53", " 53 - Delete from Driver");
        menu.put("54", " 54 - Find all Drivers");
        menu.put("55", " 55 - Find Driver by ID");

        menu.put("6", "  6 - Table: Payment");
        menu.put("61", " 61 - Create Payment");
        menu.put("62", " 62 - Update Payment");
        menu.put("63", " 63 - Delete from Payment");
        menu.put("64", " 64 - Find all Payments");
        menu.put("65", " 65 - Find Payment by ID");

        menu.put("7", "  7 - Table: Rating");
        menu.put("71", " 71 - Create Rating");
        menu.put("72", " 72 - Update Rating");
        menu.put("73", " 73 - Delete from Rating");
        menu.put("74", " 74 - Find all Ratings");
        menu.put("75", " 75 - Find Rating by ID");

        menu.put("8", "  8 - Table: Reservation");
        menu.put("81", " 81 - Create Reservation");
        menu.put("82", " 82 - Update Reservation");
        menu.put("83", " 83 - Delete from Reservation");
        menu.put("84", " 84 - Find all Reservations");
        menu.put("85", " 85 - Find Reservation by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createCar);
        methodsMenu.put("12", this::updateCar);
        methodsMenu.put("13", this::deleteFromCar);
        methodsMenu.put("14", this::findAllCars);
        methodsMenu.put("15", this::findCarById);
        methodsMenu.put("16", this::findCarByCarNumber);

        methodsMenu.put("21", this::createCarModel);
        methodsMenu.put("22", this::updateCarModel);
        methodsMenu.put("23", this::deleteFromCarModel);
        methodsMenu.put("24", this::findAllCarModels);
        methodsMenu.put("25", this::findCarModelById);

        methodsMenu.put("31", this::createClient);
        methodsMenu.put("32", this::updateClient);
        methodsMenu.put("33", this::deleteFromClient);
        methodsMenu.put("34", this::findAllClients);
        methodsMenu.put("35", this::findClientById);

        methodsMenu.put("41", this::createClientCard);
        methodsMenu.put("42", this::updateClientCard);
        methodsMenu.put("43", this::deleteFromClientCard);
        methodsMenu.put("44", this::findAllClientCards);
        methodsMenu.put("45", this::findClientCardById);

        methodsMenu.put("51", this::createDriver);
        methodsMenu.put("52", this::updateDriver);
        methodsMenu.put("53", this::deleteFromDriver);
        methodsMenu.put("54", this::findAllDrivers);
        methodsMenu.put("55", this::findDriverById);

        methodsMenu.put("61", this::createPayment);
        methodsMenu.put("62", this::updatePayment);
        methodsMenu.put("63", this::deleteFromPayment);
        methodsMenu.put("64", this::findAllPayments);
        methodsMenu.put("65", this::findPaymentById);

        methodsMenu.put("71", this::createRating);
        methodsMenu.put("72", this::updateRating);
        methodsMenu.put("73", this::deleteFromRating);
        methodsMenu.put("74", this::findAllRatings);
        methodsMenu.put("75", this::findRatingById);

        methodsMenu.put("81", this::createReservation);
        methodsMenu.put("82", this::updateReservation);
        methodsMenu.put("83", this::deleteFromReservation);
        methodsMenu.put("84", this::findAllReservations);
        methodsMenu.put("85", this::findReservationById);

    }

    private void selectAllTable() {
        findAllCars();
    }

    // region CAR ---------------------------------------------------
    private void createCar() {
        System.out.println("Input 'car_number': ");
        String carNumber = input.nextLine();
        System.out.println("Input 'model': ");
        Integer carModelId = valueOf(input.nextLine());
        Car car = new Car(null, carNumber.toUpperCase(), carModelId);

        int count = carController.create(car);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateCar() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input new 'car_name': ");
        String carNumber = input.nextLine();
        System.out.println("Input new 'car_model_id': ");
        Integer carModel = valueOf(input.nextLine());
        Car car = new Car(null, carNumber, carModel);

        int count = carController.update(id, car);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromCar() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = carController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllCars() {
        System.out.println("\nTable: CAR");
        List<Car> cars = carController.findAll();
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void findCarById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<Car> car = carController.findById(id);
        System.out.println(car.orElse(nullCar));
    }

    private void findCarByCarNumber() {
        System.out.println("Input 'car_number': ");
        String carNumber = input.nextLine();

        Optional<Car> car = carController.findByCarNumber(carNumber);
        System.out.println(car.orElse(nullCar));
    }

    // region CAR MODEL ---------------------------------------------------
    private void createCarModel() {
        System.out.println("Input 'car_model': ");
        String carName = input.nextLine();
        System.out.println("Input 'car_company': ");
        String carCompany = input.nextLine();
        System.out.println("Input 'car_class': ");
        String carClass = input.nextLine();
        System.out.println("Input 'car_model_num_of_seats': ");
        Integer seatNum = valueOf(input.nextLine());

        CarModel carModel = new CarModel(null, carName, carCompany, carClass, seatNum);

        int count = carModelController.create(carModel);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateCarModel() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input new 'car_model_name': ");
        String carModelName = input.nextLine();
        System.out.println("Input new 'car_model_company': ");
        String carModelCompany = input.nextLine();
        System.out.println("Input new 'car_model_class': ");
        String carModelClass = input.nextLine();
        System.out.println("Input new 'car_model_num_of_seats': ");
        Integer carModelSeatsNum = valueOf(input.nextLine());

        CarModel carModel = new CarModel(null, carModelName, carModelCompany, carModelClass, carModelSeatsNum);

        int count = carModelController.update(id, carModel);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromCarModel() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = carModelController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllCarModels() {
        System.out.println("\nTable: CAR MODEL");
        List<CarModel> cars = carModelController.findAll();
        for (CarModel car : cars) {
            System.out.println(car);
        }
    }

    private void findCarModelById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<CarModel> car = carModelController.findById(id);
        System.out.println(car.orElse(nullCarModel));
    }

    // region CLIENT ---------------------------------------------------
    private void createClient() {
        System.out.println("Input 'client_surname': ");
        String clientSurname = input.nextLine();
        System.out.println("Input 'client_name': ");
        String clientName = input.nextLine();
        System.out.println("Input 'client_phone_number': ");
        String clientPhone = input.nextLine();
        System.out.println("Input 'client_email': ");
        String clientEmail = input.nextLine();
        System.out.println("Input 'client_city': ");
        String clientCity = input.nextLine();
        System.out.println("Input 'client_street_address': ");
        String clientStreet = input.nextLine();
        System.out.println("Input 'client_card_id': ");
        Integer clientCardId = valueOf(input.nextLine());
        Client client = new Client(null, clientSurname, clientName, clientPhone, clientEmail,
                clientCity, clientStreet, clientCardId);

        int count = clientController.create(client);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateClient() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input 'client_surname': ");
        String clientSurname = input.nextLine();
        System.out.println("Input new 'client_name': ");
        String clientName = input.nextLine();
        System.out.println("Input new 'client_phone_number': ");
        String clientPhone = input.nextLine();
        System.out.println("Input new 'client_email': ");
        String clientEmail = input.nextLine();
        System.out.println("Input new 'client_city': ");
        String clientCity = input.nextLine();
        System.out.println("Input new 'client_street_address': ");
        String clientStreet = input.nextLine();
        System.out.println("Input new 'client_card_id': ");
        Integer clientCardId = valueOf(input.nextLine());
        Client client = new Client(null, clientSurname, clientName, clientPhone, clientEmail,
                clientCity, clientStreet, clientCardId);

        int count = clientController.update(id, client);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromClient() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = clientController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllClients() {
        System.out.println("\nTable: CLIENT");
        List<Client> clients = clientController.findAll();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void findClientById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<Client> client = clientController.findById(id);
        System.out.println(client.orElse(nullClient));
    }

    // region CLIENT CARD ---------------------------------------------------
    private void createClientCard() {
        System.out.println("Input 'client_card': ");
        String clientCard = input.nextLine();

        ClientCard card = new ClientCard(null, clientCard);

        int count = clientCardController.create(card);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateClientCard() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input new 'card_name': ");
        String clientCard = input.nextLine();

        ClientCard card = new ClientCard(null, clientCard);

        int count = clientCardController.update(id, card);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromClientCard() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = clientCardController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllClientCards() {
        System.out.println("\nTable: CLIENT CARD");
        List<ClientCard> cards = clientCardController.findAll();
        for (ClientCard card : cards) {
            System.out.println(card);
        }
    }

    private void findClientCardById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<ClientCard> car = clientCardController.findById(id);
        System.out.println(car.orElse(nullCard));
    }

    // region DRIVER ---------------------------------------------------
    private void createDriver() {
        System.out.println("Input 'driver_surname': ");
        String driverSurname = input.nextLine();
        System.out.println("Input 'driver_name': ");
        String driverName = input.nextLine();
        System.out.println("Input 'driver_gender': ");
        String driverGender = input.nextLine();
        System.out.println("Input 'driver_rating': ");
        Integer driverRating = valueOf(input.nextLine());
        System.out.println("Input 'driver_car_id': ");
        Integer carId = valueOf(input.nextLine());
        Driver driver = new Driver(null, driverSurname, driverName, driverGender, driverRating, carId);

        int count = driverController.create(driver);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateDriver() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input new 'driver_surname': ");
        String driverSurname = input.nextLine();
        System.out.println("Input new 'driver_name': ");
        String driverName = input.nextLine();
        System.out.println("Input new 'driver_gender': ");
        String driverGender = input.nextLine();
        System.out.println("Input new 'driver_rating': ");
        Integer driverRating = valueOf(input.nextLine());
        System.out.println("Input new 'driver_car_id': ");
        Integer carId = valueOf(input.nextLine());
        Driver driver = new Driver(null, driverSurname, driverName, driverGender, driverRating, carId);

        int count = driverController.update(id, driver);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromDriver() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = driverController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllDrivers() {
        System.out.println("\nTable: DRIVER");
        List<Driver> drivers = driverController.findAll();
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    private void findDriverById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<Driver> driver = driverController.findById(id);
        System.out.println(driver.orElse(nullDriver));
    }

    // region PAYMENT ---------------------------------------------------
    private void createPayment() {
        System.out.println("Input 'payment_type': ");
        String paymentType = input.nextLine();
        System.out.println("Input 'payment_time': ");
        String paymentTime = input.nextLine();
        System.out.println("Input 'payment_card_number': ");
        String paymentCard = input.nextLine();
        System.out.println("Input 'transaction_id': ");
        Integer transactionId = valueOf(input.nextLine());
        Payment payment = new Payment(null, paymentType, paymentTime, paymentCard, transactionId);

        int count = paymentController.create(payment);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updatePayment() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input new 'payment_type': ");
        String paymentType = input.nextLine();
        System.out.println("Input new 'payment_time': ");
        String paymentTime = input.nextLine();
        System.out.println("Input new 'payment_card_number': ");
        String paymentCard = input.nextLine();
        System.out.println("Input new 'transaction_id': ");
        Integer transactionId = valueOf(input.nextLine());
        Payment payment = new Payment(null, paymentType, paymentTime, paymentCard, transactionId);

        int count = paymentController.update(id, payment);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromPayment() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = paymentController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllPayments() {
        System.out.println("\nTable: PAYMENT");
        List<Payment> payments = paymentController.findAll();
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    private void findPaymentById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<Payment> car = paymentController.findById(id);
        System.out.println(car.orElse(nullPayment));
    }

    // region RATING ---------------------------------------------------
    private void createRating() {
        System.out.println("Input 'rating_value': ");
        Integer ratingValue = valueOf(input.nextLine());
        System.out.println("Input 'client_id': ");
        Integer clientId = valueOf(input.nextLine());

        Rating rating = new Rating(null, ratingValue, clientId);

        int count = ratingController.create(rating);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateRating() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input 'rating_value': ");
        Integer ratingValue = valueOf(input.nextLine());
        System.out.println("Input 'client_id': ");
        Integer clientId = valueOf(input.nextLine());

        Rating rating = new Rating(null, ratingValue, clientId);

        int count = ratingController.update(id,rating);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromRating() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = ratingController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllRatings() {
        System.out.println("\nTable: RATING");
        List<Rating> ratings = ratingController.findAll();
        for (Rating rating : ratings) {
            System.out.println(rating);
        }
    }

    private void findRatingById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<Rating> rating = ratingController.findById(id);
        System.out.println(rating.orElse(nullRating));
    }

    // region RESERVATION ---------------------------------------------------
    private void createReservation() {
        System.out.println("Input 'client_id': ");
        Integer client = valueOf(input.nextLine());
        System.out.println("Input 'reservation_time': ");
        String reservationTime = input.nextLine();
        System.out.println("Input 'reservation_payment': ");
        Integer reservationPayment = valueOf(input.nextLine());
        System.out.println("Input 'start_address': ");
        String startAddress = input.nextLine();
        System.out.println("Input 'final_address': ");
        String finalAddress = input.nextLine();
        System.out.println("Input 'reservation_car_id': ");
        Integer reservationCar = valueOf(input.nextLine());
        Reservation reservation = new Reservation(null, client, reservationTime, reservationPayment,
                startAddress, finalAddress, reservationCar);

        int count = reservationController.create(reservation);
        System.out.printf("Created %d row(s)\n", count);
    }

    private void updateReservation() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        System.out.println("Input new 'client_id': ");
        Integer client = valueOf(input.nextLine());
        System.out.println("Input new 'reservation_time': ");
        String reservationTime = input.nextLine();
        System.out.println("Input new 'reservation_payment': ");
        Integer reservationPayment = valueOf(input.nextLine());
        System.out.println("Input new 'start_address': ");
        String startAddress = input.nextLine();
        System.out.println("Input new 'final_address': ");
        String finalAddress = input.nextLine();
        System.out.println("Input new 'reservation_car_id': ");
        Integer reservationCar = valueOf(input.nextLine());
        Reservation reservation = new Reservation(null, client, reservationTime, reservationPayment,
                startAddress, finalAddress, reservationCar);

        int count = reservationController.update(id, reservation);
        System.out.printf("Updated %d row(s)\n", count);
    }

    private void deleteFromReservation() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        int count = reservationController.delete(id);
        System.out.printf("Deleted %d row(s)\n", count);
    }

    private void findAllReservations() {
        System.out.println("\nTable: RESERVATION");
        List<Reservation> reservs = reservationController.findAll();
        for (Reservation reserv : reservs) {
            System.out.println(reserv);
        }
    }

    private void findReservationById() {
        System.out.println("Input 'id': ");
        Integer id = valueOf((input.nextLine()));

        Optional<Reservation> reservation = reservationController.findById(id);
        System.out.println(reservation.orElse(nullReservation));
    }

    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //endregion
}

