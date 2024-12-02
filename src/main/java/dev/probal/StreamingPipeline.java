package dev.probal;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

public class StreamingPipeline {
    private static final String INPUT_BUCKET = "gs://apache-beam-samples/shakespeare/kinglear.txt";
    private static final String DESTINATION_BUCKET = "gs://beam-prj-data/beam-prj-data/dataflow/kinglear.txt";

    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline p = Pipeline.create(options);

        PCollection<String> input = p.apply("Read", TextIO.read().from(INPUT_BUCKET));
        PDone write = input.apply("Write", TextIO.write().to(DESTINATION_BUCKET));

        PipelineResult result = p.run();
        try {
            result.getState();
            result.waitUntilFinish();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
