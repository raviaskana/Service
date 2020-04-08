package org.service.util;

import org.service.data.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PageUtil {
    public static Result filterByPage(List<String> input, Optional<String> pageNum, Optional<String> pageLimit){
        int page = StringUtility.tryParse(pageNum.orElse("1"),1);
        int recPerPage = StringUtility.tryParse(pageLimit.get(),100);
        int startIndex = ((page * recPerPage)  - recPerPage );
        int endIndex = page * recPerPage;
        List<String> filteredResults = Arrays.asList(Arrays.copyOfRange(input.toArray(new String[0]),Math.min(startIndex,input.size()),Math.min(endIndex,input.size())));
        Result result = new Result();
        result.setCount(filteredResults.size());
        result.setData(filteredResults);
        result.setTotalCount(input.size());
        result.setPageno(page);
        result.setTotalpages(input.size()/recPerPage);
        if((page + 1 ) <= result.getTotalpages())
        result.setNext("api/v1/phonenum/%s/alphanum/page/"+ (page + 1));
        if((page - 1 ) > 0)
        result.setPrevious("api/v1/phonenum/%s/alphanum/page/"+ (page - 1));
        return result;
    }
}
