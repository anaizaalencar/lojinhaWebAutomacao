package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes WEB do modulo de produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        // abrir navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver103\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // maximizar a tela
        this.navegador.manage().window().maximize();

        //Definir tempo de espera padrão para evitar que o WebDrive não falhe o teste
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // navegar na pagina da lojinha web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Nao e permitido inserir valor do produto igual a zero")
    public void testValorZeradoProduto(){
        // fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormulario()
                .acessarFormularioAdicionarProduto() // ir para tela de adicionar um novo produto
                .informarNomeProduto("Iphone") // preencher dados do produto com o valor igual a zero
                .informarValorProduto("0,00")
                .informarCoresProduto("Preto,Branco")
                .submeterProdutoComErro() // submeter o formulario
                .capturarMensagemApresentada(); // validar que a mensagem de erro foi apresentada

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Nao e permitido inserir valor do produto maior que 7000")
    public void testValorMaiorSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormulario()
                .acessarFormularioAdicionarProduto()
                .informarNomeProduto("PS5")
                .informarValorProduto("7000,01")
                .informarCoresProduto("Branco")
                .submeterProdutoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Eh permitido inserir valor do produto entre 0,01 e 7000,00")
    public void testValorEntreUmCentavoESeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormulario()
                .acessarFormularioAdicionarProduto()
                .informarNomeProduto("Tablet")
                .informarValorProduto("500,01")
                .informarCoresProduto("Cinza")
                .submeterProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Eh permitido inserir valor do produto no limite 0,01")
    public void testValorIgualUmCentavo(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormulario()
                .acessarFormularioAdicionarProduto()
                .informarNomeProduto("Celular")
                .informarValorProduto("0,01")
                .informarCoresProduto("Azul")
                .submeterProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Eh permitido inserir valor do produto no limite 7000,00")
    public void testValorIgualSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormulario()
                .acessarFormularioAdicionarProduto()
                .informarNomeProduto("Celular")
                .informarValorProduto("7000,00")
                .informarCoresProduto("Azul")
                .submeterProdutoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertFalse(navegador.get(""););

    }

    @AfterEach
    public void afterEach(){
        // fechar navegador
        navegador.quit();
    }

}
