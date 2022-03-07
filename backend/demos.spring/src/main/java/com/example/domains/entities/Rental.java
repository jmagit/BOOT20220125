package com.example.domains.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.example.domains.core.entities.EntityBase;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * The persistent class for the rental database table.
 * 
 */
@Entity
@Table(name="rental")
@NamedQuery(name="Rental.findAll", query="SELECT r FROM Rental r")
public class Rental extends EntityBase<Rental> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id")
	private int rentalId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="rental_date")
	@NotNull
	@PastOrPresent
	private Date rentalDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="return_date")
	@PastOrPresent
	private Date returnDate;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="rental", cascade = CascadeType.ALL, orphanRemoval = true)
	@Valid
	private List<Payment> payments;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="inventory_id")
	private Inventory inventory;

	//bi-directional many-to-one association to Staff
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;

	@Column(name="last_update")
	@Generated(value = GenerationTime.ALWAYS)
	private Timestamp lastUpdate;

	public Rental() {
		super();
		payments = new ArrayList<Payment>();
	}

	public Rental(int rentalId) {
		this();
		this.rentalId = rentalId;
	}

	public Rental(int rentalId, @NotNull @PastOrPresent Date rentalDate, Inventory inventory, Customer customer,
			Staff staff, @PastOrPresent Date returnDate) {
		this();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.inventory = inventory;
		this.customer = customer;
		this.staff = staff;
		this.returnDate = returnDate;
	}

	public int getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getRentalDate() {
		return this.rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public List<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setRental(this);
		payment.setCustomer(getCustomer());

		return payment;
	}
	public Payment addPayment(BigDecimal amount, Date paymentDate, Staff staff) {
		return addPayment(new Payment(0, 
				amount, 
				paymentDate, 
				staff));
	}
	public Payment addPayment(BigDecimal amount, Date paymentDate, int staffId) {
		return addPayment(amount, paymentDate, new Staff(staffId));
	}
	public Payment addPayment(BigDecimal amount, Date paymentDate) {
		return addPayment(amount, paymentDate, staff);
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setRental(null);

		return payment;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rentalId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rental))
			return false;
		return rentalId == ((Rental) obj).rentalId;
	}
	
	
	public Payment facturar() {
		if(returnDate != null)
			throw new IllegalStateException("Pelicula ya devuelta.");
		return addPayment(inventory.getFilm().getRentalRate(), new Date());
	}
	
	public Optional<Payment> devolver(int staffId) {
		return devolver(new Staff(staffId));
	}
	public Optional<Payment> devolver(Staff staff) {
		if(returnDate != null)
			throw new IllegalStateException("Pelicula ya devuelta.");
		returnDate = new Date();
		var periodo = 
				rentalDate.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate()
			      .until(returnDate.toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDate(), 
					      ChronoUnit.DAYS
			    		);
		if(periodo > inventory.getFilm().getRentalDuration()) {
			var penalizacion = Math.floor((double)periodo / inventory.getFilm().getRentalDuration());
			var multa = inventory.getFilm().getRentalRate().multiply(BigDecimal.valueOf(penalizacion)).doubleValue();
			return Optional.of(addPayment(BigDecimal.valueOf(multa), new Date(), staff));
		}	
		return Optional.empty();
	}
	
	public Payment multarPorPerdida(int staffId) {
		return multarPorPerdida(new Staff(staffId));
	}
	public Payment multarPorPerdida(Staff staff) {
		if(returnDate != null)
			throw new IllegalStateException("Pelicula ya devuelta.");
		returnDate = new Date();
		return addPayment(inventory.getFilm().getReplacementCost(), returnDate, staff);
	}

}