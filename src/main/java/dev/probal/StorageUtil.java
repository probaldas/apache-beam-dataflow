package dev.probal;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(StorageUtil.class);

    public static String readFileAsStringFromGCS(String projectId, String bucket, String filePath, String tempPath) {
        try {
            LOG.info("Project Id: {} | Bucket: {} | File Path: {} | Temp Path: {}", projectId, bucket, filePath, tempPath);

            Storage storage = StorageOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build()
                    .getService();
            Blob blob = storage.get(bucket, filePath);
            LOG.info("StorageUtil: get file from GCS: Blob size = {}", blob.getSize());
            return new String(blob.getContent());
        } catch (Exception e) {
            LOG.error("IO Exception occured while reading the file");
            LOG.error("Exception: {}", e.getMessage());
            return null;
        }
    }

}
