package com.financialhouse.reporting.util;

import java.net.URI;
import java.net.URISyntaxException;

public class URICreator {

    public static URI createURLWithPort(String uri, int port) throws URISyntaxException {
        return new URI("http://localhost:" + port + uri);
    }
}
