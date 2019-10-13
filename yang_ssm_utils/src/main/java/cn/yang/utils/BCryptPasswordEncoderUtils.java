package cn.yang.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sound.midi.Soundbank;

/***
 * @ClassName: BCryptPasswordEncoder
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/10/1211:42
 * @version : V1.0
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String s = encodePassword("tom");
        //$2a$10$5kKe0GDqm.3mgn0Jv4yO5eoiWeTP.FzjtdAdc7h67g9qPtOsLEYfq
        //$2a$10$E23gOOHVZE5abr3v9m2kZ.YFK35YFgBzZZzkqRWFdWwlsAUQUKBA2
        System.out.println(s);
    }
}
