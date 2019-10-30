package com.example.relationtest.beans;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ApplicantDto {
	private List<GuestDto> guests = new ArrayList<>();
}
