/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fabricia Martins
 */
public class Dicionario {
    
    private Map<String, String> dicionario;
    
    public Dicionario(){
        dicionario = new HashMap<>();
        inicializarDicionario();
    }
    
    private void inicializarDicionario(){
        dicionario.put("Accio", "Feitiço Convocatório.atrai um objeto, mesmo por longas distâncias.");
        dicionario.put("Crucio", "A Maldição Cruciatus tortura a vítima, causando-lhe profunda e aguda dor por todo o corpo.");
        dicionario.put("Hermione Granger", "Hermione Jean Weasley, nascida a 19 de setembro de 1979, é a filha única do Sr. e da Sra. Granger.");
        
    }
    
    public String pesquisarSignificado(String palavra){
        
        String significado = dicionario.get(palavra);
        
        if(significado == null){
            significado = "Nome não encontrado no dicionário.";
        }
        
        return significado;
    }
}
