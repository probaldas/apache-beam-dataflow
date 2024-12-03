package dev.probal;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.probal.model.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);

    public static Config loadConfig(String configPath) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String[] configPathArray = configPath.split(":");
            LOG.info("Loading config file from: {}|{}|{}", configPathArray[0], configPathArray[1], configPathArray[2]);
            String configStr = StorageUtil.readFileAsStringFromGCS(
                    configPathArray[0],     // projectId
                    configPathArray[1],     // bucket
                    configPathArray[2],     // filePath
                    "/tmp/config.json"
            );
            Config config = mapper.readValue(configStr, Config.class);
            LOG.info("Base Config: {}", mapper.writeValueAsString(config));
            return config;
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

}
