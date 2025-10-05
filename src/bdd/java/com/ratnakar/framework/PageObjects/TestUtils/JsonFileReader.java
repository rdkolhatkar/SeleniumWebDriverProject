package com.ratnakar.framework.PageObjects.TestUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonFileReader {

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        // Reading Json file to String format with "org.apache.commons.io"
        String jsonFileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        // Now we have to convert this string to HashMap
        // This could be done with "com.fasterxml.jackson.databind"
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonFileContent, new TypeReference<List<HashMap<String, String>>>(){
        });
        return data;
    }
}
