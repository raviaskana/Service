package org.service.component;

import org.service.config.Config;
import org.service.data.Result;
import org.service.exception.InvalidPhoneNumException;
import org.service.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    Config config;

    public Result getAlphanumericNumbers(final String phoneNum, Optional<String> pageNum,Optional<String> pageLimit){
        if(StringUtils.isEmpty(phoneNum.trim())) {
            throw new InvalidPhoneNumException();
        }
        return getAllPhoneNumCombinations(phoneNum, pageNum, pageLimit);
    }

    private Result getAllPhoneNumCombinations(final String phoneNum, Optional<String> pageNum,Optional<String> pageLimit){
        List<String> results = new ArrayList<>();
        convert(phoneNum, "", results);
        Result result = PageUtil.filterByPage(results,pageNum,pageLimit);
        if(phoneNum!=null && phoneNum.length()>0) {
            result.setNext(String.format(result.getNext()!=null?result.getNext():"", phoneNum));
            result.setPrevious(String.format(result.getPrevious()!=null?result.getPrevious():"", phoneNum));
        }
        return result;
    }

    private  void convert(String input, String resultSoFar, List<String> allResults) {
        if (input.length() == 0) {
            allResults.add(resultSoFar.toUpperCase());
        } else {
            List<String> mappingArray = config.get(String.valueOf(input.charAt(0)));
            if (mappingArray != null) {
                String inputTail = input.substring(1);
                for (String nextLetter : mappingArray) {
                    convert(inputTail, resultSoFar + nextLetter, allResults);
                }
            }
        }
    }
}
