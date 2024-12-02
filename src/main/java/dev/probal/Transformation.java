package dev.probal;

import org.apache.beam.sdk.transforms.DoFn;

public class Transformation extends DoFn<String, String> {
    @ProcessElement
    public void processElement(ProcessContext c) {
        String line = c.element();
        line = line.toUpperCase();

        c.output(line);
    }
}
