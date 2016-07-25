package com.shakaz.camel.converter;

import com.shakaz.camel.model.TransferAsset;
import org.apache.camel.CamelContext;
import org.apache.camel.Converter;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.TypeConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class TransferAssetToInputStream implements TypeConverters {

    @Autowired
    public TransferAssetToInputStream(CamelContext camelContext) {
        camelContext.getTypeConverterRegistry().addTypeConverters(this);
    }

    @Converter
    public InputStream convertTo(TransferAsset transferAsset) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(transferAsset);
            oos.flush();
            oos.close();
        } catch (IOException ioe) {
            throw new RuntimeCamelException(ioe);
        }

        return new ByteArrayInputStream(baos.toByteArray());

    }
}
