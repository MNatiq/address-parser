package de.friday.controller;

import de.friday.exception.NullOrEmptyAddressException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneralExceptionHandlerTest {
  private final GeneralExceptionHandler generalExceptionHandler = new GeneralExceptionHandler();

  @Test
  public void handleException() {
    ErrorResponse errorResponse =
        generalExceptionHandler.handleException(
            new MockHttpServletRequest(), new NullOrEmptyAddressException());
    assertThat(errorResponse.getMessage())
        .isEqualTo("Address is null or empty and can not be parsed");
  }
}
