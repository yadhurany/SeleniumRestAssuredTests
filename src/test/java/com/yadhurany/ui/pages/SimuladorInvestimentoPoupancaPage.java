package com.yadhurany.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimuladorInvestimentoPoupancaPage {

    private WebDriver driver;
    private By valorAplicarField = By.name("valorAplicar");
    private By valorInvestirField = By.name("valorInvestir");
    private By tempoField = By.name("tempo");
    private By valorAplicarErrorLabel = By.id("valorAplicar-error");
    private By valorInvestirErrorLabel = By.id("valorInvestir-error");
    private By tempoErrorLabel = By.id("tempo-error");
    private By simularButton = By.cssSelector("ul.opcoes li button.btnSimular");
    private By limparButton = By.cssSelector(".btn.btnLimpar");
    private By outrasOpcoesTable = By.cssSelector(".maisOpcoes");

    public SimuladorInvestimentoPoupancaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setValorAplicar(String valorAplicar){
        driver.findElement(valorAplicarField).sendKeys(valorAplicar);
    }

    public void setValorInvestir(String valorInvestir){
        driver.findElement(valorInvestirField).sendKeys(valorInvestir);
    }

    public void setTempo(String tempo){
        driver.findElement(tempoField).sendKeys(tempo);
    }

    public String getOutrasOpcoesText(){
        return driver.findElement(outrasOpcoesTable).getText();
    }

    public String getValorAplicarErrorText(){
        return driver.findElement(valorAplicarErrorLabel).getText();
    }

    public String getValorInvestirErrorText(){
        return driver.findElement(valorInvestirErrorLabel).getText();
    }

    public String getTempoErrorText(){
        return driver.findElement(tempoErrorLabel).getText();
    }

    public String getValorAplicarText() {
        return driver.findElement(valorAplicarField).getText();
    }

    public String getValorInvestirText() {
        return driver.findElement(valorInvestirField).getText();
    }

    public String getTempoText() {
        return driver.findElement(tempoField).getText();
    }

    public void clickValorInvestirField(){
        driver.findElement(valorInvestirField).click();
    }

    public void clickTempoField(){
        driver.findElement(tempoField).click();
    }

    public void clickLimparButton(){
        driver.findElement(limparButton).click();
    }

    public void clickSimularButton(){
        driver.findElement(simularButton).click();
    }
}
