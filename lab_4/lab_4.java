import java.util.*;

class Book {
    private final String title;
    private final String author;
    private final int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Книга: {название='" + title + "', автор='" + author + "', год=" + year + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}

class Library {
    private final List<Book> books = new ArrayList<>();
    private final Set<String> uniqueAuthors = new HashSet<>();
    private final Map<String, Integer> authorBookCount = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        uniqueAuthors.add(book.getAuthor());
        authorBookCount.put(book.getAuthor(),
                authorBookCount.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            String author = book.getAuthor();
            int newCount = authorBookCount.get(author) - 1;

            if (newCount == 0) {
                authorBookCount.remove(author);
            } else {
                authorBookCount.put(author, newCount);
            }

            if (!hasBooksByAuthor(author)) {
                uniqueAuthors.remove(author);
            }
        }
    }

    private boolean hasBooksByAuthor(String author) {
        for (Book b : books) {
            if (b.getAuthor().equals(author)) return true;
        }
        return false;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) result.add(book);
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) result.add(book);
        }
        return result;
    }

    public void printAllBooks() {
        books.forEach(System.out::println);
    }

    public void printUniqueAuthors() {
        uniqueAuthors.forEach(System.out::println);
    }

    public void printAuthorStatistics() {
        authorBookCount.forEach((author, count) ->
                System.out.println(author + ": " + count + " книги"));
    }
}

// LibraryTest
public class lab_4 {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Дубровский", "Александр Сергеевич Пушкин", 1841);
        Book book2 = new Book("Пиковая дама", "Александр Сергеевич Пушкин", 1834);
        Book book3 = new Book("Преступление и наказание", "Федор Михайлович Достоевский", 1866);
        Book book4 = new Book("Кавказский пленник", "Лев Николаевич Толстой", 1872);
        Book book5 = new Book("Война и мир", "Лев Николаевич Толстой", 1867);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        System.out.println("Все книги");
        library.printAllBooks();

        System.out.println("\nУникальные авторы:");
        library.printUniqueAuthors();

        System.out.println("\nСтатистика авторов:");
        library.printAuthorStatistics();

        System.out.println("\nКниги Пушкина:");
        library.findBooksByAuthor("Александр Сергеевич Пушкин").forEach(System.out::println);

        System.out.println("\nКниги выпущенные в 1841:");
        library.findBooksByYear(1841).forEach(System.out::println);

        library.removeBook(book5);
        System.out.println("\nПосле удаления книги:");
        library.printAllBooks();

        System.out.println("\nОбновленная статистика авторов:");
        library.printAuthorStatistics();
    }
}