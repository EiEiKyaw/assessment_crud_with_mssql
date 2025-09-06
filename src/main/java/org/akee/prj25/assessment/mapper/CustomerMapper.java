package org.akee.prj25.assessment.mapper;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

	public static CustomerResponseDto toResponseDto(Customer entity) {

		CustomerResponseDto dto = new CustomerResponseDto();
		dto.setCustomerId(entity.getId());
		dto.setName(entity.getName());
		dto.setWalletBalance(entity.getWalletBalance());
		dto.setVersion(entity.getVersion());

		return dto;

	}
}