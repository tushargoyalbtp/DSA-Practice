package LiveShowBookingSystem;

abstract class User {
    protected String userName;
    protected String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
