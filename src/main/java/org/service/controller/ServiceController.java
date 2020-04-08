package org.service.controller;

import org.service.component.PhoneService;
import org.service.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    PhoneService phoneService;

    @RequestMapping(value = {"/v1/phonenum/{phonenum}/alphanum",
                             "/v1/phonenum/{phonenum}/alphanum/page/{pagenum}"},
                              method = RequestMethod.GET)
    public ResponseEntity<Result> getAlphanumericNumbers(@PathVariable("phonenum") String phoneNum,
                                                         @PathVariable("pagenum") Optional<String> pageNum,
                                                         @RequestParam(name = "pagelimit",defaultValue = "100") Optional<String> pageLimit){
        Result result = phoneService.getAlphanumericNumbers(phoneNum,pageNum,pageLimit);
        return ResponseEntity.ok(result);
    }
}
