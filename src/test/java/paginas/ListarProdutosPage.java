package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListarProdutosPage {
    private WebDriver navegador;

    public ListarProdutosPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public FormularioAdicaoProdutoPage acessarFormularioAdicionarProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new FormularioAdicaoProdutoPage(navegador);
    }

    public String capturarMensagemApresentada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

}

