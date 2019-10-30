package com.example.relationtest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DR_ITEM_RESERVATIONS")
@IdClass(Item.ItemKey.class)
public class Item {
	@Id
	@Column(name = "ID_ITEM_RESERVATION", nullable = false, updatable = false)
	private long itemReservationId;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RESERVATION", referencedColumnName = "ID_RESERVATION", nullable = false, updatable = false)
	@JsonBackReference
	private Reservation reservation;

	@OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Applicant applicant;

	@Data
	static class ItemKey implements Serializable {
		private Reservation reservation;
		private long itemReservationId;
	}
}
