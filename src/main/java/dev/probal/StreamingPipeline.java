package dev.probal;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.TupleTagList;

public class StreamingPipeline {
    private static final String BUCKET_PATH = "gs://beam-prj-data/beam-prj-data/dataflow/";
    private static final String INPUT_BUCKET_FILE_NAME = "zillow.csv";
    private static final String VALID_BUCKET_FILE_NAME = "zillow-results.txt";
    private static final String FAILURE_BUCKET_FILE_NAME = "zillow-unknown.txt";

    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline p = Pipeline.create(options);

        PCollection<String> inputData = p.apply("Read", TextIO.read().from(BUCKET_PATH + INPUT_BUCKET_FILE_NAME));

        PCollectionTuple transformedTuple = inputData.apply("Transform", ParDo.of(new Transformation())
                .withOutputTags(Transformation.VALID_DATA_TAG, TupleTagList.of(Transformation.FAILURE_DATA_TAG)));

        transformedTuple.get(Transformation.VALID_DATA_TAG)
                .setCoder(StringUtf8Coder.of())
                .apply("Save Result", TextIO.write().to(BUCKET_PATH + VALID_BUCKET_FILE_NAME));

        transformedTuple.get(Transformation.FAILURE_DATA_TAG)
                .setCoder(StringUtf8Coder.of())
                .apply("Failure Result", TextIO.write().to(BUCKET_PATH + FAILURE_BUCKET_FILE_NAME));

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
