package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto toinen;
    Varasto temp;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        toinen = new Varasto(10, 8);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisataanEnintaanSaldo() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }   

    @Test
    public void yliSaldonOttaminenTyhjentaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(10);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    } 

    @Test
    public void yliSaldonOttaminenPalauttaaSaldon() {
        varasto.lisaaVarastoon(8);

        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-2);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuusNegatiivinen() {
        temp = new Varasto(-2);

        assertEquals(0, temp.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toinenVarasto() {

        assertEquals(10, toinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toinenVarastoSaldo() {

        assertEquals(8, toinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toinenVarastoNegatiivinenTilavuus() {
        temp = new Varasto(-2, 2);

        assertEquals(0, temp.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toinenVarastoNegatiivinenSaldo() {
        temp = new Varasto(10, -2);

        assertEquals(0, temp.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toinenVarastoSaldoSuurempiKuinTilavuus() {
        temp = new Varasto(10, 12);

        assertEquals(10, temp.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toinenVarastoSaldoNormaali() {
        
        assertEquals(8, toinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaNegatiivinen() {

        assertEquals(0, toinen.otaVarastosta(-2), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaNegatiivinenEiMuutaSaldoa() {

        assertEquals(8, toinen.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {

        assertEquals("saldo = 8.0, vielä tilaa 2.0", 
            toinen.toString());
    }
}