package adalpari.github.com.kataloginlogout;

import android.support.annotation.Nullable;

/**
 * Created by Adalberto Plaza on 20/09/2018.
 */
public class LoginResult {

    public final static String NOT_ALLOWED_CHARACTER_MESSAGE = "Only text characters allowed";
    public final static String BAD_CREDENTIALS_MESSAGE = "Wrong credential";

    private boolean isError;
    private String error;

    public LoginResult(boolean isError, @Nullable String error) {
        this.isError = isError;
        this.error = error;
    }

    public boolean isError() {
        return isError;
    }

    public String getError() {
        return error;
    }
}
