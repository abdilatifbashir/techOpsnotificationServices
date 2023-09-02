package com.techOps.notificationService.services;

import com.techOps.notificationService.entities.SmsTranctions;
import com.techOps.notificationService.repositories.SmsSenderDetailsRepository;
import com.techOps.notificationService.repositories.SmsTransactionRepository;
import com.techOps.notificationService.Dto.SendSmsDto;
import com.techOps.notificationService.Dto.UniversalResponse;
import com.techOps.notificationService.Dto.smsResPayload.Recipient;
import com.techOps.notificationService.Dto.smsResPayload.SmsRespnse;
import com.techOps.notificationService.config.ConfigProperties;
import com.techOps.notificationService.entities.SmsSenderDetailsEntity;
import com.techOps.notificationService.entities.SmsAccountEntity;
import com.techOps.notificationService.repositories.SmsAccountRepository;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class SendSmsService {

    @Autowired
    private SmsAccountRepository smsAccountRepository;

    @Autowired
    private SmsTransactionRepository smsTransactionRepository;

    @Autowired
    private SmsSenderDetailsRepository smsSenderDetailsRepository;

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ConfigProperties configProperties;




    public UniversalResponse sendSms(SendSmsDto sendSmsDto){
        Optional<SmsAccountEntity> smsAccount = smsAccountRepository.findByApiKeyAndAccountId(sendSmsDto.getApiKey(), sendSmsDto.getAccuntid());
        Optional<SmsSenderDetailsEntity> senderDetails = smsSenderDetailsRepository.findBySenderId(sendSmsDto.getFrom());
                String endpoint = senderDetails.get().getSendEndPoint();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < sendSmsDto.getTo().size(); i++) {
            stringBuilder.append(sendSmsDto.getTo().get(i));

            if (i < sendSmsDto.getTo().size() - 1) {
                stringBuilder.append(", ");
            }
        }

        String msisdns = stringBuilder.toString();

        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("from", sendSmsDto.getFrom());
        valueMap.add("username", senderDetails.get().getUsername());
        valueMap.add("message", sendSmsDto.getMessage());
        valueMap.add("to", msisdns);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("apiKey", senderDetails.get().getPassword());
        headers.set("Accept","application/json");



        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(valueMap, headers);
        System.out.println(httpEntity);
        SmsRespnse smsRespnse = null;
        try {
            assert endpoint != null;
            ResponseEntity<String> productResponseEntity = restTemplate.postForEntity(
                    endpoint,httpEntity, String.class
            );



            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(productResponseEntity.getBody());
            String toString = json.toJSONString();
             smsRespnse =  new Gson().fromJson(toString,SmsRespnse.class);
            logTransaction(smsRespnse,sendSmsDto);

            int successSmsSize = 0;
            for(Recipient recipient : smsRespnse.getSMSMessageData().getRecipients() ){
                if(recipient.status.equals("Success")){
                    successSmsSize += recipient.messageParts;
                }

            }
            int updateBalance = smsAccount.get().getBalance() - successSmsSize;
            smsAccount.get().setBalance(updateBalance);
            smsAccountRepository.save(smsAccount.get());



            return new UniversalResponse(200,"sucess",json);
        }catch (Exception e) {
            logTransaction(smsRespnse,sendSmsDto);

            System.out.println(e.getMessage());
            return new UniversalResponse(500,"fail");
        }

    }


    public  void logTransaction(SmsRespnse smsRespnse,SendSmsDto sendSmsDto){
        smsRespnse.getSMSMessageData().getRecipients().forEach((transaction) -> {
            SmsTranctions smsTranctions = new SmsTranctions();
            smsTranctions.setMessage(sendSmsDto.getMessage());
            smsTranctions.setMobileNumber(transaction.getNumber());
            smsTranctions.setSenderId(sendSmsDto.getFrom());
            smsTranctions.setAccountId(sendSmsDto.getAccuntid());
            smsTranctions.setATMessageId(transaction.getMessageId());
            smsTranctions.setStatusMessage(transaction.status);
            if(transaction.getStatusCode()==101){
                smsTranctions.setDelivered(true);
            }

            if(transaction.getStatusCode()!=101){
                smsTranctions.setDelivered(false);
            }
            smsTransactionRepository.save(smsTranctions);


        });



    }
}

