package dev.probal;

import dev.probal.model.Config;
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

    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline p = Pipeline.create(options);

        Config config = ConfigUtil.loadConfig(options.getConfigPath());

        PCollection<String> inputData = p.apply("Read", TextIO.read().from(config.getSource().getInputFilePath()));

        PCollectionTuple transformedTuple = inputData.apply("Transform", ParDo.of(new Transformation())
                .withOutputTags(Transformation.VALID_DATA_TAG, TupleTagList.of(Transformation.FAILURE_DATA_TAG)));

        transformedTuple.get(Transformation.VALID_DATA_TAG)
                .setCoder(StringUtf8Coder.of())
                .apply("Save Result", TextIO.write().to(config.getSink().getSuccessOutputFilePath()));

        transformedTuple.get(Transformation.FAILURE_DATA_TAG)
                .setCoder(StringUtf8Coder.of())
                .apply("Failure Result", TextIO.write().to(config.getSink().getFailureOutputFilePath()));

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
