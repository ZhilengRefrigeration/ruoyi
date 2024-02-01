package ryas.test;

import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.uuid.IdUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alan Scipio
 * created on 2024/2/1
 */
public class TokenTest {

    @Test
    public void createToken() {
        int userId = 1;
        String userName = "管理员";

        String userKey = IdUtils.fastUUID();
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.USER_KEY, userKey);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        String token = JwtUtils.createToken(claimsMap);
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2tleSI6IjhkZDc5ZDcxLTg3MGYtNDliMy1hNGVkLTQxNzI5OTdmNTcxNCIsInVzZXJuYW1lIjoiYWRtaW4ifQ.b5230z65JXGzz-JpIa_1fSkDs_gct4nbuQ0wexW78POxixTzyeBAksh1pUUU9hEPW2_cIpK1MSF6ypi9RhxtxQ";

        Claims claims = JwtUtils.parseToken(token);
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
