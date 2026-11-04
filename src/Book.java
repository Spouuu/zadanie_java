public class Book {
    private String title;
    private String author;
    private Integer publicationYear;
    private boolean isBorrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
    }
    public void displayInfo(){
        System.out.println(title);
        System.out.println(author);
        System.out.println(publicationYear);
        System.out.println(isBorrowed);
    }

    public void borrowBook(){

    }
}
