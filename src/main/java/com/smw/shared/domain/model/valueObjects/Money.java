package com.smw.shared.domain.model.valueObjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Money {

    @NotNull
    private Double mount;

    @NotNull
    @NotBlank
    private String currency;
    
}
