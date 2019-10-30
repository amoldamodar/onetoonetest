package com.example.relationtest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DRIA_GUESTS")
@IdClass(Guest.GuestKey.class)
public class Guest {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ID_RESERVATION", referencedColumnName = "ID_RESERVATION", nullable = false, updatable = false),
			@JoinColumn(name = "ID_ITEM_RESERVATION", referencedColumnName = "ID_ITEM_RESERVATION", nullable = false, updatable = false)
	})
	@JsonBackReference
	private Applicant applicant;

	@Id
	@Column(name = "S_NUMBER", nullable = false, updatable = false)
	private Short seqNumber;

	@Column(name = "N_NAME")
	private String name;

	@Column(name = "CD_GENDER")
	private String gender;

	@Data
	static class GuestKey implements Serializable {
		private Applicant applicant;
		private Short seqNumber;
	}
}
