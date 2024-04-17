package com.saito.payment.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saito.payment.adapters.out.message.SaleMessage;
import org.apache.kafka.common.serialization.Serializer;
import org.yaml.snakeyaml.serializer.SerializerException;

public class CustomSerializer implements Serializer<SaleMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, SaleMessage message){
        try{
            if(message == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(message);
        }catch (Exception e){
            throw new SerializerException("Error when serializing SaleMessage to byte[]");
        }
    }
}