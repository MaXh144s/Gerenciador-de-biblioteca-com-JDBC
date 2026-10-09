package src.model.entities;

public class Book {
    
    private Integer id;
    private String name;
    private Integer PublishedYear;
    private Double price;

    private Author author;

    public Book(){}

    public Book(Integer id, String name, Integer publishedYear, Double price, Author author) {
        this.id = id;
        this.name = name;
        PublishedYear = publishedYear;
        this.price = price;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPublishedYear() {
        return PublishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        PublishedYear = publishedYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", PublishedYear=" + PublishedYear + ", price=" + price
                + ", author=" + author + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    

}
