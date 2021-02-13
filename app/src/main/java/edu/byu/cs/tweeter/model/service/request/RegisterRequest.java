package edu.byu.cs.tweeter.model.service.request;

/**
 * Contains all the information needed to make a register request.
 */
public class RegisterRequest {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private byte [] imageBytes;

    /**
     * Creates an instance.
     *
     * @param firstName the first name of the user to be registered.
     * @param lastName the last name of the user to be registered.
     * @param username the username of the user to be registered.
     * @param password the password of the user to be registered.
     * @param imageBytes the bytes of the profile image of the user to be registered.
     */
    public RegisterRequest(String firstName, String lastName, String username, String password, byte[] imageBytes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.imageBytes = imageBytes;
    }

    /**
     * Returns the username of the user to be registered by this request.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user to be registered by this request.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the first name of the user to be registered by this request.
     *
     * @return the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the user to be registered by this request.
     *
     * @return the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the bytes of the profile image of the user to be registered by this request.
     *
     * @return the bytes of the profile image.
     */
    public byte[] getImageBytes() {
        return imageBytes;
    }
}
