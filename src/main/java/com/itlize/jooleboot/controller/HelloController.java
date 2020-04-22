package com.itlize.jooleboot.controller;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    private static final String[][] castTable = new String[][]{{"+"},{""}, {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"}
            , {"J", "K", "L"}, {"M", "N", "O"}, {"P", "O", "R", "S"}, {"T", "U", "V"}, {"W", "X", "Y", "Z"}};

    @GetMapping("/hello")
    public String[][] sayHello() {
        return castTable;
    }

    @GetMapping("/getAllPermutation")
    public ResponseEntity<?> getAllPerMutation(@RequestParam(name="input")String input) {
        List<String> res = new ArrayList<>();
        if (input == null || input.length() != 10) {
            return new ResponseEntity<>("Invalid Number", HttpStatus.OK);
        }
        StringBuilder sb = new StringBuilder();
        char[] array = input.toCharArray();
        helper(res, array, 0, castTable, sb);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private void helper(List<String> res, char[] input, int index, String[][] castTable, StringBuilder sb) {
        if (index == input.length) {
            res.add(sb.toString());
            return;
        }
        int curNumber = Character.getNumericValue(input[index]);
        sb.append(input[index]);
        helper(res, input, index + 1, castTable, sb);
        sb.deleteCharAt(sb.length() - 1);
        if (curNumber != 1 && index >= 7) {
            for (int i = 0; i <= castTable[curNumber].length - 1; i++) {
                sb.append(castTable[curNumber][i]);
                helper(res, input, index + 1, castTable, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }


    }
}

