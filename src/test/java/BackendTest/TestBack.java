package BackendTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
/*
    NOTA1:
    ESTA CLASE DEBE IR DENTRO DEL PAQUETE test NO EL PAQUETE main.
    ADEMÁS SE DEBE IMPORTAR ESTA DEPENDENCIA EN EL POM.XML PARA EL REST-ASSURED
    Y SE RECOMIENDA IMPORTARLA ANTES DE LAS DE JUNIT 5

    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.1.1</version>
        <scope>test</scope>
    </dependency>
*/
// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
// NOTA2: SE DEBE  EJECUTAR TODOS LOS TEST, DESDE LA CLASE BANKTEST
// YA QUE LOS TESTS VAN GENERANDO LO NECESARIO (COMO LA COOKIE, CREA CUENTAS, ETC) PARA LOS SIGUIENTES TEST
//
// EN LA MAYORÍA DE PETICIONES QUEDA EL LOG().ALL() PARA QUE UNA VEZ TERMINADO LOS TEST
// PUEDAN VER LAS RESPUESTAS JSON DE LOS ENDPOINT
// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBack {


    // Aquí debes configurar las credenciales del usuario que vas  crear
    // o del que ya exista y quieras loguearte (en este caso fallará el primer test)
    private static String username = "UsuarioFalso3";
    private static String password = "12345";

    private static String sessionId;
    private static String customerId;
    private static String accountId1;
    private static String accountId2;





    @Test
    @Tag("Smoke")
    @DisplayName("Proceso de registro")
    @Order(1)
    public void testRegisterNewUser(){

        // En este endpoint (GET) se visita la página de registro para obtener la cookie de sesión
        sessionId = given()
                    .when()
                        .get("https://parabank.parasoft.com/parabank/register.htm").sessionId().toString();

        System.out.println("===========================================");
        System.out.println("Sessión creada para el registro "+sessionId);
        System.out.println("===========================================");

        // En este endpoint (POST) se manda la cookie encontrada y se envian los datos del formulario para registrar al usuario
        // Cambiar los datos del usuario a gusto de cada uno
        given()
            .cookie("JSESSIONID",sessionId)                     // Enviamos la session creada arriba con esta cookie
            .contentType("application/x-www-form-urlencoded")   // Muy importante poner que el formulario va codificado de esta forma
            .formParam("customer.firstName","Usuario")
            .formParam("customer.lastName","Prueba")
            .formParam("customer.address.street","Calle Falsa 1234")
            .formParam("customer.address.city","Ciudad Falsa 1234")
            .formParam("customer.address.state","Estado Falso 1234")
            .formParam("customer.address.zipCode","Zip")
            .formParam("customer.phoneNumber","Phone")
            .formParam("customer.ssn","SSN")
            .formParam("customer.username",username)
            .formParam("customer.password",password)
            .formParam("repeatedPassword",password)
        .when()
            .post("https://parabank.parasoft.com/parabank/register.htm")
        .then()
            .body("html.head.title", equalTo("ParaBank | Customer Created"));       // Verificamos que el title de página diga Customer Created
    }

    @Test
    @Order(2)
    @Tag("Regression")
    @DisplayName("Test del login")
    public void testLogin(){

        // En este endpoint (POST) nos logueamos enviando login/pass
        // si los datos son válidos esto nos genera una nueva sesion que debemos enviar como JSESSIONID en las cookie de cada uno de los próximos Test
        sessionId  = given()
                        .formParam("username",username)
                        .formParam("password",password)
                        .accept(ContentType.JSON)
                    .when()
                        .post("https://parabank.parasoft.com/parabank/login.htm")
                    .then()
                        .statusCode(302).log().all().extract().sessionId();  // Extraemos el sessionId - Nota: Por alguna razón responde con 302 (no 200)


        // Pero primero necesitamos obtener nuestro customerId, que el login anterior no lo devuelve
        // Así que invocamos este endpoint (GET) de otro login login para obtener nuestro customerId
        // (viene en el campo id) que necesitaremos enviar en algunos próximos test
        customerId = given()
                        .pathParam("username",username)
                        .pathParam("password",password)
                        .accept(ContentType.JSON)
                    .when()
                        .get("https://parabank.parasoft.com/parabank/services/bank/login/{username}/{password}")
                    .then()
                        .statusCode(200).log().all()
                        .extract().path("id").toString();

        System.out.println("===========================================");
        System.out.println("customerId: "+customerId);
        System.out.println("sessionId: "+sessionId);
        System.out.println("===========================================");
    }

    @Test
    @Tag("Regression")
    @DisplayName("Apertura de una nueva cuenta")
    @Order(3)
    public void testOpenAccount(){

        // De este endpoint (GET) podemos obtener el listado de cuentas que sería el equivalente de la página de overview.htm
        // De aquí podemos extraer el accountId de la primer cuenta,  le pasamos nuestro customerId encontrado arriba

        Response response1 = given()
            .cookie("JSESSIONID", sessionId)
            .pathParam("customerId", customerId)
            .accept(ContentType.JSON)
        .when()
            .get("https://parabank.parasoft.com/parabank/services_proxy/bank/customers/{customerId}/accounts")
        .then()
            .statusCode(200).log().all().extract().response();

        accountId1 = response1.jsonPath().getString("id[0]");       // Extrae el id de la primer cuenta del listado

        // En este endpoint (POST) creamos una nueva cuenta de tipo SAVINGS (1) transfiriendo un monto desde la cuenta que encontramos anteriormente
        // Esto nos genera un json con los datos de la nueva cuenta (ya tenemos mínimo 2 cuentas), del cual extraemos nuevamente el id
        Response response2 = given()
            .cookie("JSESSIONID", sessionId)
            .formParam("customerId",customerId)
            .formParam("newAccountType","1")   // SAVINGS
            .formParam("fromAccountId",accountId1)
            .accept(ContentType.JSON)
        .when()
            .post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount")
        .then()
            .statusCode(200).log().all().extract().response();

        accountId2 = response2.jsonPath().getString("id");

        System.out.println("===========================================");
        System.out.println("Account 1: "+accountId1);
        System.out.println("Account 2: "+accountId2);
        System.out.println("===========================================");
    }

    @Test
    @Order(3)
    @Tag("Regression")
    @DisplayName("Resumen de las cuentas")
    public void testAccountSummary() {

        given()
                .cookie("JSESSIONID", sessionId)
        .when()
                .get("https://parabank.parasoft.com/parabank/overview.htm")

                .then()
                .body("html.head.title", equalTo("ParaBank | Accounts Overview"));
    }

    @Test
    @Tag("Regression")
    @DisplayName("Transferencia de fondos")
    @Order(4)
    public void testTransfer(){

        // En este endpoint (POST) podemos hacer transferencia de dinero entre las dos cuentas obtenidas anteriormente
        given()
            .cookie("JSESSIONID", sessionId)
            .formParam("fromAccountId",accountId1)
            .formParam("toAccountId",accountId2)
            .formParam("amount","100")          // Cantidad a transferir
        .when()
            .post("https://parabank.parasoft.com/parabank/services_proxy/bank/transfer")
        .then()
            .statusCode(200).log().all();
    }


    @Test
    @Tag("Regression")
    @DisplayName("Actividad de la cuenta")
    @Order(5)
    public void testActivity(){

        given()
            .cookie("JSESSIONID", sessionId)
            .pathParam("account",accountId1)
            .accept(ContentType.JSON)
        .when()
            .get("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/{account}/transactions/month/All/type/All")
        .then()
            .statusCode(200).log().all();
    }

}