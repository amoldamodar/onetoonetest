package com.example.relationtest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "DRI_APPLICANT")
@IdClass(Applicant.ApplicantKey.class)
public class Applicant {
	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ID_RESERVATION", referencedColumnName = "ID_RESERVATION", nullable = false, updatable = false),
			@JoinColumn(name = "ID_ITEM_RESERVATION", referencedColumnName = "ID_ITEM_RESERVATION", nullable = false, updatable = false)
	})
	@JsonBackReference
	private Item item;

	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<Guest> guests = new HashSet<>();

	@Data
	static class ApplicantKey implements Serializable {
		private Item item;
	}
}
