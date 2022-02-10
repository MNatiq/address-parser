package de.friday.controller;

import de.friday.controller.apimodel.AddressRequest;
import de.friday.service.AddressParserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static de.friday.TestInputs.SIMPLE_ADDRESS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddressParserControllerTest {

    @InjectMocks
    private AddressParserController parseAddressController;

    @Mock
    private AddressParserService addressParserService;

    @Test
    public void parse() {
        parseAddressController.parse(new AddressRequest(SIMPLE_ADDRESS));
        verify(addressParserService, times(1)).parseAddress(SIMPLE_ADDRESS);
    }

}
