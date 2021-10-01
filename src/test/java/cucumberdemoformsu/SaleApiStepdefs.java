package cucumberdemoformsu;

import com.merchantsafeunipay.sdk.MsuApiClient;
import com.merchantsafeunipay.sdk.authentication.credentials.providers.StaticCredentialsProvider;
import com.merchantsafeunipay.sdk.authentication.types.UserCredentialsAuthentication;
import com.merchantsafeunipay.sdk.request.apiv2.financial.SaleRequest;
import com.merchantsafeunipay.sdk.request.enumerated.Currency;
import com.merchantsafeunipay.sdk.response.SaleResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by sercan.sensulun on 30/09/2021
 */
public class SaleApiStepdefs {

    private final StepData stepData;
    private final MsuApiClient msuApiClient;

    public SaleApiStepdefs(StepData stepData) {
        //Pico container dependency injection framework is used.
        this.stepData = stepData;
        this.msuApiClient = new MsuApiClient.MsuApiClientBuilder()
                .withUrl("")
                .withDefaultAuthentication(new UserCredentialsAuthentication(new StaticCredentialsProvider(
                        "",
                        "",
                        "")))
                .build();

    }

    @When("api user sends sale transaction")
    public void apiUserSendsSaleTransaction() {
        Random random = new Random();
        SaleRequest saleRequest = SaleRequest.builder()
                .withCurrency(Currency.TRY)
                .withAmount(new BigDecimal("100.0"))
                .withCardPan("4508034508034509")
                .withCardExpiry("12.2023")
                .withCardCvv("000")
                .withNameOnCard("Mr. Nobody")
                .withMerchantPaymentId("Payment_" + random.nextInt())
                .withCustomer("Customer_" + random.nextInt())
                .build();
        stepData.saleResponse = msuApiClient.doRequest(saleRequest);
    }

    @Then("sale transaction is completed successfully")
    public void saleTransactionIsCompletedSuccessfully() {
        SaleResponse saleResponse = stepData.saleResponse;
        Assert.assertTrue(saleResponse.getRawResponse(), saleResponse.isApproved());
    }

    @Given("there is merchant api user")
    public void thereIsMerchantApiUser() {
        //TODO: read from db or use api call to be ensure there
    }
}
