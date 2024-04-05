package testdata;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthData {
    private final String username;
    private final String password;
}
