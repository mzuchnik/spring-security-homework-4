package pl.mzuchnik.springsecurityhomework4;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperUltraMegaPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String wzorzec = "Ala ma kota a kot ma ale,unlucky";
        String haslo = rawPassword.toString();

        byte[] hasloBytes = haslo.getBytes();

        long hasloSum = 0l;

        for (byte hasloByte : hasloBytes) {
            hasloSum += hasloByte;
        }

        if(hasloSum%2==0)
            hasloSum++;

        char[] wzorzecBytes = wzorzec.toCharArray();

        for (int i = 0; i < wzorzecBytes.length+hasloBytes.length; i++) {
            wzorzecBytes[i%32] &= (hasloBytes[i%haslo.length()] ^ hasloSum | haslo.length()) +16;
        }

        String hashcoded = String.valueOf(wzorzecBytes);

        return hashcoded;
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
