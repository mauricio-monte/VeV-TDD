import main.Reserva;
import main.Voo;
import main.VooRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VooRepositoryTeste {
    private final String LISTA1 =
            "ID: 0 | Origem: Origem A | Destino: Destino A | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 1 | Origem: Origem B | Destino: Destino B | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 2 | Origem: Origem C | Destino: Destino C | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n";
    private final String LISTA2 =
            "ID: 0 | Origem: Origem A | Destino: Destino A | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 3 | Origem: Origem D | Destino: Destino A | Data: 20/08/2023 | Horário: 14:00 | Preço: R$ 200,00 | Vagas: 30\n";
    private final String LISTA3 =
            "ID: 0 | Origem: Origem A | Destino: Destino A | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 3 | Origem: Origem A | Destino: Destino D | Data: 20/08/2023 | Horário: 14:00 | Preço: R$ 200,00 | Vagas: 30\n";
    private final String LISTA4 =
            "ID: 0 | Origem: Origem A | Destino: Destino A | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 1 | Origem: Origem B | Destino: Destino B | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 2 | Origem: Origem C | Destino: Destino C | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 4 | Origem: Origem A | Destino: Destino E | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n";
    private final String LISTA5 =
            "ID: 0 | Origem: Origem A | Destino: Destino A | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n" +
                    "ID: 2 | Origem: Origem C | Destino: Destino C | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00 | Vagas: 30\n";


    private VooRepository vooRepository;
    private Reserva reserva;

    public void construirRepositorioVazio() {
        this.vooRepository = new VooRepository();
    }

    public void construirReservaVazia() {
        this.reserva = new Reserva();
    }

    @After
    public void destruir() {
        this.vooRepository = null;
    }

    public void construirRepositorioCom3Voos(){
        construirRepositorioVazio();

        Voo voo1 = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);
        Voo voo2 = new Voo("Origem B", "Destino B", "20/08/2023", "10:00", 200.0);
        Voo voo3 = new Voo("Origem C","Destino C", "20/08/2023", "10:00", 200.0);

        this.vooRepository.adicionarVoo(voo1);
        this.vooRepository.adicionarVoo(voo2);
        this.vooRepository.adicionarVoo(voo3);
    }

    @Test
    public void testeAdicionarVoo() {
        construirRepositorioVazio();

        Voo voo = new Voo("Origem: Origem A", "Destino A", "20/08/2023", "10:00", 200.0);

        this.vooRepository.adicionarVoo(voo);

        assertEquals(1, this.vooRepository.getVoos().size());
    }

    @Test
    public void testeAdicionarMultiplosVoos() {
        construirRepositorioCom3Voos();

        assertEquals(3, this.vooRepository.getVoos().size());
    }

    @Test
    public void testeListarVoos(){
        construirRepositorioCom3Voos();

        assertEquals(LISTA1, this.vooRepository.listarVoos());
    }

    @Test
    public void testeFiltrarVoosPorDestino(){
        construirRepositorioCom3Voos();

        Voo voo1 = new Voo( "Origem D", "Destino A", "20/08/2023", "14:00", 200.0);

        vooRepository.adicionarVoo(voo1);

        assertEquals(LISTA2, vooRepository.getStringVooPorDestino("Destino A"));
    }

    @Test
    public void testeFiltrarVoosPorOrigem(){
        construirRepositorioCom3Voos();

        Voo voo1 = new Voo( "Origem A", "Destino D", "20/08/2023", "14:00", 200.0);

        vooRepository.adicionarVoo(voo1);

        assertEquals(LISTA3, vooRepository.getStringVooPorOrigem("Origem A"));
    }

    @Test
    public void testeFiltrarVoosPorData(){
        construirRepositorioCom3Voos();

        Voo voo1 = new Voo( "Origem A", "Destino D", "21/08/2023", "14:00", 200.0);
        Voo voo2 = new Voo( "Origem A", "Destino E", "20/08/2023", "10:00", 200.0);

        vooRepository.adicionarVoo(voo1);
        vooRepository.adicionarVoo(voo2);

        assertEquals(LISTA4, vooRepository.getStringVooPorData("20/08/2023"));
    }

    @Test
    public void testeFiltrarVoosPorVagasMinimas(){
        construirReservaVazia();

        Voo voo1 = new Voo("Origem A", "Destino A", "20/08/2023", "10:00", 200.0);
        Voo voo2 = new Voo("Origem B", "Destino B", "20/08/2023", "10:00", 200.0);
        Voo voo3 = new Voo("Origem C", "Destino C", "20/08/2023", "10:00", 200.0);

        this.reserva.getVooRepository().adicionarVoo(voo1);
        this.reserva.getVooRepository().adicionarVoo(voo2);
        this.reserva.getVooRepository().adicionarVoo(voo3);

        this.reserva.reservarVoo(1, 25, null);

        assertEquals(LISTA5, this.reserva.getVooRepository().getStringVooPorVagasMinimas(10));
    }
}