package com.example.relationtest;

import com.example.relationtest.beans.ApplicantDto;
import com.example.relationtest.beans.ItemDto;
import com.example.relationtest.beans.ReservationDto;
import com.example.relationtest.entity.Applicant;
import com.example.relationtest.entity.Guest;
import com.example.relationtest.entity.Item;
import com.example.relationtest.entity.Reservation;
import com.example.relationtest.entity.ReservationRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reservation")
public class ReservationController {

	private final ReservationRepository reservationRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation createReservation(@NotNull @RequestBody ReservationDto reservationDto) {

		Reservation reservation = new Reservation();
		Set<Item> items = new HashSet<>();
		Item item = new Item();
		item.setItemReservationId(1);
		Applicant applicant = new Applicant();
		Set<Guest> guests = reservationDto.getItems().stream()
				.map(ItemDto::getApplicant)
				.map(ApplicantDto::getGuests)
				.flatMap(Collection::stream)
				.map(guestDto -> {
					Guest guest = new Guest();
					guest.setGender(guestDto.getGender());
					guest.setName(guestDto.getName());
					guest.setSeqNumber(guestDto.getSeqNumber());
					return guest;
				})
				.collect(Collectors.toSet());
		applicant.setGuests(guests);
		item.setApplicant(applicant);
		items.add(item);
		reservation.setItems(items);

		//back reference
		reservation.getItems().forEach(i -> {
			i.setReservation(reservation);
			i.getApplicant().setItem(i);
			i.getApplicant().getGuests().forEach(g -> g.setApplicant(i.getApplicant()));
		});

		return reservationRepository.save(reservation);
	}

	@GetMapping("/findByReservationId")
	public Reservation findByReservationId(@NotNull @NotEmpty @RequestParam String reservationId) {
		return reservationRepository.findById(reservationId).get();
	}
}
