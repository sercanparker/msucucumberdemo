# cucumber demo for MSU
Execute tests

```
mnv clean test
```
Update environment url, merchant, merchant api user and password 
```
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
```