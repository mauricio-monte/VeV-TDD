package tests;
import main.Voo;
import main.VooRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VooRepositoryTeste {

    private final String LISTA =
            "ID: 0 | Destino: Destino A | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00\n" +
                    "ID: 1 | Destino: Destino B | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00\n" +
                    "ID: 2 | Destino: Destino C | Data: 20/08/2023 | Horário: 10:00 | Preço: R$ 200,00\n";

    private VooRepository vooRepository;

    public void construirRepositorioVazio() {
        this.vooRepository = new VooRepository();
    }

    @AfterEach
    public void destruir() {
        this.vooRepository = null;
    }

    public void construirRepositorioCom3Voos(){
        construirRepositorioVazio();

        Voo voo1 = new Voo( "Destino A", "20/08/2023", "10:00", 200.0);
        Voo voo2 = new Voo( "Destino B", "20/08/2023", "10:00", 200.0);
        Voo voo3 = new Voo( "Destino C", "20/08/2023", "10:00", 200.0);

        this.vooRepository.adicionarVoo(voo1);
        this.vooRepository.adicionarVoo(voo2);
        this.vooRepository.adicionarVoo(voo3);
    }

    @Test
    public void testeAdicionarVoo() {
        construirRepositorioVazio();

        Voo voo = new Voo("Destino A", "20/08/2023", "10:00", 200.0);

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

        assertEquals(LISTA, this.vooRepository.listarVoos());
    }


}