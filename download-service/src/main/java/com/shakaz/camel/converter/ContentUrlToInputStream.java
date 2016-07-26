package com.shakaz.camel.converter;

import com.shakaz.camel.model.ContentUrl;
import org.apache.camel.CamelContext;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class ContentUrlToInputStream implements TypeConverters {

    @Autowired
    public ContentUrlToInputStream(CamelContext camelContext) {
        camelContext.getTypeConverterRegistry().addTypeConverters(this);
    }

    @Converter
    public InputStream convertTo(ContentUrl contentUrl) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            return new URL(contentUrl.getContentUrl()).openStream();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
