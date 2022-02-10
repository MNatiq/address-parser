package de.friday.controller;

import de.friday.controller.apimodel.AddressRequest;
import de.friday.service.AddressParserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/address-parser")
public class AddressParserController {

  private final AddressParserService addressParserService;

  @PostMapping(value = "/parse")
  @ApiOperation(
      value = "Parse an Address with concatenated street names and numbers into a normalized Address")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Ok"),
    @ApiResponse(code = 400, message = "Bad Request")
  })
  public String[] parse(@RequestBody @Valid AddressRequest addressRequest) {
    return addressParserService.parseAddress(addressRequest.getAddress());
  }
}
