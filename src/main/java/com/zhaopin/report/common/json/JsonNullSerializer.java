package com.zhaopin.report.common.json;


import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * <P>
 * Description：
 * </P>
 * 
 * @version 1.0.0
 */
public class JsonNullSerializer extends JsonSerializer<Object>
{
    public void serialize(Object value, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonProcessingException
    {
        jgen.writeString("");
    }
}
