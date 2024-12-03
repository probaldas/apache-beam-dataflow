package dev.probal.model;

import java.io.Serializable;
import java.util.Objects;

public class Config implements Serializable {

    private Source source;
    private Sink sink;

    public Config() {
    }

    public Config(Source source, Sink sink) {
        this.source = source;
        this.sink = sink;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Sink getSink() {
        return sink;
    }

    public void setSink(Sink sink) {
        this.sink = sink;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(source, config.source) && Objects.equals(sink, config.sink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, sink);
    }

    @Override
    public String toString() {
        return "Config{" +
                "source=" + source +
                ", sink=" + sink +
                '}';
    }
}
