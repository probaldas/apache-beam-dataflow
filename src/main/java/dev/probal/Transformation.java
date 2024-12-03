package dev.probal;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.TupleTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transformation extends DoFn<String, String> {

    private static final Logger LOG = LoggerFactory.getLogger(Transformation.class);

    public static TupleTag<String> VALID_DATA_TAG = new TupleTag<>();
    public static TupleTag<String> FAILURE_DATA_TAG = new TupleTag<>();

    @ProcessElement
    public void processElement(ProcessContext c) {
        String row = c.element();
        LOG.info("Row: " + row);

        String[] items = row.split(",");

        try {
            int sqft = Integer.parseInt(items[1].trim());
            int price = Integer.parseInt(items[6].trim());
            float pricePerSqft = price/sqft;

            String message = "Zip Code: " + items[4].trim() + " house cost is $" + String.valueOf(pricePerSqft) + " per sqft";
            LOG.info(message);
            c.output(VALID_DATA_TAG, message);
        } catch (Exception e) {
            String message = "Zip Code: " + items[4].trim() + " house\'s cost is UNKNOWN";
            LOG.info(message);
            c.output(FAILURE_DATA_TAG, message);
        }
    }
}
