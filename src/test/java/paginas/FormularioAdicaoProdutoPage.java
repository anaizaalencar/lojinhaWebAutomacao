package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoProdutoPage {
    private WebDriver navegador;

    public FormularioAdicaoProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public FormularioAdicaoProdutoPage informarNomeProduto(String produtoNome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }

    public FormularioAdicaoProdutoPage informarValorProduto(String produtoValor){
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;
    }

    public FormularioAdicaoProdutoPage informarCoresProduto(String produtoCores){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);

        return this;
    }

    public ListarProdutosPage submeterProdutoComErro(){
        navegador.findElement(By.cssSelector("button[type=\"submit\"]")).submit();

        return new ListarProdutosPage(navegador);
    }

    public FormularioEdicaoProdutoPage submeterProdutoComSucesso(){
        navegador.findElement(By.cssSelector("button[type=\"submit\"]")).submit();

        return new FormularioEdicaoProdutoPage(navegador);
    }

    public FormularioAdicaoProdutoPage acessarFormularioAdicionarComponente(){
        navegador.findElement(By.linkText("ADICIONAR COMPONENTE")).click();
        return this;
    }

}
