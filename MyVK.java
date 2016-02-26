import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class MyVK{
	public static void main(String args[]) throws IOException{ 
		String userId = "1";
		
		
		
		String API_REQUEST = "https://api.vk.com/method/{METHOD_NAME}"
            + "?{PARAMETERS}"
           // + "&access_token={ACCESS_TOKEN}"
            + "&v=" + "5.45";   
		String method = "photos.getAlbums";
		Params params = Params.create()
	            .add("owner_id", userId);
		final String parameters = (params == null) ? "" : params.build();
		String requestUrl = API_REQUEST
            .replace("{METHOD_NAME}", method)
           // .replace("{ACCESS_TOKEN}", accessToken)
            .replace("{PARAMETERS}&", parameters);
        final StringBuilder result = new StringBuilder();
        final URL url = new URL(requestUrl);
        try (InputStream is = url.openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reader.lines().forEach(result::append);
        }
        System.out.println(result.toString());
	}
    private static class Params {
        
        public static Params create() {
            return new Params();
        }

        private final HashMap<String, String> params;
        
        private Params() {
            params = new HashMap<>();
        }
        
        public Params add(String key, String value) {
            params.put(key, value);
            return this;
        }
        
        public String build() {
            if (params.isEmpty()) return "";
            final StringBuilder result = new StringBuilder();
            params.keySet().stream().forEach(key -> {
                result.append(key).append('=').append(params.get(key)).append('&');
            });
            return result.toString();
        }
    }
}