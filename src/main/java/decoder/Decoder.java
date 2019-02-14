package decoder;

import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Decoder {

	private static String token;
	private static String value;
	private static DecodedJWT jwt;
	private static Map<String, Claim> claims;
	
	public static void main (String [ ] args) {
		token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		decoderFunction(token);
	}
	
	public static void decoderFunction(String token) {
		try {
			jwt = JWT.decode(token);
			claims = jwt.getClaims();

			System.out.println(jwt.getAlgorithm());

			for (Map.Entry<String, Claim> entry : claims.entrySet()){
				if(entry.getKey().equals("iat"))
					value = entry.getValue().asInt().toString();
				else
					value = entry.getValue().asString();

				System.out.println(String.format("%s : %s", entry.getKey(),value));
			}

		} catch (JWTDecodeException exception){
			System.out.println("Invalid Token");
			exception.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}
}