package com.saito.payment.config.kafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saito.payment.adapters.out.message.SaleMessage;
import org.apache.kafka.common.serialization.Deserializer;
import org.yaml.snakeyaml.serializer.SerializerException;

public class CustomDeserializer implements Deserializer<SaleMessage> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public SaleMessage deserialize(String topic, byte[] data){
        try{
            if(data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), SaleMessage.class);
        }catch (Exception e){
            throw new SerializerException("Error when deserializing byte[] to sale");
        }
    }
}