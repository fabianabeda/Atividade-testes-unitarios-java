package com.gugawag.testes.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaTeste {

    Conta c1;
    Conta c2;

    @BeforeEach
    private void configuraConta() {
        c1 = new Conta();
        c1.setNumero("10");
        c2 = new Conta();
        c2.setNumero("15");
    }


    @Test
    public void deveAlterarNumeroContaNumeroValido() {
        // Config
        String numeroContaValido = "10";

        // executa
        c1.setNumero(numeroContaValido);

        // teste
        Assertions.assertEquals(numeroContaValido, c1.getNumero());
    }


    @Test
    public void deveComecarContaComSaldoZero() {
        // config
        Conta contaNova = new Conta();

        // teste
        Assertions.assertEquals(0.0, contaNova.getSaldo());
    }

//    @Test
//    public void naoDeveriaMudarSaldoSeValorNegativo(){
//        // config
//        Conta contaSaldoPositivo = new Conta();
//        Double saldoPositivo = 100.0;
//        contaSaldoPositivo.setSaldo(saldoPositivo);
//
//        // executar
//
//        Double valorNegativo = -10.0;
//        contaSaldoPositivo.setSaldo(valorNegativo);
//
//        // teste
//        Assertions.assertEquals(saldoPositivo, contaSaldoPositivo.getSaldo());
//    }
//
//    @Test
//    public void naoDeveriaTerSaldoNegativo(){
//        // config
//        Conta contaSaldoPositivo = new Conta();
//        Double saldoPositivo = 100.0;
//        contaSaldoPositivo.setSaldo(saldoPositivo);
//
//        // executar
//
//        Double valorNegativo = -10.0;
//        contaSaldoPositivo.setSaldo(valorNegativo);
//
//        // teste
//        Assertions.assertTrue(contaSaldoPositivo.getSaldo()>=0);
//    }

    @Test
    public void naoDeveDebitarValorMaiorSaldo() {
        // config
        c1.creditar(100.0);

        // executar

        Assertions.assertThrows(SaldoNegativoInvalidoException.class,
                () -> c1.debitar(200.0));
    }

    @Test
    public void deveDebitarValorIgualSaldo() {
        // config
        c1.creditar(100.0);

        // executar
        try {
            c1.debitar(100.0);
        } catch (SaldoNegativoInvalidoException e) {
            Assertions.fail();
        }

        // teste
        Assertions.assertEquals(0.0, c1.getSaldo());
        
    }

    @Test
    public void naoDeveCreditarValorNegativo() {

        // executar
        c1.creditar(-100.0);

        // teste
        Assertions.assertEquals(0.0, c1.getSaldo());
    }
    
    @Test
    public void naoDeveTransferirValorNegativo() throws SaldoNegativoInvalidoException {
        try {
            c1.transferir(-0.10, c2);
        } catch (SaldoNegativoInvalidoException e) {
            Assertions.fail();
        }

        Assertions.assertEquals(0.0, c1.getSaldo());
        Assertions.assertEquals(0.0, c2.getSaldo());
    }

    @Test
    public void naoDeveTransferirValorMaiorQue1000() throws SaldoNegativoInvalidoException {
        try{
            c1.creditar(850);
            c1.transferir(1100,"15");
            

        }catch (SaldoSuperiorInvalidoException e) {
            Assertions.fail();
        }


}
