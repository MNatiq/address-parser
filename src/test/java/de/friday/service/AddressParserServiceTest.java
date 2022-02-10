package de.friday.service;

import de.friday.exception.NullOrEmptyAddressException;
import de.friday.service.impl.AddressWithNumberTextParser;
import de.friday.service.impl.InternationalAddressParser;
import de.friday.service.impl.SimpleAddressParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static de.friday.TestInputs.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class AddressParserServiceTest {

  private AddressParserService addressParserService;

  private AddressParserFactory addressParserFactory =
      new AddressParserFactory(
          new AddressWithNumberTextParser(),
          new InternationalAddressParser(),
          new SimpleAddressParser());

  @BeforeEach
  public void setUp() throws Exception {
    addressParserService = new AddressParserService(addressParserFactory);
  }

  @Test
  public void shouldParseCorrectlySimpleCases() {
    String adress = WINTERALLEE_ADDRESS;
    String[] adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Winterallee", "3");

    adress = MUSTERSTRASSE_ADDRESS;
    adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Musterstrasse", "45");

    adress = BLAUFELDWEG_ADDRESS;
    adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Blaufeldweg", "123B");
  }

  @Test
  public void shouldParseCorrectlyMoreComplicatedCases() {
    String adress = AM_BACHLE_ADDRESS;
    String[] adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Am Bächle", "23");

    adress = SIMPLE_ADDRESS;
    adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Auf der Vogelwiese", "23 b");
  }

  @Test
  public void shouldParseCorrectlyOtherCountries() {
    String adress = RUE_DE_LA_REVOLUTION_ADDRESS;
    String[] adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("rue de la revolution", "4");

    adress = BROADWAY_AV_ADDRESS;
    adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Broadway Av", "200");

    adress = CALLE_ADUANNA;
    adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Calle Aduana", "29");

    adress = TEXT_NUMBER_ADDRESS;
    adressParsed = addressParserService.parseAddress(adress);
    assertThat(adressParsed).hasSize(2).containsExactly("Calle 39", "No 1540");
  }

  @Test
  public void shouldThrowExceptionWhenAddressIsNull() {
    Throwable thrown = catchThrowable(() -> addressParserService.parseAddress(null));
    assertThat(thrown).isExactlyInstanceOf(NullOrEmptyAddressException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }

  @Test
  public void shouldThrowExceptionWhenAddressIsEmpty() {
    Throwable thrown = catchThrowable(() -> addressParserService.parseAddress(""));
    assertThat(thrown).isExactlyInstanceOf(NullOrEmptyAddressException.class);
    assertThat(thrown).hasMessageContaining("Address is null or empty and can not be parsed");
  }
}
