package com.yadhurany.ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.yadhurany.ui.pages.SimuladorInvestimentoPoupancaPage;


import static org.junit.jupiter.api.Assertions.*;


public class SimuladorInvestimentoPoupancaUITests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/");
    }

    @AfterEach
    public void tearDown(){
         driver.quit();
    }

    @Test
    public void simularComSucesso() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("120,00");
        simuladorPage.setValorInvestir("50,00");
        simuladorPage.setTempo("6");
        simuladorPage.clickSimularButton();
        WebDriverWait wdw = new WebDriverWait(driver, 10);
        ExpectedCondition<Boolean> condition = wd -> {
            WebElement result = wd.findElement(By.className("btnRefazer"));
            return result.isDisplayed();
        };
        wdw.until(condition);
        assertTrue(simuladorPage.getOutrasOpcoesText()
                        .contains("Veja estas outras opções para você"),
                        "Não foi realizada a simulação com sucesso!");
    }

    @Test
    public void simularValorAbaixoAplicar() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("16,00");
        simuladorPage.clickValorInvestirField();
        assertEquals("Valor mínimo de 20.00", simuladorPage.getValorAplicarErrorText());
    }

    @Test
    public void simularValorAbaixoInvestir() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorInvestir("10,00");
        simuladorPage.clickTempoField();
        assertEquals("Valor mínimo de 20.00", simuladorPage.getValorInvestirErrorText());
    }

    @Test
    public void simularValorAcimaAplicar() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("22222222,00");
        simuladorPage.clickValorInvestirField();
        assertEquals("Máximo de 9999999.00", simuladorPage.getValorAplicarErrorText());
    }

    @Test
    public void simularValorAcimaInvestir() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorInvestir("22222222,00");
        simuladorPage.clickTempoField();
        assertEquals("Máximo de 9999999.00", simuladorPage.getValorInvestirErrorText());
    }

    @Test
    public void simularCamposObrigatoriosNaoPreenchidoAplicar() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorInvestir("50,00");
        simuladorPage.setTempo("6");
        simuladorPage.clickSimularButton();
        assertEquals("Valor mínimo de 20.00", simuladorPage.getValorAplicarErrorText());
    }

    @Test
    public void simularCamposObrigatoriosNaoPreenchidoInvestir() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("127,00");
        simuladorPage.setTempo("6");
        simuladorPage.clickSimularButton();
        assertEquals("Valor mínimo de 20.00", simuladorPage.getValorInvestirErrorText());
    }

    @Test
    public void simularCamposObrigatoriosNaoPreenchidoTempo() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("127,00");
        simuladorPage.setValorInvestir("50,00");
        simuladorPage.clickSimularButton();
        assertEquals("Obrigatório", simuladorPage.getTempoErrorText());
    }

    @Test
    public void limparDadosFormularioCampoAplicar() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("127,00");
        simuladorPage.clickLimparButton();
        assertEquals("", simuladorPage.getValorAplicarText());
    }

    @Test
    public void limparDadosFormularioCampoInvestir() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorInvestir("50,00");
        simuladorPage.clickLimparButton();
        assertEquals("", simuladorPage.getValorInvestirText());
    }

    @Test
    public void limparDadosFormularioCampoTempo() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setTempo("6");
        simuladorPage.clickLimparButton();
        assertEquals("", simuladorPage.getTempoText());
    }

    @Test
    public void tempoIgualZero() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setTempo("0");
        simuladorPage.clickSimularButton();
        assertEquals("Valor esperado não confere", simuladorPage.getTempoErrorText());
    }

    @Test
    public void testarValoresTabela() {
        SimuladorInvestimentoPoupancaPage simuladorPage = new SimuladorInvestimentoPoupancaPage(driver);
        simuladorPage.setValorAplicar("120,00");
        simuladorPage.setValorInvestir("50,00");
        simuladorPage.setTempo("6");
        simuladorPage.clickSimularButton();
        WebDriverWait wdw = new WebDriverWait(driver, 10);
        ExpectedCondition<Boolean> condition = wd -> {
            WebElement result = wd.findElement(By.className("btnRefazer"));
            return result.isDisplayed();
        };
        wdw.until(condition);
        assertEquals("Veja estas outras opções para você\n" +
                "Tempo (Meses) Valor\n" +
                "18 R$ 1.044\n" +
                "30 R$ 1.684\n" +
                "42 R$ 2.342\n" +
                "54 R$ 3.020", simuladorPage.getOutrasOpcoesText());
    }

}
