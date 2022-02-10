package de.friday.controller.apimodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@ApiModel(value = "AddressRequest", description = "Contains the address")
public class AddressRequest implements Serializable {

    private static final long serialVersionUID = 1402303199473401187L;

    @NotNull
    @ApiModelProperty(value = "The address to be parsed", required = true)
    private String address;

    public AddressRequest(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
