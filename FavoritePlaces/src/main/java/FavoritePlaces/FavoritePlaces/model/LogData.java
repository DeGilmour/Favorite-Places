package FavoritePlaces.FavoritePlaces.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LogData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zip_code", referencedColumnName = "id", nullable = false)
    private FavoriteZipCode favoriteZipCode;

    @Column(nullable = false)
    private LocalDate date;

    public LogData() {
        
    }

    @PrePersist
    protected void onCreate() {
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FavoriteZipCode getFavoriteZipCode() {
        return favoriteZipCode;
    }

    public void setFavoriteZipCode(FavoriteZipCode favoriteZipCode) {
        this.favoriteZipCode = favoriteZipCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
