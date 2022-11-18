package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper  extends HttpServletRequestWrapper {
    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter) {
        logger.info("In getParameterValues .. parameter .......");
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        logger.info("In getParameter .. parameter .......");
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        logger.info("In getParameter RequestWrapper ........ value .......");
        return cleanXSS(value);
    }

    public String getHeader(String name) {
        logger.info("In getHeader .. parameter .......");
        String value = super.getHeader(name);
        if (value == null)
            return null;
        logger.info("In getHeader RequestWrapper ........... value ....");
        return cleanXSS(value);
    }

    private String cleanXSS(String value) {
        // You'll need to remove the spaces from the html entities below
        logger.info("In cleanXSS RequestWrapper ..............." + value);
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        logger.info("Out cleanXSS RequestWrapper ........ value ......." + value);
        return value;
    }
}
