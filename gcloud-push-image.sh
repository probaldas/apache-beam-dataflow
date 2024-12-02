gcloud dataflow flex-template build gs://beam-prj-data/templates/dataflow-template.json --image-gcr-path="us-east1-docker.pkg.dev/probal-dev/dev-repo/dataflow:latest" --sdk-language=JAVA --flex-template-base-image=JAVA17 --jar="/Users/probal/Develop/Learning/ApacheBeam/apache-beam-dataflow/build/libs/apache-beam-dataflow-1.0-SNAPSHOT.jar" --env=FLEX_TEMPLATE_JAVA_MAIN_CLASS="dev.probal.StreamingPipeline"