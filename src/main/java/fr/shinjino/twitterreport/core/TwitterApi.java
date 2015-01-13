package fr.shinjino.twitterreport.core;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Shinjino <shinjino@outlook.fr>
 * @since 13/01/15.
 */
public class TwitterApi {


    public TwitterApi() {
        this.cookieStore = new BasicCookieStore();
        this.httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

    }

    public void connection(String login, String password) throws Exception {
        CloseableHttpResponse response = null;
        String pageContent = null;

        try {
            HttpUriRequest authentificationRequest = RequestBuilder.post()
                    .setUri(new URI("https://twitter.com/sessions"))
                    .addParameter("session[username_or_email]", login)
                    .addParameter("session[password]", password)
                    .addParameter("authenticity_token", this.getAuthenticityToken())
                    .build();

            response = this.httpClient.execute(authentificationRequest);
            HttpEntity entity = response.getEntity();
            pageContent = IOUtils.toString(entity.getContent());
            response.close();

        } catch (Exception e) {
            throw new Exception("Can't connect");
        }
        System.out.print(pageContent);

        if( pageContent.contains("error") ){
            throw new Exception("Bad credentials");
        }
    }

    public boolean reportUser(String screenName) {
        CloseableHttpResponse response = null;
        boolean success = false;

        try {
            HttpUriRequest reportRequest = RequestBuilder.post()
                    .setUri(new URI("https://twitter.com/i/user/report_spam"))
                    .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:34.0) Gecko/20100101 Firefox/34.0")
                    .addParameter("report_type", "annoying")
                    .addParameter("screen_name", screenName)
                    .addParameter("block_user", "false")
                    .addParameter("authenticity_token", this.getAuthenticityToken())
                    .build();

            response = this.httpClient.execute(reportRequest);
            success = (response.getStatusLine().getStatusCode() == 200);
            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Load authenticity_token.
     */
    public void loadAuthenticityToken() {
        HttpGet httpget = new HttpGet("https://twitter.com/");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String pageContent = IOUtils.toString(entity.getContent());
            response.close();

            // Extract value from <INPUT>
            Document document = Jsoup.parse(pageContent);
            for (Element nodeInput : document.select("form input")) {
                if (
                        nodeInput.attr("name").contentEquals("authenticity_token")
                                && nodeInput.attr("value").length() == 40
                        ) {
                    this.authenticity_token = nodeInput.attr("value");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String authenticity_token = null;

    private CloseableHttpClient httpClient;
    private BasicCookieStore cookieStore;

    public String getAuthenticityToken() {
        if (authenticity_token == null) {
            this.loadAuthenticityToken();
        }
        return authenticity_token;
    }
}
