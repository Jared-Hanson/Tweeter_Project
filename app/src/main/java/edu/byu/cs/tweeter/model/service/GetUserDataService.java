package edu.byu.cs.tweeter.model.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.GetUserDataRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserDataResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class GetUserDataService {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public GetUserDataResponse getUserData(GetUserDataRequest getUserDataRequest) throws IOException {
        ServerFacade serverFacade = getServerFacade();

        GetUserDataResponse getUserDataResponse = serverFacade.getUserFromAlias(getUserDataRequest.getAlias());

        if(getUserDataResponse.isSuccess() && getUserDataResponse.getUser().getImageBytes() == null) {
            loadImage(getUserDataResponse.getUser());
        }
        return getUserDataResponse;
    }


    private void loadImage(User user) throws IOException {
        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
