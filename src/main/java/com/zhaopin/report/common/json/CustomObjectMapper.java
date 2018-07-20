package com.zhaopin.report.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;


/**
 * 
 * <P>
 * json 格式化
 * </P>
 * 
 * @version 1.0.0
 */
public class CustomObjectMapper extends ObjectMapper
{

    public CustomObjectMapper()
    {
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>()
        {
            @Override
            public void serialize(Date value, JsonGenerator jsonGenerator,
                    SerializerProvider provider) throws IOException,
                    JsonProcessingException
            {
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                jsonGenerator.writeString(sdf.format(value));
            }
        });

        this.setSerializerFactory(factory);
        this.getSerializerProvider().setNullValueSerializer(
                new JsonNullSerializer());
    }
}