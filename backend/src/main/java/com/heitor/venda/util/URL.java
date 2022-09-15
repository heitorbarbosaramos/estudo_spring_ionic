package com.heitor.venda.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeIntList(String s){
        if(s.isEmpty() || s.isBlank()){
            try {
                throw new Exception("Informe uma categoria");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Arrays.stream(s.split(",")).map(x-> Integer.parseInt(x)).collect(Collectors.toList());
    }

    public static String buscaEspacoVazioUrl(String s){
        try {
           return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
