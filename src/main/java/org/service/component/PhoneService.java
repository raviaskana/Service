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
            // We have hit the end of the input phone number and thus the end of
            // recursion
            allResults.add(resultSoFar);
        } else {
            // Strip the next character off the front of the phone number


            // Look up the list of mappings from that digit to all letters
            List<String> mappingArray = config.get(String.valueOf(input.charAt(0)));

            // More robust error handling would throw an exception or do
            // something else when an unknown character was encountered in the
            // phone number.
            if (mappingArray != null) {

                // We have processed the first digit in the rest of the number,
                // so recurse with the rest of the number
                String inputTail = input.substring(1);

                // By iterating through the array the mapping lists do not all
                // have to be the same size.
                for (String nextLetter : mappingArray) {
                    // Put the next mapped letter on the end of the result being
                    // built and recurse
                    convert(inputTail, resultSoFar + nextLetter, allResults);
                }
            }
        }

    }
}
