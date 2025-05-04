import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Cinema {
    private String name;
    private List<Hall> halls = new ArrayList<>();

    public Cinema(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Hall> getHalls() {
        return halls;
    }
}

class Hall {
    private int number;
    private int rows;
    private int seatsInRow;
    private List<Session> sessions = new ArrayList<>();

    public Hall(int number, int rows, int seatsInRow) {
        this.number = number;
        this.rows = rows;
        this.seatsInRow = seatsInRow;
    }

    public int getNumber() {
        return number;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsInRow() {
        return seatsInRow;
    }

    public List<Session> getSessions() {
        return sessions;
    }
}

class Session {
    private String movie;
    private LocalDateTime startTime;
    private int duration;
    private boolean[][] seats;

    public Session(String movie, LocalDateTime startTime, int duration, int rows, int seatsInRow) {
        this.movie = movie;
        this.startTime = startTime;
        this.duration = duration;
        this.seats = new boolean[rows][seatsInRow];
    }

    public String getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public boolean isSeatAvailable(int row, int seat) {
        return !seats[row][seat];
    }

    public void bookSeat(int row, int seat) {
        seats[row][seat] = true;
    }
}

class AuthService {
    private static final String ADMIN_username = "admin";
    private static final String ADMIN_password = "lab_3";
    private static final String USER_username = "user";
    private static final String USER_password = "pass";

    public boolean authAdmin(String user, String pass) {
        return ADMIN_username.equals(user) && ADMIN_password.equals(pass);
    }

    public boolean authUser(String user, String pass) {
        return USER_username.equals(user) && USER_password.equals(pass);
    }
}

class CinemaService {
    private List<Cinema> cinemas = new ArrayList<>();

    public void addCinema(String name) {
        cinemas.add(new Cinema(name));
    }

    public void addHall(String cinemaName, int hallNumber, int rows, int seats) {
        for (Cinema c : cinemas) {
            if (c.getName().equals(cinemaName)) {
                c.getHalls().add(new Hall(hallNumber, rows, seats));
                return;
            }
        }
        System.out.println("Кинотеатр не найден");
    }

    public void addSession(String cinemaName, int hallNumber, String movie,
                           LocalDateTime startTIme, int duration) {
        try {
            for (Cinema c : cinemas) {
                if (c.getName().equals(cinemaName)) {
                    for (Hall h : c.getHalls()) {
                        if (h.getNumber() == hallNumber) {
                            h.getSessions().add(new Session(
                                    movie, startTIme, duration,
                                    h.getRows(), h.getSeatsInRow()
                            ));
                            return;
                        }
                    }
                }
            }
            System.out.println("Ошибка добавления сеанса");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }
}

class BookingService {
    public boolean bookSeat(Session session, int row, int seat) {
        try {
            if (session.getSeats()[row][seat]) return false;
            session.bookSeat(row, seat);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}

public class lab_3 {
    private static AuthService auth = new AuthService();
    private static CinemaService cinemaService = new CinemaService();
    private static BookingService bookingService = new BookingService();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Админ\n2. Пользователь\n3. Выход");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        userLogin();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Выберите вариант 1-3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Выберите вариант 1-3");
                scanner.nextLine();
            }
        }
    }

    private static void adminLogin() {
        System.out.print("Логин: ");
        String user = scanner.nextLine();
        System.out.print("Пароль: ");
        String pass = scanner.nextLine();

        if (auth.authAdmin(user, pass)) {
            adminMenu();
        } else {
            System.out.println("Ошибка авторизации");
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nМеню админа:");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал");
            System.out.println("3. Добавить сеанс");
            System.out.println("4. Выход");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Название кинотеатра: ");
                        cinemaService.addCinema(scanner.nextLine());
                        break;
                    case 2:
                        try {
                            System.out.print("Кинотеатр: ");
                            String cinema = scanner.nextLine();
                            System.out.print("Номер зала: ");
                            int hall = scanner.nextInt();
                            System.out.print("Количество рядов: ");
                            int rows = scanner.nextInt();
                            System.out.print("Мест в ряду: ");
                            int seats = scanner.nextInt();
                            cinemaService.addHall(cinema, hall, rows, seats);
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный ввод чисел");
                            scanner.nextLine();
                        }
                        break;
                    case 3:
                        try {
                            System.out.print("Кинотеатр: ");
                            String cinema = scanner.nextLine();
                            System.out.print("Зал: ");
                            int hall = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Фильм: ");
                            String movie = scanner.nextLine();
                            System.out.print("Дата и время (dd.MM.yyyy HH:mm): ");
                            LocalDateTime start = LocalDateTime.parse(scanner.nextLine(), dtf);
                            System.out.print("Длительность (мин): ");
                            int duration = scanner.nextInt();
                            cinemaService.addSession(cinema, hall, movie, start, duration);
                        } catch (DateTimeParseException e) {
                            System.out.println("Неверный формат даты");
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный ввод чисел");
                            scanner.nextLine();
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Выберите вариант 1-4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Выберите вариант 1-4");
                scanner.nextLine();
            }
        }
    }

    private static void userLogin() {
        System.out.print("Логин: ");
        String user = scanner.nextLine();
        System.out.print("Пароль: ");
        String pass = scanner.nextLine();

        if (auth.authUser(user, pass)) {
            userMenu();
        } else {
            System.out.println("Ошибка авторизации");
        }
    }
    private static void userMenu() {
        while (true) {
            System.out.println("\nПользовательское меню:");
            System.out.println("1. Поиск сеанса");
            System.out.println("2. Бронирование");
            System.out.println("3. Выход");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        searchSession();
                        break;
                    case 2:
                        bookTicket();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Выберите вариант 1-3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Выберите вариант 1-3");
                scanner.nextLine();
            }
        }
    }

    private static void searchSession() {
        System.out.print("Введите название фильма: ");
        String movie = scanner.nextLine();

        for (Cinema c : cinemaService.getCinemas()) {
            for (Hall h : c.getHalls()) {
                for (Session s : h.getSessions()) {
                    if (s.getMovie().equalsIgnoreCase(movie)) {
                        System.out.printf("%s | Зал %d | %s\n",
                                c.getName(), h.getNumber(),
                                s.getStartTime().format(dtf)
                        );
                    }
                }
            }
        }
    }

    private static void bookTicket() {
        try {
            System.out.print("Кинотеатр: ");
            String cinema = scanner.nextLine();
            System.out.print("Номер зала: ");
            int hall = scanner.nextInt();
            System.out.print("Ряд: ");
            int row = scanner.nextInt();
            System.out.print("Место: ");
            int seat = scanner.nextInt();
            scanner.nextLine();

            for (Cinema c : cinemaService.getCinemas()) {
                if (c.getName().equals(cinema)) {
                    for (Hall h : c.getHalls()) {
                        if (h.getNumber() == hall) {
                            for (Session s : h.getSessions()) {
                                if (bookingService.bookSeat(s, row - 1, seat - 1)) {
                                    System.out.println("Место забронировано");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Ошибка бронирования");
        } catch (InputMismatchException e) {
            System.out.println("Неверный ввод чисел");
            scanner.nextLine();
        }
    }
}