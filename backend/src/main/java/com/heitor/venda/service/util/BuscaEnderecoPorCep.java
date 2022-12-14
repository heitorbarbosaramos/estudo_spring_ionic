package com.heitor.venda.service.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.heitor.venda.domain.Endereco;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletionException;

@Slf4j
public class BuscaEnderecoPorCep {

    public static Endereco buscarCep(String cepBuscar) {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://viacep.com.br/ws/" + cepBuscar + "/json")).build();

        HttpClient client;
        Integer status  = null;
        String header   = null;
        String body     = null;

        Endereco endereco = new Endereco();

        try{
            client = HttpClient.newHttpClient();

            status = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::statusCode).join();
            header = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::headers).join().toString();
            body   = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
        }catch (CompletionException e){
            log.error("ERRO AO CONECTAR AO SITE " + request.toString());
            log.error(e.getMessage());
            return endereco;
        }

        final Gson gson = new GsonBuilder().create();

        JsonObject jsonObject;
        try {
            jsonObject = gson.fromJson(body, JsonObject.class);
        }catch (RuntimeException e){
            log.info("ERRO AO BUSCAR ENDERECO");
            log.info(e.getMessage());
            return endereco;
        }

        String cep          = (!jsonObject.get("cep").equals(null))         ? String.valueOf(jsonObject.get("cep")).replace("\"", "")         : null;
        String logradouro   = (!jsonObject.get("logradouro").equals(null))  ? String.valueOf(jsonObject.get("logradouro")).replace("\"", "")  : null;
        String complemento  = (!jsonObject.get("complemento").equals(null)) ? String.valueOf(jsonObject.get("complemento")).replace("\"", "") : null;
        String bairro       = (!jsonObject.get("bairro").equals(null))      ? String.valueOf(jsonObject.get("bairro")).replace("\"", "")      : null;
        String localidade   = (!jsonObject.get("localidade").equals(null))  ? String.valueOf(jsonObject.get("localidade")).replace("\"", "")  : null;
        String uf           = (!jsonObject.get("uf").equals(null))          ? String.valueOf(jsonObject.get("uf")).replace("\"", "")          : null;
        String ibge         = (!jsonObject.get("ibge").equals(null))        ? String.valueOf(jsonObject.get("ibge")).replace("\"", "")        : null;
        String gia          = (!jsonObject.get("gia").equals(null))         ? String.valueOf(jsonObject.get("gia")).replace("\"", "")         : null;
        String ddd          = (!jsonObject.get("ddd").equals(null))         ? String.valueOf(jsonObject.get("ddd")).replace("\"", "")         : null;
        String siafi        = (!jsonObject.get("siafi").equals(null))       ? String.valueOf(jsonObject.get("siafi")).replace("\"", "")       : null;

        endereco = new Endereco(null, cep, logradouro, complemento, bairro, localidade,"", uf, ibge, gia, ddd, siafi);

        log.info("REQUEST: " + request.uri().toString());
        log.info("ESTADO CODE: " + status);
        log.info("HEADER: " + header);
        log.info("ENDERECO: " + endereco);

        return endereco;

    }
}
