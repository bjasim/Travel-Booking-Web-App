package com.d288.bakr.entities;

import jakarta.persistence.*;
// import lombok.Getter; // Removed
// import lombok.Setter; // Removed
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name="cart_items")
// @Getter // Removed
// @Setter // Removed
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id")
    )
    private Set<Excursion> excursions = new HashSet<>();

    public CartItem() {
        this.excursions = new HashSet<>();
    }

    public void addExcursion(Excursion excursion) {
        if (excursion != null) {
            if (excursions == null) {
                excursions = new HashSet<>();
            }
            excursions.add(excursion);
        }
    }

    public void removeExcursion(Excursion excursion) {
        if (excursion != null && excursions != null) {
            excursions.remove(excursion);
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Set<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(Set<Excursion> excursions) {
        this.excursions = excursions != null ? excursions : new HashSet<>();
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", create_date=" + create_date +
                ", last_update=" + last_update +
                ", cart=" + (cart != null ? cart.getId() : "null") +
                ", vacation=" + (vacation != null ? vacation.getId() : "null") +
                ", excursions.size=" + (excursions != null ? excursions.size() : "null") +
                '}';
    }
}
