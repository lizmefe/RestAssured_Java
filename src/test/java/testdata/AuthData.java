package testdata;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthData {
    private String username;
    private String password;
}
