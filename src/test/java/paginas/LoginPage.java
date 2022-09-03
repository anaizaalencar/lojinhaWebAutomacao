package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    // PageObject - 1º - ter 1 atributo privado que representa o seu navegador, ou seja, do tipo WebDriver
    private final WebDriver navegador;

    // PageObject - 2º - ter um construtor que recebe o estado atual do navegador e passar para a classe
    public LoginPage(WebDriver navegador){
        this.navegador = navegador;
    }

    // PageObject - 3º - ter métodos de interação com cada elemento da tela
    // FluentPage - 1º - ter um tipo de retorno do método que é sempre a classe da página que após as ações do método,
    // a tela irá ou permanecerá, ou seja, após informar o usuário a tela permanecerá na página de login,
    // então o tipo de retono é a classe da tela de login que é a classe LoginPage
    public LoginPage informarUsuario(String usuario){
        navegador.findElement(By.cssSelector("label[for=\"usuario\"]")).click();
        navegador.findElement(By.id("usuario")).sendKeys(usuario);

        // FluentPages - 2º - ter sempre um retorno que é a classe inserida no tipo de retorno da chamado do método
        return this;
    }

    public LoginPage informarSenha(String senha){
        navegador.findElement(By.cssSelector("label[for=\"senha\"]")).click();
        navegador.findElement(By.id("senha")).sendKeys(senha);

        return this;
    }

    // tipo de retorno é a classe da página que após a ação do método a tela irá
    public ListarProdutosPage submeterFormulario(){
        navegador.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        // retorno é a classe inserida no tipo de retorno da chamada do método, para isso,
        // é preciso instaciar o objeto da classe e passar um objeto do tipo Webdriver (navegador)
        return new ListarProdutosPage(navegador);
    }

}
