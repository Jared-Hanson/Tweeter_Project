package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.GetUserDataRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserDataResponse;

public class GetUserDataServiceTest {

    private GetUserDataRequest validRequest;
    private GetUserDataRequest invalidRequest;

    private GetUserDataResponse successResponse;
    private GetUserDataResponse failureResponse;

    private GetUserDataService getUserDataServiceSpy;

    @BeforeEach
    public void setup() {
        String goodAlias = "@FirstNameLastName";
        String badAlias = "Gregory";
        User currentUser = new User("FirstName", "LastName",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new GetUserDataRequest(goodAlias);
        invalidRequest = new GetUserDataRequest(badAlias);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new GetUserDataResponse(currentUser);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getUserFromAlias(validRequest.getAlias())).thenReturn(successResponse);

        failureResponse = new GetUserDataResponse(false, null);
        Mockito.when(mockServerFacade.getUserFromAlias(invalidRequest.getAlias())).thenReturn(failureResponse);

        getUserDataServiceSpy = Mockito.spy(new GetUserDataService());
        Mockito.when(getUserDataServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testGetUserData_validRequest_correctResponse() throws IOException {
        GetUserDataResponse response = getUserDataServiceSpy.getUserData(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetUserData_validRequest_loadsProfileImages() throws IOException {
        GetUserDataResponse response = getUserDataServiceSpy.getUserData(validRequest);

        Assertions.assertNotNull(response.getUser().getImageBytes());
    }

    @Test
    public void testGetUserData_invalidRequest_returnsNoData() throws IOException {
        GetUserDataResponse response = getUserDataServiceSpy.getUserData(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
