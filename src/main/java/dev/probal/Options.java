package dev.probal;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;

public interface Options extends DataflowPipelineOptions {

    String getConfigPath();

    void setConfigPath(String config);

}
