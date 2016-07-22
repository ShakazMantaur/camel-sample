package com.shakaz.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class Processors {

    public static Processor testProcessor = new Processor() {

        @Override
        public void process(Exchange exchange) throws Exception {
            int i = 0;
        }
    };
}
