import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoricoConversao {

    private final List<String> historico = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public void adicionar(String  registro){
        String timestamp = LocalDateTime.now().format(FORMATTER);
        historico.add("[" + timestamp + "] " +registro);
    }

    public void mostrar(){
        if(historico.isEmpty()){
            System.out.println("Nenhuma conversão realizada ainda");
        } else {
            System.out.println(" Historico de conversões");
            historico.forEach(item -> System.out.println("- " +item));
        }
    }

}

