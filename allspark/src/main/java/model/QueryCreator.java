package model;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by Eric on 4/29/16.
 */
public class QueryCreator {

    public String createQuery(String filename) {
        String blah = this.getClass().getResource("").getPath();
        InputStream queryStream = this.getClass().getResourceAsStream("sql/" + filename);
        if (queryStream == null) {
            throw new Error(String.format("File not found: %s", filename));
        }
        LineInputStream queryLineStream = new LineInputStream(queryStream);
        StringBuilder queryStringBuilder = new StringBuilder();
        String queryString;
        while(true) {
            try {
                String queryLine = queryLineStream.readLine();
                if (queryLine == null) {
                    break;
                }
                queryStringBuilder.append(queryLine).append('\n');
            } catch (IOException ioe) {
                throw new Error(ioe);
            }
        }
        queryString = queryStringBuilder.toString();
        return queryString;
    }

    public String createQueryWithReplacements(String fileName, Map<String, String> replacements) {
        String blah = this.getClass().getResource("").getPath();
        InputStream queryStream = this.getClass().getResourceAsStream("sql/" + fileName);
        if (queryStream == null) {
            throw new Error(String.format("File not found: %s", fileName));
        }
        LineInputStream queryLineStream = new LineInputStream(queryStream);
        StringBuilder queryStringBuilder = new StringBuilder();
        String queryString;
        while(true) {
            try {
                String queryLine = queryLineStream.readLine();
                if (queryLine == null) {
                    break;
                }
                for (String replacementTarget: replacements.keySet()) {
                    if (queryLine.contains(replacementTarget)) {
                        queryLine = queryLine.replace(replacementTarget, replacements.get(replacementTarget));
                    }
                }
                queryStringBuilder.append(queryLine).append('\n');
            } catch (IOException ioe) {
                throw new Error(ioe);
            }
        }
        queryString = queryStringBuilder.toString();
        return queryString;
    }
}
