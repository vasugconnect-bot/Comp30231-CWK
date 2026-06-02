package cyclenest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ORSClient {

    public static double getDistance(
            double lon1, double lat1,
            double lon2, double lat2) throws Exception {

        String urlStr =
            "https://api.openrouteservice.org/v2/directions/driving-car"
          + "?api_key=eyJvcmciOiI1YjNjZTM1OTc4NTExMTAwMDFjZjYyNDgiLCJpZCI6IjU4N2ZjYWI5ZmY4NzRiYzU4MGZmNTUxZWUzZTYwNDlmIiwiaCI6Im11cm11cjY0In0="
          + "&start=" + lon1 + "," + lat1
          + "&end=" + lon2 + "," + lat2;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br =
            new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        JSONObject json = new JSONObject(sb.toString());

        return json
                .getJSONArray("features")
                .getJSONObject(0)
                .getJSONObject("properties")
                .getJSONObject("summary")
                .getDouble("distance");
    }
}
